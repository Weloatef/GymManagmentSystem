package com.example.gym2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    TextView gym;
    LottieAnimationView coach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gym=findViewById(R.id.gym);
        coach=findViewById(R.id.coach);
        gym.animate().translationY(-2200).setDuration(2700).setStartDelay(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), coachlogin.class);
                startActivity(i);


            }
        }, 5000);

    }
}