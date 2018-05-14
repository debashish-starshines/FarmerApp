package ratnatech.farmerapp.EditProfileActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
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

import ratnatech.farmerapp.CustomHttpClient;
import ratnatech.farmerapp.Login;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.SignUpStudentResearcher;

public class EditProfileStudentResearcher extends AppCompatActivity {

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

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String user_id;

    int position,position2;
    String email,salt,first_name,last_name,phone,gender,education_status,highest_qualification,
            specialization,university_college,other_detail,other_highest_qualification,other_specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_edit_profile_student_researcher);

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
        user_id=sharedpreferences.getString("FLAG", null);
        Log.d("user_id:",user_id);
        editor.commit(); // commit changes


        // custom dialog
        dialog = new Dialog(this);
        dialog2 = new Dialog(this);

        layoutstudycontinuing=findViewById(R.id.layoutstudycontinuing);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        pass=findViewById(R.id.pass);

        input_mob_no=findViewById(R.id.input_mob_no);
        input_mob_no.setEnabled(false);

        input_mail=findViewById(R.id.input_mail);
        editinsitutename=findViewById(R.id.editinsitutename);
        editotherdetails=findViewById(R.id.editotherdetails);
        spinnerhq=findViewById(R.id.spinnerhq);
        spinnerspecialization=findViewById(R.id.spinnerspecialization);
        radioGender=findViewById(R.id.radioGender);restatus=findViewById(R.id.restatus);

        //Function to get data
        getData();

        spinnerhq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_qualification= spinnerhq.getSelectedItem().toString();

                if(str_qualification.equals("Others"))
                {

                    dialog.setContentView(R.layout.custom);
                    dialog.setTitle("Other Highest Qualification");

                    // set the custom dialog components - text, image and button
                    editOtherHighest = dialog.findViewById(R.id.editOtherHighest);
                    editOtherHighest.setText(other_highest_qualification);


                    dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str_other_qualification= editOtherHighest.getText().toString();

                            Toast.makeText(EditProfileStudentResearcher.this, str_other_qualification, Toast.LENGTH_SHORT).show();
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
                    dialog2.setTitle("Other Specialization");

                    // set the custom dialog components - text, image and button
                    editOtherSpecialization = dialog2.findViewById(R.id.editOtherSpecialization);
                    editOtherSpecialization.setText(other_specialization);


                    btnOK = (Button) dialog2.findViewById(R.id.btnOK);
                    // if button is clicked, close the custom dialog
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            str_other_specialization= editOtherSpecialization.getText().toString();

                            Toast.makeText(EditProfileStudentResearcher.this, str_other_specialization, Toast.LENGTH_SHORT).show();
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
                    //Toast.makeText(EditProfileStudentResearcher.this, String.valueOf(checkedId), Toast.LENGTH_SHORT).show();


                }

            }
        });

        restatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {


                    str_edu_status=rb.getText().toString();
                    if(str_edu_status.equals("Study Continuing")||str_edu_status.equals("Passed Out"))
                    {
                        layoutstudycontinuing.setVisibility(View.VISIBLE);
                    }
                }

            }
        });


    }

    public void getData()
    {
        String url ="http://demo.ratnatechnology.co.in/farmerapp/api/user/getStudentResearcher?user_id="+user_id;
        //String url="https://demo.api-platform.com/books";
        //creating a string request to send request to the url


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject=new JSONObject(response);

                            JSONArray array=jsonObject.getJSONArray("data");

                            for(int i=0;i<array.length();i++) {
                                 JSONObject o = array.getJSONObject(i);
                                email = o.getString("email");
                                salt = o.getString("salt");
                                first_name = o.getString("first_name");
                                last_name = o.getString("last_name");
                                phone = o.getString("phone");
                                gender = o.getString("gender");
                                education_status = o.getString("education_status");
                                highest_qualification = o.getString("highest_qualification");
                                specialization = o.getString("specialization");
                                university_college = o.getString("university_college");
                                other_detail = o.getString("other_detail");
                                other_highest_qualification = o.getString("other_highest_qualification");
                                other_specialization = o.getString("other_specialization");

                                fname.setText(first_name);
                                lname.setText(last_name);
                                input_mob_no.setText(phone);
                                pass.setText(salt);
                                input_mail.setText(email);/*
                                int selectedId = radioGender.getCheckedRadioButtonId();
                                Toast.makeText(EditProfileStudentResearcher.this, String.valueOf(selectedId), Toast.LENGTH_SHORT).show();*/
                                if(gender.equals("male"))
                                radioGender.check(R.id.radioButtonMale);
                                else if(gender.equals("female"))
                                radioGender.check(R.id.radioButtonFemale);

                                if (education_status.equals("Study Continuing"))
                                    restatus.check(R.id.radioButtonStudyContinuing);
                                    else if (education_status.equals("Passed Out"))
                                        restatus.check(R.id.radioButtonPassedOut);

                                    if(highest_qualification.equals("Matriculation"))
                                        position=0;
                                    else if(highest_qualification.equals("+2"))
                                        position=1;
                                    else if(highest_qualification.equals("Graduate"))
                                        position=2;
                                    else if(highest_qualification.equals("Post Graduate"))
                                        position=3;
                                    else if(highest_qualification.equals("Doctorate"))
                                        position=4;
                                    else if(highest_qualification.equals("Others"))
                                        position=5;
                                spinnerhq.setSelection(position);


                                if(specialization.equals("Matriculation"))
                                    position2=0;
                              else  if(specialization.equals("Arts"))
                                    position2=1;
                                else  if(specialization.equals("Science"))
                                    position2=2;
                                else  if(specialization.equals("Commerce"))
                                    position2=3;
                                else  if(specialization.equals("Engineering"))
                                    position2=4;
                                else  if(specialization.equals("Medical"))
                                    position2=5;
                                else  if(specialization.equals("Law"))
                                    position2=6;
                                else  if(specialization.equals("Agriculture"))
                                    position2=7;
                                else  if(specialization.equals("Veterinary Science"))
                                    position2=8;
                                else  if(specialization.equals("Fishery"))
                                    position2=9;
                                else  if(specialization.equals("Horticulture"))
                                    position2=10;
                                else  if(specialization.equals("Agricultural Engineering"))
                                    position2=11;
                                else  if(specialization.equals("Food Processing"))
                                    position2=12;
                                else  if(specialization.equals("Bio Technology"))
                                    position2=13;
                                else  if(specialization.equals("Others"))
                                    position2=14;
                                spinnerspecialization.setSelection(position2);

                                editinsitutename.setText(university_college);
                                editotherdetails.setText(other_detail);
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
                        Toast.makeText(EditProfileStudentResearcher.this,"Error while loading", Toast.LENGTH_SHORT).show();

                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    public void onClickUpdate(View view)
    {
        str_institute=editinsitutename.getText().toString().trim();
        str_other_detail=editotherdetails.getText().toString().trim();
            new EditProfileStudentResearcherUpdate().execute();

    }
    private  class EditProfileStudentResearcherUpdate extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("user_id",user_id));

            cred.add(new BasicNameValuePair("education_status",str_edu_status ));

            if(str_qualification.equals("-Select-"))
            {
                Toast.makeText(EditProfileStudentResearcher.this, "Select Highest Qualification", Toast.LENGTH_SHORT).show();
            }
            else
            {
                cred.add(new BasicNameValuePair("highest_qualification",str_qualification ));

            }
            cred.add(new BasicNameValuePair("other_highest_qualification",str_other_qualification ));
            if(str_specialization.equals("-Select-"))
            {
                Toast.makeText(EditProfileStudentResearcher.this, "Select Your Specialization", Toast.LENGTH_SHORT).show();
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
            if(message.equals("Update Succesfull"))
            {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                startActivity(new Intent(EditProfileStudentResearcher.this,Login.class));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(EditProfileStudentResearcher.this);
            pDialog.setMessage("Updating...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

    }
}
