package com.dya.noor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class YoutubeChuser extends AppCompatActivity {

    ImageButton back;
    CardView nor_quran,nor;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_chuser);

        back = findViewById(R.id.back);
        nor_quran = findViewById(R.id.nor_quran);
        nor = findViewById(R.id.nor);


        back.setOnClickListener(v -> onBackPressed());

        nor_quran.setOnClickListener(view -> {

             url="https://youtube.com/channel/UCd9AeVetvPLO8tAxSObg_ZA";
            Intent intent = new Intent(YoutubeChuser.this,Youtube.class);
            intent.putExtra("urls",url);
            startActivity(intent);

        });
        nor.setOnClickListener(view -> {
            url="https://www.youtube.com/channel/UCIPW54WrawTSD8VJA5pR8Mw";
            Intent intent = new Intent(YoutubeChuser.this,Youtube.class);
            intent.putExtra("urls",url);

            startActivity(intent);


        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}