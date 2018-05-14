package ratnatech.farmerapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 12-Sep-17.
 */

public class ForgotPass extends AppCompatActivity {
    EditText valid_mail;
    Button send;
    String user_email, message="";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_forgot_pass);
        send = (Button) findViewById(R.id.send);
        valid_mail = (EditText) findViewById(R.id.pass_verify);
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
        getSupportActionBar().setTitle("Forgot Password");
        //toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = valid_mail.getText().toString();
                if (user_email.equals(""))
                {
                    Toast.makeText(ForgotPass.this, "Empty Email", Toast.LENGTH_SHORT).show();
                }
                else if (!user_email.matches(emailPattern))
                {
                    Toast.makeText(ForgotPass.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                    new AsynSignInDetails().execute();
                }
            }
        });
    }

    private class AsynSignInDetails extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null;
        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("user_email",user_email));//user_email
            Log.v("RES","Sending data " + user_email  );

            String urlRouteList= Config.forgot_password;
            try {
                String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);

                success = route_response;
                JSONObject jsonObject = new JSONObject(success);
                //user_email=jsonObject.getString("user_email");
                message = jsonObject.getString("message");


            } catch (Exception e)

            {
                Log.v("ONMESSAGE", e.toString());

            }
            return null;
        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            if (message.equals("Check your email for passcode"))
            {
                Toast.makeText(ForgotPass.this, message, Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(ForgotPass.this, Login.class));
            }
            else
            {
                Toast.makeText(ForgotPass.this, "Invalid email id", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(ForgotPass.this);
            pDialog.setMessage("Loading In...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


    }

}
