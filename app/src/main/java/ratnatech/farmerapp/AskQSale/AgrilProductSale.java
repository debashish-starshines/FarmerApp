package ratnatech.farmerapp.AskQSale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import ratnatech.farmerapp.R;

public class AgrilProductSale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_agril_product_sale);

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
        getSupportActionBar().setTitle("Sale/Agril Product");


    }

    public void onClickPaddy(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Paddy");
        startActivity(intent);

    }

    public void onClickGreenGram(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Green Gram");
        startActivity(intent);

    }

    public void onClickBlackGram(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Black Gram");
        startActivity(intent);

    }

    public void onClickMustard(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Mustard");
        startActivity(intent);

    }

    public void onClickGroundnut(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Ground Nut");
        startActivity(intent);

    }


    public void onClickVegetable(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Vegetable");
        startActivity(intent);

    }

    public void onClickFruit(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Fruit");
        startActivity(intent);

    }

    public void onClickFlower(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Flower");
        startActivity(intent);

    }


    public void onClickOthers(View view)
    {

        Intent intent = new Intent(AgrilProductSale.this, PopUpAgrilProductSale.class);

        intent.putExtra("PROD_NAME","Others");
        startActivity(intent);

    }
}
