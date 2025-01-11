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
import com.dya.noor.ui.Zikr_view_Activity;

import java.util.ArrayList;

public class ZikrAdapter extends RecyclerView.Adapter<ZikrAdapter.MyViewHolder> {

    ArrayList aZikrName ,aId;
    Context context;
    public ZikrAdapter(Context ct , ArrayList aZikrName , ArrayList id ){
        this.context = ct;
        this.aZikrName =aZikrName;
        this.aId = id;




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_dhikr_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mSurat.setText(String.valueOf(aZikrName.get(position)));
        holder.mId.setText(String.valueOf(aId.get(position)));

        MydbClass mydbClass=new MydbClass(context);


        holder.constraintLayout.setOnClickListener(v -> {
            String iZikr=String.valueOf(aZikrName.get(position));
            String iId=String.valueOf(aId.get(position));
            mydbClass.readAllDataZikr(iId);
            Intent intent =new Intent(context, Zikr_view_Activity.class);
            intent.putExtra("sura",iZikr);
            intent.putExtra("id",iId);

           context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return aZikrName.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mSurat;
        TextView mId;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mSurat= itemView.findViewById(R.id.dhikr);
            mId = itemView.findViewById(R.id.zId);
            constraintLayout = itemView.findViewById(R.id.Zikr_row_Layout);
        }
    }
}
