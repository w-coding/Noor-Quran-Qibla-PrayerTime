package com.dya.noor.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.NMAdapter;


public class NameP extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    String[] s1;

    TextView tvNameP1 ,tvNameP2 ,tvNameP3 , tvNameP4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_p);

        back = findViewById(R.id.back);

        tvNameP1 = findViewById(R.id.tvNameP1);
        tvNameP2 = findViewById(R.id.tvNameP2);
        tvNameP3 = findViewById(R.id.tvNameP3);
        tvNameP4 = findViewById(R.id.tvNameP4);

        recyclerView = findViewById(R.id.NPRecyclerView);
        s1 =getResources().getStringArray(R.array.NameP);
        SharedPreferences sharedPreferencesSize =getSharedPreferences("size",MODE_PRIVATE);
        int TextSize = sharedPreferencesSize.getInt("textSize", 20);

        tvNameP1.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        tvNameP2.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        tvNameP3.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        tvNameP4.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);

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