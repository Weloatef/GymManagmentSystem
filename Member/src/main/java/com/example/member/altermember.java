package com.example.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class altermember extends AppCompatActivity {
    private EditText name;
    Bundle b=null;
    private EditText Pass;
    private Button update;
    String username;

    private ProgressBar progressBar;
    DatabaseReference root2;
    boolean temp;
    String memName;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altermember);
        name=findViewById(R.id.uname);
        Pass=findViewById(R.id.upass);
        update=findViewById(R.id.updatealter);
        progressBar=findViewById(R.id.ProgressbarRR);
        b=getIntent().getExtras();
        if(b!=null){

            username=b.getString("username");

        }
        root2= FirebaseDatabase.getInstance().getReference().child("Membersbeta").child(username);

        update.setOnClickListener(v -> {
            Map<String, Object> updates = new HashMap<>();
            updates.put("Name", name.getText().toString());
            updates.put("Pass", Pass.getText().toString());
            root2.updateChildren(updates)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(altermember.this, "update successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(altermember.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
        });



    }
}