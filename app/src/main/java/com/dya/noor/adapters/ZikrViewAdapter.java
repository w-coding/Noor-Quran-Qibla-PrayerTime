package com.dya.noor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;

import java.util.ArrayList;

public class ZikrViewAdapter extends RecyclerView.Adapter<ZikrViewAdapter.MyViewHolder> {

    ArrayList  aArZ,aKrZ ;
    Context context;
    public ZikrViewAdapter(Context ct , ArrayList aArZ , ArrayList aKrZ){
        context = ct;
       this.aArZ =aArZ;
        this.aKrZ = aKrZ;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_dhikr_view_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.zAr.setText(String.valueOf(aArZ.get(position)));
        holder.zKr.setText(String.valueOf(aKrZ.get(position)));


    }

    @Override
    public int getItemCount() {
        return aArZ.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView zAr;
        TextView zKr;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            zAr= itemView.findViewById(R.id.zAr);
            zKr = itemView.findViewById(R.id.zKr);
            constraintLayout = itemView.findViewById(R.id.zikrViewLayout);
        }
    }
}
