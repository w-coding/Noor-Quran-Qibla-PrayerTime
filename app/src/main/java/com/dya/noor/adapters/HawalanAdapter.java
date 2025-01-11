package com.dya.noor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.module.HawallanItem;
import com.dya.noor.ui.HawalanDicActivity;

import java.util.List;

public class HawalanAdapter extends RecyclerView.Adapter<HawalanAdapter.HawalanViewHolder> {

    Context context;
    List<HawallanItem> HawalanData;

    public HawalanAdapter(Context context, List<HawallanItem> hawalanData) {
        this.context = context;
        HawalanData = hawalanData;
    }

    @NonNull
    @Override
    public HawalanAdapter.HawalanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.hawalan_row, parent, false);
        return new HawalanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HawalanAdapter.HawalanViewHolder holder, int position) {
        HawallanItem hawallanItem = HawalanData.get(position);

        holder.HawalanId.setText(hawallanItem.getId());
        holder.HawalanName.setText(hawallanItem.getName());
        holder.HawalanName.setText(hawallanItem.getName());
        String iName=hawallanItem.getName();
        String HawalanDic = hawallanItem.getDisc();

        holder.ClickLayout.setOnClickListener(v -> {

            Intent intent =new Intent(context, HawalanDicActivity.class);
            intent.putExtra("HawalanName",iName);
            intent.putExtra("HawalanDic",HawalanDic);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return HawalanData.size();
    }

    public static class HawalanViewHolder extends RecyclerView.ViewHolder {

        TextView HawalanId , HawalanName ;
        LinearLayout ClickLayout;


        public HawalanViewHolder(@NonNull View itemView) {
            super(itemView);

            HawalanId = itemView.findViewById(R.id.HawalanId);
            HawalanName = itemView.findViewById(R.id.HawalanName);
            ClickLayout = itemView.findViewById(R.id.HawalanRowLayout);
        }
    }
}
