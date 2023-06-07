package com.example.gym2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class plan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        setTitle("Plan");
        DatabaseReference firebase;

        TextView text=findViewById(R.id.plantxt);
        EditText edittxt=findViewById(R.id.myEditText);
        Button cancelbtn=findViewById(R.id.cancelid);
        Button applybtn=findViewById(R.id.applyid);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String Name= bundle.getString("Name");
            String username= bundle.getString("username");
            text.setText(Name+"'s Plan");

            firebase= FirebaseDatabase.getInstance().getReference().child("Membersbeta").child(username).child("plan");




            cancelbtn.setOnClickListener(view ->{
                Intent intent=new Intent(this,memberlist.class);
                startActivity(intent);
            });
            applybtn.setOnClickListener(view ->{
                String plann= edittxt.getText().toString();

                firebase.setValue(plann);

                Intent intent=new Intent(this,memberlist.class);
                startActivity(intent);
            });

        }
    }
}