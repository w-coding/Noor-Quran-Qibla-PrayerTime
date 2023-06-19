package com.dya.noor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Salah extends AppCompatActivity {

    CardView wc,islam,tayamum,dasNwezh,nwezhkrdn,hala;
    Animation open;
    ImageButton back ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah);

        back =findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());

        open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.frometope);

        wc = findViewById(R.id.wc);
        islam = findViewById(R.id.islam);
        tayamum = findViewById(R.id.tayamum);
        dasNwezh = findViewById(R.id.dasNwezh);
        nwezhkrdn = findViewById(R.id.nwezhkrdn);
        hala = findViewById(R.id.hala);



        wc.setAnimation(open);
        islam.setAnimation(open);
        tayamum.setAnimation(open);
        dasNwezh.setAnimation(open);
        nwezhkrdn.setAnimation(open);
        hala.setAnimation(open);

        wc.setOnClickListener(v -> {

            Intent intent = new Intent(Salah.this,Wc.class);
            startActivity(intent);

        });
        hala.setOnClickListener(v -> {

            Intent intent = new Intent(Salah.this,Halla.class);
            startActivity(intent);

        });

        islam.setOnClickListener(v -> {

            Intent intent = new Intent(Salah.this,IslamB.class);
            startActivity(intent);

        });

        tayamum.setOnClickListener(v -> {

            Intent intent = new Intent(Salah.this,Tayamum.class);
            startActivity(intent);


        });

        nwezhkrdn.setOnClickListener(v -> {

            Intent intent = new Intent(Salah.this,SallahN.class);
            startActivity(intent);

        });

        dasNwezh.setOnClickListener(v -> {

            Intent intent = new Intent(Salah.this,Dasnwezh.class);
            startActivity(intent);


        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}