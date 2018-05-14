package ratnatech.farmerapp.NavDrawer;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ratnatech.farmerapp.CentralGovtExtenOff;
import ratnatech.farmerapp.EditProfileActivity.EditProfileFarmer;
import ratnatech.farmerapp.Farmer_ViewQuestion.ViewQuestionList_Farmer;
import ratnatech.farmerapp.Fragment.Ask;
import ratnatech.farmerapp.Fragment.Contact;
import ratnatech.farmerapp.Fragment.KnowledgeBank;
import ratnatech.farmerapp.Fragment.MyStory;
import ratnatech.farmerapp.Fragment.PurchaseSellHireRepair;
import ratnatech.farmerapp.IcarExtenOff;
import ratnatech.farmerapp.InServiceGPN;
import ratnatech.farmerapp.Login;
import ratnatech.farmerapp.R;
import ratnatech.farmerapp.StateGovtExtenOff;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView keyname;
    ImageView imgeditProfile;
    String User="";
    String strsurveyboolean="true";

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";


    MenuItem item;
    String user_id;

    String mystory="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View header=navigationView.getHeaderView(0);
        //View view=navigationView.inflateHeaderView(R.layout.nav_header_nav_drawer);
        keyname = header.findViewById(R.id.keyname);
        imgeditProfile=header.findViewById(R.id.imgeditProfile);
        imgeditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavDrawer.this,EditProfileFarmer.class));
            }
        });


         Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

      /*  Intent intent2 = getIntent();
        Bundle bundle2 = intent.getExtras();
*/
        if(bundle != null){
            User = bundle.getString("keyName");
            user_id=bundle.getString("user_id");
            keyname.setText(User);
        }


      /*  if(bundle2 != null)
        {
            strsurveyboolean = bundle.getString("SURVEYMENU");

        }*/
       keyname.setText(User);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.ask,item);


    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
*/
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), "Please click BACK again to exit", Snackbar.LENGTH_LONG);

        snackbar.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
                //Logout session for user id
                sharedpreferences = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("USER_ID"); // will delete key email
                editor.remove("MY_STORY"); // will delete key email

                editor.commit(); // commit changes



            }
        }, 2000);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);//

       /* MenuItem surveyMenuItem = menu.findItem(R.id.survey);
        Toast.makeText(this, "Entered ", Toast.LENGTH_SHORT).show();
       // Toast.makeText(NavDrawer.this, surveyMenuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
        if(strsurveyboolean.equals("false"))
        {
           // surveyMenuItem.setVisible(false);

        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //Uncomment the below code to Set the message and title from the strings.xml file
            //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

            //Setting message manually and performing action on button click
            builder.setMessage("Do you want to log out ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                           //Logout session for user id
                            sharedpreferences = getSharedPreferences(mypreference,
                                    Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.remove("USER_ID"); // will delete key email
                            editor.remove("FLAG");// SEE IN LOGIN ACTIVITY
                            editor.commit(); // commit changes


                            startActivity(new Intent(NavDrawer.this,Login.class));
                            finish();
                       /*  Intent intent=new Intent(NavDrawer.this,Login.class);
                         intent.putExtra("LOGOUT","true");
                         startActivity(intent);
                         finish();*/
                           // android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                        }
                    });

            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Log Out");
            alert.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

       // item.setVisible(false);
        displaySelectedScreen(item.getItemId(),item);

        return true;
    }

    private void displaySelectedScreen(int itemId,MenuItem item) {

        //creating fragment object
        Fragment fragment = null;
            Fragment fragment2=null;
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.ask:

                // REMOVE THE MYSTORY KEY AT START

                //Logout session for user id
                if(mystory!="")
                {
                    sharedpreferences = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.remove("MY_STORY"); // will delete key email
                    editor.commit(); // commit changes
                }

                fragment = new Ask();
                break;

            case R.id.viewans:

                Intent intent=new Intent(this, ViewQuestionList_Farmer.class);
                startActivity(intent);

                break;
            case R.id.hire:
                fragment = new PurchaseSellHireRepair();
                break;
            case R.id.mystory:




                final CharSequence[] items = { "Success Story", "Failure Story" };
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NavDrawer.this);
                alertDialog.setCancelable(false);
                // Setting Dialog Title
                alertDialog.setTitle("Select Story Type...");

                // Setting Dialog Message
                //  alertDialog.setMessage("");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.success);

                alertDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("Success Story"))
                        {
                            Toast.makeText(NavDrawer.this, "Success Story", Toast.LENGTH_SHORT).show();
                            mystory="success";

                            sharedpreferences = getSharedPreferences(mypreference,
                                    Context.MODE_MULTI_PROCESS);
                            SharedPreferences.Editor editor = sharedpreferences.edit();  //deb done code for one time login
                            editor.putString("MY_STORY", mystory);
                            editor.commit();

                        }
                        else if (items[which].equals("Failure Story"))
                        {
                            Toast.makeText(NavDrawer.this, "Failure Story", Toast.LENGTH_SHORT).show();
                            mystory="failure";

                            sharedpreferences = getSharedPreferences(mypreference,
                                    Context.MODE_MULTI_PROCESS);
                            SharedPreferences.Editor editor = sharedpreferences.edit();  //deb done code for one time login
                            editor.putString("MY_STORY", mystory);
                            editor.commit();
                        }

                    }
                });

                // Showing Alert Message
                alertDialog.show();

                fragment = new MyStory();
                break;

            case R.id.knowledgebank:
                fragment = new KnowledgeBank();
                break;
            case R.id.contact:
                fragment = new Contact();
                break;
            case R.id.openforum:
                Toast.makeText(this, "Open Forum Fragment", Toast.LENGTH_SHORT).show();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
