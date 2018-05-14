package ratnatech.farmerapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpResourcePerson extends AppCompatActivity {

    AppCompatEditText name,input_mob_no,input_pass,input_mail;
    String strname,strmobile,stremail,strpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_sign_up_resource_person);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        name=findViewById(R.id.name);
       input_mob_no=findViewById(R.id.input_mob_no);
        input_pass=findViewById(R.id.input_pass);
        input_mail=findViewById(R.id.input_mail);
    }
    public void onClickNext(View view)
    {
        //validation
        strname=name.getText().toString().trim();
        strmobile=input_mob_no.getText().toString().trim();
        stremail=input_mail.getText().toString().trim();
        strpass=input_pass.getText().toString().trim();

        if(strname.equals("")||strmobile.equals("")||strpass.equals(""))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "One or More Field Blank", Snackbar.LENGTH_LONG);

            snackbar.show();
        }

        else if(!(isValidPhone(strmobile)))
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Phone number is Invalid", Snackbar.LENGTH_LONG);

            snackbar.show();
        }
        else if(strpass.length()<6&&strpass.length()>8)
        {
            Toast.makeText(this, "Length error(min 6 and max 8 characters)", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, SignUpResourcePerson_2.class);
            intent.putExtra("NAME", strname);
            intent.putExtra("MOBILE", strmobile);
            intent.putExtra("EMAIL", stremail);
            intent.putExtra("PASSWORD", strpass);
            startActivity(intent);

        }

    }
    public static boolean isValidPhone(String phone)
    {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}
