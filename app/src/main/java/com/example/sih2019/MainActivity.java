package com.example.sih2019;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {


    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    FragmentTransaction ft0 = getSupportFragmentManager().beginTransaction();
                    ft0.replace(R.id.side,new BlankFragment3());
                    ft0.commit();

                    return true;
                case R.id.navigation_dashboard:

                    FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.side,new Recyclelist());
                    ft1.commit();
                    return true;
                case R.id.navigation_notifications:
                    FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                    ft4.replace(R.id.side,new Tab2Fragment());
                    ft4.commit();
//                    viewPager = (ViewPager) findViewById(R.id.viewPager);
//                    tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//                    adapter = new TabAdapter(getSupportFragmentManager());
//                    adapter.addFragment(new Recyclelist(), "Buy");
//                    adapter.addFragment(new BlankFragment(), "Sell");
//                    viewPager.setAdapter(adapter);
//                    tabLayout.setupWithViewPager(viewPager);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        adapter = new TabAdapter(getSupportFragmentManager());
//        adapter.addFragment(new Recyclelist(), "Buy");
//        adapter.addFragment(new BlankFragment(), "Sell");
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
