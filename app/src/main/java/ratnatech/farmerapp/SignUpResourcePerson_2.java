package ratnatech.farmerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.List;

public class SignUpResourcePerson_2 extends AppCompatActivity {

    private static final int TIME_OUT = 3000;
    LinearLayout pleasespecify,layoutgpn,layoutretired;
    Spinner spinnerstatus,spinnergpn;
    String strstatus="";

    private RadioGroup radioGroup,radioGroup2,radioGroup3,radioGroup4,radioGroup5;

    Spinner spinner_sub_group_of_cultivation,spinner_sub_group_of_machineryandtools;
    String  str_agriculture_expertisein_subgroup="";
    LinearLayout layout_spinner_horizontal,layout_spinner_horizontal_machineryandtools;

    LinearLayout layout_id_govt_type;
    Spinner spinner_govt_type;

    LinearLayout layout_id_cent_govt_depart_type;
    Spinner spinner_cent_govt_department_type;

    LinearLayout layout_cent_govt_depart_type_agriculture;
    LinearLayout layout_cent_govt_depart_type_horticulture;
    LinearLayout layout_cent_govt_depart_type_soilconservation;
    LinearLayout layout_cent_govt_depart_type_animalresources;
    LinearLayout layout_cent_govt_depart_type_fishery;

    Spinner spinner_cent_govt_department_type_soilconservation_expertisein_post_cultivationpractice_subgroup,
            spinner_cent_govt_department_type_soilconservation_expertisein_post_breed_subgroup,
            spinner_cent_govt_department_type_soilconservation_expertisein_post_feed_subgroup,
            spinner_cent_govt_department_type_soilconservation_expertisein_post_disease_subgroup,
            spinner_cent_govt_department_type_soilconservation_expertisein_post_housing_subgroup;

    RadioButton radioButtonCultivationPractice,radioButtonBreed,radioButtonFeed,radioButtonDisease,radioButtonHousing;

    TextView txt_govt_type;

    LinearLayout layout_inservice_private;
    LinearLayout layout_status_retired;
    LinearLayout layout_status_selfemployed;

    Spinner district,district2,district3,district4,district5,district6,district7,
            block,block2,block3,block4,block5,block6,block7,
            gp,gp2,gp3,gp4,gp5,gp6,gp7;

    private static final String URL_DATA = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/districts/29";
    private static final String URL_DATA2 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/blocks?district_id=";
    private static final String URL_DATA3 = "http://demo.ratnatechnology.co.in/farmerapp/api/commons/gps?block_id=";


    String strdistrict="Select Your District",strblock="Select Your Block",strgp="Select Your GP";
    String strdistrict2="Select Your District",strblock2="Select Your Block",strgp2="Select Your GP";
    String strdistrict3="Select Your District",strblock3="Select Your Block",strgp3="Select Your GP";
    String strdistrict4="Select Your District",strblock4="Select Your Block",strgp4="Select Your GP";
    String strdistrict5="Select Your District",strblock5="Select Your Block",strgp5="Select Your GP";
    String strdistrict6="Select Your District",strblock6="Select Your Block",strgp6="Select Your GP";
    String strdistrict7="Select Your District",strblock7="Select Your Block",strgp7="Select Your GP";

    String str_district_id="",str_block_id="",str_gp_id="";
    String str_district_id2="",str_block_id2="",str_gp_id2="";
    String str_district_id3="",str_block_id3="",str_gp_id3="";
    String str_district_id4="",str_block_id4="",str_gp_id4="";
    String str_district_id5="",str_block_id5="",str_gp_id5="";
    String str_district_id6="",str_block_id6="",str_gp_id6="";
    String str_district_id7="",str_block_id7="",str_gp_id7="";

    ArrayList<String> arraylist_districtid=new ArrayList<String>();
    ArrayList<String> arraylist_blockid;

