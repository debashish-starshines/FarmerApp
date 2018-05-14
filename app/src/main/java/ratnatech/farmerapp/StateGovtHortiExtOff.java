package ratnatech.farmerapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import ratnatech.farmerapp.NavDrawer.NavdrawerRP;

public class StateGovtHortiExtOff extends AppCompatActivity {

    RadioGroup rgHortiPost;
    String str_horti_post="";
    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";
    private static final String URL_DATA3 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/gps?block_id=";

    Spinner district,block,gp;
    String str_district_id="",str_block_id="",str_gp_id="";

    ArrayList<String> arraylist_districtid=new ArrayList<String>();
    ArrayList<String> arraylist_blockid;
    ArrayList<String> arraylist_gp_id;

    String gpNotFound="" ,strdistrict="Select Your District",strblock="Select Your Block",strgp="Select Your GP";

    LinearLayout farmerregdlayout;
    String strname,strmobile,stremail,strpass,strgender;
    int TIME_OUT=6;
    String str_expertise_in="";
    EditText expertise_in_options,jurisdiction;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String ext_tracker="";
    String user_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_state_govt_horti_ext_off);
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

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        ext_tracker=sharedpreferences.getString("EXTOFF_TRACKER", "");
        user_id=sharedpreferences.getString("FLAG", "");
