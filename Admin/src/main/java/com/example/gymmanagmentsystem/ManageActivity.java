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

public class ManageActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton MenuButton,AdminButton,CoachesButton,MembersButton;
    LinearLayout home,manage,billing,reports,profile,aboutus;
    Bundle bundle;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        logout=findViewById(R.id.LogoutButton);

        drawerLayout = findViewById(R.id.drawerLayout);
        AdminButton =findViewById(R.id.AdminButton);
        CoachesButton=findViewById(R.id.CoachesButton);
        MembersButton=findViewById(R.id.MembersButton);
        home=findViewById(R.id.home);
        manage=findViewById(R.id.Manage);
        billing=findViewById(R.id.Billing);
        reports=findViewById(R.id.Reports);
        profile=findViewById(R.id.Profile);
        aboutus=findViewById(R.id.AboutUs);
        MenuButton=findViewById(R.id.MenuButton);
        bundle = getIntent().getExtras();

        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, MainActivity.class,bundle);
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, BillingActivity.class,bundle);
            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, ReportActivity.class,bundle);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, ProfileActivity.class,bundle);
            }
        });
        AdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, AdminManageActivity.class,bundle);
            }
        });
        CoachesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, CoachesManageActivity.class,bundle);
            }
        });
        MembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, MembersManageActivity.class,bundle);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(ManageActivity.this, LoginActivity.class,bundle);
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

    public static void redirectactivity(Activity activity, Class secondactivity,Bundle bundle){
        Intent intent=new Intent(activity,secondactivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closedrawer(drawerLayout);
    }
}