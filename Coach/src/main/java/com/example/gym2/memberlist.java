package com.example.gym2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class memberlist extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView text;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlist);
        TextView text=findViewById(R.id.membtext);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String Name = bundle.getString("Name");
            String username = bundle.getString("username");
            text.setText(Name + "'s Members");

            recyclerView = (RecyclerView) findViewById(R.id.rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            DatabaseReference membersRef = FirebaseDatabase.getInstance().getReference().child("Membersbeta");
            Query query = membersRef.orderByChild("coach").equalTo(Name);

            FirebaseRecyclerOptions<Member> options = new FirebaseRecyclerOptions.Builder<Member>()
                    .setQuery(query, Member.class)
                    .build();

            /*FirebaseRecyclerOptions<Member> options =
                    new FirebaseRecyclerOptions.Builder<Member>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Membersbeta").child(username), Member.class)
                            .build();*/

            myAdapter = new MyAdapter(options);
            recyclerView.setAdapter(myAdapter);




        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}