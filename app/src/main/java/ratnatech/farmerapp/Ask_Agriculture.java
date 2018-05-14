package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import ratnatech.farmerapp.AskQAgriculture.CultivationPractise;
import ratnatech.farmerapp.AskQAgriculture.Disease_Pest;
import ratnatech.farmerapp.AskQAgriculture.Fertiliser;
import ratnatech.farmerapp.AskQAgriculture.Insurance;
import ratnatech.farmerapp.AskQAgriculture.Irrigation;
import ratnatech.farmerapp.AskQAgriculture.Machine_Tools;
import ratnatech.farmerapp.AskQAgriculture.Processing;
import ratnatech.farmerapp.AskQAgriculture.Seed;
import ratnatech.farmerapp.AskQAgriculture.Soil;
import ratnatech.farmerapp.AskQAgriculture.Training;

public class Ask_Agriculture extends AppCompatActivity {

    ImageView cultivationpractice,soil,seed,fertiliser,disease_pest,irrigation,insurance,training,processing,machinery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_ask__agriculture);
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
        getSupportActionBar().setTitle("Question/Agriculture");// Ask a

        cultivationpractice= findViewById(R.id.cultivationpractice);
        cultivationpractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, CultivationPractise.class));
            }
        });
        soil= findViewById(R.id.soil);
        soil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Soil.class));
            }
        });
        seed= findViewById(R.id.seed);
        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Seed.class));
            }
        });
        fertiliser=findViewById(R.id.fertiliser);
        fertiliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Fertiliser.class));
            }
        });
        disease_pest=findViewById(R.id.disease_pest);
        disease_pest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Disease_Pest.class));
            }
        });
        irrigation=findViewById(R.id.irrigation);
        irrigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Irrigation.class));
            }
        });
        insurance=findViewById(R.id.insurance);
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Insurance.class));
            }
        });

        training=findViewById(R.id.training);
        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Training.class));
            }
        });
        processing=findViewById(R.id.processing);
        processing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Processing.class));
            }
        });
        machinery=findViewById(R.id.machinery);
        machinery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Agriculture.this, Machine_Tools.class));
            }
        });
    }
}
