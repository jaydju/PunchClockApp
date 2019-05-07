package com.example.android.punchclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class RoomDisplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_displayer);

        Intent myIntent = getIntent();
        String date = myIntent.getStringExtra("DATE_KEY");
        String time = myIntent.getStringExtra("DATE_TIME");
        String title = myIntent.getStringExtra("DATE_TITLE");

        TextView dateDisplay = findViewById(R.id.date_displayer);
        dateDisplay.setText(date);

        TextView timeDisplay = findViewById(R.id.time_displayer);
        timeDisplay.setText(time);

        TextView titleDisplay = findViewById(R.id.title_displayer);
        titleDisplay.setText(title);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void roomDisplayerBackToHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
