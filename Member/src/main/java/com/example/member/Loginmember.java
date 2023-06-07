package com.example.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Loginmember extends AppCompatActivity {
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
        setContentView(R.layout.activity_loginmember);
        progressBar=findViewById(R.id.ProgressbarR);
        FailedRTextView=findViewById(R.id.FailedRTextView);
        Email=findViewById(R.id.uname);
        Pass=findViewById(R.id.upass);
        Login=findViewById(R.id.loginbut);

        i=new Intent(Loginmember.this, MainActivity.class);
        root2= FirebaseDatabase.getInstance().getReference().child("Membersbeta");
        Login.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);;
            String nn=Email.getText().toString();
            String pp=Pass.getText().toString();
            check(nn,pp);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(temp){

                        i.putExtra("name",memName);
                        i.putExtra("username",userName);
                        startActivity(i);
                        //t.setText("ID is"+l);
                    }else{
                        Toast.makeText(Loginmember.this, "Wrong inputs", Toast.LENGTH_SHORT).show();
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
                        String kk=Snapshot.child("username").getValue().toString();
                        String gg=Snapshot.child("Pass").getValue().toString();
                        if(kk.equals(n)&&gg.equals(p)){
                            temp =true;
                            memName=Snapshot.child("Name").getValue().toString();
                            userName=Snapshot.child("username").getValue().toString();

                            break;
                        }else{
                            temp =false;
                            // t.setText("Faild24");
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