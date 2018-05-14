package ratnatech.farmerapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ratnatech.farmerapp.NetworkRelatedClass.NetworkCall;

public class SignUpFarmer_2 extends AppCompatActivity {

    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";
    private static final String URL_DATA3 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/gps?block_id=";
    private static final int TIME_OUT = 3000;
    LinearLayout layoutimagepreview;
    private String userChoosenTask;
    ImageView ivImage;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    String mImageName;
    File mediaFile;
    File mediaStorageDir;

    //getextra
    String strname="",strfathername="",strmobile="",stremail="",strpass="",strgender="",strcaste="",strvillage="",
            strdistrict="Select Your District",strblock="Select Your Block",strgp="Select Your GP";

    Spinner district,block,gp;//



    int j=0,k=0,l=0;

    String str_district_id="",str_block_id="",str_gp_id="";

    ArrayList<String> arraylist_districtid=new ArrayList<String>();
    ArrayList<String> arraylist_blockid;
    ArrayList<String> arraylist_gp_id;

    String gpNotFound="";

    AppCompatEditText village;
    EditText iluc,niluc; String str_iluc="",str_niluc="",str_irrisource="";
    RadioGroup radioIrrigationSource;

    //For image Upload--------------------------------------
    private static final int PICK_PHOTO = 1958;
    ImageView imageView;
    private String imagefilePath="";
    ProgressDialog progressDialog;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_sign_up_farmer_2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        layoutimagepreview=findViewById(R.id.layoutimagepreview);
        ivImage=(ImageView)findViewById(R.id.ivImage);

        district=(Spinner) findViewById(R.id.district);
        block=findViewById(R.id.block);
        gp=findViewById(R.id.gp);
        village=findViewById(R.id.village);
        iluc=findViewById(R.id.iluc);// irrigated land under cultivation
        niluc=findViewById(R.id.niluc);// non irrigated land under cultivation
        radioIrrigationSource=findViewById(R.id.radioIrrigationSource);
        imageView = (ImageView) findViewById(R.id.imageView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            strname = bundle.getString("NAME");
            strfathername = bundle.getString("FATHERNAME");
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
            strgender = bundle.getString("GENDER");
            strcaste = bundle.getString("CASTE");
        }


