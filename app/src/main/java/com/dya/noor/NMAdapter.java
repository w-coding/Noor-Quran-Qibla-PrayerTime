package com.dya.noor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;



public class NMAdapter extends RecyclerView.Adapter<NMAdapter.MyViewHolder> {
    String data1[];
    Context context;
    public NMAdapter(Context ct,String s1[]){
        context = ct;
        data1 = s1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.name_p_row,parent,false);
        return new NMAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mNameP.setText(data1[position]);

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mNameP;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameP= itemView.findViewById(R.id.NamepViw);
            constraintLayout = itemView.findViewById(R.id.NamePLayout);
        }
    }
}
