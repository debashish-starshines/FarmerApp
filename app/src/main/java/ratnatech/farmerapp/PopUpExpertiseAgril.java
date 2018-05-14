package ratnatech.farmerapp;

import android.app.Activity;
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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ratnatech.farmerapp.NavDrawer.NavDrawer;
import ratnatech.farmerapp.NavDrawer.NavdrawerRP;

public class PopUpExpertiseAgril extends Activity {

    TextView title;
    String str_expertise_in="",str_agril_post="";
    LinearLayout layoutAgrilExpertiseCtP,layoutOtherExpertise,layoutMandTool;
    int width, height;
    int TIME_OUT=6;

    String strname,strmobile,stremail,strpass,strgender,strgovttype,strdepttype,strjurisdiction,strdistrict,strblock,strgp,str_expertise_in_options;
    EditText expertise_in_options;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String ext_tracker="";
    String user_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_pop_up_expertise_agril);

        title=findViewById(R.id.title);
        layoutAgrilExpertiseCtP=findViewById(R.id.layoutAgrilExpertiseCtP);
        layoutOtherExpertise=findViewById(R.id.layoutOtherExpertise);
        layoutMandTool=findViewById(R.id.layoutMandTool);
        expertise_in_options=findViewById(R.id.expertise_in_options);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        ext_tracker=sharedpreferences.getString("EXTOFF_TRACKER", "");
        user_id=sharedpreferences.getString("FLAG", "");
