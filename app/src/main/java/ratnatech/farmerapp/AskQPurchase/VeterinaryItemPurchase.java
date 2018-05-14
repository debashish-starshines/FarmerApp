package ratnatech.farmerapp.AskQPurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import ratnatech.farmerapp.PopUpVAnimal;
import ratnatech.farmerapp.R;

public class VeterinaryItemPurchase extends AppCompatActivity {

    Button cowID,chickID,goatID,buffaloID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_veterinary_item_purchase);
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
        getSupportActionBar().setTitle("Purchase/Veterinary Item");

        cowID=findViewById(R.id.cowID);
        chickID=findViewById(R.id.chickID);
        goatID=findViewById(R.id.goatID);
        buffaloID=findViewById(R.id.buffaloID);
    }


    public void onClickCow(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Cow");

        startActivity(intent);
    }

    public void onClickChick(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Chick");

        startActivity(intent);
    }
    public void onClickGoat(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Goat");

        startActivity(intent);
    }

    public void onClickBuffalo(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Buffalo");

        startActivity(intent);
    }
}
