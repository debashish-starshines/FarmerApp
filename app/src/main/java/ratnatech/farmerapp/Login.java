package ratnatech.farmerapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

import ratnatech.farmerapp.NavDrawer.NavDrawer;
import ratnatech.farmerapp.NavDrawer.NavDrawerAdminManager;
import ratnatech.farmerapp.NavDrawer.NavDrawerBusiness;
import ratnatech.farmerapp.NavDrawer.NavDrawerStudentR;
import ratnatech.farmerapp.NavDrawer.NavdrawerRP;

public class Login extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    AppCompatEditText input_login,input_pass;
    String strusername,strpass,user_name="";
    int user_group_id=0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Snackbar snackbar;

    // first time login
    Boolean isFirstTime;
    String strlogout="false";

    Context context = this;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String flag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // PERMISSION

        int Permission_All = 1;

        String[] Permissions = {
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,

                android.Manifest.permission.INTERNET,
                android.Manifest.permission.ACCESS_WIFI_STATE,
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA, };
        if(!hasPermissions(this, Permissions)){
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }



        input_login=findViewById(R.id.input_login);
        input_pass=findViewById(R.id.input_pass);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Login");

        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();

        if(bundle != null)
        {

            strlogout = bundle.getString("LOGOUT");
        }

        SharedPreferences app_preferences = PreferenceManager
                .getDefaultSharedPreferences(Login.this);

        SharedPreferences.Editor editor = app_preferences.edit();

        isFirstTime = app_preferences.getBoolean("isFirstTime", true);

        if (isFirstTime) {

//implement your first time logic
            editor.putBoolean("isFirstTime", false);
            editor.commit();

        }else
            {
            //app open directly

             /*   if(strlogout.equals("true"))
                {

                }
                else
                    {
                    Intent intent = new Intent(this, NavDrawer.class);
                    intent.putExtra("SURVEYMENU", "false");
                    startActivity(intent);
                    finish();
                }*/
        }
    }
    public static boolean hasPermissions(Context context, String... permissions){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission: permissions){
                if(ActivityCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED){
                    return  false;
                }
            }
        }
        return true;
    }
    public void onClickSkip(View view)
    {
        startActivity(new Intent(this,NavDrawer.class));
    }
    public void gotoForgot(View view)
    {
        startActivity(new Intent(Login.this,ForgotPass.class));//
    }
    public void gotoRegister(View view)
    {
       // startActivity(new Intent(Login.this,Register.class));
        startActivity(new Intent(Login.this,SignUpAll.class));
    }
    public void onClickLogin(View view )
    {

        strusername=input_login.getText().toString().trim();
        strpass=input_pass.getText().toString().trim();

        if (strusername.equals(""))
        {

       // input_login.setError("Empty Email");
                if(strpass.equals(""))
                {
                    input_pass.setError("Empty Password");
                }
        }
      /*  else if (!strusername.matches(emailPattern))
        {

            input_login.setError("Invalid Email");
        }*/
        else
        {
            new AsyncLogInDetails().execute();
        }
    }
    private  class AsyncLogInDetails extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="",user_id="";
        boolean status=true;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Logging In...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("user_name",strusername ));
            cred.add(new BasicNameValuePair("user_pwd",strpass ));

         //   String urlRouteList="http://demo.ratnatechnology.co.in/api/api/user/login";
            String urlRouteList=Config.farmerapplogin;
            try {
                String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);
                Log.v(" ", "Response is " + route_response);

                success = route_response;
                JSONObject jsonObject = new JSONObject(success);
                status =jsonObject.getBoolean("status");
                message = jsonObject.getString("message");

            } catch (Exception e)

            {
                try {
                    String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);
                    Log.v(" ", "Response is " + route_response);

                    success = route_response;
                    JSONObject jsonObject = new JSONObject(success);
                    user_name = jsonObject.getString("user_name");
                    user_group_id=jsonObject.getInt("user_group_id");
                    user_id=jsonObject.getString("user_id");

                    Log.d("USERID", user_id);
                    sharedpreferences = getSharedPreferences(mypreference,
                            Context.MODE_MULTI_PROCESS);
                    SharedPreferences.Editor editor = sharedpreferences.edit();  //deb done code for one time login
                    editor.putString("FLAG", user_id);
                    editor.commit();

                }
                catch (Exception ex){}

            }return null;
        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();
            if (status == false)
            {
                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

            snackbar.show();
        }
            else {
                // get Connectivity Manager object to check connection
                ConnectivityManager connec =
                        (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

                // Check for network connections
                if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                        connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

                    // if connected with internet

                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Login Successful", Snackbar.LENGTH_LONG);

                    snackbar.show();
                    //Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    if(user_group_id==1)
                    {
                        Intent intent = new Intent(Login.this, NavDrawerAdminManager.class);
                        intent.putExtra("keyName", user_name);
                        intent.putExtra("USER_ID",user_id);
                        startActivity(intent);
                        finish();
                    }
                  if(user_group_id==2)
                  {

                      Intent intent = new Intent(Login.this, NavDrawer.class);
                      intent.putExtra("keyName", user_name);
                      intent.putExtra("USER_ID",user_id);
                      startActivity(intent);
                      finish();
                  }
                  else if(user_group_id==3)
                  {
                      Intent intent = new Intent(Login.this, NavdrawerRP.class);
                      intent.putExtra("keyName", user_name);
                      intent.putExtra("USER_ID",user_id);
                      startActivity(intent);
                      finish();
                  }
                  else if(user_group_id==4)
                  {
                      Intent intent = new Intent(Login.this, NavDrawerBusiness.class);
                      intent.putExtra("keyName", user_name);
                      intent.putExtra("USER_ID",user_id);
                      startActivity(intent);
                      finish();
                  }
                  else if(user_group_id==5)
                  {
                      Intent intent = new Intent(Login.this, NavDrawerStudentR.class);
                      intent.putExtra("keyName", user_name);
                      intent.putExtra("USER_ID",user_id);
                      startActivity(intent);
                      finish();
                  }
                  else if(user_group_id==6)
                  {
                      Intent intent = new Intent(Login.this, NavDrawerGuest.class);
                      intent.putExtra("keyName", user_name);
                      intent.putExtra("USER_ID",user_id);
                      startActivity(intent);
                      finish();
                  }
                } else if (
                        connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {


                   /* // Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();*/

                   /* Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Not Connected to Internet", Snackbar.LENGTH_LONG);

                    snackbar.show();*/
                    Toast.makeText(getApplicationContext(), " Not Connected to Internet", Toast.LENGTH_LONG).show();
                }
            }


        }



    }
    public void gotoResourcePerson(View view)
    {
        Intent intent=new Intent(this,NavdrawerRP.class);
        startActivity(intent);
    }

    public void gotoBusiness(View view)
    {
        Intent intent=new Intent(this,NavDrawerBusiness.class);
        startActivity(intent);
    }


}