    String strname="",strmobile="",stremail="",strpass="",strinservicetype="",str_govt_type="",str_depart_type="";
    String str_agriculture_post="",str_horticulture_post="",str_soilconservation_post="",str_animalresources_post="",str_fishery_post="";
    Spinner spinner_cent_govt_department_type_agriculture_post,spinner_cent_govt_department_type_horticulture_post,
            spinner_cent_govt_department_type_soilconservation_post,spinner_cent_govt_department_type_animalresources_post,
            spinner_cent_govt_department_type_fishery_post;
    EditText jurisdiction_retired,jurisdiction_inserviceprivate,jurisdiction_agriculture,jurisdiction_horticulture,
    jurisdiction_soilconservation,jurisdiction_animalresources,jurisdiction_fishery;

    String strjurisdiction="",str_jurisdiction_inserviceprivate="",str_jurisdiction_retired="",str_jurisdiction_agriculture="",
    str_jurisdiction_horticulture="",str_jurisdiction_soilconservation="",str_jurisdiction_animalresources="",str_jurisdiction_fishery="";

    String str_agriculture_expertisein="",str_horticulture_expertisein="",str_soilconservation_expertisein="",
            str_animalresources_expertisein="",str_fishery_expertisein="";

    EditText inservice_private_institutionname,inservice_private_post;
    String str_inservice_private_institutename="",str_inservice_private_post="",
            str_inservice_private_expertisein="",str_status_retired_expertisein="",str_status_selfemployed_expertisein="";

