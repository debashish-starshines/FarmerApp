package ratnatech.farmerapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class DealsinOtherBusiness extends Activity {


    String str_farm_name,str_website,str_mobile,str_pass,str_email,str_propertier_name,str_district_id,str_block_id,
            str_area_of_business,strbusinesstype,str_deals_in_product;

    EditText othertype;
    int TIME_OUT=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_dealsin_other_business);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = 0;
        params.height = 500;
        params.width = 700;
        params.y = 0;

        this.getWindow().setAttributes(params);

        setFinishOnTouchOutside(false);// to prevent activity from dismissing on touching outside

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            str_website = bundle.getString("WEBSITE");
            str_mobile = bundle.getString("MOBILE" );
            str_email = bundle.getString("EMAIL" );
            str_pass  = bundle.getString("PASSWORD");
            str_farm_name  = bundle.getString("FARM_NAME" );
            str_propertier_name  = bundle.getString("PROPERTIER_NAME" );
            str_district_id  = bundle.getString("DISTRICT" );
            str_block_id  = bundle.getString("BLOCK" );
            str_area_of_business  = bundle.getString("AREA_OF_BUSINESS" );
            strbusinesstype  = bundle.getString("BUSINESS_TYPE" );
            str_deals_in_product  = bundle.getString("DEALS_IN_PRODUCT" );


            Log.d("WEBSITE",str_website );
            Log.d("MOBILE",str_mobile );
            Log.d("EMAIL",str_email );
            Log.d("PASSWORD",str_pass );
            Log.d("FARM_NAME",str_farm_name );
            Log.d("PROPERTIER_NAME",str_propertier_name );
            Log.d("DISTRICT",str_district_id );
            Log.d("BLOCK",str_block_id );
            Log.d("AREA_OF_BUSINESS",str_area_of_business );
            Log.d("BUSINESS_TYPE",strbusinesstype );

            Log.d("DEALS_IN_PRODUCT",str_deals_in_product );
        }

        othertype=findViewById(R.id.othertype);
    }
    public void onClickOk(View view)
    {
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        new AsyncSignUpBusinessOthers().execute();
    }
    public void onClickCancel(View view)
    {
        finish();
    }

    private  class AsyncSignUpBusinessOthers extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(DealsinOtherBusiness.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {



            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();

            cred.add(new BasicNameValuePair("website",str_website));
            cred.add(new BasicNameValuePair("phone",str_mobile));
            cred.add(new BasicNameValuePair("password",str_pass));
            cred.add(new BasicNameValuePair("email",str_email));
            cred.add(new BasicNameValuePair("name_of_farm",str_farm_name));
            cred.add(new BasicNameValuePair("properiter_name",str_propertier_name));
            cred.add(new BasicNameValuePair("district",str_district_id));
            cred.add(new BasicNameValuePair("block",str_block_id));
            cred.add(new BasicNameValuePair("area_of_business",str_area_of_business));
            cred.add(new BasicNameValuePair("business_type",strbusinesstype));
            cred.add(new BasicNameValuePair("deals_in_product",str_deals_in_product));
            cred.add(new BasicNameValuePair("agril_product_category",null));
            cred.add(new BasicNameValuePair("seed",null));//Agriculture,horticulture
            cred.add(new BasicNameValuePair("pesticides",null));// Pesticides brand
            cred.add(new BasicNameValuePair("fertiliser",null)); // Fertiliser brand
            cred.add(new BasicNameValuePair("catg_other",null)); // agril product other category
            cred.add(new BasicNameValuePair("agril_machinery",null)); // agril machines
            cred.add(new BasicNameValuePair("sale_service_spare_parts_available",null)); // Yes no
            cred.add(new BasicNameValuePair("others",null)); // Other Agril Product

            cred.add(new BasicNameValuePair("deals_in_product_other",othertype.getText().toString().trim())); // textbox

            cred.add(new BasicNameValuePair("fish_medicine",null));
            cred.add(new BasicNameValuePair("fish_net",null));
            cred.add(new BasicNameValuePair("fish_feed",null));
            cred.add(new BasicNameValuePair("fish_others",null));

            cred.add(new BasicNameValuePair("category_type",null)); // animal category type
            cred.add(new BasicNameValuePair("quantity",null)); // animal quantity
            cred.add(new BasicNameValuePair("breed",null)); //animal breed
            cred.add(new BasicNameValuePair("feed",null)); // animal feed
            cred.add(new BasicNameValuePair("medicine",null)); // animal medicine
            cred.add(new BasicNameValuePair("others",null)); // animal others(doubt)

            String urlRouteList=Config.business_signup;
            try {

                String route_response = CustomHttpClient.executeHttpPost(urlRouteList, cred);
                Log.v(" ", "Response is " + route_response);

                success = route_response;
                JSONObject jsonObject = new JSONObject(success);
                // status =jsonObject.getString("status");
                message = jsonObject.getString("message");

            }
            catch (Exception e)
            {
                Log.v("ONMESSAGE", e.toString());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            pDialog.dismiss();
            if(message.equals("Registration Successfull"))
            {
               /* Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Intent i = new Intent(FishBusiness.this, Login.class);
                startActivity(i);
                finish();*/
                new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent i = new Intent(DealsinOtherBusiness.this, Login.class);
                        startActivity(i);
                        finish();

                        // close this activity
                        finish();
                    }
                }, TIME_OUT);

                 /* startActivity(new Intent(SignUpFarmer_2.this,Login.class));
                finish();*/
            }
            else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
              /*  Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();*/
            }

        }



    }
}
