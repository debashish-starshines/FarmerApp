package ratnatech.farmerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WelcomeScreen extends AppCompatActivity {

    int SPLASH_TIME_OUT=50;
    TextView countGuest,countStudent,countExtensionOff,countBusinessPerson,countFarmer;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        countGuest=findViewById(R.id.countGuest);
        countStudent=findViewById(R.id.countStudent);
        countExtensionOff=findViewById(R.id.countExtensionOff);
        countBusinessPerson=findViewById(R.id.countBusinessPerson);
        countFarmer=findViewById(R.id.countFarmer);

         progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url =Config.getUserCount;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                            String farmer_user,business_person_user,extension_officer_user,student_user,others_user,admin_user;
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String data= jsonObject.getString("data");
                            JSONObject jsonObject1=new JSONObject(data);
                            farmer_user=jsonObject1.getString("farmer_user");
                            business_person_user=jsonObject1.getString("business_person_user");
                            extension_officer_user=jsonObject1.getString("extension_officer_user");
                            student_user=jsonObject1.getString("student_user");
                            admin_user=jsonObject1.getString("admin_user");
                            others_user=jsonObject1.getString("others_user");

                            countFarmer.setText(farmer_user);
                            countBusinessPerson.setText(business_person_user);
                            countExtensionOff.setText(extension_officer_user);
                            countStudent.setText(student_user);
                            countGuest.setText(others_user);
                            progressDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           Toast.makeText(WelcomeScreen.this,"Error while loading Users",Toast.LENGTH_LONG);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void onClickNext(View view)
    {
        Intent i = new Intent(WelcomeScreen.this, WelcomeActivity.class);// Login
        startActivity(i);
        finish();
    }
}
