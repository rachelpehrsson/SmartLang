package com.example.rhp8r.final_project_final_spearow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.fragment.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ChoicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choicesactivity);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Vocab"));
        tabLayout.addTab(tabLayout.newTab().setText("Pics"));
        tabLayout.addTab(tabLayout.newTab().setText("Audio"));
        tabLayout.addTab(tabLayout.newTab().setText("Links"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TabAdapter adapter = new TabAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        //fragment.onActivityResult(requestCode, resultCode, data);
//        boolean processed = true;
//
//        if (resultCode == this.RESULT_OK)
//        {
//            if (requestCode == 0) {
//                // Something
//            } else if (requestCode == 1) {
//                // Something
//            } else {
//                processed = false;
//            }
//        } else { // Error
//            if (requestCode == 0) {
//                // Handle error
//            } else {
//                processed = false;
//            }
//        }
//
//        if (!processed) {
//            this.onActivityResult(requestCode, resultCode, data);
//            //fragment2.onActivityResult(requestCode, resultCode, data);
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}