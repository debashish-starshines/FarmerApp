package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import ratnatech.farmerapp.AskQFishery.FFish;
import ratnatech.farmerapp.AskQFishery.FOthers;
import ratnatech.farmerapp.AskQFishery.FPrawn;

public class Ask_Fishery extends AppCompatActivity {

    ImageView img_fishery_prawn,img_fishery_fish,img_fishery_others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_ask__fishery);
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
        getSupportActionBar().setTitle("Question/Fishery"); //Ask a

        img_fishery_prawn=findViewById(R.id.id_fishery_prawn);
        img_fishery_fish=findViewById(R.id.id_fishery_fish);
        img_fishery_others=findViewById(R.id.id_fishery_others);

        img_fishery_prawn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Fishery.this, FPrawn.class);
                startActivity(intent);
            }
        });

        img_fishery_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Fishery.this, FFish.class);
                startActivity(intent);
            }
        });

        img_fishery_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Fishery.this, FOthers.class);
                startActivity(intent);
            }
        });




    }
}
