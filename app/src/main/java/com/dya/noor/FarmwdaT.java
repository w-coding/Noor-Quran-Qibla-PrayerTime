package com.dya.noor;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FarmwdaT extends AppCompatActivity {

    MydbClass mydbClass;
    String id,name;
    RecyclerView FtRecyclerView;
    ImageButton back;
    TextView farmwdaNameTextView;
    ArrayList aArabick, aKrd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmwda_t);
        mydbClass = new MydbClass(this);
        aArabick = new ArrayList<>();
        aKrd = new ArrayList<>();
        FtRecyclerView = findViewById(R.id.FTRecyclerView);
        back = findViewById(R.id.sBack);
        farmwdaNameTextView= findViewById(R.id.farmwdaNameTextView);
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");

        farmwdaNameTextView.setText(name);
        back.setOnClickListener(v -> {
            onBackPressed();
        });


        StoreDataInArrayList();

        FarmwdaAdapter farmudaAdapter = new FarmwdaAdapter(this, aArabick, aKrd );
        FtRecyclerView.setAdapter(farmudaAdapter);
        FtRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    void StoreDataInArrayList() {

        Cursor cursor = mydbClass.readAllFarmudaData(id);


        while (cursor.moveToNext()) {
            aArabick.add(cursor.getString(2));
            aKrd.add(cursor.getString(3));
        }


    }@Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
