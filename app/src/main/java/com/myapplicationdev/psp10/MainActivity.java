package com.myapplicationdev.psp10;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    Button later;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = findViewById(R.id.viewpager1);
        later = findViewById(R.id.btnRead);


        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new MyFragmentPagerAdapter(fm, al);

        vPager.setAdapter(adapter);

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,5);

                Intent intent = new Intent (MainActivity.this, ScheduledNotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,12345,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.previous) {
            if  (vPager.getCurrentItem() > 0){
                int previousPage = vPager.getCurrentItem() -1 ;
                vPager.setCurrentItem(previousPage,true);
            }
            return true;
        }

        if (id == R.id.random) {
            int max = vPager.getChildCount();
            int min = vPager.getCurrentItem() -1 ;
            Random rand = new Random();
            int r = rand.nextInt((max - min) + 1) + min;
            if  (vPager.getCurrentItem() == r){
                int randomPage = vPager.getCurrentItem() ;
                vPager.setCurrentItem(randomPage,true);
            }
            return true;
        }

        if (id == R.id.next) {
            int max = vPager.getChildCount();
            if  (vPager.getCurrentItem() < max- 1){
                int nextPage = vPager.getCurrentItem() +1 ;
                vPager.setCurrentItem(nextPage,true);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

