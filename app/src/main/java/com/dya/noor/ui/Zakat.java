package com.dya.noor.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dya.noor.R;

public class Zakat extends AppCompatActivity {

    ImageButton back;

    Button btnCal;
    EditText edPara;
    TextView tAnjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat);


        back = findViewById(R.id.back);
        btnCal = findViewById(R.id.btnCal);
        edPara = findViewById(R.id.edPara);
        tAnjam = findViewById(R.id.nrx);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edPara.getText().toString().equals("")){
                    Toast.makeText(Zakat.this, "تکایە بڕی پارەکاەت بنوسە",Toast.LENGTH_SHORT).show();
                }else if (!edPara.toString().equals("")){
                    float koyPara = Float.parseFloat(edPara.getText().toString());
                    float koyZakat = koyPara / 40;
                    tAnjam.setText(Float.toString(koyZakat));
                    edPara.setText("");
                }
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}