package com.dya.noor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FarmwdaAdapter extends RecyclerView.Adapter<FarmwdaAdapter.MyViewHolder> {
    ArrayList arabick, krd;
    Context context;

    public FarmwdaAdapter(Context ct , ArrayList sarabick, ArrayList aKrd){
        context = ct;
        arabick = sarabick;
        krd = aKrd;
    }

    @NonNull
    @Override
    public FarmwdaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_farmuda_row,parent,false);
        return new FarmwdaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmwdaAdapter.MyViewHolder holder, int position) {
       // holder.mKrd.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        holder.mArabick.setText(String.valueOf(arabick.get(position)));
        holder.mKrd.setText(String.valueOf(krd.get(position)));
        holder.constraintLayout.setOnClickListener(view -> {

            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(
                    Context.CLIPBOARD_SERVICE
            );
            ClipData clipData = ClipData.newPlainText("text", arabick.get(position) +"\n"+ krd.get(position) +"\n"+"\n"+"#ئەپڵیکەیشنی_نور"+"\n"+"\n");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        });



    }




    @Override
    public int getItemCount() {
        return arabick.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mArabick, mKrd;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mArabick = itemView.findViewById(R.id.farmudaTxt);
            mKrd = itemView.findViewById(R.id.farmudaTxtK);
            constraintLayout  = itemView.findViewById(R.id.farmwdaLayout);

        }
    }
}
