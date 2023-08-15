package com.dya.noor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.module.SurahItem;
import com.dya.noor.ui.SuratView;
import com.dya.noor.database.MydbClass;

import java.util.ArrayList;
import java.util.List;

public class SurahAapter extends RecyclerView.Adapter<SurahAapter.MyViewHolder> implements Filterable {


    List<SurahItem> aSuratName;
    List<SurahItem> searchText1;
    Context context;
    public SurahAapter(Context ct , List<SurahItem> SuratName )
    {
        this.context = ct;
        this.aSuratName =SuratName;

        this.searchText1 = new ArrayList<>(SuratName);



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_surat_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SurahItem itemData =aSuratName.get(position);

        holder.mSurat.setText(itemData.getaSuratName());
        holder.mInfo.setText(itemData.getaSuratIn());
        holder.mInfo4.setText(itemData.getaSuratIn4());
        holder.mInfo2.setText(itemData.getaSuratIn2());
        holder.mId.setText(itemData.getaId());
        holder.mLink=itemData.getaLink();
        MydbClass mydbClass=new MydbClass(context);

        String iSurat=itemData.getaSuratName();
        String iId=itemData.getaId();
        String iLink = itemData.getaLink();
        mydbClass.readAllAyahData(iId);
        holder.constraintLayout.setOnClickListener(v -> {

            Intent intent =new Intent(context, SuratView.class);
            intent.putExtra("sura",iSurat);
            intent.putExtra("id",iId);
            intent.putExtra("link",iLink);
            intent.putExtra("scrollPosition", "0");
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        return aSuratName.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List <SurahItem> filterList = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                filterList.addAll(searchText1);
            }else {

                for (SurahItem text : searchText1 ){
                    if (text.getaSuratName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterList.add(text);
                    }else if (text.getaId().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterList.add(text);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            aSuratName.clear();
            aSuratName.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mSurat;
        TextView mInfo, mInfo2, mInfo4;
        TextView mId;
        String mLink;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mSurat= itemView.findViewById(R.id.Surat);
            mInfo = itemView.findViewById(R.id.mInfo);
            mInfo2 = itemView.findViewById(R.id.mInfo2);
            mInfo4 = itemView.findViewById(R.id.mInfo4);
            mId = itemView.findViewById(R.id.sId);
            constraintLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
