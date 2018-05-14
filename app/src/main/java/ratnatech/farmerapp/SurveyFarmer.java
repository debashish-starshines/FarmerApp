package ratnatech.farmerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ratnatech.farmerapp.FragmentSurvey.FiveFragment;
import ratnatech.farmerapp.FragmentSurvey.FourFragment;
import ratnatech.farmerapp.FragmentSurvey.OneFragment;
import ratnatech.farmerapp.FragmentSurvey.ThreeFragment;
import ratnatech.farmerapp.FragmentSurvey.TwoFragment;

public class SurveyFarmer extends AppCompatActivity {

    private static final int TIME_OUT = 1000;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int position=0;
    LinearLayout layoutprevnext,layoutsubmit;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    String flag="";

    String YOUR_STRING="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_survey_farmer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Survey Form");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        layoutprevnext=findViewById(R.id.layoutprevnext);
        layoutsubmit=findViewById(R.id.layoutsubmit);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

      /*  tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);*/

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        flag=sharedpreferences.getString("FLAG", "");
        if (flag.equals("1"))
        {
            Toast.makeText(this, "Survey has been done", Toast.LENGTH_SHORT).show();
            layoutsubmit.setVisibility(View.GONE);
            layoutprevnext.setVisibility(View.GONE);
            onBackPressed();
        }


    }
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());//  getSupportFragmentManager()
        adapter.addFragment(new OneFragment(),"S1");// "ONE"
        adapter.addFragment(new TwoFragment(),"S2");// "TWO"
        adapter.addFragment(new ThreeFragment(),"S3");// "THREE"
        adapter.addFragment(new FourFragment(),"S4");// "FOUR"
        adapter.addFragment(new FiveFragment(),"S5");// "FIVE"
         viewPager.setAdapter(adapter);

    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {//  FragmentPagerAdapter
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void MoveNext(View view) {
        //it doesn't matter if you're already in the last item

       viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        position=viewPager.getCurrentItem()+1;

        if(position==5)
        {
          layoutprevnext.setVisibility(View.GONE);
          layoutsubmit.setVisibility(View.VISIBLE);
        }
    }

    public void MovePrevious(View view) {
        //it doesn't matter if you're already in the first item
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

    }

    public void Submit(View view)
    {
         Toast.makeText(this,"Form Submitted Successfully", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedpreferences.edit();  //deb done code for one time login
        editor.putString("FLAG", "1");
        editor.commit();

        onBackPressed();

    }

    public String sendData() {
        return YOUR_STRING;
    }
}
