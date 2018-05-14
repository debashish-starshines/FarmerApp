package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import ratnatech.farmerapp.AskQHire.GiveOnHire;
import ratnatech.farmerapp.AskQHire.TakeOnHire;

public class Ask_Hire extends AppCompatActivity {

    ImageView img_giveonhire,img_takeonhire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_ask__hire);
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
        getSupportActionBar().setTitle("Question/Hire");// Ask a

        img_giveonhire=findViewById(R.id.id_giveonhire);
        img_takeonhire=findViewById(R.id.id_takeonhire);
        img_giveonhire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Hire.this, GiveOnHire.class);
                startActivity(intent);
            }
        });
        img_takeonhire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Ask_Hire.this, TakeOnHire.class);
                startActivity(intent);
            }
        });
    }

}
