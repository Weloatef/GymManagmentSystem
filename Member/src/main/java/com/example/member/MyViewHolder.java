package com.example.member;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name,id,msg,plan;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.imageview);
        name=itemView.findViewById(R.id.textname);
        id=itemView.findViewById(R.id.textid);
        msg=itemView.findViewById(R.id.textmsg);
        plan=itemView.findViewById(R.id.textplan);

    }
}
