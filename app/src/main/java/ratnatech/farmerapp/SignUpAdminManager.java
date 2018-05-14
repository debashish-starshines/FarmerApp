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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpAdminManager extends AppCompatActivity {

    private static final int TIME_OUT = 3000;
    Spinner spinner_adminmanager_posthold,spinner_adminmanager_posthold_stategovt_dept,district,districtCG;
    LinearLayout layout_stategovt,layout_centgovt,line,layoutdistrictjuris;
    RadioGroup radioGroupPostAgriculture,radioGroupPostHorticulture,radioGroupPostSoilConservation,radioGroupPostVeterinary,radioGroupPostFishery;
    EditText editJurisdiction,editCGJurisdiction;

    AppCompatEditText name,input_mob_no,input_pass,input_mail;
    String str_name,str_mobile,str_pass,str_email,str_jurisdiction="",str_district_cg="";
    String str_post_holds,str_department;
    String str_stategovt_dept_post="";
    String str_agriculture_post="",str_horticulture_post="",str_soilconservation_post="",
            str_veterinary_post="",str_fishery_post="",str_district;
    EditText editSpecifyPost,editSpecifyInstitution;
    String str_specifypost,str_specifyinstitution;
    TextView posttext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_sign_up_admin_manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        posttext=findViewById(R.id.posttext);
        name=findViewById(R.id.name);
        input_mob_no=findViewById(R.id.input_mob_no);
        input_pass=findViewById(R.id.input_pass);
        input_mail=findViewById(R.id.input_mail);
        editSpecifyPost=findViewById(R.id.editSpecifyPost);
        editSpecifyInstitution=findViewById(R.id.editSpecifyInstitution);

        editJurisdiction=findViewById(R.id.editJurisdiction);
        editCGJurisdiction=findViewById(R.id.editCGJurisdiction);
        line=findViewById(R.id.line);
        radioGroupPostAgriculture=findViewById(R.id.radioGroupPostAgriculture);
        radioGroupPostHorticulture=findViewById(R.id.radioGroupPostHorticulture);
        radioGroupPostSoilConservation=findViewById(R.id.radioGroupPostSoilConservation);
        radioGroupPostVeterinary=findViewById(R.id.radioGroupPostVeterinary);
        radioGroupPostFishery=findViewById(R.id.radioGroupPostFishery);
        layout_centgovt=findViewById(R.id.layout_id_centgovt);

        district=findViewById(R.id.district);
        districtCG=findViewById(R.id.districtCG);
        layout_stategovt=findViewById(R.id.layout_id_stategovt);
        layoutdistrictjuris=findViewById(R.id.layoutdistrictjuris);
        spinner_adminmanager_posthold=findViewById(R.id.spinner_adminmanager_posthold);
        spinner_adminmanager_posthold_stategovt_dept=findViewById(R.id.spinner_adminmanager_posthold_stategovt_dept);

        spinner_adminmanager_posthold.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_post_holds= spinner_adminmanager_posthold.getSelectedItem().toString();
              /*  if(strgovttype.equals("-Select-"))
                {
                    layout_stategovt.setVisibility(View.GONE);
                    layout_centgovt.setVisibility(View.GONE);
                }
                else*/
                 if(str_post_holds.equals("State Govt"))
                {
                    layout_stategovt.setVisibility(View.VISIBLE);
                    layout_centgovt.setVisibility(View.GONE);
                }
                else if(str_post_holds.equals("Central Govt"))
                {
                    layout_stategovt.setVisibility(View.GONE);
                    layout_centgovt.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_adminmanager_posthold_stategovt_dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 str_department= spinner_adminmanager_posthold_stategovt_dept.getSelectedItem().toString();
                Toast.makeText(SignUpAdminManager.this, str_department, Toast.LENGTH_SHORT).show();
              if(str_department.equals("Agriculture"))
                {
                    posttext.setVisibility(View.VISIBLE);
                    radioGroupPostAgriculture.setVisibility(View.VISIBLE);
                    radioGroupPostHorticulture.setVisibility(View.GONE);

                    editJurisdiction.setVisibility(View.VISIBLE);
                    district.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);
                    radioGroupPostSoilConservation.setVisibility(View.GONE);
                    radioGroupPostVeterinary.setVisibility(View.GONE);
                    radioGroupPostFishery.setVisibility(View.GONE);
                    layoutdistrictjuris.setVisibility(View.VISIBLE);
                }
                else if(str_department.equals("Horticulture"))
                {
                    posttext.setVisibility(View.VISIBLE);
                    radioGroupPostAgriculture.setVisibility(View.GONE);
                    radioGroupPostHorticulture.setVisibility(View.VISIBLE);

                    editJurisdiction.setVisibility(View.VISIBLE);
                    district.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);
                    radioGroupPostSoilConservation.setVisibility(View.GONE);
                    radioGroupPostVeterinary.setVisibility(View.GONE);
                    radioGroupPostFishery.setVisibility(View.GONE);
                    layoutdistrictjuris.setVisibility(View.VISIBLE);
                }
                else if(str_department.equals("Soil-conservation"))
                {
                    posttext.setVisibility(View.VISIBLE);
                    radioGroupPostSoilConservation.setVisibility(View.VISIBLE);
                    radioGroupPostAgriculture.setVisibility(View.GONE);
                    radioGroupPostHorticulture.setVisibility(View.GONE);

                    editJurisdiction.setVisibility(View.VISIBLE);
                    district.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);
                    radioGroupPostVeterinary.setVisibility(View.GONE);
                    radioGroupPostFishery.setVisibility(View.GONE);
                    layoutdistrictjuris.setVisibility(View.VISIBLE);
                }
                else if(str_department.equals("Veterinary"))
                {
                    posttext.setVisibility(View.VISIBLE);
                    radioGroupPostVeterinary.setVisibility(View.VISIBLE);
                    radioGroupPostAgriculture.setVisibility(View.GONE);
                    radioGroupPostHorticulture.setVisibility(View.GONE);

                    editJurisdiction.setVisibility(View.VISIBLE);
                    district.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);
                    radioGroupPostSoilConservation.setVisibility(View.GONE);
                    radioGroupPostFishery.setVisibility(View.GONE);
                    layoutdistrictjuris.setVisibility(View.VISIBLE);
                }
                else if(str_department.equals("Fishery"))
                {
                    posttext.setVisibility(View.VISIBLE);
                    radioGroupPostFishery.setVisibility(View.VISIBLE);
                    radioGroupPostAgriculture.setVisibility(View.GONE);
                    radioGroupPostHorticulture.setVisibility(View.GONE);

                    editJurisdiction.setVisibility(View.VISIBLE);
                    district.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);
                    radioGroupPostSoilConservation.setVisibility(View.GONE);
                    radioGroupPostVeterinary.setVisibility(View.GONE);
                    layoutdistrictjuris.setVisibility(View.VISIBLE);
                }
                else
              {
                  layoutdistrictjuris.setVisibility(View.GONE);
                  posttext.setVisibility(View.GONE);

                  radioGroupPostAgriculture.setVisibility(View.GONE);
                  radioGroupPostHorticulture.setVisibility(View.GONE);
                  radioGroupPostSoilConservation.setVisibility(View.GONE);
                  radioGroupPostVeterinary.setVisibility(View.GONE);
                  radioGroupPostFishery.setVisibility(View.GONE);

              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroupPostAgriculture.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_agriculture_post=rb.getText().toString();
                    str_stategovt_dept_post="";
                     Toast.makeText(SignUpAdminManager.this, str_agriculture_post, Toast.LENGTH_SHORT).show();
                    str_stategovt_dept_post=str_agriculture_post;
                     if (str_agriculture_post.equals("Director"))
                     {
                         editJurisdiction.setText("");
                         editJurisdiction.setVisibility(View.GONE);
                     }
                     else {
                         editJurisdiction.setVisibility(View.VISIBLE);
                     }
                }

            }
        });

        radioGroupPostHorticulture.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_horticulture_post=rb.getText().toString();
                    str_stategovt_dept_post="";
                     Toast.makeText(SignUpAdminManager.this, str_horticulture_post, Toast.LENGTH_SHORT).show();
                    str_stategovt_dept_post=str_horticulture_post;
                    if (str_horticulture_post.equals("Director"))
                    {
                        editJurisdiction.setText("");
                        editJurisdiction.setVisibility(View.GONE);
                    }
                    else {
                        editJurisdiction.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

        radioGroupPostSoilConservation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_soilconservation_post=rb.getText().toString();
                    str_stategovt_dept_post="";
                      Toast.makeText(SignUpAdminManager.this, str_soilconservation_post, Toast.LENGTH_SHORT).show();
                    str_stategovt_dept_post=str_soilconservation_post;
                    if (str_soilconservation_post.equals("Director"))
                    {
                        editJurisdiction.setText("");
                        editJurisdiction.setVisibility(View.GONE);
                    }
                    else {
                        editJurisdiction.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

        radioGroupPostVeterinary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_veterinary_post=rb.getText().toString();
                    str_stategovt_dept_post="";
                  Toast.makeText(SignUpAdminManager.this, str_veterinary_post, Toast.LENGTH_SHORT).show();
                    str_stategovt_dept_post=str_veterinary_post;

                    if (str_veterinary_post.equals("Director"))
                    {
                        editJurisdiction.setText("");
                        editJurisdiction.setVisibility(View.GONE);
                    }
                    else {
                        editJurisdiction.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        radioGroupPostFishery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_fishery_post=rb.getText().toString();
                    str_stategovt_dept_post="";
                 Toast.makeText(SignUpAdminManager.this, str_fishery_post, Toast.LENGTH_SHORT).show();
                    str_stategovt_dept_post=str_fishery_post;
                    if (str_fishery_post.equals("Director"))
                    {
                        editJurisdiction.setText("");
                        editJurisdiction.setVisibility(View.GONE);
                    }
                    else {
                        editJurisdiction.setVisibility(View.VISIBLE);
                    }
                }

            }
        });



        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // str_district= district.getSelectedItem().toString();
                str_district=String.valueOf(position+1);
                        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        districtCG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // str_district= district.getSelectedItem().toString();
                str_district_cg=String.valueOf(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
    public void onClickSubmit(View view)
    {
        //validation
        str_name=name.getText().toString().trim();
        str_mobile=input_mob_no.getText().toString().trim();
        str_pass=input_pass.getText().toString().trim();
        str_email=input_mail.getText().toString().trim();
        str_jurisdiction=editJurisdiction.getText().toString().trim();
        str_specifypost=editSpecifyPost.getText().toString().trim();
        str_specifyinstitution=editSpecifyInstitution.getText().toString().trim();

        if(str_name.equals("")||str_mobile.equals("")||str_pass.equals(""))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "One or More Field Blank", Snackbar.LENGTH_LONG);

            snackbar.show();
        }

        else if(!(isValidPhone(str_mobile)))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Phone number is Invalid", Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else
        {
            new AsyncSignUpAdminManager().execute();

        }
    }

    private  class AsyncSignUpAdminManager extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {



            pDialog = new ProgressDialog(SignUpAdminManager.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("name",str_name));
            cred.add(new BasicNameValuePair("phone",str_mobile));
            cred.add(new BasicNameValuePair("password",str_pass));
            cred.add(new BasicNameValuePair("email",str_email));
            cred.add(new BasicNameValuePair("govt_type",str_post_holds));//post_holds

            if(str_post_holds.equals("Central Govt"))
            {
                cred.add(new BasicNameValuePair("govt_department",null));
                cred.add(new BasicNameValuePair("post",str_specifypost));
                cred.add(new BasicNameValuePair("institution",str_specifyinstitution));
                cred.add(new BasicNameValuePair("jurisdiction", editCGJurisdiction.getText().toString()));
                cred.add(new BasicNameValuePair("district",str_district_cg));
            }
            else if(str_post_holds.equals("State Govt"))
            {

                cred.add(new BasicNameValuePair("govt_department",str_department));
                cred.add(new BasicNameValuePair("post",str_stategovt_dept_post));
                cred.add(new BasicNameValuePair("institution",null));
                cred.add(new BasicNameValuePair("jurisdiction", str_jurisdiction));
                cred.add(new BasicNameValuePair("district",str_district));
            }

   /*
            if(str_post_holds.equals("Central Govt"))
            {
                cred.add(new BasicNameValuePair("department",null));
                cred.add(new BasicNameValuePair("agriculture_post",null));
                cred.add(new BasicNameValuePair("horticulture_post",null));
                cred.add(new BasicNameValuePair("veterinary_post",null));
                cred.add(new BasicNameValuePair("fishery_post",null));
            }
            else
                {
                    cred.add(new BasicNameValuePair("department",str_department));
            }
         if(str_department.equals("Agriculture"))
            {
                cred.add(new BasicNameValuePair("agriculture_post",str_agriculture_post));
                cred.add(new BasicNameValuePair("horticulture_post",null));
                cred.add(new BasicNameValuePair("soilconservation_post",null));
                cred.add(new BasicNameValuePair("veterinary_post",null));
                cred.add(new BasicNameValuePair("fishery_post",null));
            }
            if(str_department.equals("Horticulture"))
            {
                cred.add(new BasicNameValuePair("agriculture_post",null));
                cred.add(new BasicNameValuePair("horticulture_post",str_horticulture_post));
                cred.add(new BasicNameValuePair("soilconservation_post",null));
                cred.add(new BasicNameValuePair("veterinary_post",null));
                cred.add(new BasicNameValuePair("fishery_post",null));
            }
            if(str_department.equals("Soil-conservation"))
            {
                cred.add(new BasicNameValuePair("agriculture_post",null));
                cred.add(new BasicNameValuePair("horticulture_post",null));
                cred.add(new BasicNameValuePair("soilconservation_post",str_soilconservation_post));
                cred.add(new BasicNameValuePair("veterinary_post",null));
                cred.add(new BasicNameValuePair("fishery_post",null));
            }
            if(str_department.equals("Veterinary"))
            {
                cred.add(new BasicNameValuePair("agriculture_post",null));
                cred.add(new BasicNameValuePair("horticulture_post",null));
                cred.add(new BasicNameValuePair("soilconservation_post",null));
                cred.add(new BasicNameValuePair("veterinary_post",str_veterinary_post));
                cred.add(new BasicNameValuePair("fishery_post",null));
            }
            if(str_department.equals("Fishery"))
            {
                cred.add(new BasicNameValuePair("agriculture_post",null));
                cred.add(new BasicNameValuePair("horticulture_post",null));
                cred.add(new BasicNameValuePair("soilconservation_post",null));
                cred.add(new BasicNameValuePair("veterinary_post",null));
                cred.add(new BasicNameValuePair("fishery_post",str_fishery_post));
            }*/


           /* if(str_post_holds.equals("Central Govt"))
            {
                cred.add(new BasicNameValuePair("district","0"));
                cred.add(new BasicNameValuePair("specifypost",str_specifypost));
                cred.add(new BasicNameValuePair("institution",str_specifyinstitution));
            }
            else
            {
                cred.add(new BasicNameValuePair("district",str_district));
                cred.add(new BasicNameValuePair("specifypost",null));
                cred.add(new BasicNameValuePair("institution",null));
            }
*/




            String urlRouteList=Config.adminmanager_signup;
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
                        Intent i = new Intent(SignUpAdminManager.this, Login.class);
                        startActivity(i);
                        finish();
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
