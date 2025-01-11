package com.dya.noor.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.ui.Zhyan_View;

public class ZHAdapter extends RecyclerView.Adapter<ZHAdapter.MyViewHolder> {

    String data1[];
    Context context;
    public ZHAdapter(Context ct , String s1[]){

        context = ct;
        data1 = s1;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.zhyan_row,parent,false);
        return new ZHAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.mZhyan.setText(data1[i]);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item=data1[i];
                Intent intent =new Intent(context, Zhyan_View.class);
                intent.putExtra("fileName",item);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return  data1.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mZhyan;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mZhyan= itemView.findViewById(R.id.Zhyan);
            constraintLayout = itemView.findViewById(R.id.zhianLayout);
        }
    }
}
