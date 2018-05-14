package ratnatech.farmerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class InServiceGPN extends AppCompatActivity {

    String strname,strmobile,stremail,strpass,strgender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_service_gpn);

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
        getSupportActionBar().setTitle("In-Service");


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            strname = bundle.getString("NAME");
            strmobile = bundle.getString("MOBILE");
            stremail = bundle.getString("EMAIL");
            strpass = bundle.getString("PASSWORD");
            strgender = bundle.getString("GENDER");
        }

    }
    public void onClickGovt(View view)
    {  final CharSequence[] items = { "Central Govt", "State Govt", "ICAR/OUAT" };
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InServiceGPN.this);

        // Setting Dialog Title
        alertDialog.setTitle("Select Govt Type...");

        // Setting Dialog Message
     //  alertDialog.setMessage("");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.logo_new_5th_april);

        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Central Govt"))
                {
                    Intent intent=new Intent(InServiceGPN.this,CentralGovtExtenOff.class);

                    intent.putExtra("NAME",strname);
                    intent.putExtra("MOBILE",strmobile);
                    intent.putExtra("EMAIL",stremail);
                    intent.putExtra("PASSWORD",strpass);
                    intent.putExtra("GENDER",strgender);
                    startActivity(intent);
                }
               else if (items[which].equals("State Govt"))
                {
                    Intent intent=new Intent(InServiceGPN.this,StateGovtExtenOff.class);

                    intent.putExtra("NAME",strname);
                    intent.putExtra("MOBILE",strmobile);
                    intent.putExtra("EMAIL",stremail);
                    intent.putExtra("PASSWORD",strpass);
                    intent.putExtra("GENDER",strgender);
                    startActivity(intent);
                }
                else if(items[which].equals("ICAR/OUAT"))
                {
                    Intent intent=new Intent(InServiceGPN.this,IcarExtenOff.class);

                    intent.putExtra("NAME",strname);
                    intent.putExtra("MOBILE",strmobile);
                    intent.putExtra("EMAIL",stremail);
                    intent.putExtra("PASSWORD",strpass);
                    intent.putExtra("GENDER",strgender);
                    startActivity(intent);
                }
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

   public void onClickPrivate(View view)
   {
       Intent intent=new Intent(InServiceGPN.this,PrivateExtenOff.class);

       intent.putExtra("NAME",strname);
       intent.putExtra("MOBILE",strmobile);
       intent.putExtra("EMAIL",stremail);
       intent.putExtra("PASSWORD",strpass);
       intent.putExtra("GENDER",strgender);
       startActivity(intent);
   }
   public void onClickNGO(View view)
   {
       Intent intent=new Intent(InServiceGPN.this,NGOExtenOff.class);


       intent.putExtra("NAME",strname);
       intent.putExtra("MOBILE",strmobile);
       intent.putExtra("EMAIL",stremail);
       intent.putExtra("PASSWORD",strpass);
       intent.putExtra("GENDER",strgender);

       startActivity(intent);
   }
}
