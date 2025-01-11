package com.dya.noor.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.HawalanAdapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.module.HawallanItem;

import java.util.ArrayList;
import java.util.List;

public class HawalanActivity extends AppCompatActivity {


    MydbClass mydbClass;
    List<HawallanItem> HawalanData;

    HawalanAdapter hawalanAdapter;

    RecyclerView hawalanRecyclerView;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hawalan);

        hawalanRecyclerView = findViewById(R.id.hawalanRecyclerView);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> onBackPressed());


        mydbClass = new MydbClass(this);
        HawalanData = new ArrayList<>();

        StoreDataInArrayList();

        hawalanAdapter = new HawalanAdapter(this,HawalanData);

        hawalanRecyclerView.setAdapter(hawalanAdapter);
        hawalanRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    void StoreDataInArrayList(){

        Cursor cursor = mydbClass.readHawalan();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                HawalanData.add(new HawallanItem(cursor.getString(0),cursor.getString(1)
                        ,cursor.getString(2)));
            }
        }
    }

}