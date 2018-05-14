package ratnatech.farmerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StateGovtAgriExtOff extends AppCompatActivity {




    RadioGroup rgAgrilPost;
    String str_agril_post="";
    LinearLayout farmerregdlayout;


    EditText gp;


    //----  district , block multiselect
    Spinner district;
    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";
    private static final String URL_DATA3 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/gps?block_id=";

    ArrayList<String> arraylist_districtid = new ArrayList<String>();
    ArrayList<String> arraylist_blockid;
    String strdistrict,str_district_id = "";

    CheckBox checkBox;
    LinearLayout linearMain;

    String  strname,strmobile,stremail,strpass,strgender,str_dept="";
    int TIME_OUT=6;

    EditText post,jurisdiction;
    String blockmultiselect="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_state_govt_agri_ext_off);
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



        linearMain=findViewById(R.id.linearMain);
        district=findViewById(R.id.district);

        rgAgrilPost=findViewById(R.id.rgAgrilPost);
        farmerregdlayout=findViewById(R.id.farmerregdlayout);
        district=(Spinner) findViewById(R.id.district);
       // block=findViewById(R.id.block);
        gp=findViewById(R.id.gp);
        jurisdiction=findViewById(R.id.jurisdiction);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            strname = bundle.getString("NAME");
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
            strgender = bundle.getString("GENDER");
        }


        load_district_in_spinner();

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


        rgAgrilPost.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb2 = (RadioButton) group.findViewById(checkedId);
                if (null != rb2 && checkedId > -1) {
                    str_agril_post=  rb2.getText().toString();
                      Toast.makeText(StateGovtAgriExtOff.this, str_agril_post, Toast.LENGTH_SHORT).show();

                    // POP up alert
                    if (str_agril_post.equals("Krushak Sathi")||str_agril_post.equals("VAW"))
                    {


                        AlertDialog.Builder builder = new AlertDialog.Builder(StateGovtAgriExtOff.this);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder.setMessage("Are you also a farmer ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        farmerregdlayout.setVisibility(View.VISIBLE);

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        farmerregdlayout.setVisibility(View.GONE);
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Please Let Us Know");
                        alert.show();
                    }
                    else if(str_agril_post.equals("Mechanic")||str_agril_post.equals("Fitter")||str_agril_post.equals("OTHERS"))
                    {
                        farmerregdlayout.setVisibility(View.GONE);

                    }
                    else if(str_agril_post.equals("AAO")||str_agril_post.equals("BAO")||str_agril_post.equals("DAO")||
                            str_agril_post.equals("AAE")||str_agril_post.equals("SMS"))
                    {

                        farmerregdlayout.setVisibility(View.GONE);

                    }
                    else
                    {
                        farmerregdlayout.setVisibility(View.GONE);

                    }


                }

            }
        });


    }// END OF ON-CREATE

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
                                    StateGovtAgriExtOff.this,R.layout.spinner_item,districtList);

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
                Toast.makeText(getApplicationContext(),"Error while loading District",Toast.LENGTH_LONG).show();
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

                            // Toast.makeText(SignUpFarmer_2.this, "arraylist_blockid", Toast.LENGTH_SHORT).show();

                          /*  for (int x=0;x<array.length();x++)
                            {
                                Toast.makeText(CentralGovtExtenOff.this, al.get(x), Toast.LENGTH_SHORT).show();
                            }
*/
                            for(int i=0;i<al.size();i++)
                            {
                                checkBox=new CheckBox(StateGovtAgriExtOff.this);
                                checkBox.setId(i);
                                checkBox.setText(al.get(i));
                                checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
                                linearMain.addView(checkBox);
                            }



                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"Error while loading Block",Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    View.OnClickListener getOnClickDoSomething(final Button button)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(CentralGovtExtenOff.this, button.getText().toString(), Toast.LENGTH_SHORT).show();
                blockmultiselect=blockmultiselect+","+button.getText().toString();
                Toast.makeText(StateGovtAgriExtOff.this, blockmultiselect, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void onClickCP(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Cultivation Practise");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());

        startActivity(intent);
    }

    public void onClickSeed(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Seed");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }

    public void onClickDiseasePest(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Disease and Pest");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }

    public void onClickTraining(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Training");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }

    public void onClickProcessing(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Processing");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }

    public void onClickOther(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Other");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }

    public void onClickSoil(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Soil");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }


    public void onClickFertiliser(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Fertiliser");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }

    public void onClickIrrigation(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Irrigation");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);

    }
    public void onClickInsurance(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Insurance");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);

    }

    public void onClickMandTool(View view) {
        Intent intent = new Intent(this, PopUpExpertiseAgril.class);
        intent.putExtra("EXP_IN", "Machinery and Tools");
        intent.putExtra("POST", str_agril_post);
        intent.putExtra("NAME",strname);
        intent.putExtra("MOBILE",strmobile);
        intent.putExtra("EMAIL",stremail);
        intent.putExtra("PASSWORD",strpass);
        intent.putExtra("GENDER",strgender);
        intent.putExtra("GOVT_TYPE","State Government");
        intent.putExtra("DEPT_TYPE","Agril");
        intent.putExtra("JURISDICTION",jurisdiction.getText().toString().trim());
        intent.putExtra("DISTRICT",str_district_id);
        intent.putExtra("BLOCK",blockmultiselect);
        intent.putExtra("GP",gp.getText().toString().trim());
        startActivity(intent);
    }

}
