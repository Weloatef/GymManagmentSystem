package com.example.gym2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.AbstractResolvableFuture;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MyAdapter extends FirebaseRecyclerAdapter<Member,MyAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    ArrayList<Member> Members;


    public MyAdapter(@NonNull FirebaseRecyclerOptions<Member> options) {
        super(options);
    }

    //buttonClickListener buttonClickListener;

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Member model) {

        holder.Coach.setText(model.getCoach());
        holder.Name.setText(model.getName());
        holder.username.setText(model.getUsername());


        //holder.position=position;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        //return new myViewHolder(view,buttonClickListener);
       return new myViewHolder(view);

    }

    class myViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{

        TextView Name ,Coach,username;
        ImageButton plan,mess;
        int position;
        Dialog mDialog;

 //       buttonClickListener buttonClickListener;


        public myViewHolder(@NonNull View itemView ){
            super(itemView);
            Name=(TextView) itemView.findViewById(R.id.acname);
            Coach=(TextView) itemView.findViewById(R.id.accoach);
            username=(TextView) itemView.findViewById(R.id.acusrname);
            plan=(ImageButton) itemView.findViewById(R.id.planbtn);
            mess=(ImageButton) itemView.findViewById(R.id.messagebtn);

            plan.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    /*DatabaseReference firebase;
                    String userrname=username.getText().toString();
                    firebase= FirebaseDatabase.getInstance().getReference().child("Membersbeta").child(userrname).child("plan");*/

                    Context context = v.getContext();
                    int position = getBindingAdapterPosition();
                    Intent intent=new Intent(context, plan.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("Name",Name.getText().toString());
                    bundle.putString("username",username.getText().toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            mess.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    Context context = v.getContext();
                    int position = getBindingAdapterPosition();

                    Intent intent=new Intent(context, message.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("Name",Name.getText().toString());
                    bundle.putString("username",username.getText().toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }



    }





}
