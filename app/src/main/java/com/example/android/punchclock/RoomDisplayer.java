package com.example.android.punchclock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomDisplayer extends AppCompatActivity {
    private DatabaseReference roomRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_displayer);
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        roomRef = database.getReference("Rooms");

        Intent myIntent = getIntent();

        String name = "";
        String date = myIntent.getStringExtra("DATE_KEY");
        String time = myIntent.getStringExtra("DATE_TIME");
        String title = myIntent.getStringExtra("DATE_TITLE");
        String idNumber = myIntent.getStringExtra("ID_NUMBER");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            name = user.getDisplayName();
        }

//        DatabaseReference childRefIdNumber = roomRef.child(idNumber);
//        DatabaseReference childRefDateNumber = childRefIdNumber.child("date");
//        childRefDateNumber.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String date = dataSnapshot.getValue(String.class);
//                TextView debug = findViewById(R.id.date);
//                debug.setText(date);
//                setRoomDate(date);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        //Get Time Element
//        DatabaseReference childRefTimeNumber = childRefIdNumber.child("time");
//        childRefTimeNumber.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String time = dataSnapshot.getValue(String.class);
//                TextView debug = findViewById(R.id.time);
//                debug.setText(time);
//                setRoomTime(time);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        //Get Title Element
//        DatabaseReference childTitle = childRefIdNumber.child("title");
//        childTitle.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String title = dataSnapshot.getValue(String.class);
//                TextView debug = findViewById(R.id.title);
//                debug.setText(title);
//                setRoomTitle(title);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        TextView dateDisplay = findViewById(R.id.date_displayer);
        dateDisplay.setText(date);

        TextView timeDisplay = findViewById(R.id.time_displayer);
        timeDisplay.setText(time);

        TextView titleDisplay = findViewById(R.id.title_displayer);
        titleDisplay.setText(title);

        TextView userDisplay = findViewById(R.id.user_displayer);
        userDisplay.setText(name);
    }

//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    public void goMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
