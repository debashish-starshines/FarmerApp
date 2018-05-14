package ratnatech.farmerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class VeterinaryItemBusiness extends AppCompatActivity {

    Button cowID,chickID,goatID,buffaloID;

    String str_farm_name,str_website,str_mobile,str_pass,str_email,str_propertier_name,str_district_id,str_block_id,
            str_area_of_business,strbusinesstype,str_deals_in_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinary_item_business);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        cowID=findViewById(R.id.cowID);
        chickID=findViewById(R.id.chickID);
        goatID=findViewById(R.id.goatID);
        buffaloID=findViewById(R.id.buffaloID);
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
            str_deals_in_product=bundle.getString("DEALS_IN_PRODUCT");

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
            Log.d("DEALS_IN_PRODUCT",str_deals_in_product );
        }
    }


    public void onClickCow(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Cow");

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

    public void onClickChick(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Chick");

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
    public void onClickGoat(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Goat");

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

    public void onClickBuffalo(View view)
    {
        Intent intent=new Intent(this,PopUpVAnimal.class);
        intent.putExtra("ANIMAL", "Buffalo");

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
}
