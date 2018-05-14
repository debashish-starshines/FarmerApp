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

public class Block extends AppCompatActivity {

    TextView districtname,blockname,qnatext;
    ImageView back;
    Spinner gp;
    String gptext;
    String bname="",dname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        districtname=findViewById(R.id.districtname);
        blockname=findViewById(R.id.blockname);
        qnatext=findViewById(R.id.qnatext);
        back=findViewById(R.id.back);
        gp=findViewById(R.id.gp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                Intent intent=new Intent(Block.this,NavDrawer.class);
                startActivity(intent);
                finish();
            }
        });
        getSupportActionBar().setTitle("Question Answers");

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                /*bname= null;
                dname=null;*/
                bname="Block";
                dname="Dist";
            } else {
                bname= extras.getString("BLOCK");
                dname= extras.getString("DISTRICT");
            }
        } else {
            bname= (String) savedInstanceState.getSerializable("BLOCK");
            dname= (String) savedInstanceState.getSerializable("DISTRICT");
        }

        districtname.setText(dname);
        blockname.setText("/"+bname);

        qnatext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        districtname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(this,));
                onBackPressed();
            }
        });
       /* blockname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        gp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gptext= gp.getSelectedItem().toString();
                if(gptext.equals("Select Your GP"))
                {

                }
                else
                {
                    Intent intent=new Intent(Block.this,GramPanchayat.class);
                    intent.putExtra("BLOCK", bname);
                    intent.putExtra("DISTRICT",dname);
                    intent.putExtra("GP",gptext);
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
    }
}
