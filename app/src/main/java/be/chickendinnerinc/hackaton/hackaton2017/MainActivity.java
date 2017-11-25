package be.chickendinnerinc.hackaton.hackaton2017;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentUserId = 2;
    private String serverAddress = "http://10.109.52.59:3000/";

    private MyJobsFragment myJobsFragment = new MyJobsFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private SearchJobsFragment searchJobsFragment = new SearchJobsFragment();
    private OwnedJobsFragment ownedJobsFragment = new OwnedJobsFragment();

    private android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    transaction.replace(R.id.contentFrame, profileFragment ).commit();
                    return true;
                case R.id.nav_search:
                    transaction.replace(R.id.contentFrame, searchJobsFragment ).commit();
                    return true;
                case R.id.nav_jobs:
                    transaction.replace(R.id.contentFrame, myJobsFragment ).commit();
                    return true;
                case R.id.nav_owned_jobs:
                    transaction.replace(R.id.contentFrame, ownedJobsFragment ).commit();
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

        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentFrame, profileFragment).commit();

        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("currentUserId", currentUserId);
        editor.putString("serverAddress", serverAddress);
        editor.commit();
    }

}
