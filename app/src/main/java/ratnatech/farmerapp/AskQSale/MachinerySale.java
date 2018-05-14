package ratnatech.farmerapp.AskQSale;

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

import ratnatech.farmerapp.AskQPurchase.MachineryPurchase;
import ratnatech.farmerapp.PopUpMachineTool;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.Utility;

public class MachinerySale extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_machinery_sale);

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
        getSupportActionBar().setTitle("Sale/Machinery");

    }

        public void TRACTOR(View view)
        {
            Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
            intent.putExtra("MACHINE_NAME","TRACTOR");
            startActivity(intent);

        }

        public void POWERTILLER(View view)
        {
            Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
            intent.putExtra("MACHINE_NAME","POWER TILLER");
            startActivity(intent);

        }

    public void Rotavator(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Rotavator");
        startActivity(intent);

    }
    public void Plough(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Plough");
        startActivity(intent);

    }
    public void Ridger(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Ridger");
        startActivity(intent);

    }

    public void Leveller(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Leveller");
        startActivity(intent);

    }
    public void seeddrill(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","seed drill");
        startActivity(intent);

    }

    public void transplanter(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","transplanter");
        startActivity(intent);

    }

    public void weeder(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","weeder");
        startActivity(intent);

    }
    public void Sprayer(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Sprayer");
        startActivity(intent);

    }
    public void Reaper(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Reaper");
        startActivity(intent);

    }

    public void Thresher(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Thresher");
        startActivity(intent);

    }

    public void Cleaner(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Cleaner");
        startActivity(intent);

    }

    public void Grader(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Grader");
        startActivity(intent);

    }

    public void Combine(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Combine");
        startActivity(intent);

    }
    public void Pumps(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Pumps");
        startActivity(intent);

    }

    public void Parboilingunit(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Parboilingunit");
        startActivity(intent);

    }
    public void ricemill(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","ricemill");
        startActivity(intent);

    }

    public void Decorticator(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Decorticator");
        startActivity(intent);

    }

    public void Toolsforfruitandvegetable(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","Tools for fruit and vegetable");
        startActivity(intent);

    }

    public void others(View view)
    {
        Intent intent =new Intent (this, PopUpMachineToolSale.class);//PopUpMachineTool
        intent.putExtra("MACHINE_NAME","others");
        startActivity(intent);

    }

}
