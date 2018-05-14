package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ratnatech.farmerapp.NavDrawer.NavDrawer;

public class VillageName extends AppCompatActivity {

    TextView districtname,blockname,qnatext,gpname,villagename;
    ImageView back;
    String bname,dname,gname,vname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_name);
        districtname=findViewById(R.id.districtname);
        blockname=findViewById(R.id.blockname);
        qnatext=findViewById(R.id.qnatext);
        back=findViewById(R.id.back);
        gpname=findViewById(R.id.gpname);
        villagename=findViewById(R.id.villagename);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent=new Intent(VillageName.this,NavDrawer.class);
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
                vname="Village";

            } else {
                bname= extras.getString("BLOCK");
                dname= extras.getString("DISTRICT");
                gname= extras.getString("GP");
                vname= extras.getString("VILLAGE");
            }
        } else {
            bname= (String) savedInstanceState.getSerializable("BLOCK");
            dname= (String) savedInstanceState.getSerializable("DISTRICT");
            gname= (String) savedInstanceState.getSerializable("GP");
            vname= (String) savedInstanceState.getSerializable("VILLAGE");
        }

        districtname.setText(dname);
        blockname.setText("/"+bname);
        gpname.setText("/"+gname);
        villagename.setText("/"+vname);

        qnatext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();
                startActivity(new Intent(VillageName.this,QuestionAnswers.class));
                finish();
            }
        });
        districtname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // onBackPressed();
                startActivity(new Intent(VillageName.this,DistrictName.class));
                finish();
            }
        });
        blockname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                startActivity(new Intent(VillageName.this,Block.class));
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        gpname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                startActivity(new Intent(VillageName.this,GramPanchayat.class));
                finish();
            }
        });
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("BLOCK", bname);
        savedInstanceState.putString("DISTRICT", dname);
        savedInstanceState.putString("GP", gname);
        savedInstanceState.putString("VILLAGE", vname);
    }
}
