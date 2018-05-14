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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ratnatech.farmerapp.NavDrawer.NavdrawerRP;

public class IcarExtenOff extends AppCompatActivity {

    RadioGroup rgExpICAR;

    LinearLayout layoutSubmit;
    String strname,strmobile,stremail,strpass,strgender;
    RadioGroup rgIcarDept;
    String str_icar_dept="",str_icar_post="";
    EditText jurisdiction;
    Spinner district;
    String str_district_id="";
    EditText expertise_in_options;
    int TIME_OUT=7;
    String str_icar_expertisein="";

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String ext_tracker="";
    String user_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_icar_exten_off);

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
        layoutSubmit=findViewById(R.id.layoutSubmit);
        jurisdiction=findViewById(R.id.jurisdiction);
        rgExpICAR=findViewById(R.id.rgExpICAR);
        rgIcarDept=findViewById(R.id.rgIcarDept);
        expertise_in_options=findViewById(R.id.expertise_in_options);
        district=findViewById(R.id.district);
        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // strdistrict= district.getSelectedItem().toString();
                str_district_id= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        rgIcarDept.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb2 = (RadioButton) group.findViewById(checkedId);
                if (null != rb2 && checkedId > -1) {
                    str_icar_dept=  rb2.getText().toString();
                  Toast.makeText(IcarExtenOff.this, str_icar_dept, Toast.LENGTH_SHORT).show();

                }

            }
        });




        rgExpICAR.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb2 = (RadioButton) group.findViewById(checkedId);
                if (null != rb2 && checkedId > -1) {
                    str_icar_expertisein=  rb2.getText().toString();
                 //   Toast.makeText(IcarExtenOff.this, str_dept_icar, Toast.LENGTH_SHORT).show();
                    if(str_icar_expertisein.equals("Agriculture"))
                    {
                        layoutSubmit.setVisibility(View.GONE);
                        Intent intent=new Intent(IcarExtenOff.this,IcarAgri.class);
                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        intent.putExtra("ICAR_DEPT",str_icar_dept);
                        intent.putExtra("ICAR_POST",str_icar_post);
                        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
                        intent.putExtra("DISTRICT",str_district_id);
                        startActivity(intent);
                    }
                    else if(str_icar_expertisein.equals("Horticulture"))
                    {
                        layoutSubmit.setVisibility(View.GONE);
                        Intent intent=new Intent(IcarExtenOff.this,IcarHorti.class);
                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        intent.putExtra("ICAR_DEPT",str_icar_dept);
                        intent.putExtra("ICAR_POST",str_icar_post);
                        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
                        intent.putExtra("DISTRICT",str_district_id);
                        startActivity(intent);
                    }
                    else if(str_icar_expertisein.equals("Veterinary"))
                    {
                        layoutSubmit.setVisibility(View.GONE);
                        Intent intent=new Intent(IcarExtenOff.this,IcarVeterinary.class);
                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        intent.putExtra("ICAR_DEPT",str_icar_dept);
                        intent.putExtra("ICAR_POST",str_icar_post);
                        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
                        intent.putExtra("DISTRICT",str_district_id);
                        startActivity(intent);
                    }
                    else if(str_icar_expertisein.equals("Fishery"))
                    {

                        layoutSubmit.setVisibility(View.GONE);
                        Intent intent=new Intent(IcarExtenOff.this,IcarFishery.class);
                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        intent.putExtra("ICAR_DEPT",str_icar_dept);
                        intent.putExtra("ICAR_POST",str_icar_post);
                        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
                        intent.putExtra("DISTRICT",str_district_id);
                        startActivity(intent);
                    }
                    else if(str_icar_expertisein.equals("Soil conservation and watershed"))
                    {
                        layoutSubmit.setVisibility(View.VISIBLE);
                    }
                    else if(str_icar_expertisein.equals("Others"))
                    {
                        layoutSubmit.setVisibility(View.VISIBLE);
                    }

                }

            }
        });


        HashMap<CheckBox, String> checkBoxMap2 = new HashMap<>();
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox1), "Scientist");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox2), "Others");




        CheckBoxGroup<String> checkBoxGroup2 = new CheckBoxGroup<>(checkBoxMap2,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values2) {
                       /* Toast.makeText(StateGovtAniResourceExtOff.this,
                                values.toString(),
                                Toast.LENGTH_LONG).show();*/

                        // Convert the ArrayList into a String.

                        StringBuilder sb2 = new StringBuilder();
                        for (String s2 : values2)
                        {
                            sb2.append(s2);
                            sb2.append(",");
                        }
                        str_icar_post= sb2.toString();
                        System.out.println(sb2.toString());
                        Toast.makeText(IcarExtenOff.this, str_icar_post, Toast.LENGTH_SHORT).show();

                    }


                });


    }

    public void onClickSubmit(View view)
    {
        new AsyncSignUpExtICAR().execute();
    }

    private  class AsyncSignUpExtICAR extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(IcarExtenOff.this);

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
                cred.add(new BasicNameValuePair("profile_img", null));

                cred.add(new BasicNameValuePair("status", "In-Service"));
                cred.add(new BasicNameValuePair("status_other", null));
                cred.add(new BasicNameValuePair("inservice_type", "Government"));

                cred.add(new BasicNameValuePair("govt_type", "ICAR/OUAT"));
                cred.add(new BasicNameValuePair("depart_type", str_icar_dept));
                cred.add(new BasicNameValuePair("depart_post", str_icar_post));

                cred.add(new BasicNameValuePair("expertise_in", str_icar_expertisein));
                if (str_icar_expertisein.equals("Others")) {
                    cred.add(new BasicNameValuePair("expertise_in_options", expertise_in_options.getText().toString()));
                } else if (str_icar_expertisein.equals("Soil conservation and watershed")) {
                    cred.add(new BasicNameValuePair("expertise_in_options", null));
                }

                cred.add(new BasicNameValuePair("jurisdiction", jurisdiction.getText().toString().trim()));


                cred.add(new BasicNameValuePair("district", str_district_id));
                cred.add(new BasicNameValuePair("block", null));
                cred.add(new BasicNameValuePair("gp", null));

                cred.add(new BasicNameValuePair("horticulture_other_text", null));
                cred.add(new BasicNameValuePair("veterinary_options", null));
                cred.add(new BasicNameValuePair("veterinary_other_text", null));

                cred.add(new BasicNameValuePair("fishery_options", null));
                cred.add(new BasicNameValuePair("fishery_other_text", null));
            }
            else {
                cred.add(new BasicNameValuePair("name", strname));
                cred.add(new BasicNameValuePair("email", stremail));
                cred.add(new BasicNameValuePair("password", strpass));
                cred.add(new BasicNameValuePair("phone", strmobile));
                cred.add(new BasicNameValuePair("gender", strgender));
                cred.add(new BasicNameValuePair("profile_img", null));

                cred.add(new BasicNameValuePair("status", "In-Service"));
                cred.add(new BasicNameValuePair("status_other", null));
                cred.add(new BasicNameValuePair("inservice_type", "Government"));

                cred.add(new BasicNameValuePair("govt_type", "ICAR/OUAT"));
                cred.add(new BasicNameValuePair("depart_type", str_icar_dept));
                cred.add(new BasicNameValuePair("depart_post", str_icar_post));

                cred.add(new BasicNameValuePair("expertise_in", str_icar_expertisein));
                if (str_icar_expertisein.equals("Others")) {
                    cred.add(new BasicNameValuePair("expertise_in_options", expertise_in_options.getText().toString()));
                } else if (str_icar_expertisein.equals("Soil conservation and watershed")) {
                    cred.add(new BasicNameValuePair("expertise_in_options", null));
                }

                cred.add(new BasicNameValuePair("jurisdiction", jurisdiction.getText().toString().trim()));


                cred.add(new BasicNameValuePair("district", str_district_id));
                cred.add(new BasicNameValuePair("block", null));
                cred.add(new BasicNameValuePair("gp", null));

                cred.add(new BasicNameValuePair("horticulture_other_text", null));
                cred.add(new BasicNameValuePair("veterinary_options", null));
                cred.add(new BasicNameValuePair("veterinary_other_text", null));

                cred.add(new BasicNameValuePair("fishery_options", null));
                cred.add(new BasicNameValuePair("fishery_other_text", null));
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
                            Intent i = new Intent(IcarExtenOff.this, Login.class);
                            startActivity(i);
                            finish();
                        }
                        else if(message.equals("Update Successfull"))
                        {
                            Intent i = new Intent(IcarExtenOff.this, NavdrawerRP.class);
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