//        Log.d("ext_tracker:",ext_tracker);
        editor.commit(); // commit changes

        WindowManager.LayoutParams params = getWindow().getAttributes();
      /*  params.x = 0;
        params.height = 1000;//700
        params.width = 700;//400
        params.y = 0;
        this.getWindow().setAttributes(params);
        */
        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();

        if(bundle != null)
        {
            str_agril_post=bundle.getString("POST");
            str_expertise_in = bundle.getString("EXP_IN");
           title.setText(str_expertise_in);
            //intent.putExtra("EXP_IN", "Cultivation Practise");
            //intent.putExtra("POST", str_agril_post);
            strname=bundle.getString("NAME");
            strmobile=bundle.getString("MOBILE");
            stremail=bundle.getString("EMAIL");
            strpass=bundle.getString("PASSWORD");
            strgender=bundle.getString("GENDER");
            strgovttype=bundle.getString("GOVT_TYPE");
           strdepttype=bundle.getString("DEPT_TYPE");
            strjurisdiction=bundle.getString("JURISDICTION");
            strdistrict=bundle.getString("DISTRICT");
            strblock=bundle.getString("BLOCK");
            strgp=bundle.getString("GP");
        }
        if(str_expertise_in.equals("Soil")||str_expertise_in.equals("Fertiliser")||str_expertise_in.equals("Irrigation")||
                str_expertise_in.equals("Other")||str_expertise_in.equals("Insurance"))
        {
             width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);//0.90
             height = (int) (getResources().getDisplayMetrics().heightPixels * 0.30);//0.90
        }
        else
        {
             width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);//0.90
             height = (int) (getResources().getDisplayMetrics().heightPixels * 0.80);//0.90
        }

        getWindow().setLayout(width, height);


        setFinishOnTouchOutside(false);// to prevent activity from dismissing on touching outside



        if(str_expertise_in.equals("Cultivation Practise")||str_expertise_in.equals("Seed")||str_expertise_in.equals("Disease and Pest")
                ||str_expertise_in.equals("Training")||str_expertise_in.equals("Processing"))
        {
            layoutAgrilExpertiseCtP.setVisibility(View.VISIBLE);
            layoutOtherExpertise.setVisibility(View.GONE);
            layoutMandTool.setVisibility(View.GONE);
        }
        else if(str_expertise_in.equals("Other"))
        {
           layoutOtherExpertise.setVisibility(View.VISIBLE);
            layoutAgrilExpertiseCtP.setVisibility(View.GONE);
            layoutMandTool.setVisibility(View.GONE);
        }
        else if (str_expertise_in.equals("Machinery and Tools"))
        {
            layoutMandTool.setVisibility(View.VISIBLE);
            layoutOtherExpertise.setVisibility(View.GONE);
            layoutAgrilExpertiseCtP.setVisibility(View.GONE);
        }
        else {

            layoutMandTool.setVisibility(View.GONE);
            layoutOtherExpertise.setVisibility(View.GONE);
            layoutAgrilExpertiseCtP.setVisibility(View.GONE);
        }

        HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox1), "Vegetable");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox2), "Fruit");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox3), "Flower");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox4), "Mushroom");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox5), "Cereal");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox6), "Pulses");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox7), "Oilseed");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox8), "Spices");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox9), "Cotton Sugarcane");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox10), "Fodder");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox11), "Spices");

        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM1), "Plowing");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM2), "Levelling");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM3), "Ridging");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM4), "Irrigation");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM5), "Spraying");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM6), "Weeding");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM7), "Sowing");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM8), "Transplanting");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM9), "Harvesting");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM10), "Cleaning");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM11), "Grading");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM12), "Parboiling");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM13), "Decortication");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM14), "Flower and fruit related");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBoxM15), "others");

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
                        str_expertise_in_options= sb.toString();
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


    }
    public void onClickOk(View view)
    {
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        new AsyncSignUpExtStateAgrilDept().execute();

    }
    private  class AsyncSignUpExtStateAgrilDept extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(PopUpExpertiseAgril.this);

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

                cred.add(new BasicNameValuePair("govt_type", "State Government"));
                cred.add(new BasicNameValuePair("depart_type", strdepttype));
                cred.add(new BasicNameValuePair("depart_post", str_agril_post));

                cred.add(new BasicNameValuePair("expertise_in", str_expertise_in));
                cred.add(new BasicNameValuePair("expertise_in_options",str_expertise_in_options ));// expertise_in_options.getText().toString().trim()
                cred.add(new BasicNameValuePair("jurisdiction", strjurisdiction));


                cred.add(new BasicNameValuePair("district", strdistrict));
                cred.add(new BasicNameValuePair("block", strblock));
                cred.add(new BasicNameValuePair("gp", strgp));

                cred.add(new BasicNameValuePair("horticulture_other_text", null));
                cred.add(new BasicNameValuePair("veterinary_options", null));
                cred.add(new BasicNameValuePair("veterinary_other_text", null));

                cred.add(new BasicNameValuePair("fishery_options", null));
                cred.add(new BasicNameValuePair("fishery_other_text", null));

            }
            else
            {

                // this is signup part

                cred.add(new BasicNameValuePair("name", strname));
                cred.add(new BasicNameValuePair("email", stremail));
                cred.add(new BasicNameValuePair("password", strpass));
                cred.add(new BasicNameValuePair("phone", strmobile));
                cred.add(new BasicNameValuePair("gender", strgender));
                cred.add(new BasicNameValuePair("profile_img", null));

                cred.add(new BasicNameValuePair("status", "In-Service"));
                cred.add(new BasicNameValuePair("status_other", null));
                cred.add(new BasicNameValuePair("inservice_type", "Government"));

                cred.add(new BasicNameValuePair("govt_type", "State Government"));
                cred.add(new BasicNameValuePair("depart_type", strdepttype));
                cred.add(new BasicNameValuePair("depart_post", str_agril_post));

                cred.add(new BasicNameValuePair("expertise_in", str_expertise_in));
                cred.add(new BasicNameValuePair("expertise_in_options",str_expertise_in_options ));// expertise_in_options.getText().toString().trim()
                cred.add(new BasicNameValuePair("jurisdiction", strjurisdiction));


                cred.add(new BasicNameValuePair("district", strdistrict));
                cred.add(new BasicNameValuePair("block", strblock));
                cred.add(new BasicNameValuePair("gp", strgp));

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
                  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
              /*  Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();*/

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if(message.equals("Registration Successfull")) {
                            Intent i = new Intent(PopUpExpertiseAgril.this, Login.class);
                            startActivity(i);
                            finish();
                        }
                        else if(message.equals("Update Successfull"))
                        {
                            Intent i = new Intent(PopUpExpertiseAgril.this, NavdrawerRP.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }, TIME_OUT);

       /* startActivity(new Intent(SignUpFarmer_2.this,Login.class));
                finish();*/
            }
            else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            /*    Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();*/
            }

        }



    }
    public void onClickCancel(View view)
    {
        finish();
    }
}
