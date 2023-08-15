package com.dya.noor.ui;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.ZHAdapter;

public class Zhian_Nama extends AppCompatActivity {

    RecyclerView recyclerView;
    String s1[];
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhian__nama);

        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.ZHRecyclerView);
        s1 =getResources().getStringArray(R.array.Zhyan);

        ZHAdapter zhAdapter= new ZHAdapter(this,s1);
        recyclerView.setAdapter(zhAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}