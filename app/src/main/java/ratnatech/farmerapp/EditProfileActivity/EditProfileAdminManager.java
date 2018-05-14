package ratnatech.farmerapp.EditProfileActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ratnatech.farmerapp.Config;
import ratnatech.farmerapp.CustomHttpClient;
import ratnatech.farmerapp.Login;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.SignUpAdminManager;

public class EditProfileAdminManager extends AppCompatActivity {

    private static final int TIME_OUT = 3000;
    Spinner spinner_adminmanager_posthold,spinner_adminmanager_posthold_stategovt_dept,district,districtCG;
    LinearLayout layout_stategovt,layout_centgovt,line,layoutdistrictjuris;
    RadioGroup radioGroupPostAgriculture,radioGroupPostHorticulture,radioGroupPostSoilConservation,radioGroupPostVeterinary,radioGroupPostFishery;
    EditText editJurisdiction;

    AppCompatEditText name,input_mob_no,input_pass,input_mail;
    String str_name,str_mobile,str_pass,str_email,str_jurisdiction="";
    String str_post_holds,str_department;
    String str_stategovt_dept_post="";
    String str_agriculture_post="",str_horticulture_post="",str_soilconservation_post="",
            str_veterinary_post="",str_fishery_post="",str_district;
    EditText editSpecifyPost,editSpecifyInstitution,editCGJurisdiction;
    String str_specifypost,str_specifyinstitution;
    TextView posttext;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String user_id;
    String email,salt,first_name,phone,govt_type,govt_department,
            districtid,post,institution,jurisdiction;
    int position,position2,position3;
    TextView getPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_edit_profile_admin_manager);
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


        posttext=findViewById(R.id.posttext);
        name=findViewById(R.id.name);
        input_mob_no=findViewById(R.id.input_mob_no);
        input_pass=findViewById(R.id.input_pass);
        input_mail=findViewById(R.id.input_mail);
        editSpecifyPost=findViewById(R.id.editSpecifyPost);
        editSpecifyInstitution=findViewById(R.id.editSpecifyInstitution);
        editCGJurisdiction=findViewById(R.id.editCGJurisdiction);
        editJurisdiction=findViewById(R.id.editJurisdiction);
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
        spinner_adminmanager_posthold.setEnabled(false);

        spinner_adminmanager_posthold_stategovt_dept=findViewById(R.id.spinner_adminmanager_posthold_stategovt_dept);
        spinner_adminmanager_posthold_stategovt_dept.setEnabled(false);

        getPost=findViewById(R.id.getPost);
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
                    Toast.makeText(EditProfileAdminManager.this, str_agriculture_post, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(EditProfileAdminManager.this, str_horticulture_post, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(EditProfileAdminManager.this, str_soilconservation_post, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(EditProfileAdminManager.this, str_veterinary_post, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(EditProfileAdminManager.this, str_fishery_post, Toast.LENGTH_SHORT).show();
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

        //Function to get data
        getData();
    }

    public void getData()
    {
        String url ="http://demo.ratnatechnology.co.in/farmerapp/api/user/getAdmin?user_id="+user_id;
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

                                phone = o.getString("phone");

                                govt_type = o.getString("govt_type");
                                govt_department = o.getString("govt_department");
                                Toast.makeText(EditProfileAdminManager.this, govt_department, Toast.LENGTH_SHORT).show();
                                districtid = o.getString("district");
                                post = o.getString("post");
                                institution = o.getString("institution");
                                jurisdiction= o.getString("jurisdiction");

                                name.setText(first_name);
                                input_mob_no.setText(phone);
                                input_pass.setText(salt);
                                input_mail.setText(email);

                                if(govt_type.equals("Central Govt"))
                                editCGJurisdiction.setText(jurisdiction);
                                else if(govt_type.equals("State Govt"))
                                    editJurisdiction.setText(jurisdiction);
                                //spinner get
                                if(govt_type.equals("State Govt"))
                                {
                                    position = 0;
                                    getPost.setText(post);
                                }
                                else if(govt_type.equals("Central Govt"))
                                {
                                    position = 1;
                                    editSpecifyPost.setText(post);
                                }
                                spinner_adminmanager_posthold.setSelection(position);



                                //spinner get
                                if(govt_department.equals("Agriculture"))
                                    position2=0;
                                else if(govt_department.equals("Horticulture"))
                                    position2=1;
                                else if(govt_department.equals("Soil-conservation"))
                                    position2=2;
                                else if(govt_department.equals("Veterinary"))
                                    position2=3;
                                else if(govt_department.equals("Fishery"))
                                    position2=4;
                                else if(govt_department.equals("Secretary, Agril and Farmers Empowerment"))
                                    position2=5;
                                else if(govt_department.equals("Secretary, Animal Resources and Fishery"))
                                    position2=6;

                                spinner_adminmanager_posthold_stategovt_dept.setSelection(position2);



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
                                district.setEnabled(false);

                                districtCG.setSelection(position3);
                                districtCG.setEnabled(false);
                                editSpecifyInstitution.setText(institution);

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
                        Toast.makeText(EditProfileAdminManager.this,"Error while loading", Toast.LENGTH_SHORT).show();

                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

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
    public void onClickUpdate(View view)
    {
        //validation
        str_name=name.getText().toString().trim();
        str_mobile=input_mob_no.getText().toString().trim();
        str_pass=input_pass.getText().toString().trim();
        str_email=input_mail.getText().toString().trim();
        str_jurisdiction=editJurisdiction.getText().toString().trim();
        str_specifypost=editSpecifyPost.getText().toString().trim();
        str_specifyinstitution=editSpecifyInstitution.getText().toString().trim();

        if(str_name.equals("")||str_mobile.equals(""))//||str_pass.equals("")
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
            new UpdateProfileAdminManager().execute();

        }
    }

    private  class UpdateProfileAdminManager extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {



            pDialog = new ProgressDialog(EditProfileAdminManager.this);
            pDialog.setMessage("Updating...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();

            cred.add(new BasicNameValuePair("user_id",user_id));

                if(govt_type.equals("Central Govt"))
                {
                    cred.add(new BasicNameValuePair("post",editSpecifyPost.getText().toString()));
                    cred.add(new BasicNameValuePair("jurisdiction",editCGJurisdiction.getText().toString()));
                }

                else if(govt_type.equals("State Govt"))
                {
                    cred.add(new BasicNameValuePair("post",str_stategovt_dept_post));
                    cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction));
                }






            String urlRouteList= Config.adminmanager_signup;
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
            if(message.equals("Update Successfull"))
            {
                  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();


                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Intent i = new Intent(EditProfileAdminManager.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                }, TIME_OUT);

       /* startActivity(new Intent(SignUpFarmer_2.this,Login.class));
                finish();*/
            }
            else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

            }

        }



    }
}
