package ratnatech.farmerapp.EditProfileActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.CheckBox;
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
import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ratnatech.farmerapp.Config;
import ratnatech.farmerapp.CustomHttpClient;
import ratnatech.farmerapp.Login;
import ratnatech.farmerapp.NavDrawer.NavDrawer;
import ratnatech.farmerapp.PopUpAgrilToolImplement;
import ratnatech.farmerapp.PopUpMachineTool;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.SignUpFarmer_2;

public class EditProfileFarmer2 extends AppCompatActivity {

    Button tractorID,powertillerID,rotavatorID,ploughID,ridgerID,levellerID,seeddrillID,transplanterID,weederID,sprayerID,othersID,
            reaperID,thresherID,cleanerID,graderID,combineID,pumpsID,parboilingunitID,ricemillID,decorticatorID,toolfruitvegID;
    int TIME_OUT=6;
    LinearLayout yesfisherylayout,yeslayoutvactivity;
    RadioGroup rgMachineTool,rgFertiliserUsed,rgPesticideUsed,radioGroupSoilTesting,rgFishery,rgftrain,rgmedicine,rgveterinary_lone_taken,
            rgMachinesUsed,rgSpecialCrop,rgCoveredByInsurance,rgLoanTaken,rgOwnLease,rgloan,rgfveterinary,rgvinsurance,
            rgveterinary_trained_for_farming,rgveterinary_medicine_used;
    String str_option="";
    LinearLayout layoutcheckbox;
    String str_machinetool_use_yes_no,str_specialcrop_yes_no="",str_trained_for_farming_fishery="";
    Spinner spinner_rabi_kharif,spinner_crop,spinner_seedling_variety,spinner_machine_tool_rabikharif,spinnerspecialcrop,
            spinnerfingerlingfrom,spinner_lone_taken_for;

    String machine_tool_own="";

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String user_id;
    EditText edittextfisheryareaoffpond;
    String machine_name,last_crop_grown_in,area,soil_testing_done,crop,seed_seedling_variety,fertiliser_used,pesticide_used,fingerling_from,
            machines_used,covered_by_insuarnce,loan_taken,any_special_crop_grown,fishery_activity,area_of_pond_acres,area_of_pond_own_lease,
            trained_for_farming,medicine_used,lone_taken_fishery,lone_taken_for,veterinary_activity,animal_type,
            covered_by_insurance,animal_variety,animal_numbers,veterinary_trained_for_farming,veterinary_medicine_used;
    int position,position2,position3,position4,position5,position6,position7,position8,position9;

    String str_animal_type;

    String str_variety_of_cows,str_variety_of_goat,str_variety_of_chick,str_variety_of_other_animal;
    String str_machines,str_veterinary_lone_taken,str_veterinary_loan_taken_for;

    LinearLayout machinesTools;
    TextView owned_machines_tools;
    String  strname,strfathersname,strmobile,stremail,strpass,str_gender,str_caste,districtid,blockid,gpid,
            villagename,irrigated_land_under_cultivation,irrigation_source,non_irrigated_land_under_cultivation;

    String str_spinner_rabi_kharif,str_spinner_in_acres,str_soil_testing_done,str_crop,str_seedling_variety,strLoanTaken,
            strFertiliserUsed,strPesticideUsed,strMachinesUsed,str_machine_tool_rabikharif,strCoveredByInsurance;
    Spinner spinner_in_acres,spinner_veterinary_loan_taken_for;
    String str_any_special_crop_grown,strOwnLease,str_fingerlingfrom,str_fishery_medicine_used;
    String veterinary_lone_taken,veterinary_lone_taken_for,str_fishery_lone_taken,str_fishery_lone_taken_for,
            str_veterinary_activity,str_veterinary_insurance,str_veterinary_trained_for_farming,str_veterinary_medicine_used;

    EditText no_of_cows,no_of_goat,variety_of_chick,no_of_other_animal;
    EditText variety_of_cows,variety_of_goat,no_of_chick,variety_of_other_animal;
    String no_of_animal,variety_of_animal;

