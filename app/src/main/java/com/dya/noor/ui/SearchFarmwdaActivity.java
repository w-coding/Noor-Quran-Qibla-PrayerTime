package com.dya.noor.ui;

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

import com.dya.noor.R;
import com.dya.noor.module.SearchFarmwdaItem;
import com.dya.noor.adapters.SearchFarmwdaAdapter;
import com.dya.noor.database.MydbClass;

import java.util.ArrayList;
import java.util.List;

public class SearchFarmwdaActivity extends AppCompatActivity {


    EditText SearchEditText;
    ImageButton btnClose;
    RecyclerView searchFarmwdaRecyclerView;
    MydbClass mydbClass;

    List<SearchFarmwdaItem> farmwda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_farmwda);
        mydbClass = new MydbClass(this);
        farmwda= new ArrayList<>();
        searchFarmwdaRecyclerView = findViewById(R.id.searchFarmwdaRecyclerView);
        btnClose=findViewById(R.id.btnClose);
        SearchEditText = findViewById(R.id.SearchEditText);
        StoreDataInArrayList();

        btnClose.setOnClickListener(v -> onBackPressed());

        SearchFarmwdaAdapter adapter = new SearchFarmwdaAdapter(this,farmwda);
        searchFarmwdaRecyclerView.setAdapter(adapter);
        searchFarmwdaRecyclerView.setLayoutManager(new LinearLayoutManager(this));


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

        Cursor cursor = mydbClass.readAllFarmudaDataSearch();



        while (cursor.moveToNext()) {
            farmwda.add(new SearchFarmwdaItem(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }


    }
}