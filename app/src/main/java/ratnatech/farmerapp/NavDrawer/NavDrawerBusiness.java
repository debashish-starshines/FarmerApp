package ratnatech.farmerapp.NavDrawer;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import ratnatech.farmerapp.EditProfileActivity.EditProfileBusiness;
import ratnatech.farmerapp.EditProfileActivity.EditProfileFarmer2;
import ratnatech.farmerapp.Fragment.KnowledgeBank;
import ratnatech.farmerapp.Login;
import ratnatech.farmerapp.R;

public class NavDrawerBusiness extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView keyname;
    String User="";
    ImageView imgeditProfile;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer_business);
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
                startActivity(new Intent(NavDrawerBusiness.this,EditProfileBusiness.class));
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            User = bundle.getString("keyName");
            keyname.setText(User);
        }
        keyname.setText(User);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.qna);
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
                editor.commit(); // commit changes
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer_business, menu);
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
                            startActivity(new Intent(NavDrawerBusiness.this,Login.class));
                            finish();
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
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.qna:

                break;
            case R.id.message:


                break;
            case R.id.survey:

                break;
            case R.id.productpromotion:

                break;

            case R.id.forum:

                break;
            case R.id.contact:

                break;


            case R.id.knowledgebank:
                fragment = new KnowledgeBank();
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
