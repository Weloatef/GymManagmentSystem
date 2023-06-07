package com.example.gym2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class message extends AppCompatActivity {

    int messcounter=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setTitle("Notes");
        DatabaseReference firebase;

        TextView text=findViewById(R.id.droptxt);
        EditText edittxt=findViewById(R.id.dropedittxt);
        Button cancelbtn=findViewById(R.id.canceldropid);
        Button dropbtn=findViewById(R.id.dropbtn);
        String message="message";

        message = message.concat(Integer.toString(messcounter));



        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String Name= bundle.getString("Name");
            String username= bundle.getString("username");
            text.setText("Drop Note to "+Name);

            firebase= FirebaseDatabase.getInstance().getReference().child("Membersbeta").child(username).child("Messages").child("message"+messcounter);

            dropbtn.setOnClickListener(view ->{
                messcounter++;

                String messag= edittxt.getText().toString();
                firebase.setValue(messag);
                Intent intent=new Intent(this,memberlist.class);
                startActivity(intent);
            });


            cancelbtn.setOnClickListener(view ->{
                Intent intent=new Intent(this,memberlist.class);
                startActivity(intent);
            });


        }
}
}