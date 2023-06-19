package com.dya.noor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Zikr_view_Activity extends AppCompatActivity {



    MydbClass mydbClass;
    SQLiteDatabase db;
    Cursor myCursor;
    String id;
    RecyclerView zRecyclerView;
    TextView txtZikrName;
    ImageButton back ;
    ArrayList aArZ,aKrZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikr_view);

        zRecyclerView=findViewById(R.id.zRecyclerView);

        txtZikrName = findViewById(R.id.txtZikrName);


        back =findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());

        mydbClass = new MydbClass(this);

        db=mydbClass.getWritableDatabase();

        aArZ = new ArrayList<>();
        aKrZ = new ArrayList<>();
        id=getIntent().getStringExtra("id");




        StoreDataInArrayList();

        ZikrViewAdapter zikrViewAdapter = new ZikrViewAdapter(this,  aArZ,aKrZ );
        zRecyclerView.setAdapter(zikrViewAdapter);
        zRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        txtZikrName.setText(getIntent().getStringExtra("sura"));

    }


    void StoreDataInArrayList(){


        Cursor cursor = mydbClass.readAllDataZikr(id);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                aArZ.add(cursor.getString(2));
                aKrZ.add(cursor.getString(3));
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}