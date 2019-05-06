package com.example.android.punchclock;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText userNameEditText;
    private FirebaseAuth auth;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        passwordEditText = (EditText) findViewById(R.id.edit_text_password);
        userNameEditText = (EditText) findViewById(R.id.edit_text_name);
        auth = FirebaseAuth.getInstance();
    }

    //Logging User In
    public void logIn(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(login.this, task.getResult().getUser().getEmail() + " logged in successful",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

    //Signing User Up
    public void signUp(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(login.this, task.getResult().getUser().getEmail() + " signed up successful",
                                    Toast.LENGTH_SHORT).show();
                            addUserNameToUser(task.getResult().getUser());
                            finish();
                        }
                    }
                });
    }

    private void addUserNameToUser(FirebaseUser user) {
        String username = userNameEditText.getText().toString();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }

    private void addName(String userName){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        }
                    }
                });
    }

    public void userSignUp(View view) {
        EditText userName = findViewById(R.id.edit_text_name);
        userName.setVisibility(View.VISIBLE);
        Button submitSignup = findViewById(R.id.submit_signup);
        submitSignup.setVisibility(View.VISIBLE);
        Button signIn = findViewById(R.id.signin);
        signIn.setVisibility(View.GONE);
        Button signUp = findViewById(R.id.signup);
        signUp.setVisibility(View.GONE);
        Button backButton = findViewById(R.id.back);
        backButton.setVisibility(View.VISIBLE);
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.good_night_img);

    }

    public void back(View view){
        userNameEditText.setVisibility(View.GONE);
        Button submitSignup = findViewById(R.id.submit_signup);
        submitSignup.setVisibility(View.GONE);
        Button signIn = findViewById(R.id.signin);
        signIn.setVisibility(View.VISIBLE);
        Button signUp = findViewById(R.id.signup);
        signUp.setVisibility(View.VISIBLE);
        Button backButton = findViewById(R.id.back);
        backButton.setVisibility(View.GONE);
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.good_morning_img);

    }
}