        gpNotFound="No";
        load_district_in_spinner();
        // SPINNER SELECT

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict= district.getSelectedItem().toString();
                str_district_id= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block_from_a_district(str_district_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock= block.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id=arraylist_blockid.get(position);
                load_gps_from_blockid(str_block_id);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp= gp.getSelectedItem().toString();

                // TASK IS TO GET THE GP_ID FROM BLOCK_ID ..?
                str_gp_id=arraylist_gp_id.get(position);

                //str_gp_id= String.valueOf(position+1);

               // Toast.makeText(SignUpFarmer_2.this, str_gp_id, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        radioIrrigationSource.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_irrisource=rb.getText().toString();
                    Toast.makeText(SignUpFarmer_2.this, str_irrisource, Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

    private void load_district_in_spinner() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                      /*  progressDialog.dismiss();*/
                        try {
                            // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                            Log.d("onResponse:",s);

                            //Initialize arraylist
                            ArrayList<String> dl=new ArrayList<String>();


                            JSONObject jsonObject=new JSONObject(s);
                            JSONArray array=jsonObject.getJSONArray("districts");

                            for(int i=0;i<array.length();i++)
                            {
                                String did="",dname="",sid="";

                                JSONObject o=array.getJSONObject(i);
                                did=o.getString("id");
                                dname =o.getString("name");
                                sid =o.getString("state_id");

                                dl.add(dname);
                                arraylist_districtid.add(did);

                            }

                            final List<String> districtList = new ArrayList<>(dl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                                    SignUpFarmer_2.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district.setAdapter(spinnerArrayAdapter);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
               // Toast.makeText(getApplicationContext(),"Error while loading",Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "Error while loading", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void load_block_from_a_district(String districtid)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                      /*  progressDialog.dismiss();*/
                        try {
                          // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                            Log.d("onResponse:",s);
                            JSONObject jsonObject=null;
                            JSONArray array=null;

                            //Initialize arraylist
                            ArrayList<String> al=new ArrayList<String>();
                            arraylist_blockid =new ArrayList<String>();
                            jsonObject=new JSONObject(s);
                             array=jsonObject.getJSONArray("blocks");

                           // Toast.makeText(getApplicationContext(),String.valueOf(array.length()),Toast.LENGTH_LONG).show();



                            for(int k=0;k<array.length();k++)
                            {
                                String bid="",name="",district_id="";

                                JSONObject o=array.getJSONObject(k);
                                bid=o.getString("id");
                                name =o.getString("name");
                                district_id =o.getString("district_id");


                                al.add(name);
                                arraylist_blockid.add(bid);

                            }
                         /*   Toast.makeText(SignUpFarmer_2.this, "arraylist_blockid", Toast.LENGTH_SHORT).show();

                            for (int x=0;x<array.length();x++)
                            {
                                Toast.makeText(SignUpFarmer_2.this, arraylist_blockid.get(x), Toast.LENGTH_SHORT).show();
                            }*/

                          //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> blockList = new ArrayList<>(al);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                                    SignUpFarmer_2.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block.setAdapter(spinnerArrayAdapter2);



                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
              //  Toast.makeText(getApplicationContext(),"Error while loading",Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "Error while loading", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void load_gps_from_blockid(String str_block_id)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                      /*  progressDialog.dismiss();*/
                        try {
                             //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                            Log.d("onResponse:",s);
                            JSONObject jsonObject=null;
                            JSONArray array=null;

                            //Initialize arraylist
                            ArrayList<String> gpl=new ArrayList<String>();
                            arraylist_gp_id =new ArrayList<String>();

                            jsonObject=new JSONObject(s);
                            array=jsonObject.getJSONArray("gps");

                            // Toast.makeText(getApplicationContext(),String.valueOf(array.length()),Toast.LENGTH_LONG).show();



                            for(int k=0;k<array.length();k++)
                            {
                                String gid="",name="",b_id="";

                                JSONObject o=array.getJSONObject(k);
                                gid=o.getString("id");
                                name =o.getString("name");
                                b_id =o.getString("block_id");


                                gpl.add(name);
                                arraylist_gp_id.add(gid);
                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpFarmer_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp.setAdapter(spinnerArrayAdapter3);
                            gp.setVisibility(View.VISIBLE);
                            gpNotFound="No";

                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
               // Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "No GP Found", Snackbar.LENGTH_LONG);

                snackbar.show();

                str_gp_id="0"; // for passing in str_gp_id as zero
                gpNotFound="Yes";

                gp.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void addPhoto(View view) {
      //  responseTextView.setText("");
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO) {
            Uri imageUri = data.getData();
            imagefilePath = getPath(imageUri);
            imageView.setImageURI(imageUri);
            imageView.setVisibility(View.VISIBLE);
        }

    }

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };//,Video,Audio
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);//Video
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void onClickSubmit(View view)
    {
        strvillage=village.getText().toString().trim();
        str_iluc=iluc.getText().toString().trim();
        str_niluc=niluc.getText().toString().trim();
        if(strdistrict.equals("Select Your District"))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), strdistrict, Snackbar.LENGTH_LONG);

            snackbar.show();

        }
        else if(strblock.equals("Select Your Block"))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), strblock, Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else if (strgp.equals("Select Your GP"))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), strgp, Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else if(strvillage.equals(""))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Village name can't be empty", Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else if(str_iluc.equals(""))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Some field are be empty", Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else
        {

            new AsyncSignUpFarmer().execute();
        }
    }


    private  class AsyncSignUpFarmer extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

                pDialog = new ProgressDialog(SignUpFarmer_2.this);
                pDialog.setMessage("Registering...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {



            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("name",strname));
            cred.add(new BasicNameValuePair("fathers_name",strfathername ));
            cred.add(new BasicNameValuePair("email",stremail));
            cred.add(new BasicNameValuePair("password",strpass));
            cred.add(new BasicNameValuePair("phone",strmobile));
            cred.add(new BasicNameValuePair("district",str_district_id));//strdistrict
            cred.add(new BasicNameValuePair("block",str_block_id));//strblock
            if (gpNotFound.equals("Yes"))
            {
                cred.add(new BasicNameValuePair("gp","0"));//strgp
            }
            else
            {
                cred.add(new BasicNameValuePair("gp",str_gp_id));//strgp
            }
            cred.add(new BasicNameValuePair("village",strvillage));
            cred.add(new BasicNameValuePair("gender",strgender));
            cred.add(new BasicNameValuePair("caste",strcaste));
            //cred.add(new BasicNameValuePair("gp",str_gp_id));
            cred.add(new BasicNameValuePair("irrigated_land_under_cultivation",str_iluc));
            cred.add(new BasicNameValuePair("irrigation_source",str_irrisource));
            cred.add(new BasicNameValuePair("non_irrigated_land_under_cultivation",str_niluc));
            cred.add(new BasicNameValuePair("photo_url",null));
            cred.add(new BasicNameValuePair("any_special_crop_grown",null));

            cred.add(new BasicNameValuePair("machine_tool_own",null));
            cred.add(new BasicNameValuePair("machine_name",null));
            cred.add(new BasicNameValuePair("yr_of_purchase",null));
            cred.add(new BasicNameValuePair("make",null));
            cred.add(new BasicNameValuePair("model",null));
            cred.add(new BasicNameValuePair("machine_condition",null));
            cred.add(new BasicNameValuePair("photograph",null));

            cred.add(new BasicNameValuePair("last_crop_grown_in",null));
            cred.add(new BasicNameValuePair("area",null));
            cred.add(new BasicNameValuePair("soil_testing_done",null));
            cred.add(new BasicNameValuePair("crop",null));
            cred.add(new BasicNameValuePair("seed_seedling_variety",null));
            cred.add(new BasicNameValuePair("fertiliser_used",null));
            cred.add(new BasicNameValuePair("pesticide_used",null));
            cred.add(new BasicNameValuePair("machines_used",null));
            cred.add(new BasicNameValuePair("covered_by_insuarnce",null));
            cred.add(new BasicNameValuePair("lc_loan_taken",null));//  loan_taken

            cred.add(new BasicNameValuePair("fishery_activity",null));
            cred.add(new BasicNameValuePair("area_of_pond_acres",null));
            cred.add(new BasicNameValuePair("fingerling_from",null));
            cred.add(new BasicNameValuePair("trained_for_farming",null));
            cred.add(new BasicNameValuePair("fishery_medicine_used",null));//medicine_used
            cred.add(new BasicNameValuePair("fishery_lone_taken",null));//  lone_taken
            cred.add(new BasicNameValuePair("area_of_pond_own_lease",null));
            cred.add(new BasicNameValuePair("fishery_lone_taken_for",null));// lone_taken_for

            cred.add(new BasicNameValuePair("veterinary_activity",null));
            cred.add(new BasicNameValuePair("animal_type",null));
            cred.add(new BasicNameValuePair("covered_by_insurance",null));
            cred.add(new BasicNameValuePair("veterinary_lone_taken",null));//  lone_taken
            cred.add(new BasicNameValuePair("veterinary_lone_taken_for",null));//  lone_taken_for
            cred.add(new BasicNameValuePair("trained_for_farming",null));
            cred.add(new BasicNameValuePair("veterinary_medicine_used",null));// medicine_used
            cred.add(new BasicNameValuePair("animal_variety",null));
            cred.add(new BasicNameValuePair("animal_numbers",null));


            String urlRouteList=Config.farmersignup;
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
              //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Intent i = new Intent(SignUpFarmer_2.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                }, TIME_OUT);

       /* startActivity(new Intent(SignUpFarmer_2.this,Login.class));
                finish();*/
            }
            else {
                //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();
            }

        }



    }
}
