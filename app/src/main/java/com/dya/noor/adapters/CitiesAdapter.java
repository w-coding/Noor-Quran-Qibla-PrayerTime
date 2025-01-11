package com.dya.noor.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.module.CitiesItem;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> implements Filterable {


    Context context;
    List<CitiesItem> citiesItems;
    List<CitiesItem> searchText;
    private int selectedPosition = -1; // To track selected item
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public CitiesAdapter(Context context, List<CitiesItem> citiesItems) {
        this.context = context;
        this.citiesItems = citiesItems;
        this.searchText = new ArrayList<>(citiesItems);
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cities_row, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {

        CitiesItem item = citiesItems.get(position);

        sharedPreferences = context.getSharedPreferences("key",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String Iso =item.getIso()
                .replaceAll("IQ", "عێراق")
                .replaceAll("IR", "ئێران");

        holder.citiesName.setText(item.getNameKurdish());
        holder.country.setText(Iso);


        // Set background color and text color based on selected position
        if (selectedPosition == position) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardBackgroundColorSelect)); // Use a custom color resource
            holder.citiesName.setTextColor(ContextCompat.getColor(context, R.color.textColorSelected));
            holder.country.setTextColor(ContextCompat.getColor(context, R.color.textColorHintSelected));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.cardBackgroundColor)); // Use a custom color resource
            holder.citiesName.setTextColor(ContextCompat.getColor(context, R.color.textColorBlack));
            holder.country.setTextColor(ContextCompat.getColor(context, R.color.textColor));
        }


        holder.mainCitiesLayout.setOnClickListener(v -> {
            // Clear previous selection if it exists
            if (selectedPosition != -1) {
                notifyItemChanged(selectedPosition);
            }

            // Update selected position
            selectedPosition = holder.getPosition();

            // Notify adapter to update the UI
            notifyItemChanged(selectedPosition);

            editor.putString("City",item.getDbName());
            editor.putString("City2",item.getNameKurdish());
            editor.apply();

        });



    }

    @Override
    public int getItemCount() {
        return citiesItems.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<CitiesItem> filterList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filterList.addAll(searchText);
            } else {

                for (CitiesItem text : searchText) {
                    if (text.getNameKurdish().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filterList.add(text);
                    } else if (text.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filterList.add(text);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            citiesItems.clear();
            citiesItems.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };









    public static class CitiesViewHolder extends RecyclerView.ViewHolder {
        TextView citiesName, country;
        LinearLayout mainCitiesLayout;
        CardView cardView;


        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);

            citiesName = itemView.findViewById(R.id.citiesName);
            country = itemView.findViewById(R.id.country);
            mainCitiesLayout = itemView.findViewById(R.id.mainCitiesLayout);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
