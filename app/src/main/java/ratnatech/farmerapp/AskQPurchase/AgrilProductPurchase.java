package ratnatech.farmerapp.AskQPurchase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ratnatech.farmerapp.AskQAgriculture.CultivationPractise;
import ratnatech.farmerapp.AskQSale.AgrilProductSale;
import ratnatech.farmerapp.AskQSale.PopUpAgrilProductSale;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.Utility;

public class AgrilProductPurchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_agril_product_purchase);

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
        getSupportActionBar().setTitle("Purchase/Agril Product");


    }

    public void onClickPaddy(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Paddy");
        startActivity(intent);

    }

    public void onClickGreenGram(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Green Gram");
        startActivity(intent);

    }

    public void onClickBlackGram(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Black Gram");
        startActivity(intent);

    }

    public void onClickMustard(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Mustard");
        startActivity(intent);

    }

    public void onClickGroundnut(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Ground Nut");
        startActivity(intent);

    }


    public void onClickVegetable(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Vegetable");
        startActivity(intent);

    }

    public void onClickFruit(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Fruit");
        startActivity(intent);

    }

    public void onClickFlower(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Flower");
        startActivity(intent);

    }


    public void onClickOthers(View view)
    {

        Intent intent = new Intent(AgrilProductPurchase.this, PopUpAgrilProductPurchase.class);

        intent.putExtra("PROD_NAME","Others");
        startActivity(intent);

    }

}
