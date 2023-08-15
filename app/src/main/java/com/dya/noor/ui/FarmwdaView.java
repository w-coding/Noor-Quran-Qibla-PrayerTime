package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.adapters.FarmwdaViewAdapter;
import com.dya.noor.R;
import com.dya.noor.database.MydbClass;

import java.util.ArrayList;

public class FarmwdaView extends AppCompatActivity {
    MydbClass mydbClass;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    ArrayList aFarmwdaName,aId;
    ImageButton back;
    ImageButton searchButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmwda_view);

        back = findViewById(R.id.back);
        searchButton = findViewById(R.id.searchButton);
        recyclerView =findViewById(R.id.FrecyclerView);
        mydbClass = new MydbClass(this);

        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(FarmwdaView.this, SearchFarmwdaActivity.class);
            startActivity(intent);
        });


        db=mydbClass.getWritableDatabase();
        aFarmwdaName = new ArrayList<>();
        aId = new ArrayList<>();
        StoreDataInArrayList();


        FarmwdaViewAdapter MAdapter = new FarmwdaViewAdapter(this, aFarmwdaName,aId);
        recyclerView.setAdapter(MAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(v -> {
            onBackPressed();

        });


    }
    void StoreDataInArrayList(){

        Cursor cursor = mydbClass.readAllFarmudaTData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                aId.add(cursor.getString(0));
                aFarmwdaName.add(cursor.getString(1));


            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}