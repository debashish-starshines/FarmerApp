package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Ask_GovtSchemes extends AppCompatActivity {

    ImageView img_govt_agriculture,img_govt_horticulture,fishery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_ask__govt_schemes);
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
        getSupportActionBar().setTitle("Question/Govt Schemes");//Ask a

        img_govt_agriculture=findViewById(R.id.id_govt_agriculture);
        img_govt_horticulture=findViewById(R.id.id_govt_horticulture);
        fishery=findViewById(R.id.fishery);
        img_govt_agriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     /*           Intent intent=new Intent(Ask_GovtSchemes.this, GAgriculture.class);
                startActivity(intent);*/
            }
        });

        img_govt_horticulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(Ask_GovtSchemes.this, GHorticulture.class);
                startActivity(intent);*/
            }
        });

        fishery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent=new Intent(Ask_GovtSchemes.this, GFishery.class);
                startActivity(intent);*/
            }
        });
    }
}
