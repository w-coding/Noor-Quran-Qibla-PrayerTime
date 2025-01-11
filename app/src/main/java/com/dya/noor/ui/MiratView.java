package com.dya.noor.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codesgood.views.JustifiedTextView;
import com.dya.noor.R;

public class MiratView extends AppCompatActivity {

    TextView title;
    JustifiedTextView dataView;
    ImageView btnBack;
    String Name , dic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirat_view);

        Name = getIntent().getStringExtra("MiratTitle");
        dic=getIntent().getStringExtra("MiratDic");

        String formatted = (dic + "").replaceAll("\n", "");



        dataView = findViewById(R.id.dataView);
        title= findViewById(R.id.title);
        btnBack= findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });


        title.setText(Name);
        dataView.setText(dic);

    }
}