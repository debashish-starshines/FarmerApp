package ratnatech.farmerapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ratnatech.farmerapp.AskQAgriculture.CultivationPractise;

public class FileUploadTest extends AppCompatActivity {

    Spinner subgroup;
    EditText edittext;
    String strsubgroup="",  str_qnaid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_file_upload_test);

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

        subgroup=findViewById(R.id.subgroup);
        edittext=findViewById(R.id.edittext);

        subgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strsubgroup= subgroup.getSelectedItem().toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



    public void onClickSubmit(View view)
    {
        new AsyncQna().execute();
    }


    private  class AsyncQna extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {



            pDialog = new ProgressDialog(FileUploadTest.this);
            pDialog.setMessage("Uploading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("category_id","1"));
            cred.add(new BasicNameValuePair("sub_category_id","1"));
            cred.add(new BasicNameValuePair("dropdown",strsubgroup));
            cred.add(new BasicNameValuePair("questext",edittext.getText().toString()));
            cred.add(new BasicNameValuePair("user_id","54"));//


            String urlRouteList= Config.apiaqna;
            try {
                String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);
                Log.v(" ", "Response is " + route_response);

                success = route_response;

                JSONObject jsonObject = new JSONObject(success);
                // status =jsonObject.getString("status");
                str_qnaid = jsonObject.getString("qna_id");

            } catch (Exception e)

            {
                Log.v("ONMESSAGE", e.toString());

            }return null;
        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();


            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Question Submitted", Snackbar.LENGTH_LONG);

            snackbar.show();


        }



    }
}
