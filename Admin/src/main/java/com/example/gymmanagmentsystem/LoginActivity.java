package com.example.gymmanagmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Pass;
    private Button Login;
    private TextView FailedTextView;
    private ProgressBar progressBar;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar=findViewById(R.id.Progressbar);
        FailedTextView=findViewById(R.id.FailedTextView);
        Username=findViewById(R.id.Username);
        Pass=findViewById(R.id.Password);
        Login=findViewById(R.id.loginButton);
        TextView Register = findViewById(R.id.RegisterTextView);
        String RegisterText="Don't have an account? Register here.";
        SpannableString ss=new SpannableString(RegisterText);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent RegisterIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(RegisterIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan1,23,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Register.setText(ss);
        Register.setMovementMethod(LinkMovementMethod.getInstance());


        Login.setOnClickListener(v -> {

            progressBar.setVisibility(View.VISIBLE);
            if (!validateUsername() | !validatePassword()) {
            } else {
                checkUser();
            }

        });
    }


    public Boolean validateUsername(){
        String val= Username.getText().toString();
        if (val.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this, "Empty Input!", Toast.LENGTH_SHORT).show();
            FailedTextView.setTextColor(Color.RED);
            FailedTextView.setText("Please enter your username");
            return false;
        } else {
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = Pass.getText().toString();
        if (val.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this, "Empty Input!", Toast.LENGTH_SHORT).show();
            FailedTextView.setTextColor(Color.RED);
            FailedTextView.setText("Please enter your password");
            return false;
        } else {
            return true;
        }
    }

    public void checkUser(){
        String userUsername = Username.getText().toString().trim();
        String userPassword = Pass.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Admins");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                if (snapshot.exists()){
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if (Objects.equals(passwordFromDB,userPassword)) {
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        String phonenumberFromDB = snapshot.child(userUsername).child("phoneNumber").getValue(String.class);
                        String imageFromDB = snapshot.child(userUsername).child("image").getValue(String.class);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("image", imageFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("phoneNumber", phonenumberFromDB);

                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                        FailedTextView.setTextColor(Color.RED);
                        FailedTextView.setText("Please check your password & Try Again");
                        Pass.requestFocus();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "There is no users with this username", Toast.LENGTH_SHORT).show();
                    FailedTextView.setTextColor(Color.RED);
                    FailedTextView.setText("Please be aware that this is the admin only application, we don't have an admin with this username");
                    Username.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}