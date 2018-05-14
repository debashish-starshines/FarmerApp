package ratnatech.farmerapp.EditProfileActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ratnatech.farmerapp.CustomHttpClient;
import ratnatech.farmerapp.Login;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.SignUpBusiness_2;

public class EditProfileBusiness extends AppCompatActivity {

    AppCompatEditText idwebsite,id_properiter_name,input_mob_no,input_pass,input_mail,id_farm_name;
    String strwebsite,strmobile,stremail,strpass;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String user_id;

    int TIME_OUT=6;
    String website, email,salt,name_of_farm,properiter_name,area_of_business,business_type,str_area_of_business,
    deals_in_product,phone,deals_in_product_other,fish_medicine,fish_net,fish_feed,fish_others,
            districtid,blockid,category_type,quantity,breed,feed,medicine,others,agril_product_category,
            seed,pesticides,fertiliser,catg_other,agril_machinery,sale_service_spare_parts_available;

    int position3=0;

    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";
    private static final String URL_DATA3 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/gps?block_id=";

    Spinner district,block,gp;
    String str_district_id="",str_block_id="",str_gp_id="";
    String strfathername="",strgender="",strcaste="",strvillage="",
            strdistrict="Select Your District",strblock="Select Your Block",strgp="Select Your GP";
    ArrayList<String> arraylist_districtid=new ArrayList<String>();
    ArrayList<String> arraylist_blockid;
    ArrayList<String> arraylist_gp_id;

