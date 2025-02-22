package com.dya.noor.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.dya.noor.R;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter <myAdapter.MyViewHolder>{

    ArrayList data1,data2,data3;
    Context context;

    public myAdapter(Context ct , ArrayList s1, ArrayList s2 , ArrayList s3){
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_name_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        SharedPreferences sharedPreferencesSize = context.getSharedPreferences("size",MODE_PRIVATE);
        int TextSize = sharedPreferencesSize.getInt("textSize", 20);
        holder.mName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        holder.mMana.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        holder.mMana2.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);

        holder.mName.setText(String.valueOf(data1.get(position)));
        holder.mMana.setText(String.valueOf(data2.get(position)));
        holder.mMana2.setText(String.valueOf(data3.get(position)));
        holder.constraintLayout.setOnClickListener(view -> {

            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(
                    Context.CLIPBOARD_SERVICE
            );
            ClipData clipData = ClipData.newPlainText("text", data1.get(position) +"\n"+ data2.get(position) +"\n"+ data3.get(position) +"\n"+"\n"+"#ئەپڵیکەیشنی_نور"+"\n"+"\n");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        });



    }




    @Override
    public int getItemCount() {
        return data1.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mName,mMana,mMana2;
         ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mName= itemView.findViewById(R.id.name);
            mMana = itemView.findViewById(R.id.mana);
            mMana2 = itemView.findViewById(R.id.mana2);
            constraintLayout  = itemView.findViewById(R.id.constraintLayout);

        }
    }
}
