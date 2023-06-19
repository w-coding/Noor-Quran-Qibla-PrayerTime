package com.dya.noor;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {


    EditText SearchEditText;
    ImageButton btnClose;
    RecyclerView SearchRecyclerView;
    MydbClass mydbClass;

    List<SearchSuraItem> suratName;
    List<SearchAyahItem> Ayah;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mydbClass = new MydbClass(this);
        btnClose = findViewById(R.id.btnClose);
        SearchEditText = findViewById(R.id.SearchEditText);
        SearchRecyclerView = findViewById(R.id.SearchRecyclerView);
        Ayah = new ArrayList<>();
        suratName = new ArrayList<>();


        btnClose.setOnClickListener(v -> onBackPressed());

        StoreDataInArrayList();

        SearchAdapter adapter = new SearchAdapter(this,Ayah);
        SearchRecyclerView.setAdapter(adapter);
        SearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));




        SearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    void StoreDataInArrayList() {

        Cursor cursor = mydbClass.readAllqurannewData();



        while (cursor.moveToNext()) {
            Ayah.add(new SearchAyahItem(
                    cursor.getString(6),
                    cursor.getString(5),
                    cursor.getString(7),
                    cursor.getString(4),
                    cursor.getString(0),
                    cursor.getString(3),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}