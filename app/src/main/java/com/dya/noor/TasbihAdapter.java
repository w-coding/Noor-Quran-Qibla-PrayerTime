package com.dya.noor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TasbihAdapter extends RecyclerView.Adapter<TasbihAdapter.MyViewHolder> {


    String data1[];
    Context context;
    public TasbihAdapter(Context ct,String s1[]){
        context = ct;
        data1 = s1;
    }

    @NonNull
    @Override
    public TasbihAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_zikr_row,parent,false);
        return new TasbihAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasbihAdapter.MyViewHolder holder, int position) {
        holder.mZikr.setText(data1[position]);

        holder.constraintLayout.setOnClickListener(v -> {
            String item=data1[position];
            Intent intent =new Intent(context,Tasbih.class);
            intent.putExtra("fileName",item);
            context.startActivity(intent);


        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mZikr;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mZikr= itemView.findViewById(R.id.TxtZikr);
            constraintLayout = itemView.findViewById(R.id.ZikrLayout);
        }
    }
}
