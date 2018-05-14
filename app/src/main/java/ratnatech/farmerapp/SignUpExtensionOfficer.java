package ratnatech.farmerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

import ratnatech.farmerapp.EditProfileActivity.EditProfileStudentResearcher;

public class SignUpExtensionOfficer extends AppCompatActivity {

    TextView txtinservice;
    AppCompatEditText name,input_mob_no,input_pass,input_mail;
    RadioGroup radioGender;
    String str_gender="",str_input_mail;

    String str_name="",str_input_mob_no="",str_input_pass="";
    String str_check_value="";


    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String user_id="";

    String email,salt,first_name,phone,gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_sign_up_extension_officer);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        user_id=sharedpreferences.getString("FLAG", "");
        Log.d("user_id:",user_id);
        editor.commit(); // commit changes

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            str_check_value = bundle.getString("CHECK_VALUE_FOR_EDIT_PROFILE_EXTOFF");

        }
            if(str_check_value.equals("getname_and_changetitle"))
            {
                getSupportActionBar().setTitle("Edit Profile- Extension Officer");
                //Function to get data
                getData();
            }

        name=findViewById(R.id.name);
        input_mob_no=findViewById(R.id.input_mob_no);
        input_pass=findViewById(R.id.input_pass);
        input_mail=findViewById(R.id.input_mail);
        radioGender=findViewById(R.id.radioGender);

        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_gender=rb.getText().toString();
                   // Toast.makeText(SignUpExtensionOfficer.this, str_gender, Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void getData()
    {
        String url ="http://demo.ratnatechnology.co.in/farmerapp/api/user/getExtensionOfficer?user_id="+user_id;
        //String url="https://demo.api-platform.com/books";
        //creating a string request to send request to the url


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                      //  Toast.makeText(SignUpExtensionOfficer.this, response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject=new JSONObject(response);

                            JSONArray array=jsonObject.getJSONArray("data");

                            for(int i=0;i<array.length();i++) {
                                JSONObject o = array.getJSONObject(i);
                                email = o.getString("email");
                                input_mail.setText(email);
                                input_mail.setEnabled(false);

                                salt = o.getString("salt");
                                input_pass.setText(salt);
                                input_pass.setEnabled(false);

                                first_name = o.getString("first_name");
                                name.setText(first_name);
                                name.setEnabled(false);

                                phone = o.getString("phone");
                                input_mob_no.setText(phone);
                                input_mob_no.setEnabled(false);

                                gender = o.getString("gender");
                                if (gender.equals("male"))
                                    radioGender.check(R.id.radioButtonMale);
                                else if(gender.equals("female"))
                                    radioGender.check(R.id.radioButtonFemale);


                                for (int k = 0; k < radioGender.getChildCount(); k++) { //diaabling radiogroup button
                                    radioGender.getChildAt(k).setEnabled(false);
                                }

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
                        Toast.makeText(SignUpExtensionOfficer.this,"Error while loading", Toast.LENGTH_SHORT).show();

                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }
    public void onClickInService(View view)
    {
        str_name=name.getText().toString().trim();
        str_input_mob_no=input_mob_no.getText().toString().trim();
        str_input_pass=input_pass.getText().toString().trim();
        str_input_mail=input_mail.getText().toString().trim();
        if(str_name.equals("")||str_input_mob_no.equals("")||str_input_pass.equals(""))
        {
            Toast.makeText(this, "Some field(s) are blank", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent=new Intent(this,InServiceGPN.class);
            intent.putExtra("NAME",str_name);
            intent.putExtra("MOBILE",str_input_mob_no);
            intent.putExtra("PASSWORD",str_input_pass);
            intent.putExtra("EMAIL",str_input_mail);
            intent.putExtra("GENDER",str_gender);
            startActivity(intent);
        }

    }
    public void onClickRetired(View view)
    {
        str_name=name.getText().toString().trim();
        str_input_mob_no=input_mob_no.getText().toString().trim();
        str_input_pass=input_pass.getText().toString().trim();
        if(str_name.equals("")||str_input_mob_no.equals("")||str_input_pass.equals("")||str_gender.equals(""))
        {
            Toast.makeText(this, "Some field(s) are blank", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent=new Intent(this,RetiredExtOff.class);
            intent.putExtra("NAME",str_name);
            intent.putExtra("MOBILE",str_input_mob_no);
            intent.putExtra("PASSWORD",str_input_pass);
            intent.putExtra("EMAIL",str_input_mail);
            intent.putExtra("GENDER",str_gender);
            startActivity(intent);
        }

    }

    public void onClickSelfEmployed(View view)
    {

        str_name=name.getText().toString().trim();
        str_input_mob_no=input_mob_no.getText().toString().trim();
        str_input_pass=input_pass.getText().toString().trim();
        if(str_name.equals("")||str_input_mob_no.equals("")||str_input_pass.equals("")||str_gender.equals(""))
        {
            Toast.makeText(this, "Some field(s) are blank", Toast.LENGTH_SHORT).show();
        }
        else
            {
                Intent intent=new Intent(this,SelfEmployedExtOff.class);
                intent.putExtra("NAME",str_name);
                intent.putExtra("MOBILE",str_input_mob_no);
                intent.putExtra("PASSWORD",str_input_pass);
                intent.putExtra("EMAIL",str_input_mail);
                intent.putExtra("GENDER",str_gender);
                startActivity(intent);
        }

    }

    public void onClickOthers(View view)
    {
        str_name=name.getText().toString().trim();
        str_input_mob_no=input_mob_no.getText().toString().trim();
        str_input_pass=input_pass.getText().toString().trim();
        str_input_mail=input_mail.getText().toString().trim();
        if(str_name.equals("")||str_input_mob_no.equals("")||str_input_pass.equals("")||str_gender.equals(""))
        {
            Toast.makeText(this, "Some field(s) are blank", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent=new Intent(this,OthersExtOff.class);
            intent.putExtra("NAME",str_name);
            intent.putExtra("MOBILE",str_input_mob_no);
            intent.putExtra("PASSWORD",str_input_pass);
            intent.putExtra("EMAIL",str_input_mail);
            intent.putExtra("GENDER",str_gender);
            startActivity(intent);
        }

    }

}