    String gpNotFound="";
    RadioGroup rgbusinesstype;
    TextView load_area_of_business,txtbusiness_type,txt_deals_in_product,txt_deals_in_product_other,
            txtcategory_type,txtagril_product_category,txtagril_machinery,txtsparepartavailability;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_edit_profile_business);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idwebsite=findViewById(R.id.idwebsite);
        input_mob_no=findViewById(R.id.input_mob_no);
        input_pass=findViewById(R.id.input_pass);
        input_mail=findViewById(R.id.input_mail);
        id_farm_name=findViewById(R.id.id_farm_name);
        district=(Spinner) findViewById(R.id.district);
        block=findViewById(R.id.block);
        rgbusinesstype=findViewById(R.id.rgbusinesstype);
        id_properiter_name=findViewById(R.id.id_properiter_name);
        load_area_of_business=findViewById(R.id.load_area_of_business);
        txtbusiness_type=findViewById(R.id.business_type);
        txt_deals_in_product=findViewById(R.id.txt_deals_in_product);
        txt_deals_in_product_other=findViewById(R.id.txt_deals_in_product_other);
        txtcategory_type=findViewById(R.id.category_type);
        txtagril_product_category=findViewById(R.id.agril_product_category);
        txtagril_machinery=findViewById(R.id.agril_machinery);

        district.setEnabled(false);
        block.setEnabled(false);
        txtsparepartavailability=findViewById(R.id.txtsparepartavailability);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        user_id=sharedpreferences.getString("FLAG", null);
        Log.d("user_id:",user_id);
        editor.commit(); // commit changes

        gpNotFound="No";
        load_district_in_spinner();
        // SPINNER SELECT

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict= district.getSelectedItem().toString();
                str_district_id= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block_from_a_district(str_district_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock= block.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id=arraylist_blockid.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //load data
        getData();


        HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox1), "Angul/Anugul");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox2), "Balangir");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox3), "Balasore-Baleshwar");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox4), "Bargarh");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox5), "Baudh");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox6), "Bhadrak");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox7), "Cuttack");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox8), "Deogarh-Debagarh");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox9), "Dhenkanal");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox10), "Gajapati");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox11), "Ganjam");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox12), "Jagatsinghapur");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox13), "Jajpur");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox14), "Jharsuguda");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox15), "Kalahandi");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox16), "Kandhamal");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox17), "Kendrapara");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox18), "Kendujhar");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox19), "Khordha");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox20), "Koraput");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox21), "Malkangiri");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox22), "Mayurbhanj");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox23), "Nabarangpur");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox24), "Nayagarh");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox25), "Nuapada");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox26), "Puri");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox27), "Rayagada");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox28), "Sambalpur");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox29), "Subarnapur");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox30), "Sundargarh");

        CheckBoxGroup<String> checkBoxGroup = new CheckBoxGroup<>(checkBoxMap,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values) {


                        // Convert the ArrayList into a String.

                        StringBuilder sb = new StringBuilder();
                        for (String s : values)
                        {
                            sb.append(s);
                            sb.append(",");
                        }
                        str_area_of_business= sb.toString();
                        System.out.println(sb.toString());
                          Toast.makeText(EditProfileBusiness.this,str_area_of_business,Toast.LENGTH_LONG).show();
                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });

    }
    private void load_district_in_spinner() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                      /*  progressDialog.dismiss();*/
                        try {
                            // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                            Log.d("onResponse:",s);

                            //Initialize arraylist
                            ArrayList<String> dl=new ArrayList<String>();


                            JSONObject jsonObject=new JSONObject(s);
                            JSONArray array=jsonObject.getJSONArray("districts");

                            for(int i=0;i<array.length();i++)
                            {
                                String did="",dname="",sid="";

                                JSONObject o=array.getJSONObject(i);
                                did=o.getString("id");
                                dname =o.getString("name");
                                sid =o.getString("state_id");

                                dl.add(dname);
                                arraylist_districtid.add(did);

                            }

                            final List<String> districtList = new ArrayList<>(dl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                                    EditProfileBusiness.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district.setAdapter(spinnerArrayAdapter);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                // Toast.makeText(getApplicationContext(),"Error while loading",Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "Error while loading", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void load_block_from_a_district(String districtid)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                      /*  progressDialog.dismiss();*/
                        try {
                            // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                            Log.d("onResponse:",s);
                            JSONObject jsonObject=null;
                            JSONArray array=null;

                            //Initialize arraylist
                            ArrayList<String> al=new ArrayList<String>();
                            arraylist_blockid =new ArrayList<String>();
                            jsonObject=new JSONObject(s);
                            array=jsonObject.getJSONArray("blocks");

                            // Toast.makeText(getApplicationContext(),String.valueOf(array.length()),Toast.LENGTH_LONG).show();



                            for(int k=0;k<array.length();k++)
                            {
                                String bid="",name="",district_id="";

                                JSONObject o=array.getJSONObject(k);
                                bid=o.getString("id");
                                name =o.getString("name");
                                district_id =o.getString("district_id");


                                al.add(name);
                                arraylist_blockid.add(bid);

                            }
                         /*   Toast.makeText(SignUpFarmer_2.this, "arraylist_blockid", Toast.LENGTH_SHORT).show();

                            for (int x=0;x<array.length();x++)
                            {
                                Toast.makeText(SignUpFarmer_2.this, arraylist_blockid.get(x), Toast.LENGTH_SHORT).show();
                            }*/

                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> blockList = new ArrayList<>(al);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                                    EditProfileBusiness.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block.setAdapter(spinnerArrayAdapter2);



                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                //  Toast.makeText(getApplicationContext(),"Error while loading",Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "Error while loading", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void getData()
    {
        String url ="http://demo.ratnatechnology.co.in/farmerapp/api/user/getBusinessMan?user_id="+user_id;
        //String url="https://demo.api-platform.com/books";
        //creating a string request to send request to the url


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String getresponse) {


                        try {

                            Log.d( "onResponse: ",getresponse);
                            JSONObject jsonObject=new JSONObject(getresponse);

                            JSONArray array=jsonObject.getJSONArray("data");

                            for(int i=0;i<array.length();i++) {


                                JSONObject o = array.getJSONObject(i);
                                website=o.getString("website");
                                phone = o.getString("phone");
                                salt = o.getString("salt");
                                email = o.getString("email");
                                name_of_farm=o.getString("name_of_farm");
                                properiter_name=o.getString("properiter_name");
                                districtid = o.getString("district");
                                blockid = o.getString("block");
                                area_of_business= o.getString("area_of_business");
                                business_type=o.getString("business_type");

                                deals_in_product=o.getString("deals_in_product");
                                deals_in_product_other=o.getString("deals_in_product_other");


                                fish_medicine=o.getString("fish_medicine");
                                fish_net=o.getString("fish_net");
                                fish_feed=o.getString("fish_feed");
                                fish_others=o.getString("fish_others");



                                category_type=o.getString("category_type");
                                quantity=o.getString("quantity");
                                breed=o.getString("breed");
                                feed=o.getString("feed");
                                medicine=o.getString("medicine");
                                others=o.getString("others");
                                agril_product_category=o.getString("agril_product_category");
                                seed=o.getString("seed");
                                pesticides=o.getString("pesticides");
                                fertiliser=o.getString("fertiliser");
                                catg_other=o.getString("catg_other");
                                agril_machinery=o.getString("agril_machinery");
                                sale_service_spare_parts_available=o.getString("sale_service_spare_parts_available");

                                idwebsite.setText(website);
                                input_mob_no.setText(phone);
                                input_pass.setText(salt);
                                if(email.equals(null))
                                input_mail.setText("- no email set -");
                                else
                                    input_mail.setText(email);
                                id_farm_name.setText(name_of_farm);
                                id_properiter_name.setText(properiter_name);

                                /*    if(business_type.equals("Manufacturer"))
                                    rgbusinesstype.check(R.id.radioButtonManufacturer);
                                else if(rgbusinesstype.equals("Dealer"))
                                    rgbusinesstype.check(R.id.radioButtonDealer);
                                else if(rgbusinesstype.equals("Franchise"))
                                    rgbusinesstype.check(R.id.radioButtonFranchise);
                                else if(rgbusinesstype.equals("Service"))
                                    rgbusinesstype.check(R.id.radioButtonService);*/

                                load_area_of_business.setText("- "+area_of_business+"(your current area of business)");
                                txtbusiness_type.setText(business_type);
                                txt_deals_in_product.setText(deals_in_product);
                                if(deals_in_product_other.equals(""))
                                txt_deals_in_product_other.setText("---");
                                else
                                txt_deals_in_product_other.setText(deals_in_product_other);

                                if(category_type.equals(""))
                                    txtcategory_type.setText("---");
                                else
                                    txtcategory_type.setText(category_type);


                                if (agril_product_category.equals(""))
                                    txtagril_product_category.setText("---");
                                else
                                    txtagril_product_category.setText(agril_product_category);

                                if(agril_machinery.equals(""))
                                    txtagril_machinery.setText("---");
                                else txtagril_machinery.setText(agril_machinery);

                                if (sale_service_spare_parts_available.equals(""))
                                txtsparepartavailability.setText("---");
                                else
                                    txtsparepartavailability.setText(sale_service_spare_parts_available);

                                //spinner get
                                if(districtid.equals("1"))
                                    position3=0;
                                else if(districtid.equals("2"))
                                    position3=1;
                                else if(districtid.equals("3"))
                                    position3=2;
                                else if(districtid.equals("4"))
                                    position3=3;
                                else if(districtid.equals("5"))
                                    position3=4;
                                else if(districtid.equals("6"))//
                                    position3=5;
                                else if(districtid.equals("7"))
                                    position3=6;
                                else if(districtid.equals("8"))
                                    position3=7;
                                else if(districtid.equals("9"))
                                    position3=8;
                                else if(districtid.equals("10"))
                                    position3=9;
                                else if(districtid.equals("11"))
                                    position3=10;
                                else if(districtid.equals("12"))
                                    position3=11;
                                else if(districtid.equals("13"))
                                    position3=12;
                                else if(districtid.equals("14"))
                                    position3=13;
                                else if(districtid.equals("15"))
                                    position3=14;
                                else if(districtid.equals("16"))
                                    position3=15;
                                else if(districtid.equals("17"))
                                    position3=16;
                                else if(districtid.equals("18"))
                                    position3=17;
                                else if(districtid.equals("19"))
                                    position3=18;
                                else if(districtid.equals("20"))
                                    position3=19;
                                else if(districtid.equals("21"))
                                    position3=20;
                                else if(districtid.equals("22"))
                                    position3=21;
                                else if(districtid.equals("23"))
                                    position3=22;
                                else if(districtid.equals("24"))
                                    position3=23;
                                else if(districtid.equals("25"))
                                    position3=24;
                                else if(districtid.equals("26"))
                                    position3=25;
                                else if(districtid.equals("27"))
                                    position3=26;
                                else if(districtid.equals("28"))
                                    position3=27;
                                else if(districtid.equals("29"))
                                    position3=28;
                                else if(districtid.equals("30"))
                                    position3=29;


                                district.setSelection(position3);



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(EditProfileBusiness.this,"Error while loading", Toast.LENGTH_SHORT).show();

                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    public void onClickUpdate(View view)
    {
        new AsyncUpdateBusiness().execute();
    }
    private  class AsyncUpdateBusiness extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String success = null, message = "";

        @Override
        protected void onPreExecute() {


            pDialog = new ProgressDialog(EditProfileBusiness.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("user_id", user_id));
            cred.add(new BasicNameValuePair("area_of_business", str_area_of_business));



            String urlRouteList ="http://demo.ratnatechnology.co.in/farmerapp/api/user/signup_businessMan";
            try {
                String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);
                Log.v(" ", "Response is " + route_response);

                success = route_response;
                JSONObject jsonObject = new JSONObject(success);
                // status =jsonObject.getString("status");
                message = jsonObject.getString("message");

            } catch (Exception e)

            {
                Log.v("ONMESSAGE", e.toString());

            }
            return null;
        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();
            if (message.equals("Update Successfull")) {
                //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(EditProfileBusiness.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                }, TIME_OUT);


            } else {
                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();
            }

        }



    }
}
