package com.example.android.punchclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        TextView userName = findViewById(R.id.user_name);

        if (user1 != null) {
            userName.setText(user1.getDisplayName());
            TextView punchClockId = findViewById(R.id.punchclock_id);
            punchClockId.setText("PunchClock ID: " + user1.getUid());
        }
    }
}
