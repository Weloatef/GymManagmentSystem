/*package com.example.gym2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class coachlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachlogin);
    }
}*/
package com.example.gym2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
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
import com.google.firebase.database.ValueEventListener;


import android.os.Bundle;

public class coachlogin extends AppCompatActivity {
    private EditText Email;
    Intent i;
    private EditText Pass;
    private Button Login;
    private TextView FailedRTextView;
    private ProgressBar progressBar;
    DatabaseReference root2;
    boolean temp;
    String memName;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coachlogin);
        progressBar=findViewById(R.id.ProgressbarR);
        FailedRTextView=findViewById(R.id.FailedRTextView);
        Email=findViewById(R.id.EmailR);
        Pass=findViewById(R.id.PasswordR);
        Login=findViewById(R.id.RegisterButton);

        i=new Intent(coachlogin.this, memberlist.class);
        root2= FirebaseDatabase.getInstance().getReference().child("Coaches");
        Login.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);;
            String nn=Email.getText().toString();
            String pp=Pass.getText().toString();
            check(nn,pp);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(temp){

                        i.putExtra("Name",memName);
                        i.putExtra("username",userName);
                        startActivity(i);
                        //t.setText("ID is"+l);
                    }else{
                        Toast.makeText(coachlogin.this, "Wrong credentials", Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);
                    }
                    // Code to execute after delay
                }
            }, 3000);



        });




    }
    public void check(String n,String p){



        root2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot Snapshot : snapshot.getChildren()) {
                        String kk=Snapshot.child("UserName").getValue().toString();
                        String gg=Snapshot.child("Pass").getValue().toString();
                        if(kk.equals(n)&&gg.equals(p)){
                            temp =true;
                            memName=Snapshot.child("Name").getValue().toString();
                            userName=Snapshot.child("UserName").getValue().toString();

                            break;
                        }else{
                            temp =false;
                            Toast.makeText(coachlogin.this, "kkkkkk", Toast.LENGTH_SHORT).show();

                            //t.setText("Faild24");
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // t.setText("Faild");

            }

        });


    }
}