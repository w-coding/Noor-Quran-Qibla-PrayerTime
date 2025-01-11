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
import com.dya.noor.module.MiratData;
import com.dya.noor.ui.MiratView;

import java.util.List;

public

class MiratAdapter extends RecyclerView.Adapter<MiratAdapter.MiratViewHolder> {


    Context context;
    List<MiratData> MiratListData;

    public MiratAdapter(Context context, List<MiratData> miratListData) {
        this.context = context;
        MiratListData = miratListData;
    }





    @NonNull
    @Override
    public MiratAdapter.MiratViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mirat_row, parent, false);
        return new MiratViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiratViewHolder holder, int position) {


        MiratData MiratItem = MiratListData.get(position);

        holder.MiratId.setText(MiratItem.getId());
        holder.MiratTitel.setText(MiratItem.getTitle());
        String iName=MiratItem.getTitle();
        String HawalanDic = MiratItem.getDisc();

        holder.ClickLayout.setOnClickListener(v -> {

            Intent intent =new Intent(context, MiratView.class);
            intent.putExtra("MiratTitle",iName);
            intent.putExtra("MiratDic",HawalanDic);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return MiratListData.size();
    }

    public static class MiratViewHolder extends RecyclerView.ViewHolder {

        TextView MiratTitel , MiratId;
        LinearLayout ClickLayout;
        public MiratViewHolder(@NonNull View itemView) {
            super(itemView);

            MiratId = itemView.findViewById(R.id.MiratId);
            MiratTitel = itemView.findViewById(R.id.MiratTitel);
            ClickLayout = itemView.findViewById(R.id.MiratRowLayout);


        }
    }
}
