package ratnatech.farmerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PopUpAgrilToolImplement extends AppCompatActivity {

    String str_farm_name,str_website,str_mobile,str_pass,str_email,str_propertier_name,str_district_id,str_block_id,
            str_area_of_business,strbusinesstype,str_deals_in_product;
    String str_machines="",str_sparepart_availability="No";
    RadioGroup rgSparePart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_agril_tool_implement);

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

        rgSparePart=findViewById(R.id.rgSparePart);

        rgSparePart.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_sparepart_availability=rb.getText().toString();
                    Toast.makeText(PopUpAgrilToolImplement.this, str_sparepart_availability, Toast.LENGTH_SHORT).show();


                }

            }
        });
        HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox1), "Tractor");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox2), "Power Tiller");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox3), "Rotavator");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox4), "Plough");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox5), "Ridger");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox6), "Leveller");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox7), "Seed Drill");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox8), "Transplanter");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox9), "Weeder");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox10), "Sprayer");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox11), "Reaper");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox12), "Thresher");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox13), "Cleaner");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox14), "Grader");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox15), "Combine");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox16), "Pumps");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox17), "Parboiling Unit");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox18), "Rice Mill");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox19), "Decorticator");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox20), "Tools for fruits and vegetable");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox21), "Others");



        CheckBoxGroup<String> checkBoxGroup = new CheckBoxGroup<>(checkBoxMap,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values) {
                        // Convert the ArrayList into a String.

                        StringBuilder sb = new StringBuilder();
                        for (String s : values)
                        {
                            sb.append(s);
                            sb.append(",");
                        }
                        str_machines= sb.toString();
                        System.out.println(sb.toString());
                        Toast.makeText(PopUpAgrilToolImplement.this,str_machines,Toast.LENGTH_LONG).show();
                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });

    }
    public void onClickSubmit(View view)
    {
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        new AsyncSignUpBusinessAgriltools().execute();

    }

    private class AsyncSignUpBusinessAgriltools extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(PopUpAgrilToolImplement.this);
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
            cred.add(new BasicNameValuePair("agril_product_category","Agril Machinery/ Tools/ Implements"));
            cred.add(new BasicNameValuePair("seed",null));//Agriculture,horticulture
            cred.add(new BasicNameValuePair("pesticides",null));// Pesticides brand
            cred.add(new BasicNameValuePair("fertiliser",null)); // Fertiliser brand
            cred.add(new BasicNameValuePair("catg_other",null)); // agril product other category
            cred.add(new BasicNameValuePair("agril_machinery",str_machines)); // agril machines
            cred.add(new BasicNameValuePair("sale_service_spare_parts_available",str_sparepart_availability)); // Yes no
            cred.add(new BasicNameValuePair("others",null)); // Other Agril Product

            cred.add(new BasicNameValuePair("deals_in_product_other",null)); // textbox

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
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Intent i = new Intent(PopUpAgrilToolImplement.this, Login.class);
                startActivity(i);
                finish();


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