    TextView txtanimaltype,txtanimalnumber,txtanimalvariety;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_edit_profile);

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
        getSupportActionBar().setTitle("Update Profile- Farmer");




        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {

            strname = bundle.getString("NAME");
            strfathersname = bundle.getString("FATHERNAME");
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
            str_gender = bundle.getString("GENDER");
            str_caste = bundle.getString("CASTE");
            districtid = bundle.getString("DISTRICTID");
            blockid = bundle.getString("BLOCKID");
            gpid = bundle.getString("GPID");
            villagename = bundle.getString("VILLAGE");
            irrigated_land_under_cultivation = bundle.getString("ILUC");
            irrigation_source = bundle.getString("SOURCE_OF_IRRIGATION");
            non_irrigated_land_under_cultivation = bundle.getString("NILUC");
        }

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        user_id=sharedpreferences.getString("FLAG", null);
        Log.d("user_id:",user_id);
        editor.commit(); // commit changes

        radioGroupSoilTesting=findViewById(R.id.radioGroupSoilTesting);
        spinner_in_acres=findViewById(R.id.spinner_in_acres);
        machinesTools=findViewById(R.id.machinesTools);
        owned_machines_tools=findViewById(R.id.owned_machines_tools);
        tractorID=findViewById(R.id.tractorID);powertillerID=findViewById(R.id.powertillerID);rotavatorID=findViewById(R.id.rotavatorID);
        ploughID=findViewById(R.id.ploughID);ridgerID=findViewById(R.id.ridgerID);levellerID=findViewById(R.id.levellerID);seeddrillID=findViewById(R.id.seeddrillID);
        transplanterID=findViewById(R.id.transplanterID);weederID=findViewById(R.id.weederID);sprayerID=findViewById(R.id.sprayerID);
        othersID=findViewById(R.id.othersID);reaperID=findViewById(R.id.reaperID);thresherID=findViewById(R.id.thresherID);cleanerID=findViewById(R.id.cleanerID);
        graderID=findViewById(R.id.graderID);combineID=findViewById(R.id.combineID);pumpsID=findViewById(R.id.pumpsID);
        parboilingunitID=findViewById(R.id.parboilingunitID);ricemillID=findViewById(R.id.ricemillID);decorticatorID=findViewById(R.id.decorticatorID);
        toolfruitvegID=findViewById(R.id.toolfruitvegID);

        rgFishery=findViewById(R.id.rgFishery);
        rgfveterinary=findViewById(R.id.rgfveterinary);
        yesfisherylayout=findViewById(R.id.yesfisherylayout);
        yeslayoutvactivity=findViewById(R.id.yeslayoutvactivity);
        layoutcheckbox=findViewById(R.id.layoutcheckbox);
        rgMachineTool=findViewById(R.id.rgMachineTool);
        spinner_rabi_kharif=findViewById(R.id.spinner_rabi_kharif);
        spinner_in_acres=findViewById(R.id.spinner_in_acres);
        spinner_crop=findViewById(R.id.spinner_crop);
        spinner_seedling_variety=findViewById(R.id.spinner_seedling_variety);
        rgFertiliserUsed=findViewById(R.id.rgFertiliserUsed);
        rgPesticideUsed=findViewById(R.id.rgPesticideUsed);
        rgMachinesUsed=findViewById(R.id.rgMachinesUsed);
        spinnerspecialcrop=findViewById(R.id.spinnerspecialcrop);
        spinner_machine_tool_rabikharif=findViewById(R.id.spinner_machine_tool_rabikharif);
        rgCoveredByInsurance=findViewById(R.id.rgCoveredByInsurance);
        rgLoanTaken=findViewById(R.id.rgLoanTaken);
        edittextfisheryareaoffpond=findViewById(R.id.edittextfisheryareaoffpond);
        rgSpecialCrop=findViewById(R.id.rgSpecialCrop);
        spinnerfingerlingfrom=findViewById(R.id.spinnerfingerlingfrom);
        rgftrain=findViewById(R.id.rgftrain);
        rgmedicine=findViewById(R.id.rgmedicine);
        rgOwnLease=findViewById(R.id.rgOwnLease);
        rgloan=findViewById(R.id.rgloan);
        spinner_lone_taken_for=findViewById(R.id.spinner_lone_taken_for);
        rgfveterinary=findViewById(R.id.rgfveterinary);
        rgvinsurance=findViewById(R.id.rgvinsurance);
        no_of_cows=findViewById(R.id.no_of_cows);
        no_of_goat=findViewById(R.id.no_of_goat);
        no_of_chick=findViewById(R.id.no_of_chick);
        no_of_other_animal=findViewById(R.id.no_of_other_animal);

        variety_of_cows=findViewById(R.id.variety_of_cows);
        variety_of_goat=findViewById(R.id.variety_of_goat);
        variety_of_chick=findViewById(R.id.variety_of_chick);
        variety_of_other_animal=findViewById(R.id.variety_of_other_animal);
        rgveterinary_lone_taken=findViewById(R.id.rgveterinary_lone_taken);
        spinner_veterinary_loan_taken_for=findViewById(R.id.spinner_veterinary_loan_taken_for);
        rgveterinary_trained_for_farming=findViewById(R.id.rgveterinary_trained_for_farming);
        rgveterinary_medicine_used=findViewById(R.id.rgveterinary_medicine_used);

        no_of_cows=findViewById(R.id.no_of_cows);
        no_of_goat=findViewById(R.id.no_of_goat);
        no_of_chick=findViewById(R.id.no_of_chick);
        no_of_other_animal=findViewById(R.id.no_of_other_animal);
        variety_of_cows=findViewById(R.id.variety_of_cows);
        variety_of_goat=findViewById(R.id.variety_of_goat);
        variety_of_chick=findViewById(R.id.variety_of_chick);
        variety_of_other_animal=findViewById(R.id.variety_of_other_animal);
        txtanimaltype=findViewById(R.id.txtanimaltype);
        txtanimalnumber=findViewById(R.id.txtanimalnumber);
        txtanimalvariety=findViewById(R.id.txtanimalvariety);


        rgveterinary_medicine_used.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_veterinary_medicine_used=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgveterinary_trained_for_farming.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_veterinary_trained_for_farming=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgveterinary_lone_taken.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_veterinary_lone_taken=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });
        rgvinsurance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_veterinary_insurance=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgloan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_fishery_lone_taken=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgmedicine.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_fishery_medicine_used=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgftrain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_trained_for_farming_fishery=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgOwnLease.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    strOwnLease=rb.getText().toString();

                    //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgLoanTaken.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    strLoanTaken=rb.getText().toString();

                  //  Toast.makeText(EditProfileFarmer2.this, strLoanTaken, Toast.LENGTH_SHORT).show();


                }

            }
        });
        rgCoveredByInsurance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    strCoveredByInsurance=rb.getText().toString();
               //     Toast.makeText(EditProfileFarmer2.this, strCoveredByInsurance, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgMachinesUsed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    strMachinesUsed=rb.getText().toString();
                  //  Toast.makeText(EditProfileFarmer2.this, strMachinesUsed, Toast.LENGTH_SHORT).show();


                }

            }
        });


        rgPesticideUsed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    strPesticideUsed=rb.getText().toString();
                   // Toast.makeText(EditProfileFarmer2.this, strPesticideUsed, Toast.LENGTH_SHORT).show();


                }

            }
        });



        rgFertiliserUsed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    strFertiliserUsed=rb.getText().toString();
                //    Toast.makeText(EditProfileFarmer2.this, strFertiliserUsed, Toast.LENGTH_SHORT).show();


                }

            }
        });

        rgSpecialCrop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_specialcrop_yes_no=rb.getText().toString();
                  //  Toast.makeText(EditProfileFarmer2.this, str_specialcrop_yes_no, Toast.LENGTH_SHORT).show();


                    if (str_specialcrop_yes_no.equals("Yes"))
                    {
                        spinnerspecialcrop.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        spinnerspecialcrop.setVisibility(View.GONE);
                    }
                }

            }
        });

        rgMachineTool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_machinetool_use_yes_no=rb.getText().toString();
                   // Toast.makeText(EditProfileFarmer2.this, str_machinetool_use_yes_no, Toast.LENGTH_SHORT).show();


                    if (str_machinetool_use_yes_no.equals("Yes"))
                    {
                        //yesfisherylayout.setVisibility(View.VISIBLE);
                       // layoutcheckbox.setVisibility(View.VISIBLE);
                        machinesTools.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        //yesfisherylayout.setVisibility(View.GONE);
                      //  layoutcheckbox.setVisibility(View.GONE);
                        machinesTools.setVisibility(View.GONE);
                    }
                }

            }
        });

        rgFishery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_option=rb.getText().toString();
                  //  Toast.makeText(EditProfileFarmer2.this, str_option, Toast.LENGTH_SHORT).show();


                    if (str_option.equals("Yes"))
                    {
                        yesfisherylayout.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        yesfisherylayout.setVisibility(View.GONE);
                    }
                }

            }
        });


        rgfveterinary.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_veterinary_activity=rb.getText().toString();
                 //   Toast.makeText(EditProfileFarmer2.this, str_voption, Toast.LENGTH_SHORT).show();


                    if (str_veterinary_activity.equals("Yes"))
                    {
                        yeslayoutvactivity.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        yeslayoutvactivity.setVisibility(View.GONE);
                    }
                }

            }
        });


        radioGroupSoilTesting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_soil_testing_done=rb.getText().toString();
                   // Toast.makeText(EditProfileFarmer2.this, str_soil_testing_done, Toast.LENGTH_SHORT).show();

                }

            }
        });

        HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        checkBoxMap.put((CheckBox) findViewById(R.id.cowID), "Cow");
        checkBoxMap.put((CheckBox) findViewById(R.id.goatID), "Goat");
        checkBoxMap.put((CheckBox) findViewById(R.id.chickID), "Chick");
        checkBoxMap.put((CheckBox) findViewById(R.id.otherID), "Other");

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
                        str_animal_type= sb.toString();
                        System.out.println(sb.toString());
                         Toast.makeText(EditProfileFarmer2.this,str_animal_type,Toast.LENGTH_LONG).show();
                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });

        HashMap<CheckBox, String> checkBoxMap2 = new HashMap<>();
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox1), "Tractor");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox2), "Power Tiller");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox3), "Rotavator");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox4), "Plough");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox5), "Ridger");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox6), "Leveller");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox7), "Seed Drill");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox8), "Transplanter");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox9), "Weeder");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox10), "Sprayer");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox11), "Reaper");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox12), "Thresher");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox13), "Cleaner");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox14), "Grader");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox15), "Combine");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox16), "Pumps");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox17), "Parboiling Unit");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox18), "Rice Mill");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox19), "Decorticator");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox20), "Tools for fruits and vegetable");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox21), "Others");



        CheckBoxGroup<String> checkBoxGroup2 = new CheckBoxGroup<>(checkBoxMap2,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values2) {
                        // Convert the ArrayList into a String.

                        StringBuilder sb2 = new StringBuilder();
                        for (String s2 : values2)
                        {
                            sb2.append(s2);
                            sb2.append(",");
                        }
                        str_machines= sb2.toString();
                        System.out.println(sb2.toString());
                        Toast.makeText(EditProfileFarmer2.this,str_machines,Toast.LENGTH_LONG).show();
                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });

        spinner_veterinary_loan_taken_for.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_veterinary_loan_taken_for= spinner_veterinary_loan_taken_for.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_lone_taken_for.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_fishery_lone_taken_for= spinner_lone_taken_for.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerfingerlingfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_fingerlingfrom= spinnerfingerlingfrom.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerspecialcrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_any_special_crop_grown= spinnerspecialcrop.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_machine_tool_rabikharif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_machine_tool_rabikharif= spinner_machine_tool_rabikharif.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_rabi_kharif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_spinner_rabi_kharif= spinner_rabi_kharif.getSelectedItem().toString();
               // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_in_acres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_spinner_in_acres= spinner_in_acres.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_crop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_crop= spinner_crop.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_seedling_variety.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_seedling_variety= spinner_seedling_variety.getSelectedItem().toString();
                // str_district_id= String.valueOf(position+1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Function to get data
        getData();
    }

    public void getData()
    {
        String url ="http://demo.ratnatechnology.co.in/farmerapp/api/user/getFarmer?user_id="+user_id;
        //String url="https://demo.api-platform.com/books";
        //creating a string request to send request to the url


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String getresponse) {


                        try {

                            Log.d( "onResponse: ",getresponse);
                            JSONObject jsonObject=new JSONObject(getresponse);

                            JSONArray array=jsonObject.getJSONArray("data");

                            for(int i=0;i<array.length();i++) {


                                JSONObject o = array.getJSONObject(i);
                                machine_tool_own = o.getString("machine_tool_own");
                                if(machine_tool_own.equals("Yes"))
                                    rgMachineTool.check(R.id.radioButtonMachineryToolsYes);
                                else
                                    rgMachineTool.check(R.id.radioButtonMachineryToolsNo);


                                machine_name=o.getString("machine_name");
                                if(machine_name.equals(""))
                                    owned_machines_tools.setText("---");
                                else
                                owned_machines_tools.setText(machine_name);

                                area=o.getString("area");
                                if(area.equals("1"))
                                    position7=0;
                                else if(area.equals("1-2"))
                                    position7=1;
                                else if(area.equals("2-5"))
                                    position7=2;
                                else if(area.equals("more than 5"))
                                    position7=3;
                                spinner_in_acres.setSelection(position7);

                                last_crop_grown_in=o.getString("last_crop_grown_in");
                                if(last_crop_grown_in.equals("Rabi"))
                                    position=0;
                                else if(last_crop_grown_in.equals("Kharif"))
                                    position=1;
                                spinner_rabi_kharif.setSelection(position);


                                soil_testing_done=o.getString("soil_testing_done");
                                if(soil_testing_done.equals("Yes"))
                                    radioGroupSoilTesting.check(R.id.radioButtonSoilTestingDoneYes);
                                else if(soil_testing_done.equals("No"))
                                    radioGroupSoilTesting.check(R.id.radioButtonSoilTestingDoneNo);

                                crop=o.getString("crop");
                                //spinner get
                                if(crop.equals("Paddy"))
                                    position2=0;
                                else if(crop.equals("Wheat"))
                                    position2=1;
                                else if(crop.equals("Green Gram"))
                                    position2=2;
                                else if(crop.equals("Black Gram"))
                                    position2=3;
                                else if(crop.equals("Arhar"))
                                    position2=4;
                                else if(crop.equals("Vegetable"))
                                    position2=5;
                                else if(crop.equals("Fruit"))
                                    position2=6;
                                spinner_crop.setSelection(position2);


                                seed_seedling_variety=o.getString("seed_seedling_variety");
                                //spinner get
                                if(seed_seedling_variety.equals("Hybrid"))
                                    position3=0;
                                else if(seed_seedling_variety.equals("Hi-Yielding"))
                                    position3=1;
                                else if(seed_seedling_variety.equals("Local"))
                                    position3=2;
                                spinner_seedling_variety.setSelection(position3);


                                fertiliser_used=o.getString("fertiliser_used");
                                if(fertiliser_used.equals("Yes"))
                                    rgFertiliserUsed.check(R.id.radioButtonFertilisersUsedYes);
                                else if(fertiliser_used.equals("No"))
                                    rgFertiliserUsed.check(R.id.radioButtonFertilisersUsedNo);


                                pesticide_used=o.getString("pesticide_used");
                                if(pesticide_used.equals("Yes"))
                                    rgPesticideUsed.check(R.id.radioButtonPesticidesUsedYes);
                                else if(pesticide_used.equals("No"))
                                    rgPesticideUsed.check(R.id.radioButtonPesticidesUsedNo);


                                machines_used=o.getString("lcp_machines_used"); //  machines_used
                                if(machines_used.equals("Own"))
                                    rgMachinesUsed.check(R.id.radioButtonOwnMachineUsed);
                                else if(machines_used.equals("On Hire"))
                                    rgMachinesUsed.check(R.id.radioButtonOnHireMachinesUsed);

                                covered_by_insuarnce=o.getString("covered_by_insuarnce");
                                if(covered_by_insuarnce.equals("Yes"))
                                    rgCoveredByInsurance.check(R.id.radioButtonInsuranceYes);
                                else if(covered_by_insuarnce.equals("No"))
                                    rgCoveredByInsurance.check(R.id.radioButtonInsuranceNo);

                                loan_taken=o.getString("lcp_loan_taken"); //  loan_taken
                                if(loan_taken.equals("Yes"))
                                    rgLoanTaken.check(R.id.radioButtonLoanTakenYes);
                                else if(loan_taken.equals("No"))
                                    rgLoanTaken.check(R.id.radioButtonLoanTakenNo);

                                any_special_crop_grown=o.getString("any_special_crop_grown");
                               /* if(any_special_crop_grown.equals("Maize"))
                                    position8=0;
                                else if(any_special_crop_grown.equals("Cotton"))
                                    position8=1;
                                else if(any_special_crop_grown.equals("Palm"))
                                    position8=2;
                                else if(any_special_crop_grown.equals("Spices"))
                                    position8=3;*/




                                if(any_special_crop_grown.equals("No"))
                                    rgSpecialCrop.check(R.id.radioButtonSpecialCropGrownNo);
                                else
                                if(any_special_crop_grown.equals(""))
                                {

                                }
                                else
                                {
                                    rgSpecialCrop.check(R.id.radioButtonSpecialCropGrownYes);
                                    //spinner get
                                    if(any_special_crop_grown.equals("Maize"))
                                        position4=0;
                                    else if(any_special_crop_grown.equals("Cotton"))
                                        position4=1;
                                    else if(any_special_crop_grown.equals("Palm"))
                                        position4=2;
                                    else if(any_special_crop_grown.equals("Spices"))
                                        position4=3;

                                    spinnerspecialcrop.setSelection(position4);
                                }



                                fishery_activity=o.getString("fishery_activity");//yes no
                                if(fishery_activity.equals("Yes"))
                                    rgFishery.check(R.id.fyes);
                                else if(fishery_activity.equals("No"))
                                    rgFishery.check(R.id.fno);

                                area_of_pond_acres=o.getString("area_of_pond_acres");
                                edittextfisheryareaoffpond.setText(area_of_pond_acres);

                                area_of_pond_own_lease=o.getString("area_of_pond_own_lease");
                                Log.d("AREA",area_of_pond_own_lease);
                                if(area_of_pond_own_lease.equals("Own"))
                                    rgOwnLease.check(R.id.own);
                                else if(area_of_pond_own_lease.equals("Lease"))
                                    rgOwnLease.check(R.id.lease);

                                fingerling_from=o.getString("fingerling_from");
                                //spinner get
                                if(fingerling_from.equals("Govt"))
                                    position5=0;
                                else if(fingerling_from.equals("Private"))
                                    position5=1;
                                else if(fingerling_from.equals("Own"))
                                    position5=2;

                                spinnerfingerlingfrom.setSelection(position5);

                                trained_for_farming=o.getString("fishery_trained_for_farming");
                                if(trained_for_farming.equals("Yes"))
                                    rgftrain.check(R.id.ftyes);
                                else if(trained_for_farming.equals("No"))
                                    rgftrain.check(R.id.ftno);

                                medicine_used=o.getString("fishery_medicine_used");
                                if(medicine_used.equals("Yes"))
                                    rgmedicine.check(R.id.myes);
                                else if(medicine_used.equals("No"))
                                    rgmedicine.check(R.id.mno);


                                lone_taken_fishery=o.getString("fishery_lone_taken");// yes no  lone_taken
                                if(lone_taken_fishery.equals("Yes"))
                                    rgloan.check(R.id.lyes);
                                else if(lone_taken_fishery.equals("No"))
                                    rgloan.check(R.id.lno);


                                lone_taken_for=o.getString("fishery_lone_taken_for"); //  lone_taken_for
                                //spinner get
                                if(lone_taken_for.equals("Pond"))
                                    position6=0;
                                else if(lone_taken_for.equals("Rearing"))
                                    position6=1;
                                else if(lone_taken_for.equals("Other"))
                                    position6=2;

                                spinner_lone_taken_for.setSelection(position6);


                                veterinary_activity=o.getString("veterinary_activity");// yes no
                                if(veterinary_activity.equals("Yes"))
                                    rgfveterinary.check(R.id.vyes);
                                else if(veterinary_activity.equals("No"))
                                    rgfveterinary.check(R.id.vno);




                                covered_by_insurance=o.getString("covered_by_insurance");// animal insurance
                                if(covered_by_insurance.equals("Yes"))
                                    rgvinsurance.check(R.id.viyes);
                                else if(covered_by_insurance.equals("No"))
                                    rgvinsurance.check(R.id.vino);


                                veterinary_lone_taken=o.getString("veterinary_lone_taken"); //yes no
                                if(veterinary_lone_taken.equals("Yes"))
                                    rgveterinary_lone_taken.check(R.id.vlyes);
                                else if(veterinary_lone_taken.equals("No"))
                                    rgveterinary_lone_taken.check(R.id.vlno);


                                veterinary_lone_taken_for=o.getString("veterinary_lone_taken_for");// pond rearing other
                                //spinner get
                                if(veterinary_lone_taken_for.equals("Pond"))
                                    position9=0;
                                else if(veterinary_lone_taken_for.equals("Rearing"))
                                    position9=1;
                                else if(veterinary_lone_taken_for.equals("Other"))
                                    position9=2;

                                spinner_veterinary_loan_taken_for.setSelection(position9);

                                veterinary_trained_for_farming=o.getString("veterinary_trained_for_farming");
                                if(veterinary_trained_for_farming.equals("Yes"))
                                    rgveterinary_trained_for_farming.check(R.id.vtrainedforfarmingyes);
                                else if(veterinary_trained_for_farming.equals("No"))
                                    rgveterinary_trained_for_farming.check(R.id.vtrainedforfarmingno);

                                veterinary_medicine_used=o.getString("veterinary_medicine_used");
                                if(veterinary_medicine_used.equals("Yes"))
                                    rgveterinary_medicine_used.check(R.id.vmedicineusedyes);
                                else if(veterinary_medicine_used.equals("No"))
                                    rgveterinary_medicine_used.check(R.id.vmedicineusedno);


                                animal_type=o.getString("animal_type");// Cow,Goat, ( concat)
                                txtanimaltype.setText(animal_type);

                                animal_numbers=o.getString("animal_numbers"); // (concat)
                                txtanimalnumber.setText(animal_numbers);

                                animal_variety=o.getString("animal_variety"); //( concat)
                                txtanimalvariety.setText(animal_variety);

                                if(machine_tool_own.equals("Yes"))
                                    rgMachineTool.check(R.id.radioButtonMachineryToolsYes);
                                else if(machine_tool_own.equals("No"))
                                    rgMachineTool.check(R.id.radioButtonMachineryToolsNo);





                                //  things to get animal type, animal number, animal variety , covered by insurance, loan taken,
                                // trained for farming, medicine used
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(EditProfileFarmer2.this,"Error while loading", Toast.LENGTH_SHORT).show();

                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    public void onClickUpdate(View view)
    {
        animal_numbers=no_of_cows.getText().toString()+","+no_of_goat.getText().toString()+","+no_of_chick.getText().toString()
                +","+no_of_other_animal.getText().toString()+",";
        variety_of_animal=variety_of_cows.getText().toString()+","+variety_of_goat.getText().toString()+","
                +variety_of_chick.getText().toString()+","+variety_of_other_animal.getText().toString()+",";
        new AsyncEditProfileFarmer().execute();
    }


    private  class AsyncEditProfileFarmer extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        String  success = null,message="";

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(EditProfileFarmer2.this);
            pDialog.setMessage("Updating...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void  doInBackground(Void... params) {



            ArrayList<NameValuePair> cred = new ArrayList<NameValuePair>();

            cred.add(new BasicNameValuePair("user_id",user_id ));
          //  cred.add(new BasicNameValuePair("fathers_name",strfathersname ));
            cred.add(new BasicNameValuePair("email",stremail));
           // cred.add(new BasicNameValuePair("password",strpass));
         //   cred.add(new BasicNameValuePair("phone",strmobile));
            cred.add(new BasicNameValuePair("district",districtid));//strdistrict
            cred.add(new BasicNameValuePair("block",blockid));//strblock
            cred.add(new BasicNameValuePair("gp",gpid));//strgp

            cred.add(new BasicNameValuePair("village",villagename));
            cred.add(new BasicNameValuePair("gender",str_gender));
            cred.add(new BasicNameValuePair("caste",str_caste));
            //cred.add(new BasicNameValuePair("gp",str_gp_id));
            cred.add(new BasicNameValuePair("irrigated_land_under_cultivation",irrigated_land_under_cultivation));
            cred.add(new BasicNameValuePair("irrigation_source",irrigation_source));
            cred.add(new BasicNameValuePair("non_irrigated_land_under_cultivation",non_irrigated_land_under_cultivation));
            cred.add(new BasicNameValuePair("photo_url",null));

            if(str_specialcrop_yes_no.equals("Yes"))
            cred.add(new BasicNameValuePair("any_special_crop_grown",str_any_special_crop_grown));
            else if(str_specialcrop_yes_no.equals("No"))
                cred.add(new BasicNameValuePair("any_special_crop_grown","No"));

            cred.add(new BasicNameValuePair("machine_tool_own",str_machinetool_use_yes_no));
            cred.add(new BasicNameValuePair("machine_name",str_machines));
            cred.add(new BasicNameValuePair("yr_of_purchase",null));
            cred.add(new BasicNameValuePair("make",null));
            cred.add(new BasicNameValuePair("model",null));
            cred.add(new BasicNameValuePair("machine_condition",null));
            cred.add(new BasicNameValuePair("photograph",null));

            cred.add(new BasicNameValuePair("last_crop_grown_in",str_spinner_rabi_kharif));
            cred.add(new BasicNameValuePair("area",str_spinner_in_acres));
            cred.add(new BasicNameValuePair("soil_testing_done",str_soil_testing_done));
            cred.add(new BasicNameValuePair("crop",str_crop));
            cred.add(new BasicNameValuePair("seed_seedling_variety",str_seedling_variety));
            cred.add(new BasicNameValuePair("fertiliser_used",strFertiliserUsed));
            cred.add(new BasicNameValuePair("pesticide_used",strPesticideUsed));
            cred.add(new BasicNameValuePair("lcp_machines_used" ,strMachinesUsed));
            cred.add(new BasicNameValuePair("covered_by_insuarnce",strCoveredByInsurance));
            cred.add(new BasicNameValuePair("lcp_loan_taken",strLoanTaken));//  loan_taken

            cred.add(new BasicNameValuePair("fishery_activity",str_option));
            cred.add(new BasicNameValuePair("area_of_pond_acres",edittextfisheryareaoffpond.getText().toString()));
            cred.add(new BasicNameValuePair("fingerling_from",str_fingerlingfrom));
            cred.add(new BasicNameValuePair("fishery_trained_for_farming",str_trained_for_farming_fishery));//<-----not posting
            cred.add(new BasicNameValuePair("fishery_medicine_used",str_fishery_medicine_used));//<------- not posting
            cred.add(new BasicNameValuePair("fishery_lone_taken",str_fishery_lone_taken));//  lone_taken
            cred.add(new BasicNameValuePair("area_of_pond_own_lease",strOwnLease));
            cred.add(new BasicNameValuePair("fishery_lone_taken_for",str_fishery_lone_taken_for));// lone_taken_for

            cred.add(new BasicNameValuePair("veterinary_activity",str_veterinary_activity));
            cred.add(new BasicNameValuePair("animal_type",str_animal_type));
            cred.add(new BasicNameValuePair("covered_by_insurance",str_veterinary_insurance));
            cred.add(new BasicNameValuePair("veterinary_lone_taken",str_veterinary_lone_taken));//  lone_taken
            cred.add(new BasicNameValuePair("veterinary_lone_taken_for",str_veterinary_loan_taken_for));//  lone_taken_for
            cred.add(new BasicNameValuePair("veterinary_trained_for_farming",str_veterinary_trained_for_farming));
            cred.add(new BasicNameValuePair("veterinary_medicine_used",str_veterinary_medicine_used));// medicine_used
            cred.add(new BasicNameValuePair("animal_variety",variety_of_animal));
            cred.add(new BasicNameValuePair("animal_numbers",animal_numbers));


            String urlRouteList= Config.farmersignup;
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
            if(message.equals("Update Successfull"))
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
                        Intent i = new Intent(EditProfileFarmer2.this, NavDrawer.class);
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
         /*   public void onClickTractor(View view)
            {
                Intent intent=new Intent(this,PopUpMachineTool.class);
                intent.putExtra("MACHINE_NAME", "Tractor");

                startActivity(intent);
            }

            public void onClickPowerTiller(View view)
            {
                Intent intent=new Intent(this,PopUpMachineTool.class);
                intent.putExtra("MACHINE_NAME", "Power Tiller");

                startActivity(intent);
            }

            public void onClickRotavator(View view)
            {
                Intent intent=new Intent(this,PopUpMachineTool.class);
                intent.putExtra("MACHINE_NAME", "Rotavator");

                startActivity(intent);
            }

            public void onClickPlough(View view)
            {
                Intent intent=new Intent(this,PopUpMachineTool.class);
                intent.putExtra("MACHINE_NAME", "Plough");

                startActivity(intent);
            }
            public void onClickRidger(View view)
            {
                Intent intent=new Intent(this,PopUpMachineTool.class);
                intent.putExtra("MACHINE_NAME", "Ridger");

                startActivity(intent);
            }

            public void onClickLeveller(View view)
            {
                Intent intent=new Intent(this,PopUpMachineTool.class);
                intent.putExtra("MACHINE_NAME", "Leveller");

                startActivity(intent);

            }


            public void onClickSeedDrill(View view)
            {
                Intent intent=new Intent(this,PopUpMachineTool.class);
                intent.putExtra("MACHINE_NAME", "SeedDrill");
                startActivity(intent);

            }*/

}
