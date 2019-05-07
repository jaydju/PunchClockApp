package com.example.android.punchclock;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Timer;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;


import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FirebaseDatabase database;
    DatabaseReference userReference;

    private FirebaseAuth auth;
    private boolean activeRoom;
    private FirebaseAuth.AuthStateListener authListener;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    userReference = database.getReference(user.getUid());
                }
                else {
                    startActivity(new Intent(MainActivity.this, login.class));
                }
            }
        };

        //Tab View Adapter
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //Adding Dividers to TabLayout
        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.GRAY);
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);

        Timer t = new Timer();
        createTabIcons();


        //Setting Up Profile Page Fragment (Null Pointer Exception?)
//        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
//        TextView userName = findViewById(R.id.user_name_tab);
//
//        if (user1 != null) {
//            String user = (user1.getDisplayName());
//            if (!user.equals("")){
//                userName.setText(user);
//            }
//            TextView punchClockId = findViewById(R.id.punchclock_id_tab);
//            punchClockId.setText("PunchClock ID: " + user1.getUid());
//        }

    }
    private void createTabIcons(){
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Discover");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_compass_grey_24dp, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Join/Create");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_joincreate_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Upcoming");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_alarm_24dp, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView profileTab = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        profileTab.setText("Previous");
        profileTab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_profile_24dp, 0, 0);
        tabLayout.getTabAt(3).setCustomView(profileTab);
    }
    public void joinRoom(View view){
        Intent intent = new Intent(this, joinRoom.class);
        startActivity(intent);
    }

    public void createRoom(View view){
        Intent intent = new Intent(this, createRoom.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    public void logOut(MenuItem item){auth.signOut();}

    public void showProfile(MenuItem item){
        Intent intent = new Intent(this, profilePage.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
