package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class StateGovtExtenOff extends AppCompatActivity {

    RadioGroup rgStateDepart;
    String str_stategovt_dept="";
    String strname,strmobile,stremail,strpass,strgender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_state_govt_exten_off);
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

        if(bundle != null)
        {
            strname = bundle.getString("NAME");
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
            strgender = bundle.getString("GENDER");
        }



        rgStateDepart=findViewById(R.id.rgStateDepart);
        rgStateDepart.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    str_stategovt_dept=  rb.getText().toString();

                    if(str_stategovt_dept.equals("Agril"))
                    {
                        Intent intent=new Intent(StateGovtExtenOff.this,StateGovtAgriExtOff.class);

                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        startActivity(intent);
                    }
                    else if(str_stategovt_dept.equals("Horticulture"))
                    {
                        Intent intent=new Intent(StateGovtExtenOff.this,StateGovtHortiExtOff.class);

                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        startActivity(intent);
                    }
                    else if(str_stategovt_dept.equals("Soil-Conservation and Watershed"))
                    {
                        Intent intent=new Intent(StateGovtExtenOff.this,StateGovtSoilWatershedExtOff.class);

                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        startActivity(intent);
                    }
                    else if(str_stategovt_dept.equals("Animal Resources"))
                    {
                        Intent intent=new Intent(StateGovtExtenOff.this,StateGovtAniResourceExtOff.class);

                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        startActivity(intent);
                    }
                    else if(str_stategovt_dept.equals("Fishery"))
                    {
                        Intent intent=new Intent(StateGovtExtenOff.this,StateGovtFisheryExtOff.class);

                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);
                        startActivity(intent);
                    }
                    else if(str_stategovt_dept.equals("Marketing Related"))
                    {
                        Intent intent=new Intent(StateGovtExtenOff.this,StateGovtMarketingExtOff.class);

                        intent.putExtra("NAME",strname);
                        intent.putExtra("MOBILE",strmobile);
                        intent.putExtra("EMAIL",stremail);
                        intent.putExtra("PASSWORD",strpass);
                        intent.putExtra("GENDER",strgender);

                        startActivity(intent);
                    }
                }

            }
        });
    }
}
