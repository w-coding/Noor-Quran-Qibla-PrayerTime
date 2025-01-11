package com.dya.noor.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.CitiesAdapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.module.CitiesItem;
import com.dya.noor.widget.SalatWidget;
import com.dya.noor.widget.SalatWidgetVertical;

import java.util.ArrayList;
import java.util.List;


public class CitiesSetting extends AppCompatActivity {

    String activityName ;
    ImageView btnBacks;
    RecyclerView citiesRecyclerView;
    MydbClass mydbClass;
    List<CitiesItem> citiesItem;
    CitiesAdapter citiesAdapter;
    EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_setting);

        mydbClass = new MydbClass(this);
        citiesItem = new ArrayList<>();

        btnBacks = findViewById(R.id.btnBacks);
        edSearch = findViewById(R.id.edSearch);

        citiesRecyclerView = findViewById(R.id.citiesRecyclerView);


        btnBacks.setOnClickListener(v -> onBackPressed());

        StoreCitiesData();


        citiesAdapter = new CitiesAdapter(this,citiesItem);
        citiesRecyclerView.setAdapter(citiesAdapter);
        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        activityName = getIntent().getStringExtra("act");


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                citiesAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void StoreCitiesData() {
        Cursor cursor = mydbClass.readAllCities();

        while (cursor.moveToNext()) {
            citiesItem.add( new CitiesItem(
                    cursor.getString(0),cursor.getString(1),cursor.getString(2)
                    ,cursor.getString(3),cursor.getString(4)
                    ,cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8)
            ));
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent;
        if (activityName.equals("main")) {
            intent = new Intent(CitiesSetting.this, MainActivity.class);
        } else {
            intent = new Intent(CitiesSetting.this, ActivityPrayersTime.class);
        }

        startActivity(intent);
        SalatWidget.updateAllWidgetViews(this);
        SalatWidgetVertical.updateAllWidgetViews(this);
        finish();
    }
}
