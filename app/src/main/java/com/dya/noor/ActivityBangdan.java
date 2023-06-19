package com.dya.noor;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



public class ActivityBangdan extends AppCompatActivity {


    ImageButton back ;
    ImageView btnBangPlay;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdan);


        back =findViewById(R.id.back);
        btnBangPlay = findViewById(R.id.btnBangPlay);




        back.setOnClickListener(v -> onBackPressed());


        mediaPlayer = MediaPlayer.create(this,R.raw.bang_dan);

        btnBangPlay.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                btnBangPlay.setImageResource(R.drawable.ic_pause);


            }else {
                mediaPlayer.pause();
                btnBangPlay.setImageResource(R.drawable.ic_play);
            }
        });


        mediaPlayer.setOnCompletionListener(mp -> {
            btnBangPlay.setImageResource(R.drawable.ic_play);
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