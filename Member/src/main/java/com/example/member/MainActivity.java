package com.example.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    boolean temp=false ;
    Intent i;
    Bundle b=null;
    String username;
    String coachname="d7k";
    String endsub,msg,plan;

    TextView t;
    Button Show;
    Button Manage;
    Button updated;
    RecyclerView Sch;
    DatabaseReference root;
    DatabaseReference root2;
    String mname;
    List<String> list = new ArrayList<>();
    //String testname="abdo";
    //String testnpass="123";
    int testid;
    List<item> items=new ArrayList<item>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=findViewById(R.id.textView);
        Show=findViewById(R.id.show);
        Manage=findViewById(R.id.manage);
        updated=findViewById(R.id.updatambut);
        Sch=findViewById(R.id.Schedule);
        i=new Intent(this, altermember.class);
        root2= FirebaseDatabase.getInstance().getReference().child("Membersbeta");



        b=getIntent().getExtras();
        if(b!=null){
            mname=b.getString("name");
            username=b.getString("username");
            t.setText(mname);
        }
        root= FirebaseDatabase.getInstance().getReference().child("Membersbeta").child(username);
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               coachname= snapshot.child("coach").getValue().toString();
               msg= snapshot.child("message").getValue().toString();
               plan=snapshot.child("plan").getValue().toString();
               endsub= snapshot.child("subscritption end date").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                root.child("schedules").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for (DataSnapshot Snapshot : snapshot.getChildren()) {
                                String object = Snapshot.getValue(String.class);
                                list.add(object);
                                items.add(new item(coachname,object,R.drawable.man,msg,plan));
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // t.setText("Faild22");

                    }

                });
                // Code to execute after delay
            }
        }, 2000);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

// Display the current date
        String currentDate = (month + 1) + "/" + day + "/" + year;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentDate.equals(endsub)){
                    NotificationUtils.showNotification(MainActivity.this, "Subscription alert", "Your subscription ends today!");}
                // Code to execute after delay
            }
        }, 3000);





        Manage.setOnClickListener(v -> {


            Sch.setLayoutManager(new LinearLayoutManager(this));
            Sch.setAdapter(new MyAdapter(getApplicationContext(),items));


        });
        Show.setOnClickListener(v -> {

            Toast.makeText(this, endsub, Toast.LENGTH_SHORT).show();
           // check(testname,testnpass);


           // if(temp){
               // String l=Integer.toString(testid);
                //t.setText("ID is"+l);
           //}
        });

        updated.setOnClickListener(v -> {

            i.putExtra("username",username);
            startActivity(i);

        });
    }

    public void check(String n,String p){



        root2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot Snapshot : snapshot.getChildren()) {
                        String kk=Snapshot.child("Name").getValue().toString();
                        String gg=Snapshot.child("Pass").getValue().toString();
                       if(kk.equals(n)&&gg.equals(p)){
                            temp =true;
                            testid=Snapshot.child("ID").getValue(int.class);
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
