package com.example.android.punchclock;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.DateFormat;
import java.util.Calendar;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.UUID;

public class createRoom extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    FirebaseDatabase database;
    DatabaseReference userReference;
    private String uniqueId;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference roomRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        roomRef = database.getReference("Rooms");

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                userReference = database.getReference(user.getUid());
                }
        };
    }

    public void setDate(View v){
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "Date Picker");
    }

    public void setTime(View v){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Time Picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.chosen_time);
        textView.setText(hourOfDay + ":" + minute);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.chosen_date);
        textView.setText(currentDateString);

    }


    public void generateNumber(View view){
        UUID idOne = UUID.randomUUID();
        String id = String.valueOf(idOne);
        String randomId = id.substring(0, 5);
        TextView string = (TextView) findViewById(R.id.unique_key_id);
        string.setText(randomId);
        uniqueId = randomId;
    }

    //Push Room Object to Firebase
    public void sendToFirebase(View view) {
        String name = "";
        TextView chosenTime = findViewById(R.id.chosen_time);
        String time = chosenTime.getText().toString();

        TextView chosenDate = findViewById(R.id.chosen_date);
        String date = chosenDate.getText().toString();

        EditText roomTitle = findViewById(R.id.room_title);
        String title = roomTitle.getText().toString();

        TextView ID = findViewById(R.id.unique_key_id);
        String uniqueID = ID.getText().toString();

        TextView location = findViewById(R.id.chosen_location);
        String locationCoord = location.getText().toString();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            name = user.getDisplayName();
        }

        //Pushing Elements to a New Room
        roomRef.child(uniqueID).setValue(new Room(uniqueID, title, date, time, name, locationCoord));

        Intent intent = new Intent();
        intent.putExtra("validID",uniqueID);
        setResult(RESULT_OK,intent);
        finish();
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

    public void setLocation(View view) {


        Intent intent = new Intent(createRoom.this,MapsActivity.class);
        startActivityForResult(intent,60);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 60)
        {
            String latitude = data.getStringExtra("latitude");
            String longitude = data.getStringExtra("longitude");

            TextView chosenLocation = findViewById(R.id.chosen_location);
            chosenLocation.append(" " +latitude+", "+longitude);
        }
    }
}
