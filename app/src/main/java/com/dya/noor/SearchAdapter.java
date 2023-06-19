package com.dya.noor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable {


        List<SearchAyahItem> Ayah;
        List<SearchAyahItem> searchText1;
        Context context;
public SearchAdapter(Context ct , List<SearchAyahItem> ayah )
        {
        this.context = ct;
        this.Ayah = ayah;

        this.searchText1 = new ArrayList<>(Ayah);



        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_search_row,parent,false);
        return new MyViewHolder(view);
        }

@SuppressLint("SetTextI18n")
@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SearchAyahItem itemData = Ayah.get(position);


        holder.Ayah.setText(itemData.getAyahText());
        holder.info.setText("جوزئی :"+itemData.getJuzz()+" لاپەڕەی : "+itemData.getPage());
        holder.suratName.setText(itemData.getSuratName() +"  "+itemData.getId()+" : "+itemData.getAyahNumber());
        holder.mLink= itemData.getLink();
        MydbClass mydbClass=new MydbClass(context);

        String iPosition = itemData.getAyahNumber();
        String iSurat=itemData.getSuratName();
        String iId= itemData.getId();
        String iLink = itemData.getLink();
        mydbClass.readAllAyahData(iId);
        holder.linearLayout.setOnClickListener(v -> {

        Intent intent =new Intent(context,SuratView.class);
        intent.putExtra("sura",iSurat);
        intent.putExtra("id",iId);
        intent.putExtra("link",iLink);
        intent.putExtra("scrollPosition", iPosition);
        context.startActivity(intent);
        });

        }

@Override
public int getItemCount() {

        return Ayah.size();
        }

@Override
public Filter getFilter() {
        return filter;
        }

        Filter filter = new Filter() {
@Override
protected FilterResults performFiltering(CharSequence charSequence) {
        List <SearchAyahItem> filterList = new ArrayList<>();

        if (charSequence.toString().isEmpty()){
        filterList.addAll(searchText1);
        }else {

        for (SearchAyahItem text : searchText1 ){
        if (text.getAyahSearch().toLowerCase().contains(charSequence.toString().toLowerCase())){
        filterList.add(text);
        }else if (text.getAyahNumber().toLowerCase().contains(charSequence.toString().toLowerCase())){
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
        Ayah.clear();
        Ayah.addAll((List)filterResults.values);
        notifyDataSetChanged();

        }
        };

public static class MyViewHolder extends RecyclerView.ViewHolder {
    TextView Ayah;
    TextView info;
    TextView suratName;
    String mLink;
    LinearLayout linearLayout;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        Ayah = itemView.findViewById(R.id.Ayah);
        info = itemView.findViewById(R.id.info);
        suratName = itemView.findViewById(R.id.suratName);
        linearLayout = itemView.findViewById(R.id.searchLayout);
    }
}
}