//        Log.d("ext_tracker:",ext_tracker);
        editor.commit(); // commit changes

        farmerregdlayout=findViewById(R.id.farmerregdlayout);
        rgHortiPost=findViewById(R.id.rgHortiPost);
        expertise_in_options=findViewById(R.id.expertise_in_options);
        jurisdiction=findViewById(R.id.jurisdiction);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            strname = bundle.getString("NAME");
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
            strgender = bundle.getString("GENDER");
        }

        rgHortiPost.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb2 = (RadioButton) group.findViewById(checkedId);
                if (null != rb2 && checkedId > -1) {
                    str_horti_post=  rb2.getText().toString();

                    Toast.makeText(StateGovtHortiExtOff.this, str_horti_post, Toast.LENGTH_SHORT).show();

                    // POP up alert
                    if (str_horti_post.equals("Udyan Sathi")||str_horti_post.equals("Gardener")||str_horti_post.equals("HEW"))
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(StateGovtHortiExtOff.this);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder.setMessage("Are you also a farmer ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        farmerregdlayout.setVisibility(View.VISIBLE);

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        farmerregdlayout.setVisibility(View.GONE);
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Please Let Us Know");
                        alert.show();
                    }
                    else
                    {
                        farmerregdlayout.setVisibility(View.GONE);
                    }

                }

            }
        });

        HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox1), "Mushroom");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox2), "Floriculture");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox3), "Vegetable");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox4), "Fruits");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox5), "Others");


        CheckBoxGroup<String> checkBoxGroup = new CheckBoxGroup<>(checkBoxMap,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values) {
                       /* Toast.makeText(StateGovtHortiExtOff.this,
                                values.toString(),
                                Toast.LENGTH_LONG).show();*/


                        // Convert the ArrayList into a String.

                        StringBuilder sb = new StringBuilder();
                        for (String s : values)
                        {
                            sb.append(s);
                            sb.append(",");
                        }
                        str_expertise_in= sb.toString();
                        System.out.println(sb.toString());
                    }

                 
                });

        district=(Spinner) findViewById(R.id.district);
        block=findViewById(R.id.block);
        gp=findViewById(R.id.gp);

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
                                    StateGovtHortiExtOff.this,R.layout.spinner_item,districtList);

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
                                    StateGovtHortiExtOff.this,R.layout.spinner_item_2,blockList);

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
                                    StateGovtHortiExtOff.this,R.layout.spinner_item_2,gpList);

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

    public void onClickSubmit(View view)
    {
        new AsyncSignUpExtStateHorti().execute();
    }

    private  class AsyncSignUpExtStateHorti extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(StateGovtHortiExtOff.this);

            if(ext_tracker.equals("extofftrackeron"))
                pDialog.setMessage("Updating...");
            else
                pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {



            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            if(ext_tracker.equals("extofftrackeron"))
            {
                cred.add(new BasicNameValuePair("user_id",user_id));
                cred.add(new BasicNameValuePair("profile_img",null));

                cred.add(new BasicNameValuePair("status","In-Service"));
                cred.add(new BasicNameValuePair("status_other",null));
                cred.add(new BasicNameValuePair("inservice_type","Government"));

                cred.add(new BasicNameValuePair("govt_type","State Government"));
                cred.add(new BasicNameValuePair("depart_type","Horticulture" ));
                cred.add(new BasicNameValuePair("depart_post",str_horti_post));

                cred.add(new BasicNameValuePair("expertise_in",str_expertise_in));
                cred.add(new BasicNameValuePair("expertise_in_options",expertise_in_options.getText().toString().trim() ));
                cred.add(new BasicNameValuePair("jurisdiction",jurisdiction.getText().toString().trim()));


                cred.add(new BasicNameValuePair("district",str_district_id));
                cred.add(new BasicNameValuePair("block",str_block_id ));
                cred.add(new BasicNameValuePair("gp",str_gp_id));

                cred.add(new BasicNameValuePair("horticulture_other_text",null));
                cred.add(new BasicNameValuePair("veterinary_options",null ));
                cred.add(new BasicNameValuePair("veterinary_other_text",null));

                cred.add(new BasicNameValuePair("fishery_options",null));
                cred.add(new BasicNameValuePair("fishery_other_text",null ));
            }
            else
            {
                cred.add(new BasicNameValuePair("name",strname));
                cred.add(new BasicNameValuePair("email",stremail));
                cred.add(new BasicNameValuePair("password",strpass));
                cred.add(new BasicNameValuePair("phone",strmobile));
                cred.add(new BasicNameValuePair("gender",strgender));
                cred.add(new BasicNameValuePair("profile_img",null));

                cred.add(new BasicNameValuePair("status","In-Service"));
                cred.add(new BasicNameValuePair("status_other",null));
                cred.add(new BasicNameValuePair("inservice_type","Government"));

                cred.add(new BasicNameValuePair("govt_type","State Government"));
                cred.add(new BasicNameValuePair("depart_type","Horticulture" ));
                cred.add(new BasicNameValuePair("depart_post",str_horti_post));

                cred.add(new BasicNameValuePair("expertise_in",str_expertise_in));
                cred.add(new BasicNameValuePair("expertise_in_options",expertise_in_options.getText().toString().trim() ));
                cred.add(new BasicNameValuePair("jurisdiction",jurisdiction.getText().toString().trim()));


                cred.add(new BasicNameValuePair("district",str_district_id));
                cred.add(new BasicNameValuePair("block",str_block_id ));
                cred.add(new BasicNameValuePair("gp",str_gp_id));

                cred.add(new BasicNameValuePair("horticulture_other_text",null));
                cred.add(new BasicNameValuePair("veterinary_options",null ));
                cred.add(new BasicNameValuePair("veterinary_other_text",null));

                cred.add(new BasicNameValuePair("fishery_options",null));
                cred.add(new BasicNameValuePair("fishery_other_text",null ));
            }



            String urlRouteList=Config.extoff_signup;
            try {

                String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);
                Log.v(" ", "Response is " + route_response);

                success = route_response;
                JSONObject jsonObject = new JSONObject(success);
                // status =jsonObject.getString("status");
                message = jsonObject.getString("message");

            }
            catch (Exception e)
            {
                Log.v("ONMESSAGE", e.toString());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();
            if(message.equals("Registration Successfull")||message.equals("Update Successfull"))
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
                        if(message.equals("Registration Successfull"))
                        {
                            Intent i = new Intent(StateGovtHortiExtOff.this, Login.class);
                            startActivity(i);
                            finish();
                        }
                        else if(message.equals("Update Successfull"))
                        {
                            Intent i = new Intent(StateGovtHortiExtOff.this, NavdrawerRP.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }, TIME_OUT);

       /* startActivity(new Intent(SignUpFarmer_2.this,Login.class));
                finish();*/
            }
            else {
                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();
            }

        }



    }

}
