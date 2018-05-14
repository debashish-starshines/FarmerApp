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

public class DistrictName extends AppCompatActivity {

    TextView districtname,qnatext;
    ImageView back;
    Spinner block;
    String blocktext="";
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_name);
        districtname=findViewById(R.id.districtname);
        block=findViewById(R.id.block);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
              //  name= null;
                name="Dist";
            } else {
                name= extras.getString("DISTRICT");
            }
        } else {
            name= (String) savedInstanceState.getSerializable("DISTRICT");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                Intent intent=new Intent(DistrictName.this,NavDrawer.class);
                startActivity(intent);
                finish();
            }
        });
        getSupportActionBar().setTitle("Question Answers");
        districtname.setText(name);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        qnatext=findViewById(R.id.qnatext);
        qnatext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blocktext= block.getSelectedItem().toString();
                if(blocktext.equals("Select Your Block"))
                {

                }
                else
                {
                    Intent intent=new Intent(DistrictName.this,Block.class);
                    intent.putExtra("BLOCK", blocktext);
                    intent.putExtra("DISTRICT",name);
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
        savedInstanceState.putString("DISTRICT", name);
    }
}
