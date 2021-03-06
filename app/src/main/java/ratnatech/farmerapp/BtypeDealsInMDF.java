package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class BtypeDealsInMDF extends AppCompatActivity {

    String str_website,str_mobile,str_email,str_pass,str_farm_name,
            str_propertier_name,str_district_id,str_block_id,str_area_of_business,strbusinesstype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btype_deals_in_mdf);
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


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            str_website = bundle.getString("WEBSITE");
            str_mobile = bundle.getString("MOBILE");
            str_email = bundle.getString("EMAIL");
            str_pass = bundle.getString("PASSWORD");
            str_farm_name = bundle.getString("FARM_NAME");
            str_propertier_name = bundle.getString("PROPERTIER_NAME");
            str_district_id= bundle.getString("DISTRICT");
            str_block_id=bundle.getString("BLOCK");
            str_area_of_business=bundle.getString("AREA_OF_BUSINESS");
            strbusinesstype=bundle.getString("BUSINESS_TYPE");

            Log.d("WEBSITE",str_website );
            Log.d("MOBILE",str_mobile );
            Log.d("EMAIL",str_email );
            Log.d("PASSWORD",str_pass );
            Log.d("FARM_NAME",str_farm_name );
            Log.d("PROPERTIER_NAME",str_propertier_name );
            Log.d("DISTRICT",str_district_id );
            Log.d("BLOCK",str_block_id );
            Log.d("AREA_OF_BUSINESS",str_area_of_business );
            Log.d("BUSINESS_TYPE",strbusinesstype );
        }

    }

    public void onClickAgrilProduct(View view)
    {
        Intent intent=new Intent(this,AgrilProductBusiness.class);

        intent.putExtra("WEBSITE",str_website );
        intent.putExtra("MOBILE",str_mobile );
        intent.putExtra("EMAIL",str_email );
        intent.putExtra("PASSWORD",str_pass );
        intent.putExtra("FARM_NAME",str_farm_name );
        intent.putExtra("PROPERTIER_NAME",str_propertier_name );
        intent.putExtra("DISTRICT",str_district_id );
        intent.putExtra("BLOCK",str_block_id );
        intent.putExtra("AREA_OF_BUSINESS",str_area_of_business );
        intent.putExtra("BUSINESS_TYPE",strbusinesstype );
        intent.putExtra("DEALS_IN_PRODUCT","Agril Product" );

        startActivity(intent);
    }


    public void onClickVeterinary(View view)
    {
        Intent intent=new Intent(this,VeterinaryItemBusiness.class);

        intent.putExtra("WEBSITE",str_website );
        intent.putExtra("MOBILE",str_mobile );
        intent.putExtra("EMAIL",str_email );
        intent.putExtra("PASSWORD",str_pass );
        intent.putExtra("FARM_NAME",str_farm_name );
        intent.putExtra("PROPERTIER_NAME",str_propertier_name );
        intent.putExtra("DISTRICT",str_district_id );
        intent.putExtra("BLOCK",str_block_id );
        intent.putExtra("AREA_OF_BUSINESS",str_area_of_business );
        intent.putExtra("BUSINESS_TYPE",strbusinesstype );
        intent.putExtra("DEALS_IN_PRODUCT","Veterinary Item" );
        startActivity(intent);
    }

    public void onClickFish(View view)
    {
        Intent intent=new Intent(this,FishBusiness.class);

        intent.putExtra("WEBSITE",str_website );
        intent.putExtra("MOBILE",str_mobile );
        intent.putExtra("EMAIL",str_email );
        intent.putExtra("PASSWORD",str_pass );
        intent.putExtra("FARM_NAME",str_farm_name );
        intent.putExtra("PROPERTIER_NAME",str_propertier_name );
        intent.putExtra("DISTRICT",str_district_id );
        intent.putExtra("BLOCK",str_block_id );
        intent.putExtra("AREA_OF_BUSINESS",str_area_of_business );
        intent.putExtra("BUSINESS_TYPE",strbusinesstype );
        intent.putExtra("DEALS_IN_PRODUCT","Fish" );
        startActivity(intent);
    }

    public void onClickOthers(View view)
    {
        Intent intent=new Intent(this,DealsinOtherBusiness.class);

        intent.putExtra("WEBSITE",str_website );
        intent.putExtra("MOBILE",str_mobile );
        intent.putExtra("EMAIL",str_email );
        intent.putExtra("PASSWORD",str_pass );
        intent.putExtra("FARM_NAME",str_farm_name );
        intent.putExtra("PROPERTIER_NAME",str_propertier_name );
        intent.putExtra("DISTRICT",str_district_id );
        intent.putExtra("BLOCK",str_block_id );
        intent.putExtra("AREA_OF_BUSINESS",str_area_of_business );
        intent.putExtra("BUSINESS_TYPE",strbusinesstype );
        intent.putExtra("DEALS_IN_PRODUCT","Others" );
        startActivity(intent);
    }
}
