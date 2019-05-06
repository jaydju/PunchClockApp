package com.example.android.punchclock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class joinRoom extends AppCompatActivity {
    FirebaseDatabase database;
    private EditText keyId;
    private DatabaseReference roomRef;
    public String roomDate = "";
    public String roomTitle = "";
    public String roomTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        database = FirebaseDatabase.getInstance();
        keyId = findViewById(R.id.key_id_input);
        roomRef = database.getReference("Rooms");
    }

    public void setRoomDate(String date) {
        roomDate = date;
    }

    public void setRoomTime(String time) {
        roomTime = time;
    }

    public void setRoomTitle(String title){
        roomTitle = title;
    }

    public void enterRoom(View view){
        String idNumber = keyId.getText().toString();
        DatabaseReference childRefIdNumber = roomRef.child(idNumber);
        Intent intent = new Intent(this,  RoomDisplayer.class);


        //Get Date Element

        DatabaseReference childRefDateNumber = childRefIdNumber.child("date");
        childRefDateNumber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date = dataSnapshot.getValue(String.class);
                TextView debug = findViewById(R.id.date);
                debug.setText(date);
                setRoomDate(date);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Get Time Element
        DatabaseReference childRefTimeNumber = childRefIdNumber.child("time");
        childRefTimeNumber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String time = dataSnapshot.getValue(String.class);
                TextView debug = findViewById(R.id.time);
                debug.setText(time);
                setRoomTime(time);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Get Title Element
        DatabaseReference childTitle = childRefIdNumber.child("title");
        childTitle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.getValue(String.class);
                TextView debug = findViewById(R.id.title);
                debug.setText(title);
                setRoomTitle(title);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(!roomDate.equals("") && !roomTime.equals("") && !roomTitle.equals("")) {
            intent.putExtra("DATE_KEY", roomDate);
            intent.putExtra("DATE_TIME", roomTime);
            intent.putExtra("DATE_TITLE", roomTitle);
            startActivity(intent);
        }
    }
}
