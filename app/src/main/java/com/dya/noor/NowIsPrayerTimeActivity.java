package com.dya.noor;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class NowIsPrayerTimeActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    SharedPreferences preferences ;
    private MediaPlayer mediaPlayer;
    int AnimationDuration = 2000;
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

        preferences = getSharedPreferences("key",MODE_PRIVATE);
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
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        );




        // zikrakan
        scheduleNotification(2, 8, 0, "ویردەکانی بەیانیان", "ئیستا کاتی خویندنی ویردەکانی بەیانیانە ☀");
        //
        scheduleNotification(3, 16, 40, "ویردەکانی ئیواران", "ئیستا کاتی خویندنی ویردەکانی ئیوارانە ✨");
        //
        scheduleNotification(4, 21, 30, "ویردەکانی خەوتنان", "ئیستا کاتی خویندنی ویردەکانی خەوتنانە 💤");
        //
        scheduleNotification(5, 22, 10, "سورەتی مولک", "شەوانە پێش خەوتن 🛌 سورەتی { الملک } بخوێنن چونکه  ① دەبێتە ڕێگر لە سزای گـۆڕ ② دەبێتە شەفاعەت و تکاکار بۆخوێنەرەکەی تاوەکو خوای گەورە لێی خۆش دەبێت ");


        new Utils(this);
        new MydbClass(this);
        YoYo.with(Techniques.Flash).duration(AnimationDuration).repeat(100).playOn(tvPrayer);
        YoYo.with(Techniques.Flash).duration(AnimationDuration).repeat(100).playOn(closeIv);
        tvPrayer.setText(" ئێستا کاتی بانگی ( " + MydbClass.getCurrentClosestPrayer().prayerNameKurdish + " )\n" +
                "بانگی دواتر لە \n" + new SimpleDateFormat("hh:mm").format(new Date(Objects.requireNonNull(MydbClass.getNextPrayer()).milli)) + " " + MydbClass.getNextPrayer().prayerNameKurdish + " دەبێت");
        closeIv.setOnClickListener(v -> {

            finish();

        });

        //MediaPlayer lera dabne

        if (bangs.equals("1")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bang2);
            mediaPlayer.start();

        } else if (bangs.equals("2")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bang_dan);
            mediaPlayer.start();
        }

        mediaPlayer.setOnCompletionListener(mp -> {
            mediaPlayer.seekTo(0);
            finish();



        });

    }



    @RequiresApi(api = Build.VERSION_CODES.S)
    public void scheduleNotification(int requestCode, int hour, int minute, String title, String message) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        // If the alarm time is less than the current time, set it for the next day.
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent intent = new Intent(NowIsPrayerTimeActivity.this, NotificationReceiver.class);
        intent.putExtra("title", title);
        intent.putExtra("message", message);
        intent.putExtra("notificationId", requestCode);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_MUTABLE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
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