    Spinner spinner_expertise_in_private,spinner_status_retired_expertisein,spinner_status_selfemployed_expertisein;
    EditText status_others_pleasespecify;
    String str_status_others_pleasespecify="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_sign_up_resource_person_2);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
        }


        district=findViewById(R.id.district);district2=findViewById(R.id.district2);district3=findViewById(R.id.district3);district4=findViewById(R.id.district4);district5=findViewById(R.id.district5);district6=findViewById(R.id.district6);district7=findViewById(R.id.district7);
        block=findViewById(R.id.block);block2=findViewById(R.id.block2);block3=findViewById(R.id.block3);block4=findViewById(R.id.block4);block5=findViewById(R.id.block5); block6=findViewById(R.id.block6);block7=findViewById(R.id.block7);
        gp=findViewById(R.id.gp);gp2=findViewById(R.id.gp2);gp3=findViewById(R.id.gp3);gp4=findViewById(R.id.gp4); gp5=findViewById(R.id.gp5);gp6=findViewById(R.id.gp6); gp7=findViewById(R.id.gp7);

        txt_govt_type=findViewById(R.id.txt_id_govt_type);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup2=findViewById(R.id.radioGroup2);
        radioGroup3=findViewById(R.id.radioGroup3);
        radioGroup4 = (RadioGroup) findViewById(R.id.radioGroup4);
        radioGroup5=findViewById(R.id.radioGroup5);

        inservice_private_institutionname=findViewById(R.id.inservice_private_institutionname);
        inservice_private_post=findViewById(R.id.inservice_private_post);

        radioButtonCultivationPractice=findViewById(R.id.radioButtonCultivationPractice);
        radioButtonBreed=findViewById(R.id.radioButtonBreed);
        radioButtonFeed=findViewById(R.id.radioButtonFeed);
        radioButtonDisease=findViewById(R.id.radioButtonDisease);
        radioButtonHousing=findViewById(R.id.radioButtonHousing);
        status_others_pleasespecify=findViewById(R.id.status_others_pleasespecify);

        spinner_sub_group_of_cultivation=findViewById(R.id.spinner_id_sub_group_of_cultivation);
        spinner_sub_group_of_machineryandtools=findViewById(R.id.spinner_id_sub_group_of_machineryandtools);

        spinner_sub_group_of_cultivation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_agriculture_expertisein_subgroup=spinner_sub_group_of_cultivation.getSelectedItem().toString();

               // Toast.makeText(SignUpResourcePerson_2.this, str_agriculture_expertisein_subgroup, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        layout_spinner_horizontal=findViewById(R.id.layout_spinner_horizontal);
        layout_spinner_horizontal_machineryandtools=findViewById(R.id.layout_spinner_horizontal_machineryandtools);

        radioGroup.clearCheck();
        radioGroup4.clearCheck();

        layout_id_govt_type=findViewById(R.id.layout_id_govt_type);
        spinner_govt_type=findViewById(R.id.spinner_govt_type);

        layout_id_cent_govt_depart_type=findViewById(R.id.layout_id_cent_govt_depart_type);

        spinner_cent_govt_department_type=findViewById(R.id.spinner_cent_govt_department_type);

        layout_cent_govt_depart_type_agriculture=findViewById(R.id.layout_id_cent_govt_depart_type_agriculture);
        layout_cent_govt_depart_type_horticulture=findViewById(R.id.layout_id_cent_govt_depart_type_horticulture);
        layout_cent_govt_depart_type_soilconservation=findViewById(R.id.layout_id_cent_govt_depart_type_soilconservation);
        layout_cent_govt_depart_type_animalresources=findViewById(R.id.layout_id_cent_govt_depart_type_animalresources);
        layout_cent_govt_depart_type_fishery=findViewById(R.id.layout_id_cent_govt_depart_type_fishery);

        spinner_cent_govt_department_type_soilconservation_expertisein_post_cultivationpractice_subgroup=findViewById(R.id.spinner_cent_govt_department_type_soilconservation_expertisein_post_cultivationpractice_subgroup);
        spinner_cent_govt_department_type_soilconservation_expertisein_post_breed_subgroup=findViewById(R.id.spinner_cent_govt_department_type_soilconservation_expertisein_post_breed_subgroup);
        spinner_cent_govt_department_type_soilconservation_expertisein_post_feed_subgroup=findViewById(R.id.spinner_cent_govt_department_type_soilconservation_expertisein_post_feed_subgroup);
        spinner_cent_govt_department_type_soilconservation_expertisein_post_disease_subgroup=findViewById(R.id.spinner_cent_govt_department_type_soilconservation_expertisein_post_disease_subgroup);
        spinner_cent_govt_department_type_soilconservation_expertisein_post_housing_subgroup=findViewById(R.id.spinner_cent_govt_department_type_soilconservation_expertisein_post_housing_subgroup);

        layout_inservice_private=findViewById(R.id.layout_id_inservice_private);

        layout_status_retired=findViewById(R.id.layout_id_status_retired);
        layout_status_selfemployed=findViewById(R.id.layout_id_status_selfemployed);

        spinner_cent_govt_department_type_agriculture_post=findViewById(R.id.spinner_cent_govt_department_type_agriculture_post);
        spinner_cent_govt_department_type_horticulture_post=findViewById(R.id.spinner_cent_govt_department_type_horticulture_post);
        spinner_cent_govt_department_type_soilconservation_post=findViewById(R.id.spinner_cent_govt_department_type_soilconservation_post);
        spinner_cent_govt_department_type_animalresources_post=findViewById(R.id.spinner_cent_govt_department_type_animalresources_post);
        spinner_cent_govt_department_type_fishery_post=findViewById(R.id.spinner_cent_govt_department_type_fishery_post);

        jurisdiction_retired=findViewById(R.id.jurisdiction_retired);
        jurisdiction_inserviceprivate=findViewById(R.id.jurisdiction_inserviceprivate);
        jurisdiction_agriculture=findViewById(R.id.jurisdiction_agriculture);
        jurisdiction_horticulture=findViewById(R.id.jurisdiction_horticulture);
        jurisdiction_soilconservation=findViewById(R.id.jurisdiction_soilconservation);
        jurisdiction_animalresources=findViewById(R.id.jurisdiction_animalresources);
        jurisdiction_fishery=findViewById(R.id.jurisdiction_fishery);

        spinner_expertise_in_private=findViewById(R.id.spinner_expertise_in_private);
        spinner_status_selfemployed_expertisein=findViewById(R.id.spinner_status_selfemployed_expertisein);

        spinner_status_selfemployed_expertisein.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_status_selfemployed_expertisein=spinner_status_selfemployed_expertisein.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_status_retired_expertisein=findViewById(R.id.spinner_status_retired_expertisein);

        spinner_status_retired_expertisein.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_status_retired_expertisein=spinner_status_retired_expertisein.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {

                     str_agriculture_expertisein=rb.getText().toString();
                  //  Toast.makeText(SignUpResourcePerson_2.this, str_agriculture_expertisein, Toast.LENGTH_SHORT).show();

                    if(str_agriculture_expertisein.equals("Cultivation Practice"))
                    {
                        spinner_sub_group_of_cultivation.setVisibility(View.VISIBLE);
                        layout_spinner_horizontal.setVisibility(View.VISIBLE);

                        spinner_sub_group_of_machineryandtools.setVisibility(View.GONE);
                        layout_spinner_horizontal_machineryandtools.setVisibility(View.GONE);
                    }
                    else if(str_agriculture_expertisein.equals("Machinery and Tools"))
                    {
                        spinner_sub_group_of_machineryandtools.setVisibility(View.VISIBLE);
                        layout_spinner_horizontal_machineryandtools.setVisibility(View.VISIBLE);

                        spinner_sub_group_of_cultivation.setVisibility(View.GONE);
                        layout_spinner_horizontal.setVisibility(View.GONE);

                    }
                    else
                    {
                        spinner_sub_group_of_cultivation.setVisibility(View.GONE);
                        layout_spinner_horizontal.setVisibility(View.GONE);

                        spinner_sub_group_of_machineryandtools.setVisibility(View.GONE);
                        layout_spinner_horizontal_machineryandtools.setVisibility(View.GONE);
                    }
                  //  Toast.makeText(SignUpResourcePerson_2.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb2 = (RadioButton) group.findViewById(checkedId);
                if (null != rb2 && checkedId > -1) {
                   str_horticulture_expertisein=  rb2.getText().toString();


                }

            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb3 = (RadioButton) group.findViewById(checkedId);
                if (null != rb3 && checkedId > -1) {
                    str_soilconservation_expertisein=  rb3.getText().toString();


                }

            }
        });

            radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb4 = (RadioButton) group.findViewById(checkedId);
                    if (null != rb4 && checkedId > -1) {
                        str_animalresources_expertisein=  rb4.getText().toString();

                    }

                }
            });

        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb5 = (RadioButton) group.findViewById(checkedId);
                if (null != rb5 && checkedId > -1) {
                    str_fishery_expertisein=  rb5.getText().toString();

                }

            }
        });

            //GETTING THE RADIO BUTTON SELECTED DURING SIGNUP
            /*RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
            Toast.makeText(SignUpResourcePerson_2.this, rb.getText(), Toast.LENGTH_SHORT).show();
     */

        pleasespecify=findViewById(R.id.pleasespecify);
        layoutgpn=findViewById(R.id.layoutgpn);


        spinnerstatus=findViewById(R.id.spinnerstatus);
        spinnergpn=findViewById(R.id.spinnergpn);


        spinnerstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strstatus= spinnerstatus.getSelectedItem().toString();
                if(strstatus.equals("Others"))
                {
                    pleasespecify.setVisibility(View.VISIBLE);
                    layoutgpn.setVisibility(View.GONE);
                    layout_id_govt_type.setVisibility(View.GONE);
                    layout_status_retired.setVisibility(View.GONE);
                    layout_inservice_private.setVisibility(View.GONE);

                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);

                    layout_status_selfemployed.setVisibility(View.GONE);

                    layout_id_cent_govt_depart_type.setVisibility(View.GONE);
                }
                else if(strstatus.equals("In-Service"))
                {
                    pleasespecify.setVisibility(View.GONE);
                    layoutgpn.setVisibility(View.VISIBLE);
                    layout_status_retired.setVisibility(View.GONE);

                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);

                    layout_status_selfemployed.setVisibility(View.GONE);
                }
                else if(strstatus.equals("Retired"))
                {
                    layout_status_retired.setVisibility(View.VISIBLE);
                    pleasespecify.setVisibility(View.GONE);
                    layoutgpn.setVisibility(View.GONE);

                    layout_id_govt_type.setVisibility(View.GONE);

                    layout_id_cent_govt_depart_type.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);

                    layout_inservice_private.setVisibility(View.GONE);
                    layout_id_cent_govt_depart_type.setVisibility(View.GONE);

                    layout_status_selfemployed.setVisibility(View.GONE);

                }
                else if(strstatus.equals("Self-employed"))
                {
                    layout_status_selfemployed.setVisibility(View.VISIBLE);

                    pleasespecify.setVisibility(View.GONE);
                    layoutgpn.setVisibility(View.GONE);
                    layout_id_govt_type.setVisibility(View.GONE);
                    layout_status_retired.setVisibility(View.GONE);

                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);

                    layout_id_cent_govt_depart_type.setVisibility(View.GONE);
                    layout_inservice_private.setVisibility(View.GONE);
                }
                else if(strstatus.equals("Select Your Status"))
                {

                    pleasespecify.setVisibility(View.GONE);
                    layoutgpn.setVisibility(View.GONE);
                    layout_id_govt_type.setVisibility(View.GONE);
                    layout_status_retired.setVisibility(View.GONE);

                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);

                    layout_id_cent_govt_depart_type.setVisibility(View.GONE);
                    layout_status_selfemployed.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnergpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String strinservicetype= spinnergpn.getSelectedItem().toString();
                if(strinservicetype.equals("Govt"))
                {
                    layout_id_govt_type.setVisibility(View.VISIBLE);
                    layout_inservice_private.setVisibility(View.GONE);
                }

                else if(strinservicetype.equals("Private"))
                {
                    layout_inservice_private.setVisibility(View.VISIBLE);
                    layout_id_govt_type.setVisibility(View.GONE);
                    layout_id_cent_govt_depart_type.setVisibility(View.GONE);

                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                }
                else if(strinservicetype.equals("NGO"))
                {
                    layout_inservice_private.setVisibility(View.GONE);
                    layout_id_govt_type.setVisibility(View.GONE);
                    layout_id_cent_govt_depart_type.setVisibility(View.GONE);

                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_govt_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 str_govt_type=spinner_govt_type.getSelectedItem().toString();
                if (str_govt_type.equals("Central Govt"))
                {
                    // SHOW LAYOUT CENT GOVT DEPT
                    txt_govt_type.setText("CENTRAL-GOVT-DEPARTMENT-TYPE");
                    layout_id_cent_govt_depart_type.setVisibility(View.VISIBLE);
                }
                else if(str_govt_type.equals("State Govt"))
                {
                    //Toast.makeText(SignUpResourcePerson_2.this, "This layout for State Govt", Toast.LENGTH_SHORT).show();
                    txt_govt_type.setText("STATE-GOVT-DEPARTMENT-TYPE");
                    layout_id_cent_govt_depart_type.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_cent_govt_department_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 str_depart_type=spinner_cent_govt_department_type.getSelectedItem().toString();
                if(str_depart_type.equals("Agriculture"))
                {
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.VISIBLE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                }
                else if(str_depart_type.equals("Horticulture"))
                {
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.VISIBLE);
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                   layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                }
                else if(str_depart_type.equals("Soil Conservation"))
                {
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.VISIBLE);
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                }
                else if(str_depart_type.equals("Animal Resources"))
                {
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.VISIBLE);
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                }
                else if(str_depart_type.equals("Fishery"))
                {
                    layout_cent_govt_depart_type_fishery.setVisibility(View.VISIBLE);
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                }
                else if(str_depart_type.equals("Marketing Related"))
                {
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);

                }
                else
                {
                    layout_cent_govt_depart_type_agriculture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_horticulture.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_soilconservation.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_animalresources.setVisibility(View.GONE);
                    layout_cent_govt_depart_type_fishery.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_cent_govt_department_type_agriculture_post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_agriculture_post= spinner_cent_govt_department_type_agriculture_post.getSelectedItem().toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_cent_govt_department_type_horticulture_post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_horticulture_post= spinner_cent_govt_department_type_horticulture_post.getSelectedItem().toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_cent_govt_department_type_soilconservation_post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_soilconservation_post= spinner_cent_govt_department_type_soilconservation_post.getSelectedItem().toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_cent_govt_department_type_animalresources_post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_animalresources_post= spinner_cent_govt_department_type_animalresources_post.getSelectedItem().toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_cent_govt_department_type_fishery_post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_fishery_post= spinner_cent_govt_department_type_fishery_post.getSelectedItem().toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_expertise_in_private.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_inservice_private_expertisein= spinner_expertise_in_private.getSelectedItem().toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        load_district2_in_spinner();

        district2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict2= district2.getSelectedItem().toString();
                str_district_id2= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block2_from_a_district2(str_district_id2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock2= block2.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id2=arraylist_blockid.get(position);
                load_gps2_from_blockid2(str_block_id2);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp2= gp2.getSelectedItem().toString();
                str_gp_id2= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ----------------SECOND SPINNER-----------------------------
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
                str_gp_id= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ---------------- THIRD SPINNER-------------------------------------

        load_district3_in_spinner3();

        district3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict3= district3.getSelectedItem().toString();
                str_district_id3= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block3_from_a_district3(str_district_id3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock3= block3.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id3=arraylist_blockid.get(position);
                load_gps3_from_blockid3(str_block_id3);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp= gp.getSelectedItem().toString();
                str_gp_id= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ----------------FOURTH SPINNER-----------------------------------------

        load_district4_in_spinner4();

        district4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict4= district4.getSelectedItem().toString();
                str_district_id4= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block4_from_a_district4(str_district_id4);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock4= block4.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id4=arraylist_blockid.get(position);
                load_gps4_from_blockid4(str_block_id4);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp4= gp4.getSelectedItem().toString();
                str_gp_id4= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ------------------ FIFTH SPINNER ----------------------------------------

        load_district5_in_spinner5();

        district5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict5= district5.getSelectedItem().toString();
                str_district_id5= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block5_from_a_district5(str_district_id5);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock5= block5.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id5=arraylist_blockid.get(position);
                load_gps5_from_blockid5(str_block_id5);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp5= gp5.getSelectedItem().toString();
                str_gp_id5= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // ------------------- SIXTH SPINNER --------------------------------------
        load_district6_in_spinner6();

        district6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict6= district6.getSelectedItem().toString();
                str_district_id6= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block6_from_a_district6(str_district_id6);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock6= block6.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id6=arraylist_blockid.get(position);
                load_gps6_from_blockid6(str_block_id6);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp6= gp6.getSelectedItem().toString();
                str_gp_id6= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // ---------------------- SEVENTH SPINNER ----------------------------------
        load_district7_in_spinner7();

        district7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strdistrict7= district7.getSelectedItem().toString();
                str_district_id7= String.valueOf(position+1);


                //Toast.makeText(SignUpFarmer_2.this,"CLICKED AND PASSED districtid " +str_district_id , Toast.LENGTH_SHORT).show();
                load_block7_from_a_district7(str_district_id7);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        block7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strblock7= block7.getSelectedItem().toString();
                //str_block_id= String.valueOf(position+1);

                // TASK IS TO GET THE BLOCK_ID FROM DISTRICT_ID ..?
                str_block_id7=arraylist_blockid.get(position);
                load_gps7_from_blockid7(str_block_id7);//str_block_id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gp7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strgp7= gp7.getSelectedItem().toString();
                str_gp_id7= String.valueOf(position+1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }// END -OF ONCREATE


    // FIRST API CALL ----------------------------------------
    private void load_district2_in_spinner() {

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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district2.setAdapter(spinnerArrayAdapter);




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
    private void load_block2_from_a_district2(String districtid2)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid2,
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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block2.setAdapter(spinnerArrayAdapter2);



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

    private void load_gps2_from_blockid2(String str_block_id2)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id2,
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

                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp2.setAdapter(spinnerArrayAdapter3);
                            gp2.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                gp2.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // SECOND API CALL-----------------------------------------
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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item,districtList);

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
                         /*   Toast.makeText(SignUpFarmer_2.this, "arraylist_blockid", Toast.LENGTH_SHORT).show();

                            for (int x=0;x<array.length();x++)
                            {
                                Toast.makeText(SignUpFarmer_2.this, arraylist_blockid.get(x), Toast.LENGTH_SHORT).show();
                            }*/

                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> blockList = new ArrayList<>(al);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,blockList);

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
                Toast.makeText(getApplicationContext(),"Error while loading Block",Toast.LENGTH_LONG).show();
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

                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp.setAdapter(spinnerArrayAdapter3);
                            gp.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                gp.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    // THIRD API CALL ------------------------------------------

    private void load_district3_in_spinner3() {

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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district3.setAdapter(spinnerArrayAdapter);




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
    private void load_block3_from_a_district3(String districtid3)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid3,
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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block3.setAdapter(spinnerArrayAdapter2);



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

    private void load_gps3_from_blockid3(String str_block_id3)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id3,
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

                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp3.setAdapter(spinnerArrayAdapter3);
                            gp3.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                gp3.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // FOURTH API CALL ------------------------------------------

    private void load_district4_in_spinner4() {

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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district4.setAdapter(spinnerArrayAdapter);




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
    private void load_block4_from_a_district4(String districtid4)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid4,
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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block4.setAdapter(spinnerArrayAdapter2);



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

    private void load_gps4_from_blockid4(String str_block_id4)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id4,
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

                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp4.setAdapter(spinnerArrayAdapter3);
                            gp4.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                gp4.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // FIFTH API CALL -------------------------------------------
    private void load_district5_in_spinner5() {

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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district5.setAdapter(spinnerArrayAdapter);




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
    private void load_block5_from_a_district5(String districtid5)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid5,
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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block5.setAdapter(spinnerArrayAdapter2);



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

    private void load_gps5_from_blockid5(String str_block_id5)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id5,
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

                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp5.setAdapter(spinnerArrayAdapter3);
                            gp5.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                gp5.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // SIXTH API CALL ------------------------------------------

    private void load_district6_in_spinner6() {

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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district6.setAdapter(spinnerArrayAdapter);




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
    private void load_block6_from_a_district6(String districtid6)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid6,
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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block6.setAdapter(spinnerArrayAdapter2);



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

    private void load_gps6_from_blockid6(String str_block_id6)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id6,
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

                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp6.setAdapter(spinnerArrayAdapter3);
                            gp6.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                gp6.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // SEVENTH API CALL ---------------------------------------

    private void load_district7_in_spinner7() {

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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item,districtList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                            district7.setAdapter(spinnerArrayAdapter);




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
    private void load_block7_from_a_district7(String districtid7)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA2+districtid7,
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
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,blockList);

                            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item_2);
                            block7.setAdapter(spinnerArrayAdapter2);



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

    private void load_gps7_from_blockid7(String str_block_id7)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA3+str_block_id7,
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

                            }


                            //  final List<String> blockList = new ArrayList<>(Arrays.asList(strarrayblocks));
                            final List<String> gpList = new ArrayList<>(gpl);

                            // Initializing an ArrayAdapter
                            final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                                    SignUpResourcePerson_2.this,R.layout.spinner_item_2,gpList);

                            spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item_2);
                            gp7.setAdapter(spinnerArrayAdapter3);
                            gp7.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {//JSONException
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
               /* progressDialog.dismiss();*/
                Toast.makeText(getApplicationContext(),"No GP Found",Toast.LENGTH_LONG).show();
                gp7.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onClickSubmit(View view)
    {
        str_jurisdiction_inserviceprivate=jurisdiction_inserviceprivate.getText().toString().trim();
        str_jurisdiction_agriculture=jurisdiction_agriculture.getText().toString().trim();
        str_jurisdiction_horticulture=jurisdiction_horticulture.getText().toString().trim();
        str_jurisdiction_soilconservation=jurisdiction_soilconservation.getText().toString().trim();
        str_jurisdiction_animalresources=jurisdiction_animalresources.getText().toString().trim();
        str_jurisdiction_fishery=jurisdiction_fishery.getText().toString().trim();
        str_jurisdiction_retired=jurisdiction_retired.getText().toString().trim();

        if(strinservicetype.equals("Private"))
        {
            str_inservice_private_institutename=inservice_private_institutionname.getText().toString().trim();
            str_inservice_private_post=inservice_private_post.getText().toString().trim();
        }
        if(strstatus.equals("Others"))
        {
            str_status_others_pleasespecify=status_others_pleasespecify.getText().toString().trim();
        }
        new AsyncSignUpResourcePerson().execute();
    }

    private  class AsyncSignUpResourcePerson extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {



            pDialog = new ProgressDialog(SignUpResourcePerson_2.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {
            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();
            cred.add(new BasicNameValuePair("name",strname));
            cred.add(new BasicNameValuePair("phone",strmobile));
            cred.add(new BasicNameValuePair("password",strpass));
            cred.add(new BasicNameValuePair("email",stremail));
            cred.add(new BasicNameValuePair("status",strstatus));
            cred.add(new BasicNameValuePair("inservice_type",strinservicetype));
            cred.add(new BasicNameValuePair("govt_type",str_govt_type));
            if(strstatus.equals("Others")||strstatus.equals("Retired")||strstatus.equals("Self-employed"))
            {
                cred.add(new BasicNameValuePair("depart_type",""));
            }

            else
                {
                    cred.add(new BasicNameValuePair("depart_type",str_depart_type));
            }

            cred.add(new BasicNameValuePair("agriculture_post",str_agriculture_post));
            cred.add(new BasicNameValuePair("agriculture_expertisein",str_agriculture_expertisein));
            cred.add(new BasicNameValuePair("agriculture_expertisein_subgroup",str_agriculture_expertisein_subgroup));
            cred.add(new BasicNameValuePair("horticulture_post",str_horticulture_post));
            cred.add(new BasicNameValuePair("horticulture_expertisein",str_horticulture_expertisein));
            cred.add(new BasicNameValuePair("soilconservation_post",str_soilconservation_post));
            cred.add(new BasicNameValuePair("soilconservation_expertisein",str_soilconservation_expertisein));
            cred.add(new BasicNameValuePair("animalresources_post",str_animalresources_post));
            cred.add(new BasicNameValuePair("animalresources_expertisein",str_animalresources_expertisein));
            cred.add(new BasicNameValuePair("animalresources_expertiseincultivationpractice_subgroup",""));
            cred.add(new BasicNameValuePair("animalresources_expertiseinbreed_subgroup",""));
            cred.add(new BasicNameValuePair("animalresources_expertiseinfeed_subgroup",""));
            cred.add(new BasicNameValuePair("animalresources_expertiseindisease_subgroup",""));
            cred.add(new BasicNameValuePair("animalresources_expertiseinhousing_subgroup",""));
            cred.add(new BasicNameValuePair("fishery_post",str_fishery_post));//strdistrict
            cred.add(new BasicNameValuePair("fishery_expertisein",str_fishery_expertisein));//strblock
            cred.add(new BasicNameValuePair("fishery_expertiseincp_subgroup",""));
            cred.add(new BasicNameValuePair("fishery_expertiseinfeed_subgroup",""));
            cred.add(new BasicNameValuePair("fishery_expertiseindisease_subgroup",""));
            cred.add(new BasicNameValuePair("fishery_expertiseinprocessing_subgroup",""));
            cred.add(new BasicNameValuePair("inservice_private_institution_name",str_inservice_private_institutename));
            cred.add(new BasicNameValuePair("inservice_private_post",str_inservice_private_post));
            cred.add(new BasicNameValuePair("inservice_private_expertisein",str_inservice_private_expertisein));
            cred.add(new BasicNameValuePair("retired_expertisein",str_status_retired_expertisein));
            cred.add(new BasicNameValuePair("selfemployed_expertisein",str_status_selfemployed_expertisein));
            cred.add(new BasicNameValuePair("status_others_specify",str_status_others_pleasespecify));

            if(strstatus.equals("Retired"))
            {
                cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction_retired));
            }
           else if(strinservicetype.equals("Private"))
            {
                cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction_inserviceprivate));
            }
            else if(str_depart_type.equals("Agriculture"))
            {
                cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction_agriculture));
            }
            else if(str_depart_type.equals("Horticulture"))
            {
                cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction_horticulture));
            }

            else if(str_depart_type.equals("Soil Conservation"))
            {
                cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction_soilconservation));
            }
            else if(str_depart_type.equals("Animal Resources"))
            {
                cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction_animalresources));
            }

            else if(str_depart_type.equals("Fishery"))
            {
                cred.add(new BasicNameValuePair("jurisdiction",str_jurisdiction_fishery));
            }

            cred.add(new BasicNameValuePair("district",""));
            cred.add(new BasicNameValuePair("block",""));
            cred.add(new BasicNameValuePair("gp",""));

            String urlRouteList=Config.resourceperson_signup;
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
                //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

                snackbar.show();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Intent i = new Intent(SignUpResourcePerson_2.this, Login.class);
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
