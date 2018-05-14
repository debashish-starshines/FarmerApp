package ratnatech.farmerapp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends AppCompatActivity {
    Toolbar toolbar;
    Button register;

    AppCompatEditText fname,lname,input_mail,input_mob_no,input_pass;
    String strfname="",strlname="",stremail="",strmob="",strpass="";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_register);
        register=(Button)findViewById(R.id.register);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        input_mail=findViewById(R.id.input_mail);
        input_mob_no=findViewById(R.id.input_mob_no);
        input_pass=findViewById(R.id.input_pass);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }
    public void onClickRegister(View view)
    {
        strfname=fname.getText().toString().trim();
        strlname=lname.getText().toString().trim();
        stremail=input_mail.getText().toString().trim();
        strmob=input_mob_no.getText().toString().trim();
        strpass=input_pass.getText().toString().trim();


        if (strfname.equals("")||strlname.equals("")||stremail.equals("")||strmob.equals("")||strpass.equals(""))
        {
            Toast.makeText(this, "Empty Field(s)", Toast.LENGTH_SHORT).show();
        }
        else if (!stremail.matches(emailPattern))
        {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }
        else if(!(isValidPhone(strmob)))
        {
            Toast.makeText(view.getContext(), "Phone number is InValid", Toast.LENGTH_LONG).show();
        }
        else if(strpass.length()<6&&strpass.length()>8)
        {
            Toast.makeText(this, "Length error(min 6 and max 8 characters)", Toast.LENGTH_SHORT).show();
        }
        else
        {
            new AsyncSignUpDetails().execute();
        }
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
    private  class AsyncSignUpDetails extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("first_name",strfname));
            cred.add(new BasicNameValuePair("last_name",strlname ));
            cred.add(new BasicNameValuePair("user_email",stremail ));
            cred.add(new BasicNameValuePair("user_pwd",strpass ));
            cred.add(new BasicNameValuePair("phone",strmob ));

            String urlRouteList="http://demo.ratnatechnology.co.in/api/api/user/signup";
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
                startActivity(new Intent(Register.this,Login.class));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

    }

}
