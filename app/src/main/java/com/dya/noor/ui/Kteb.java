package com.dya.noor.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dya.noor.R;


public class Kteb extends AppCompatActivity  {

    ImageButton back;
    TextView txtKteb1 , txtKteb2 ,txtKteb3 ,txtKteb4 ,txtKteb5 ,txtKteb6 ,txtKteb7 ,txtKteb8 ,
            txtKteb9 , txtKteb10 ,txtKteb11 ,txtKteb12 ,txtKtebBawar,txtKtebName,txtKtebPiroz,
            txtKteb13 ,txtKteb14 ,txtKteb15 ,txtKteb16 ,txtKteb17 , txtKteb18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kteb);
        back = findViewById(R.id.back);

        SharedPreferences sharedPreferencesSize =getSharedPreferences("size",MODE_PRIVATE);
        int TextSize = sharedPreferencesSize.getInt("textSize", 20);

        txtKteb1 = findViewById(R.id.txtKteb1);
        txtKteb2 = findViewById(R.id.txtKteb2);
        txtKteb3 = findViewById(R.id.txtKteb3);
        txtKteb4 = findViewById(R.id.txtKteb4);
        txtKteb5 = findViewById(R.id.txtKteb5);
        txtKteb6 = findViewById(R.id.txtKteb6);
        txtKteb7 = findViewById(R.id.txtKteb7);
        txtKteb8 = findViewById(R.id.txtKteb8);
        txtKteb9 = findViewById(R.id.txtKteb9);
        txtKteb10 = findViewById(R.id.txtKteb10);
        txtKteb11 = findViewById(R.id.txtKteb11);
        txtKteb12 = findViewById(R.id.txtKteb12);
        txtKteb13 = findViewById(R.id.txtKteb13);
        txtKteb14 = findViewById(R.id.txtKteb14);
        txtKteb15 = findViewById(R.id.txtKteb15);
        txtKteb16 = findViewById(R.id.txtKteb16);
        txtKteb17 = findViewById(R.id.txtKteb17);
        txtKteb18 = findViewById(R.id.txtKteb18);
        txtKtebBawar = findViewById(R.id.txtKtebBawar);
        txtKtebName = findViewById(R.id.txtKtebName);
        txtKtebPiroz = findViewById(R.id.txtKtebPiroz);



        txtKteb1.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb2.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb3.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb4.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb5.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb6.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb7.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb8.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb9.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb10.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb11.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb12.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb13.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb14.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb15.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb16.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb17.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKteb18.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKtebBawar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKtebName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        txtKtebPiroz.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);


        back.setOnClickListener(v -> onBackPressed());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}