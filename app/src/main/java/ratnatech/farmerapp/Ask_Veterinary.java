package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import ratnatech.farmerapp.AskQVeterinary.VBreed;
import ratnatech.farmerapp.AskQVeterinary.VCultivationPractise;
import ratnatech.farmerapp.AskQVeterinary.VDisease;
import ratnatech.farmerapp.AskQVeterinary.VFeed;
import ratnatech.farmerapp.AskQVeterinary.VHousing;
import ratnatech.farmerapp.AskQVeterinary.VInsurance;
import ratnatech.farmerapp.AskQVeterinary.VTraining;

public class Ask_Veterinary extends AppCompatActivity {

    ImageView cultivation_practice,breed,feed,disease,housing,training,insurance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask__veterinary);
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
        getSupportActionBar().setTitle("Question/Veterinary"); //Ask a

        cultivation_practice=findViewById(R.id.cultivation_practice);
        cultivation_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Veterinary.this, VCultivationPractise.class));
            }
        });

        breed=findViewById(R.id.breed);
        breed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Veterinary.this, VBreed.class));
            }
        });
        feed=findViewById(R.id.feed);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Veterinary.this, VFeed.class));
            }
        });
        disease=findViewById(R.id.disease);
        disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Veterinary.this, VDisease.class));
            }
        });
        housing=findViewById(R.id.housing);
        housing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Veterinary.this, VHousing.class));
            }
        });
        training=findViewById(R.id.training);
        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Veterinary.this, VTraining.class));
            }
        });
        /*insurance=findViewById(R.id.insurance);
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ask_Veterinary.this, VInsurance.class));
            }
        });*/
    }
}
