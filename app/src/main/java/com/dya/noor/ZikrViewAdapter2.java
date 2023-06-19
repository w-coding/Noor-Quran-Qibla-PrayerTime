package com.dya.noor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZikrViewAdapter2 extends RecyclerView.Adapter<ZikrViewAdapter2.MyViewHolder> {

    ArrayList  aArZ ;
    Context context;
    public ZikrViewAdapter2(Context ct , ArrayList aArZ ){
        context = ct;
       this.aArZ =aArZ;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_dhikr_view_row2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.zAr.setText(String.valueOf(aArZ.get(position)));


    }

    @Override
    public int getItemCount() {
        return aArZ.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView zAr;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            zAr= itemView.findViewById(R.id.zAr);
            constraintLayout = itemView.findViewById(R.id.zikrViewLayout2);
        }
    }
}
