package ratnatech.farmerapp.AskQPurchase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import ratnatech.farmerapp.Login;
import ratnatech.farmerapp.NavDrawer.NavDrawer;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.SignUpBusiness_2;
import ratnatech.farmerapp.Utility;

public class MachineryPurchase extends AppCompatActivity {


    TextView txtnewmachinery,txtoldmachinery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinery_purchase);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadAlert();
        txtoldmachinery=findViewById(R.id.txtoldmachinery);
        txtnewmachinery=findViewById(R.id.txtnewmachinery);

        HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox1), "TRACTOR");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox2), "POWER TILLER");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox3), "Rotavator");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox4), "Plough");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox5), "Ridger");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox6), "Leveller");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox7), "seed drill");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox8), "transplanter");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox9), "weeder");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox10), "Sprayer");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox11), "Reaper");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox12), "Thresher");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox13), "Cleaner");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox14), "Grader");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox15), "Combine");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox16), "Pumps");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox17), "Parboiling unit");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox18), "rice mill");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox19), "Decorticator");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox20), "Tools for fruit and vegetable");
        checkBoxMap.put((CheckBox) findViewById(R.id.checkBox21), "others");



        CheckBoxGroup<String> checkBoxGroup = new CheckBoxGroup<>(checkBoxMap,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values) {

                            Toast.makeText(MachineryPurchase.this,
                                    values.toString(),
                                    Toast.LENGTH_LONG).show();

                            txtoldmachinery.setText(values.toString());



                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });

        //  SECOND NEW CHECKBOX -------------------------------------------------------------------------------

        HashMap<CheckBox, String> checkBoxMap2 = new HashMap<>();

        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox22), "TRACTOR");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox23), "POWER TILLER");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox24), "Rotavator");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox25), "Plough");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox26), "Ridger");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox27), "Leveller");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox28), "seed drill");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox29), "transplanter");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox30), "weeder");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox31), "Sprayer");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox32), "Reaper");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox33), "Thresher");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox34), "Cleaner");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox35), "Grader");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox36), "Combine");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox37), "Pumps");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox38), "Parboiling unit");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox39), "rice mill");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox40), "Decorticator");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox41), "Tools for fruit and vegetable");
        checkBoxMap2.put((CheckBox) findViewById(R.id.checkBox42), "others");


        CheckBoxGroup<String> checkBoxGroup2 = new CheckBoxGroup<>(checkBoxMap2,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values2) {

                        Toast.makeText(MachineryPurchase.this,
                                values2.toString(),
                                Toast.LENGTH_LONG).show();

                        txtnewmachinery.setText(values2.toString());



                    }

                   /* @Override
                    public void onOptionChange(ArrayList<String> options) {
                        Toast.makeText(BtypeDealsInS.this,
                                options.toString(),
                                Toast.LENGTH_LONG).show();
                    }*/
                });


    }
    public void loadAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage("Scroll left and right and select the machinery tools, Implements")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });


        //Creating dialog box
       AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("App Information");
        alert.show();

    }
}
