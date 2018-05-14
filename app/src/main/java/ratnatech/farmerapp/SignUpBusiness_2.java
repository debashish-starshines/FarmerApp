package ratnatech.farmerapp;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class SignUpBusiness_2 extends AppCompatActivity {

    private static final int TIME_OUT = 3000;
    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";

    ArrayList<String> arraylist_districtid = new ArrayList<String>();
    ArrayList<String> arraylist_blockid;


    String str_deals_in = "", strdistrict = "", str_district_id = "", strblock = "", str_block_id = "";
    LinearLayout layout_agril_machinery, layout_seed, layout_medicine_feed;

    String strwebsite, strmobile, stremail, strpass;
    AppCompatEditText farm_name, properiter_name, farm_location;
    Spinner spinner_business_type, spinner_deals_in, district, block;
    String agril_type = "";//plowing,levelling,etc
    EditText editPreferedBusinessArea;
    String strarea_of_business = "";
    RadioGroup rgbusinesstype;
    String str_businesstype = "";//Dealer Manufactuer,Franchise,Service

    String strfarmname = "", strproperiter = "", strfarmlocation = "",  str_agril_type = "", str_seed_type = "", str_medicinefeed_type = "", str_sparepartavailability = "";

    String str_area_of_business="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_sign_up_business_2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        farm_name = findViewById(R.id.id_farm_name);
        properiter_name = findViewById(R.id.id_properiter_name);
        rgbusinesstype = findViewById(R.id.rgbusinesstype);
        // farm_location=findViewById(R.id.id_farm_location);

        //  editPreferedBusinessArea=findViewById(R.id.editPreferedBusinessArea);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            strwebsite = bundle.getString("WEBSITE");
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
        }

        Toast.makeText(this, "website:"+strwebsite+" mobile:"+strmobile+" email:"+stremail
                +" pwd:"+strpass, Toast.LENGTH_LONG).show();

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
                      //  Toast.makeText(SignUpBusiness_2.this,str_area_of_business,Toast.LENGTH_LONG).show();
                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });



        rgbusinesstype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_businesstype=rb.getText().toString();
                    Toast.makeText(SignUpBusiness_2.this, str_businesstype, Toast.LENGTH_SHORT).show();

                    if(str_businesstype.equals("Manufacturer")||str_businesstype.equals("Dealer")||str_businesstype.equals("Franchise"))
                    {
                        Intent intent=new Intent(SignUpBusiness_2.this,BtypeDealsInMDF.class);
                        intent.putExtra("FARM_NAME",farm_name.getText().toString());
                        intent.putExtra("PROPERTIER_NAME",properiter_name.getText().toString());
                        intent.putExtra("DISTRICT",str_district_id);
                        intent.putExtra("BLOCK",str_block_id);
                        intent.putExtra("AREA_OF_BUSINESS",str_area_of_business);
                        intent.putExtra("WEBSITE",strwebsite);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("BUSINESS_TYPE",str_businesstype);
                        startActivity(intent);
                    }
                    else if(str_businesstype.equals("Service"))
                    {
                        Intent intent=new Intent(SignUpBusiness_2.this,BtypeDealsInS.class);
                        intent.putExtra("FARM_NAME",farm_name.getText().toString());
                        intent.putExtra("PROPERTIER_NAME",properiter_name.getText().toString());
                        intent.putExtra("DISTRICT",str_district_id);
                        intent.putExtra("BLOCK",str_block_id);
                        intent.putExtra("AREA_OF_BUSINESS",str_area_of_business);
                        intent.putExtra("WEBSITE",strwebsite);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("BUSINESS_TYPE",str_businesstype);
                        startActivity(intent);
                    }

                }

            }
        });







        district=findViewById(R.id.district);
        block=findViewById(R.id.block);

        load_district_in_spinner();

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict= district.getSelectedItem().toString();
                str_district_id= String.valueOf(position+1);


              //  Toast.makeText(SignUpBusiness_2.this,"districtid " +str_district_id , Toast.LENGTH_SHORT).show();
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
              // load_gps_from_blockid(str_block_id);//str_block_id
                //Toast.makeText(SignUpBusiness_2.this,"blockid " +str_block_id , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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
                                    SignUpBusiness_2.this,R.layout.spinner_item,districtList);

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
                Toast.makeText(getApplicationContext(),"Error while loading District",Toast.LENGTH_LONG).show();
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
                                    SignUpBusiness_2.this,R.layout.spinner_item_2,blockList);

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
                Toast.makeText(getApplicationContext(),"Error while loading Block",Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

   /* public void onClickSubmit(View view)
    {
        //validation
        strfarmname=farm_name.getText().toString().trim();
        strproperiter=properiter_name.getText().toString().trim();
       // strfarmlocation=farm_location.getText().toString().trim();

        if(strfarmname.equals("")||strproperiter.equals("")||strfarmlocation.equals(""))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "One or More Field Blank", Snackbar.LENGTH_LONG);

            snackbar.show();
        }

       *//* else if(strpass.length()<6&&strpass.length()>8)
        {
            Toast.makeText(this, "Length error(min 6 and max 8 characters)", Toast.LENGTH_SHORT).show();
        }*//*
        else
        {
           new AsyncSignUpBusiness().execute();

        }
    }

    private  class AsyncSignUpBusiness extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {



            pDialog = new ProgressDialog(SignUpBusiness_2.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("website",strwebsite));
            cred.add(new BasicNameValuePair("email",stremail));
            cred.add(new BasicNameValuePair("password",strpass));
            cred.add(new BasicNameValuePair("phone",strmobile));
            if(!(str_deals_in.equals("Agril Machinery/tools/implements")))
            {
                cred.add(new BasicNameValuePair("district","0"));//strdistrict
                cred.add(new BasicNameValuePair("block","0"));
            }
            else
                {
                    cred.add(new BasicNameValuePair("district",str_district_id));//strdistrict
                    cred.add(new BasicNameValuePair("block",str_block_id));
            }
            //strblock
            cred.add(new BasicNameValuePair("name_of_farm",strfarmname));
            cred.add(new BasicNameValuePair("properiter_name",strproperiter));
          //  cred.add(new BasicNameValuePair("farm_location",strfarmlocation));
            cred.add(new BasicNameValuePair("business_type",str_businesstype));
            cred.add(new BasicNameValuePair("deals_in",str_deals_in));
            cred.add(new BasicNameValuePair("agril_type",str_agril_type));
            cred.add(new BasicNameValuePair("seed_type",str_seed_type));
            cred.add(new BasicNameValuePair("medicinefeed_type",str_medicinefeed_type));
            cred.add(new BasicNameValuePair("business_area",strarea_of_business));
            cred.add(new BasicNameValuePair("spare_part_availability",str_sparepartavailability));


            String urlRouteList=Config.business_signup;
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

            }return null;
        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();
            if(message.equals("Registration Successfull"))
            {
                //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Intent i = new Intent(SignUpBusiness_2.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                }, TIME_OUT);

       *//* startActivity(new Intent(SignUpFarmer_2.this,Login.class));
                finish();*//*
            }
            else {
                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();
            }

        }



    }*/
}

