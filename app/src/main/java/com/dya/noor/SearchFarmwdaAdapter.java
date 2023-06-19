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

public class SearchFarmwdaAdapter extends RecyclerView.Adapter<SearchFarmwdaAdapter.MyViewHolder> implements Filterable {


        List<SearchFarmwdaItem> Farmwda;
        List<SearchFarmwdaItem> searchText1;
        Context context;
public SearchFarmwdaAdapter(Context ct , List<SearchFarmwdaItem> farmwda )
        {
        this.context = ct;
        this.Farmwda = farmwda;

        this.searchText1 = new ArrayList<>(Farmwda);



        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_farmuda_serach_row,parent,false);
        return new MyViewHolder(view);
        }

@SuppressLint("SetTextI18n")
@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SearchFarmwdaItem itemData = Farmwda.get(position);


        holder.FarmudaTextView.setText(itemData.getAr_Desc());

        MydbClass mydbClass=new MydbClass(context);

       // String iPosition = itemData.getAyahNumber();
       // String iSurat=itemData.getSuratName();
        String iId= itemData.getId();
        String name=itemData.getName();
       // String iLink = itemData.getLink();
        mydbClass.readAllFarmudaData(iId);
        holder.linearLayout.setOnClickListener(v -> {

        Intent intent =new Intent(context,FarmwdaT.class);
        //intent.putExtra("sura",iSurat);
        intent.putExtra("id",iId);
        intent.putExtra("name",name);
       // intent.putExtra("link",iLink);
        //intent.putExtra("scrollPosition", iPosition);
        context.startActivity(intent);
        });

        }

@Override
public int getItemCount() {

        return Farmwda.size();
        }

@Override
public Filter getFilter() {
        return filter;
        }

        Filter filter = new Filter() {
@Override
protected FilterResults performFiltering(CharSequence charSequence) {
        List <SearchFarmwdaItem> filterList = new ArrayList<>();

        if (charSequence.toString().isEmpty()){
        filterList.addAll(searchText1);
        }else {

        for (SearchFarmwdaItem text : searchText1 ){
        if (text.getAr_Desc().toLowerCase().contains(charSequence.toString().toLowerCase())){
        filterList.add(text);
        }else if (text.getKu_Desc().toLowerCase().contains(charSequence.toString().toLowerCase())){
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
        Farmwda.clear();
        Farmwda.addAll((List)filterResults.values);
        notifyDataSetChanged();

        }
        };

public static class MyViewHolder extends RecyclerView.ViewHolder {
    TextView FarmudaTextView;
    LinearLayout linearLayout;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        FarmudaTextView = itemView.findViewById(R.id.farmudaTxtView);
        linearLayout = itemView.findViewById(R.id.farmwdaSearchLayout);
    }
}
}