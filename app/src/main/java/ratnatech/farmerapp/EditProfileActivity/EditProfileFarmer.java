package ratnatech.farmerapp.EditProfileActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import ratnatech.farmerapp.R;
import ratnatech.farmerapp.SignUpFarmer;
import ratnatech.farmerapp.SignUpFarmer_2;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditProfileFarmer extends AppCompatActivity {

    AppCompatEditText fname,fathername,input_mob_no,input_mail,input_pass;
    String strname="",strfathersname="",strmobile="",stremail="",strpass="";
    RadioGroup radioGender,radioCaste;
    String str_gender="",str_caste="";


    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";
    private static final String URL_DATA3 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/gps?block_id=";
    private static final int TIME_OUT = 3000;
    LinearLayout layoutimagepreview;

    //getextra
    String strfathername="",strgender="",strcaste="",strvillage="",
            strdistrict="Select Your District",strblock="Select Your Block",strgp="Select Your GP";

    Spinner district,block,gp;//



    int j=0,k=0,l=0;

    String str_district_id="",str_block_id="",str_gp_id="";

    ArrayList<String> arraylist_districtid=new ArrayList<String>();
    ArrayList<String> arraylist_blockid;
    ArrayList<String> arraylist_gp_id;

    String gpNotFound="";

    AppCompatEditText village;
    EditText iluc,niluc; String str_iluc="",str_niluc="",str_irrisource="";
    RadioGroup radioIrrigationSource;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String user_id;

    String email,salt,first_name,phone,fathers_name,govt_department,irrigated_land_under_cultivation,irrigation_source,villagename,
            districtid,blockid,gpid,post,institution,jurisdiction,gender,non_irrigated_land_under_cultivation,
            any_special_crop_grown,caste;
    int position,position2,position3,count=0;
    Spinner spinner_in_acres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_edit_profile_farmer);

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

        layoutimagepreview=findViewById(R.id.layoutimagepreview);


        district=(Spinner) findViewById(R.id.district);
        block=findViewById(R.id.block);
        gp=findViewById(R.id.gp);
        village=findViewById(R.id.village);
        iluc=findViewById(R.id.iluc);// irrigated land under cultivation
        niluc=findViewById(R.id.niluc);// non irrigated land under cultivation
        radioIrrigationSource=findViewById(R.id.radioIrrigationSource);

        fname=findViewById(R.id.fname);
        fathername=findViewById(R.id.fathername);
        input_mob_no=findViewById(R.id.input_mob_no);
        input_mail=findViewById(R.id.input_mail);
        input_pass=findViewById(R.id.input_pass);
        radioGender=findViewById(R.id.radioGender);
        radioCaste=findViewById(R.id.radioCaste);



        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        user_id=sharedpreferences.getString("FLAG", null);
        Log.d("user_id:",user_id);
        editor.commit(); // commit changes

        //Function to get data
        getData();

        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_gender=rb.getText().toString();
                  //  Toast.makeText(EditProfileFarmer.this, str_gender, Toast.LENGTH_SHORT).show();


                }

            }
        });
        radioCaste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_caste=rb.getText().toString();
                  //  Toast.makeText(EditProfileFarmer.this, str_caste, Toast.LENGTH_SHORT).show();


                }

            }
        });


        gpNotFound="No";
        load_district_in_spinner();

        // SPINNER SELECT

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict= district.getSelectedItem().toString();
                str_district_id= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                if(count<1)
                {
                    load_block_from_a_district(districtid); //str_district_id
                    count=count+1;
                }

                else
                    load_block_from_a_district(str_district_id); //

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
                load_gps_from_blockid(str_block_id);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp= gp.getSelectedItem().toString();

                // TASK IS TO GET THE GP_ID FROM BLOCK_ID ..?
                str_gp_id=arraylist_gp_id.get(position);

                //str_gp_id= String.valueOf(position+1);

                // Toast.makeText(SignUpFarmer_2.this, str_gp_id, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioIrrigationSource.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_irrisource=rb.getText().toString();
                 //   Toast.makeText(EditProfileFarmer.this, str_irrisource, Toast.LENGTH_SHORT).show();


                }

            }
        });


    }
    public void getData()
    {
        String url ="http://demo.ratnatechnology.co.in/farmerapp/api/user/getFarmer?user_id="+user_id;
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
                                email = o.getString("email");
                                salt = o.getString("salt");
                                first_name = o.getString("first_name");

                                phone = o.getString("phone");
                                gender = o.getString("gender");
                                fathers_name = o.getString("fathers_name");

                                districtid = o.getString("district");
                                blockid = o.getString("block");
                                gpid = o.getString("gp");
                                irrigated_land_under_cultivation=o.getString("irrigated_land_under_cultivation");
                                irrigation_source=o.getString("irrigation_source");
                                non_irrigated_land_under_cultivation=o.getString("non_irrigated_land_under_cultivation");
                                villagename=o.getString("village");
                                any_special_crop_grown=o.getString("any_special_crop_grown");
                                caste=o.getString("caste");

                                fname.setText(first_name);
                                fname.setEnabled(false);
                                fathername.setText(fathers_name);
                                input_mob_no.setText(phone);
                                input_mob_no.setEnabled(false);
                                input_pass.setText(salt);
                                input_mail.setText(email);

                                if(gender.equals("male"))
                                    radioGender.check(R.id.radioButtonMale);
                                else if(gender.equals("female"))
                                     radioGender.check(R.id.radioButtonFemale);

                                if (caste.equals("SC"))
                                    radioCaste.check(R.id.radioButtonSC);
                                else if(caste.equals("ST"))
                                    radioCaste.check(R.id.radioButtonST);
                                else if(caste.equals("GENERAL"))
                                    radioCaste.check(R.id.radioButtonGeneral);

                                if(irrigation_source.equals("Dug Well"))
                                    radioIrrigationSource.check(R.id.radioButtonDugWell);
                                else if(irrigation_source.equals("Bore Well"))
                                    radioIrrigationSource.check(R.id.radioButtonBoreWell);
                                else if(irrigation_source.equals("L1 Point"))
                                    radioIrrigationSource.check(R.id.radioButtonL1Point);
                                else if(irrigation_source.equals("River Lift"))
                                    radioIrrigationSource.check(R.id.radioButtonRiverLift);
                                else if(irrigation_source.equals("Others"))
                                    radioIrrigationSource.check(R.id.radioButtonOtherSource);

                                niluc.setText(non_irrigated_land_under_cultivation);

                                village.setText(villagename);
                                iluc.setText(irrigated_land_under_cultivation);


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
                        Toast.makeText(EditProfileFarmer.this,"Error while loading", Toast.LENGTH_SHORT).show();

                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

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
                                    EditProfileFarmer.this,R.layout.spinner_item,districtList);

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
                                    EditProfileFarmer.this,R.layout.spinner_item_2,blockList);

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

    private void load_gps_from_blockid(String str_block_id)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                      /*  progressDialog.dismiss();*/
                        try {
                            //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                            Log.d("onResponse:",s);
                            JSONObject jsonObject=null;
                            JSONArray array=null;

                            //Initialize arraylist
                            ArrayList<String> gpl=new ArrayList<String>();
                            arraylist_gp_id =new ArrayList<String>();

                            jsonObject=new JSONObject(s);
                            array=jsonObject.getJSONArray("gps");

                            // Toast.makeText(getApplicationContext(),String.valueOf(array.length()),Toast.LENGTH_LONG).show();



                            for(int k=0;k<array.length();k++)
                            {
                                String gid="",name="",b_id="";

                                JSONObject o=array.getJSONObject(k);
                                gid=o.getString("id");
                                name =o.getString("name");
                                b_id =o.getString("block_id");


                                gpl.add(name);
                                arraylist_gp_id.add(gid);
                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    EditProfileFarmer.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp.setAdapter(spinnerArrayAdapter3);
                            gp.setVisibility(View.VISIBLE);
                            gpNotFound="No";

                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                // Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "No GP Found", Snackbar.LENGTH_LONG);

                snackbar.show();

                str_gp_id="0"; // for passing in str_gp_id as zero
                gpNotFound="Yes";

                gp.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public  void onClickNext(View view)
    {//validation
        strname=fname.getText().toString().trim();
        strfathersname=fathername.getText().toString().trim();
        strmobile=input_mob_no.getText().toString().trim();
        stremail=input_mail.getText().toString().trim();
        strpass=input_pass.getText().toString().trim();

        if(strname.equals("")||strmobile.equals("")||strpass.equals(""))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "One or More Field Blank", Snackbar.LENGTH_LONG);

            snackbar.show();
        }

        else if(!(isValidPhone(strmobile)))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Phone number is Invalid", Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else if(strpass.length()<6&&strpass.length()>8)
        {
            Toast.makeText(this, "Length error(min 6 and max 8 characters)", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, EditProfileFarmer2.class);
            intent.putExtra("NAME", strname);
            intent.putExtra("FATHERNAME", strfathersname);
            intent.putExtra("MOBILE", strmobile);
            intent.putExtra("EMAIL", stremail);
            intent.putExtra("PASSWORD", strpass);
            intent.putExtra("GENDER",str_gender);
            intent.putExtra("CASTE",str_caste);
            intent.putExtra("DISTRICTID",districtid);
            intent.putExtra("BLOCKID",blockid);
            intent.putExtra("GPID",gpid);
            intent.putExtra("VILLAGE",villagename);
            intent.putExtra("ILUC",irrigated_land_under_cultivation);
            intent.putExtra("SOURCE_OF_IRRIGATION",irrigation_source);
            intent.putExtra("NILUC",non_irrigated_land_under_cultivation);
            startActivity(intent);

        }
    }
    public static boolean isValidPhone(String phone)
    {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}
