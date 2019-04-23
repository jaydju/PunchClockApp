package com.example.android.punchclock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference userReference;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
