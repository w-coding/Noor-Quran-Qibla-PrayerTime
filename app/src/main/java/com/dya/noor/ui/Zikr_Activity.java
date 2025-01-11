package com.dya.noor.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.ZikrAdapter;
import com.dya.noor.database.MydbClass;

import java.util.ArrayList;

public class Zikr_Activity extends AppCompatActivity {


    MydbClass mydbClass;
    SQLiteDatabase db;
    RecyclerView ZikrRecyclerView;
    ArrayList aZikrName ,aId;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikr_);


        back = findViewById(R.id.back);
        ZikrRecyclerView = findViewById(R.id.ZikrRecyclerView);


        mydbClass = new MydbClass(this);
        mydbClass.StartWork();
        db=mydbClass.getWritableDatabase();
        aZikrName = new ArrayList<>();
        aId = new ArrayList<>();

        StoreDataInArrayList();


        ZikrAdapter zikrAdapter = new ZikrAdapter(this,aZikrName ,aId);
        ZikrRecyclerView.setAdapter(zikrAdapter);
        ZikrRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(v -> {
            onBackPressed();

        });

    }

    void StoreDataInArrayList(){

        Cursor cursor = mydbClass.readAllZikrData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                aId.add(cursor.getString(0));
                aZikrName.add(cursor.getString(1));


            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}