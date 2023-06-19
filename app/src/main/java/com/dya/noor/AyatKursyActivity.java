package com.dya.noor;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class AyatKursyActivity extends AppCompatActivity {

    ImageButton back ;
    ImageView btnAyaPlay;
    Button btnMana,btnSwd,btnAya;
    Animation open,close;
    TextView txtMana,txtSud,txtAya;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat_kursy);

        open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.close_fab);
        back =findViewById(R.id.back);
        btnAyaPlay = findViewById(R.id.btnAyaPlay);

        btnMana = findViewById(R.id.btnManaKey);
        btnSwd = findViewById(R.id.btnSud);
        btnAya = findViewById(R.id.btnAya);

        txtMana =findViewById(R.id.txtMana);
        txtSud = findViewById(R.id.txtSud);
        txtAya = findViewById(R.id.txtAya);
        txtAya.setText(txtAya.getText()+"  ٥٥٢");


        back.setOnClickListener(v -> onBackPressed());


        btnMana.setOnClickListener(v -> {
            txtSud.setVisibility(View.GONE);
            txtAya.setVisibility(View.GONE);
            btnAyaPlay.setVisibility(View.GONE);
            txtAya.setAnimation(close);
            txtSud.setAnimation(close);
            txtMana.setVisibility(View.VISIBLE);
            txtMana.setAnimation(open);



        });


        btnSwd.setOnClickListener(v -> {
            txtAya.setVisibility(View.GONE);
            txtAya.setAnimation(close);
            txtMana.setVisibility(View.GONE);
            txtMana.setAnimation(close);
            btnAyaPlay.setVisibility(View.GONE);
            txtSud.setVisibility(View.VISIBLE);
            txtSud.setAnimation(open);


        });
        btnAya.setOnClickListener(v -> {
            txtSud.setVisibility(View.GONE);
            txtSud.setAnimation(close);
            txtMana.setVisibility(View.GONE);
            txtMana.setAnimation(close);
            btnAyaPlay.setVisibility(View.VISIBLE);
            txtAya.setVisibility(View.VISIBLE);
            txtAya.setAnimation(open);


        });

        mediaPlayer = MediaPlayer.create(this,R.raw.aya);

        btnAyaPlay.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                btnAyaPlay.setImageResource(R.drawable.ic_pause);


            }else {
                mediaPlayer.pause();
                btnAyaPlay.setImageResource(R.drawable.ic_play);
            }
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            btnAyaPlay.setImageResource(R.drawable.ic_play);
            mediaPlayer.seekTo(0);
        });

    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        super.onBackPressed();
        finish();
    }
}