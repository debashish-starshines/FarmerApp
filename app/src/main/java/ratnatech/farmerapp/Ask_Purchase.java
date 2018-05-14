package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import ratnatech.farmerapp.AskQPurchase.AgrilProductPurchase;
import ratnatech.farmerapp.AskQPurchase.FishPurchase;
import ratnatech.farmerapp.AskQPurchase.MachineryPurchase;
import ratnatech.farmerapp.AskQPurchase.OtherItemPurchase;
import ratnatech.farmerapp.AskQPurchase.SeeData;

public class Ask_Purchase extends AppCompatActivity {

    ImageView img_agril_product,img_machinery,img_veterinary,img_fish,img_other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_ask__purchase);
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
        getSupportActionBar().setTitle(" Question/Purchase");// Ask
        img_agril_product=findViewById(R.id.id_agril_product);
        img_machinery=findViewById(R.id.id_machinery);
        img_veterinary=findViewById(R.id.id_veterinary);
        img_fish=findViewById(R.id.id_fish);
        img_other=findViewById(R.id.id_other);

        img_agril_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Purchase.this, AgrilProductPurchase.class);
                startActivity(intent);
            }
        });

        img_machinery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Purchase.this, MachineryPurchase.class);
                startActivity(intent);
            }
        });

        img_veterinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Purchase.this, VeterinaryItemBusiness.class);// SAME ACTIVITY CALLING FOR FARMER PURCHASE
                startActivity(intent);
            }
        });

        img_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Purchase.this, FishPurchase.class);// SAME ACTIVITY CALLING FOR FARMER PURCHASE
                startActivity(intent);
            }
        });
        img_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Purchase.this, OtherItemPurchase.class);// SAME ACTIVITY CALLING FOR FARMER PURCHASE
                startActivity(intent);
            }
        });

    }
            public void onClickSeeData(View view)
            {
                Intent intent=new Intent(this, SeeData.class);
                startActivity(intent);
            }

    }