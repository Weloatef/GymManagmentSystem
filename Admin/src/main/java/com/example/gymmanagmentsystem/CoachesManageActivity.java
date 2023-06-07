package com.example.gymmanagmentsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CoachesManageActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton MenuButton;
    Button AddCButton;
    LinearLayout home,manage,billing,reports,profile,aboutus;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coaches_manage);
        logout=findViewById(R.id.LogoutButton);

        drawerLayout = findViewById(R.id.drawerLayout);
        AddCButton=findViewById(R.id.AddCButton);
        home=findViewById(R.id.home);
        manage=findViewById(R.id.Manage);
        billing=findViewById(R.id.Billing);
        reports=findViewById(R.id.Reports);
        profile=findViewById(R.id.Profile);
        aboutus=findViewById(R.id.AboutUs);
        MenuButton=findViewById(R.id.MenuButton);
        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(CoachesManageActivity.this, MainActivity.class);
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(CoachesManageActivity.this, ManageActivity.class);
            }
        });
        billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(CoachesManageActivity.this, BillingActivity.class);
            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(CoachesManageActivity.this, ReportActivity.class);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(CoachesManageActivity.this, ProfileActivity.class);
            }
        });
        AddCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(CoachesManageActivity.this, CoachRegisterActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(CoachesManageActivity.this, LoginActivity.class);
            }
        });
    }

    public static void opendrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closedrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        };
    }

    public static void redirectactivity(Activity activity, Class secondactivity){
        Intent intent=new Intent(activity,secondactivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closedrawer(drawerLayout);
    }
}