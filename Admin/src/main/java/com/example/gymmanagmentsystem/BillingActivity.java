package com.example.gymmanagmentsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BillingActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton MenuButton;
    LinearLayout home,manage,billing,reports,profile,aboutus;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        logout=findViewById(R.id.LogoutButton);

        drawerLayout = findViewById(R.id.drawerLayout);
        home=findViewById(R.id.home);
        manage=findViewById(R.id.Manage);
        billing=findViewById(R.id.Billing);
        reports=findViewById(R.id.Reports);
        profile=findViewById(R.id.Profile);
        aboutus=findViewById(R.id.AboutUs);
        MenuButton=findViewById(R.id.MenuButton);
        Button CalcButton = findViewById(R.id.CalcButton);
        TextView reminderView = findViewById(R.id.view_text);
        EditText Amount_EditText = findViewById(R.id.Amount_EditText);
        EditText Need_EditTExt = findViewById(R.id.Need_EditTExt);


        CalcButton.setOnClickListener(view -> {

            int no1 = Integer.parseInt(Amount_EditText.getText().toString());
            int no2 = Integer.parseInt(Need_EditTExt.getText().toString());
            int result = no1 - no2 ;
            String str = ""+result;
            reminderView.setText(str );


        });



        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(BillingActivity.this, MainActivity.class);
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(BillingActivity.this, ManageActivity.class);
            }
        });
        billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(BillingActivity.this, ReportActivity.class);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(BillingActivity.this, ProfileActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectactivity(BillingActivity.this, LoginActivity.class);
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