package com.dya.noor;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dhikrView2 extends AppCompatActivity {


    MydbClass mydbClass;
    SQLiteDatabase db;
    String id;
    RecyclerView zRecyclerView;
    TextView txtZikrName, duration;
    SeekBar seekBar2;
    ArrayList aArZ;
    CardView cardViewPlay2;
    ImageView btnPlay2 , back;
    MediaPlayer mediaPlayer2;
    Handler handler2 = new Handler();
    Runnable runnable2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhikr_view2);
        zRecyclerView=findViewById(R.id.zRecyclerView2);

        txtZikrName = findViewById(R.id.txtZikrName2);
        cardViewPlay2 = findViewById(R.id.cardViewPlay2);
        btnPlay2 = findViewById(R.id.btnPlay2);
        seekBar2 = findViewById(R.id.seek_bar2);
        back = findViewById(R.id.back);
        mediaPlayer2= new MediaPlayer();
        mydbClass = new MydbClass(this);
        db=mydbClass.getWritableDatabase();
        duration = findViewById(R.id.duration);
        aArZ = new ArrayList<>();

        id=getIntent().getStringExtra("id");

        back.setOnClickListener(view -> onBackPressed());


        StoreDataInArrayList();

        ZikrViewAdapter2 zikrViewAdapter2 = new ZikrViewAdapter2(this,  aArZ);
        zRecyclerView.setAdapter(zikrViewAdapter2);
        zRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        txtZikrName.setText(getIntent().getStringExtra("sura"));

        if (txtZikrName.getText().toString().equals("بەیانیان")){
            mediaPlayer2 = MediaPlayer.create(this,R.raw.b);
        }
        else if (txtZikrName.getText().toString().equals("ئێواران")){
            mediaPlayer2 = MediaPlayer.create(this,R.raw.e);
        }
        else {
            mediaPlayer2 = MediaPlayer.create(this,R.raw.x);
        }





        btnPlay2.setOnClickListener(v -> {


            if (!mediaPlayer2.isPlaying()) {

                mediaPlayer2.start();
                btnPlay2.setImageResource(R.drawable.ic_pause);
                seekBar2.setMax(mediaPlayer2.getDuration());
                handler2.postDelayed(runnable2, 0);

            }else {
                mediaPlayer2.pause();
                btnPlay2.setImageResource(R.drawable.ic_play);
            }




        });
///////////////////////////////////////////
        runnable2 = new Runnable() {
            @Override
            public void run() {
                seekBar2.setProgress(mediaPlayer2.getCurrentPosition());
                int currentDuration;
                // if (mediaPlayer.isPlaying()) {
                currentDuration = mediaPlayer2.getCurrentPosition();
                updatePlayer(currentDuration);
                duration.postDelayed(this, 1000);
                //   }else {
                duration.removeCallbacks(this);
                //  }
                handler2.postDelayed(this,500);

            }
        };


        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Check condition
                if(fromUser){
                    // when drag the seek bar
                    // set progress on seek bar
                    mediaPlayer2.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer2.seekTo(seekBar.getProgress());

            }
        });


        mediaPlayer2.setOnCompletionListener(mp -> {
            btnPlay2.setImageResource(R.drawable.ic_play);
            mediaPlayer2.seekTo(0);
        });

    }


    void StoreDataInArrayList(){


        Cursor cursor = mydbClass.readAllDataZikr(id);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                aArZ.add(cursor.getString(2));
            }
        }

    }
    private void updatePlayer(int currentDuration){

        duration.setText("" + milliSecondsToTimer((long) currentDuration));
    }





    /**
     * Function to convert milliseconds time to Timer Format
     * Hours:Minutes:Seconds
     * */
    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    @Override
    public void onBackPressed() {
        mediaPlayer2.stop();
        super.onBackPressed();
        finish();
    }
}