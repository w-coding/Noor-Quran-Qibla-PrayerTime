package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dya.noor.R;
import com.dya.noor.utlis.Utils;
import com.dya.noor.database.MydbClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class NowIsPrayerTimeActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    SharedPreferences preferences ;
    private MediaPlayer mediaPlayer;
    int AnimationDuration = 2000;

    public Vibrator vibrator;
    Button closeIv ;
    String bangs ="";
    TextView tvPrayer ;
    @SuppressLint({"MissingInflatedId", "SetTextI18n", "SimpleDateFormat", "WakelockTimeout"})
    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_is_prayer_time);
        tvPrayer = findViewById(R.id.tvPrayer);
        closeIv = findViewById(R.id.closeIv);

        preferences = getSharedPreferences("key", MODE_PRIVATE);
        bangs = preferences.getString("bang", "1");
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        // Acquire the wake lock.
        wakeLock.acquire();

        // Do your work here.


        // Release the wake lock.
        wakeLock.release();

        // bo pishandan le katy qfLda

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().addFlags(/**WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|*/
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        );




        new Utils(this);
        new MydbClass(this);
        YoYo.with(Techniques.Flash).duration(AnimationDuration).repeat(100).playOn(tvPrayer);
        YoYo.with(Techniques.Flash).duration(AnimationDuration).repeat(100).playOn(closeIv);
        tvPrayer.setText(" ئێستا کاتی بانگی ( " + MydbClass.getCurrentClosestPrayer().prayerNameKurdish + " )\n" +
                "بانگی دواتر لە \n" + new SimpleDateFormat("hh:mm").format(new Date(Objects.requireNonNull(MydbClass.getNextPrayer()).milli)) + " " + MydbClass.getNextPrayer().prayerNameKurdish + " دەبێت");
        closeIv.setOnClickListener(v -> {
            vibrator.cancel();
            finish();

        });

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int ringerMode = audioManager.getRingerMode();

        //MediaPlayer lera dabne
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {500, 700, 500, 700, 700};

        if (ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
            // Do not play media
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            vibrator.vibrate(pattern, 0);
        } else if (ringerMode == AudioManager.RINGER_MODE_SILENT ) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            vibrator.vibrate(0);
        }
        else{
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            // Calculate the desired volume level (50% of the maximum volume)
            int desiredVolume = maxVolume / 2;
           // audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

            // Get the current volume level for the music stream
            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

            // Check if the current volume is already at 50% or higher
            if (currentVolume < desiredVolume) {
                // Set the volume to the desired level
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, desiredVolume, 0);
            }
            vibrator.vibrate(pattern, 0);
        }
        if (bangs.equals("1")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bang2);
            mediaPlayer.start();

        } else if (bangs.equals("2")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bang_dan);
            mediaPlayer.start();
        }else if (bangs.equals("3")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.hijaz_azan);
            mediaPlayer.start();
        }else if (bangs.equals("4")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.rad_muhammad_alkurdi);
            mediaPlayer.start();
        }else if (bangs.equals("5")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.peshawa_qadir);
            mediaPlayer.start();
        }else if (bangs.equals("6")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.abdul_basit_abdul_samad);
            mediaPlayer.start();
        }else if (bangs.equals("7")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.islam_sobhi);
            mediaPlayer.start();
        }

        mediaPlayer.setOnCompletionListener(mp -> {
            mediaPlayer.seekTo(0);
            vibrator.cancel();
            finish();
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        vibrator.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            vibrator.cancel();
        }
    }

    // lekaty sawtdan yan kzkrdn mediaPlayer awastet
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {



                    finish();


                }
                return true;
            default:

        return super.dispatchKeyEvent(event);
    }
    }
}