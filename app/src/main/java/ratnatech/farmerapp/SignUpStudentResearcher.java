package ratnatech.farmerapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
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

public class SignUpStudentResearcher extends AppCompatActivity {

    Dialog dialog,dialog2;
    TextView text;
    Button dialogButton,btnOK;
    EditText editOtherHighest,editOtherSpecialization;
    String str_other_qualification="",str_other_specialization="";


    RadioGroup restatus,radioGender;
    String str_edu_status="",str_gender="",str_qualification="",str_specialization="",str_institute="",str_other_detail="";
    LinearLayout layoutstudycontinuing;
    AppCompatEditText fname,lname,input_mob_no,input_mail,pass;
    Spinner spinnerhq,spinnerspecialization;
    EditText editinsitutename,editotherdetails;
    String str_email="",strfname="",strlname="",strpass="",strmob="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_sign_up_student_researcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        // custom dialog
        dialog = new Dialog(this);
        dialog2 = new Dialog(this);

        layoutstudycontinuing=findViewById(R.id.layoutstudycontinuing);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        pass=findViewById(R.id.pass);
        input_mob_no=findViewById(R.id.input_mob_no);
        input_mail=findViewById(R.id.input_mail);
        editinsitutename=findViewById(R.id.editinsitutename);
        editotherdetails=findViewById(R.id.editotherdetails);
        spinnerhq=findViewById(R.id.spinnerhq);
        spinnerspecialization=findViewById(R.id.spinnerspecialization);
        radioGender=findViewById(R.id.radioGender);restatus=findViewById(R.id.restatus);
        spinnerhq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_qualification= spinnerhq.getSelectedItem().toString();

                if(str_qualification.equals("Others"))
                {

                    dialog.setContentView(R.layout.custom);
                    dialog.setTitle("Others");

                    // set the custom dialog components - text, image and button
                    editOtherHighest = dialog.findViewById(R.id.editOtherHighest);



                    dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str_other_qualification= editOtherHighest.getText().toString();

                            Toast.makeText(SignUpStudentResearcher.this, str_other_qualification, Toast.LENGTH_SHORT).show();
                            if(!str_other_qualification.equals(""))
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerspecialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_specialization= spinnerspecialization.getSelectedItem().toString();

                if(str_specialization.equals("Others"))
                {
                    dialog2.setContentView(R.layout.custom2);
                    dialog2.setTitle("Specialization");

                    // set the custom dialog components - text, image and button
                    editOtherSpecialization = dialog2.findViewById(R.id.editOtherSpecialization);



                    btnOK = (Button) dialog2.findViewById(R.id.btnOK);
                    // if button is clicked, close the custom dialog
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str_other_specialization= editOtherSpecialization.getText().toString();

                            Toast.makeText(SignUpStudentResearcher.this, str_other_specialization, Toast.LENGTH_SHORT).show();
                            if(!str_other_specialization.equals(""))
                                dialog2.dismiss();
                        }
                    });

                    dialog2.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_gender=rb.getText().toString();
                    Toast.makeText(SignUpStudentResearcher.this, str_gender, Toast.LENGTH_SHORT).show();


                }

            }
        });

        restatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                  // String text=rb.getText().toString();
                     //Toast.makeText(SignUpStudentResearcher.this, str_edu_status, Toast.LENGTH_SHORT).show();
/*
                    if(text.equals("Study Continuing")||text.equals("Passed Out"))
                    {
                        layoutstudycontinuing.setVisibility(View.VISIBLE);
                    }
                    if (text.equals("Study Continuing"))
                    {
                        str_edu_status="1";
                    }
                    else if(text.equals("Passed Out"))
                    {
                        str_edu_status="2";
                    }*/

                     str_edu_status=rb.getText().toString();
                    if(str_edu_status.equals("Study Continuing")||str_edu_status.equals("Passed Out"))
                    {
                        layoutstudycontinuing.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

    }

    public void onClickSubmit(View view)
    {
        strfname=fname.getText().toString().trim();
        strlname=lname.getText().toString().trim();
        str_institute=editinsitutename.getText().toString().trim();
        str_other_detail=editotherdetails.getText().toString().trim();
        str_email=input_mail.getText().toString().trim();
        strpass=pass.getText().toString().trim();
        strmob=input_mob_no.getText().toString().trim();
        if(strfname.equals("")||strlname.equals("")||str_institute.equals("")||str_email.equals("")||strpass.equals("")||strmob.equals(""))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "One or More Field(s) Blank", Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else
        {
            new AsyncSignUpStudentResearcher().execute();
        }
    }
    private  class AsyncSignUpStudentResearcher extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("first_name",strfname));
            cred.add(new BasicNameValuePair("last_name",strlname ));
            cred.add(new BasicNameValuePair("email",str_email ));
            cred.add(new BasicNameValuePair("password",strpass ));
            cred.add(new BasicNameValuePair("phone",strmob ));
            if(str_gender.equals(""))
            {
                Toast.makeText(SignUpStudentResearcher.this, "Select Gender", Toast.LENGTH_SHORT).show();
            }
            else if(str_gender.equals("Male"))
            {
                cred.add(new BasicNameValuePair("gender", "1"));
            }
            else if(str_gender.equals("Female"))
            {
                cred.add(new BasicNameValuePair("gender", "2"));
            }
            cred.add(new BasicNameValuePair("education_status",str_edu_status ));

            if(str_qualification.equals("-Select-"))
            {
                Toast.makeText(SignUpStudentResearcher.this, "Select Highest Qualification", Toast.LENGTH_SHORT).show();
            }
            else
            {
                cred.add(new BasicNameValuePair("highest_qualification",str_qualification ));

            }
            cred.add(new BasicNameValuePair("other_highest_qualification",str_other_qualification ));
            if(str_specialization.equals("-Select-"))
            {
                Toast.makeText(SignUpStudentResearcher.this, "Select Your Specialization", Toast.LENGTH_SHORT).show();
            }
            else
            {
                cred.add(new BasicNameValuePair("specialization",str_specialization ));
            }

            cred.add(new BasicNameValuePair("other_specialization",str_other_specialization ));
            cred.add(new BasicNameValuePair("university_college",str_institute ));
            cred.add(new BasicNameValuePair("other_detail",str_other_detail ));
            //
            String urlRouteList="http://demo.ratnatechnology.co.in/farmerapp/api/user/signup_StudentResearcher";
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
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUpStudentResearcher.this,Login.class));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(SignUpStudentResearcher.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

    }
}
