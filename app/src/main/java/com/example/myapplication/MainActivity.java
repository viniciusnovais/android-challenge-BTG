package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myapplication.adapters.ViewPagerAdapter;
import com.example.myapplication.utils.Helper;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.viewpager);
        TabLayout tabs = findViewById(R.id.tablayout);

        tabs.setupWithViewPager(pager);

        ViewPagerAdapter viewPagerAdapter =
                new ViewPagerAdapter(this, getSupportFragmentManager(), 1);
        pager.setAdapter(viewPagerAdapter);

        Helper.initialize(this);

    }


}
