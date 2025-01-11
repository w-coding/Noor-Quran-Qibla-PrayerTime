package com.dya.noor.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.MiratAdapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.module.MiratData;

import java.util.ArrayList;
import java.util.List;

public class ActivityMirat extends AppCompatActivity {

    ImageView back;
    MydbClass mydbClass;
    List<MiratData> miratData;
    MiratAdapter miratAdapter;
    RecyclerView MiratRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirat);
        back = findViewById(R.id.back);
        MiratRecyclerView = findViewById(R.id.MiratRecyclerView);
        back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        mydbClass = new MydbClass(this);
        miratData = new ArrayList<>();

        StoreDataInArrayList();

        miratAdapter = new MiratAdapter(this, miratData);

        MiratRecyclerView.setAdapter(miratAdapter);
        MiratRecyclerView.setLayoutManager(new LinearLayoutManager(this));




    }


    void StoreDataInArrayList(){

        Cursor cursor = mydbClass.mirat();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                miratData.add(new MiratData(cursor.getString(0),cursor.getString(1)
                        ,cursor.getString(2)));
            }
        }
    }
}