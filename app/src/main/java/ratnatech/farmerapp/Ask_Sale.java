package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import ratnatech.farmerapp.AskQSale.AgrilProductSale;
import ratnatech.farmerapp.AskQSale.FishProductSale;
import ratnatech.farmerapp.AskQSale.MachinerySale;
import ratnatech.farmerapp.AskQSale.OtherProducttoSale;
import ratnatech.farmerapp.AskQSale.SeePurchaseData;
import ratnatech.farmerapp.AskQSale.VeterinaryProductSale;

public class Ask_Sale extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_ask__sale);
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
        getSupportActionBar().setTitle(" Question/Sale"); //Ask a


    }

    public void onClickPurchaseData(View view)
    {
        Intent intent=new Intent(this, SeePurchaseData.class);
        startActivity(intent);
    }


    public void onClickAgrilProduct(View view)
    {
        Intent intent=new Intent(this, AgrilProductSale.class);
        startActivity(intent);
    }
    public void onClickMachineProduct(View view)
    {
        Intent intent=new Intent(this, MachinerySale.class);
        startActivity(intent);
    }
    public void onClickVeterinaryProduct(View view)
    {
        Intent intent=new Intent(this, VeterinaryProductSale.class);
        startActivity(intent);
    }
    public void onClickFishSale(View view)
    {
        Intent intent=new Intent(this, FishProductSale.class);
        startActivity(intent);
    }


    public void onClickOtherProductSale(View view)
    {
        Intent intent=new Intent(this, OtherProducttoSale.class);
        startActivity(intent);
    }
}
