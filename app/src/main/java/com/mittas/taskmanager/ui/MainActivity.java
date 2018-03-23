package com.mittas.taskmanager.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.auth.DbxUserAuthRequests;
import com.mittas.taskmanager.BuildConfig;
import com.mittas.taskmanager.R;
import com.mittas.taskmanager.ui.add.AddTaskActivity;
import com.mittas.taskmanager.ui.completed.CompletedTasksFragment;
import com.mittas.taskmanager.ui.pending.PendingTasksFragment;
import com.mittas.taskmanager.ui.upload.DropboxClientFactory;

public class MainActivity extends AppCompatActivity {
    private FragmentsPagerAdapter fragmentsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Auth.startOAuth2Authentication(MainActivity.this, BuildConfig.DROPBOX_APP_KEY);

        fragmentsPagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String accessToken = prefs.getString("access-token", null);
        if (accessToken == null) {
            accessToken = Auth.getOAuth2Token();
            if (accessToken != null) {
                prefs.edit().putString("access-token", accessToken).apply();
                initAndLoadData(accessToken);
            }
        } else {
            initAndLoadData(accessToken);
        }

        String uid = Auth.getUid();
        String storedUid = prefs.getString("user-id", null);
        if (uid != null && !uid.equals(storedUid)) {
            prefs.edit().putString("user-id", uid).apply();
        }

    }

    private void initAndLoadData(String accessToken) {
        DropboxClientFactory.init(accessToken);
    }

    public class FragmentsPagerAdapter extends FragmentPagerAdapter {
        public FragmentsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            if(position == 0) {
               PendingTasksFragment pendingTasksFragment = new PendingTasksFragment();
               return pendingTasksFragment;
            } else {
                CompletedTasksFragment completedTasksFragment = new CompletedTasksFragment();
                return completedTasksFragment;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
