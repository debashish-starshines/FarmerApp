package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import ratnatech.farmerapp.NavDrawer.NavDrawer;

public class GramPanchayat extends AppCompatActivity {

    TextView districtname,blockname,qnatext,gpname;
    ImageView back;
    String bname,dname,gname;
    Spinner gp;
    String villagetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gram_panchayat);
        districtname=findViewById(R.id.districtname);
        blockname=findViewById(R.id.blockname);
        qnatext=findViewById(R.id.qnatext);
        back=findViewById(R.id.back);
        gpname=findViewById(R.id.gpname);
        gp=findViewById(R.id.gp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  onBackPressed();
                Intent intent=new Intent(GramPanchayat.this,NavDrawer.class);
                startActivity(intent);
                finish();
            }
        });
        getSupportActionBar().setTitle("Question Answers");


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
              /*  bname= null;
                dname=null;
                gname=null;*/
                bname="Block";
                dname="District";
                gname="GP";
            } else {
                bname= extras.getString("BLOCK");
                dname= extras.getString("DISTRICT");
                gname= extras.getString("GP");
            }
        } else {
            bname= (String) savedInstanceState.getSerializable("BLOCK");
            dname= (String) savedInstanceState.getSerializable("DISTRICT");
            gname= (String) savedInstanceState.getSerializable("GP");
        }

        districtname.setText(dname);
        blockname.setText("/"+bname);
        gpname.setText("/"+gname);

        qnatext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        districtname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //onBackPressed();
                startActivity(new Intent(GramPanchayat.this,DistrictName.class));
                finish();
            }
        });
        blockname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                startActivity(new Intent(GramPanchayat.this,Block.class));
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        gp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                villagetext= gp.getSelectedItem().toString();
                if(villagetext.equals("Select Your Village"))
                {

                }
                else
                {
                    Intent intent=new Intent(GramPanchayat.this,VillageName.class);
                    intent.putExtra("BLOCK", bname);
                    intent.putExtra("DISTRICT",dname);
                    intent.putExtra("GP",gname);
                    intent.putExtra("VILLAGE",villagetext);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("BLOCK", bname);
        savedInstanceState.putString("DISTRICT", dname);
        savedInstanceState.putString("GP", gname);
    }
}
