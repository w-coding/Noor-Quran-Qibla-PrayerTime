package com.dya.noor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Nawakany_xuda extends AppCompatActivity {

    MydbClass mydbClass;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    ArrayList s1, s2;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nawakany_xuda);
        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.mback);

        mydbClass = new MydbClass(this);
        mydbClass.StartWork();
        db=mydbClass.getWritableDatabase();
        
        s1 = new ArrayList<>();
        s2 = new ArrayList<>();

        StoreDataInArrayList();

        myAdapter mAdapter = new myAdapter(this,s1,s2);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(v -> onBackPressed());
    }

    void StoreDataInArrayList(){

        Cursor cursor = mydbClass.readAllAllahName();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                s1.add(cursor.getString(1));
                s2.add(cursor.getString(2));


            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}