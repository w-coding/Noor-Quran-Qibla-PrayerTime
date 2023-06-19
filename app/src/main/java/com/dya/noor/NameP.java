package com.dya.noor;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NameP extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    String[] s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_p);

        back = findViewById(R.id.back);

        recyclerView = findViewById(R.id.NPRecyclerView);
        s1 =getResources().getStringArray(R.array.NameP);

        NMAdapter nmAdapter = new NMAdapter(this,s1);
        recyclerView.setAdapter(nmAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}