package ratnatech.farmerapp;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ratnatech.farmerapp.NavDrawer.NavdrawerRP;

public class NGOExtenOff extends AppCompatActivity {

    Spinner district;
    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";

    ArrayList<String> arraylist_districtid = new ArrayList<String>();
    ArrayList<String> arraylist_blockid;
    String strdistrict,str_district_id = "";

    CheckBox checkBox;
    LinearLayout layoutBlock;

    String strname,strmobile,stremail,strpass,strgender;
    String strdept,strpost,strjurisdiction;
    EditText ngoNameOrDept,ngoPost,ngoJurisdiction;

    String blockmultiselect="";

    int TIME_OUT=3;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String ext_tracker="";
    String user_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_ngoexten_off);

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


        layoutBlock=findViewById(R.id.layoutBlock);
        district=findViewById(R.id.district);
        ngoNameOrDept=findViewById(R.id.ngoNameOrDept);
        ngoPost=findViewById(R.id.ngoPost);
        ngoJurisdiction=findViewById(R.id.ngoJurisdiction);

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

        load_district_in_spinner();

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
                                    NGOExtenOff.this,R.layout.spinner_item,districtList);

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

                            // Toast.makeText(SignUpFarmer_2.this, "arraylist_blockid", Toast.LENGTH_SHORT).show();

                          /*  for (int x=0;x<array.length();x++)
                            {
                                Toast.makeText(CentralGovtExtenOff.this, al.get(x), Toast.LENGTH_SHORT).show();
                            }
*/
                            for(int i=0;i<al.size();i++)
                            {
                                checkBox=new CheckBox(NGOExtenOff.this);
                                checkBox.setId(i);
                                checkBox.setText(al.get(i));
                                checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
                                layoutBlock.addView(checkBox);
                            }



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
    View.OnClickListener getOnClickDoSomething(final Button button)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NGOExtenOff.this, button.getText().toString(), Toast.LENGTH_SHORT).show();
                blockmultiselect=blockmultiselect+","+button.getText().toString();
                Toast.makeText(NGOExtenOff.this, blockmultiselect, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void onClickSubmit(View view)
    {
        strdept=ngoNameOrDept.getText().toString().trim();
        strpost=ngoPost.getText().toString().trim();
        strjurisdiction=ngoJurisdiction.getText().toString().trim();

        new AsyncSignUpExtOffNGO().execute();

    }

    private  class AsyncSignUpExtOffNGO extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(NGOExtenOff.this);

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
                cred.add(new BasicNameValuePair("inservice_type","NGO"));

                cred.add(new BasicNameValuePair("govt_type",null));
                cred.add(new BasicNameValuePair("depart_type",strdept ));
                cred.add(new BasicNameValuePair("depart_post",strpost));

                cred.add(new BasicNameValuePair("expertise_in",null));
                cred.add(new BasicNameValuePair("expertise_in_options",null ));
                cred.add(new BasicNameValuePair("jurisdiction",strjurisdiction));


                cred.add(new BasicNameValuePair("district",str_district_id));
                cred.add(new BasicNameValuePair("block",blockmultiselect ));
                cred.add(new BasicNameValuePair("gp",null));

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
                cred.add(new BasicNameValuePair("inservice_type","NGO"));

                cred.add(new BasicNameValuePair("govt_type",null));
                cred.add(new BasicNameValuePair("depart_type",strdept ));
                cred.add(new BasicNameValuePair("depart_post",strpost));

                cred.add(new BasicNameValuePair("expertise_in",null));
                cred.add(new BasicNameValuePair("expertise_in_options",null ));
                cred.add(new BasicNameValuePair("jurisdiction",strjurisdiction));


                cred.add(new BasicNameValuePair("district",str_district_id));
                cred.add(new BasicNameValuePair("block",blockmultiselect ));
                cred.add(new BasicNameValuePair("gp",null));

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
                            Intent i = new Intent(NGOExtenOff.this, Login.class);
                            startActivity(i);
                            finish();
                        }
                        else if(message.equals("Update Successfull"))
                        {
                            Intent i = new Intent(NGOExtenOff.this, NavdrawerRP.class);
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
