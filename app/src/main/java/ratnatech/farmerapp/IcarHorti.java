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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ratnatech.farmerapp.NavDrawer.NavdrawerRP;

public class IcarHorti extends AppCompatActivity {


    String strname,strmobile,stremail,strpass,strgender;
    String str_expertise_in_options="",str_icar_post="",str_icar_dept="",str_jurisddiction="",str_district_id="";
    int TIME_OUT=7;
    EditText horticulture_other_text;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String ext_tracker="";
    String user_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_icar_horti);
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
            str_icar_dept= bundle.getString("ICAR_DEPT");
            str_icar_post= bundle.getString("ICAR_POST");
            str_jurisddiction= bundle.getString("JURISDICTION");
            str_district_id= bundle.getString("DISTRICT");
        }

        horticulture_other_text=findViewById(R.id.horticulture_other_text);

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
                        // Convert the ArrayList into a String.

                        StringBuilder sb2 = new StringBuilder();
                        for (String s2 : values)
                        {
                            sb2.append(s2);
                            sb2.append(",");
                        }
                        str_expertise_in_options= sb2.toString();
                        System.out.println(sb2.toString());
                        Toast.makeText(IcarHorti.this, str_expertise_in_options, Toast.LENGTH_SHORT).show();
                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });

    }

    public void onClickSubmit(View view)
    {
        new AsyncSignUpExtICARHorti().execute();
    }
    private  class AsyncSignUpExtICARHorti extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(IcarHorti.this);

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

                cred.add(new BasicNameValuePair("expertise_in", "Horticulture"));
                cred.add(new BasicNameValuePair("expertise_in_options", str_expertise_in_options));


                cred.add(new BasicNameValuePair("jurisdiction", str_jurisddiction));


                cred.add(new BasicNameValuePair("district", str_district_id));
                cred.add(new BasicNameValuePair("block", null));
                cred.add(new BasicNameValuePair("gp", null));

                cred.add(new BasicNameValuePair("horticulture_other_text", horticulture_other_text.getText().toString().trim()));
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

                cred.add(new BasicNameValuePair("expertise_in", "Horticulture"));
                cred.add(new BasicNameValuePair("expertise_in_options", str_expertise_in_options));


                cred.add(new BasicNameValuePair("jurisdiction", str_jurisddiction));


                cred.add(new BasicNameValuePair("district", str_district_id));
                cred.add(new BasicNameValuePair("block", null));
                cred.add(new BasicNameValuePair("gp", null));

                cred.add(new BasicNameValuePair("horticulture_other_text", horticulture_other_text.getText().toString().trim()));
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
                            Intent i = new Intent(IcarHorti.this, Login.class);
                            startActivity(i);
                            finish();
                        }
                        else if(message.equals("Update Successfull"))
                        {
                            Intent i = new Intent(IcarHorti.this, NavdrawerRP.class);
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
