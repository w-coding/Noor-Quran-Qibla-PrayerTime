package com.dya.noor.adapters;

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
import com.dya.noor.database.MydbClass;
import com.dya.noor.ui.FarmwdaT;

import java.util.ArrayList;

public class FarmwdaViewAdapter extends RecyclerView.Adapter<FarmwdaViewAdapter.MyViewHolder> {

    ArrayList aFarmwdaName, aId;
    Context context;
    public FarmwdaViewAdapter(Context ct , ArrayList FarmwdaName , ArrayList id ){
        this.context = ct;
        this.aFarmwdaName =FarmwdaName;
        this.aId = id;




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_farmudat_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mFarmwda.setText(String.valueOf(aFarmwdaName.get(position)));
        holder.mId.setText(String.valueOf(aId.get(position)));
        MydbClass mydbClass=new MydbClass(context);


        holder.constraintLayout.setOnClickListener(v -> {
            String iId=String.valueOf(aId.get(position));
            String name=String.valueOf(aFarmwdaName.get(position));
            mydbClass.readAllAyahData(iId);
            Intent intent =new Intent(context, FarmwdaT.class);
            intent.putExtra("id",iId);
            intent.putExtra("name",name);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        return aFarmwdaName.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mFarmwda;
        TextView mId;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mFarmwda = itemView.findViewById(R.id.FarmwadaT);
            mId = itemView.findViewById(R.id.fId);
            constraintLayout = itemView.findViewById(R.id.FmainLayout);
        }
    }
}
