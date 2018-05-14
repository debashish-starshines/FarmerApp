package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class SignUpAll extends AppCompatActivity {

    ImageView farmeroption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_all);
        //farmeroption=findViewById(R.id.farmerradio);

    }
    public void farmeroption(View view)
    {
        Intent intent=new Intent(this,SignUpFarmer.class);
        startActivity(intent);
    }

    public void resourcepersonoption(View view)
    {
        Intent intent=new Intent(this,SignUpExtensionOfficer.class);//SignUpResourcePerson
        startActivity(intent);
    }
    public void businessoption(View view)
    {
        Intent intent=new Intent(this,SignUpBusiness.class);
        startActivity(intent);
    }
    public void admin_manageroption(View view)
    {
        Intent intent=new Intent(this,SignUpAdminManager.class);
        startActivity(intent);
    }
    public void studentresearcheroption(View view)
    {
        Intent intent=new Intent(this,SignUpStudentResearcher.class);
        startActivity(intent);
    }

    public void guestoption(View view)
    {
        Intent intent=new Intent(this,SignUpGuest.class);
        startActivity(intent);
    }
}
