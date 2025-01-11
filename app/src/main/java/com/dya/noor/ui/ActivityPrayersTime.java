package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dya.noor.R;
import com.dya.noor.utlis.Utils;
import com.dya.noor.database.MydbClass;
import com.dya.noor.database.PrayerInfo;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ActivityPrayersTime extends AppCompatActivity {
    //new

    static boolean isActive = true;
    LinearLayout linearLayout;
    TextView date2, fajr, sunrise, dhuhr, asr, maghrib, isha, mCity, katymawa;
    TextClock time2;
    String City, City2;
    String mDate, mDate2, dayOfWeek;
    CardView cardViewB, cardViewN, cardViewA, cardViewE, cardViewX;
    ImageButton back, changeCity, setting;
    static ImageView ic_fajr, ic_dhuhr, ic_asr, ic_maghrib, ic_isha;
    static ImageView ic_fajr_alarm, ic_dhuhr_alarm, ic_asr_alarm, ic_maghrib_alarm, ic_isha_alarm;
    ConstraintLayout constraintLayoutB, constraintLayoutN, constraintLayoutA, constraintLayoutE, constraintLayoutX;
    public static Snackbar snackbar;
    public static TextView snackbarText;
    SharedPreferences prefe;
    private int numberOfOpens;

    @SuppressLint("SimpleDateFormat")
    public void refreshTodayPrayerTimes() {
        ArrayList<Long> prayer = MydbClass.getTodayPrayers(true);

        fajr.setText(new SimpleDateFormat("hh:mm").format(new Date(prayer.get(0))));
        sunrise.setText(new SimpleDateFormat("h:mm").format(new Date(prayer.get(1))));
        dhuhr.setText(new SimpleDateFormat("hh:mm").format(new Date(prayer.get(2))));
        asr.setText(new SimpleDateFormat("hh:mm").format(new Date(prayer.get(3))));
        maghrib.setText(new SimpleDateFormat("hh:mm").format(new Date(prayer.get(4))));
        isha.setText(new SimpleDateFormat("hh:mm").format(new Date(prayer.get(5))));

      /*  cardViewB = findViewById(R.id.cardViewB);
        cardViewN = findViewById(R.id.cardViewN);
        cardViewA = findViewById(R.id.cardViewA);
        cardViewE = findViewById(R.id.cardViewE);
        cardViewX = findViewById(R.id.cardViewX);

        constraintLayoutB = findViewById(R.id.constraintLayoutB);
        constraintLayoutN = findViewById(R.id.constraintLayoutN);
        constraintLayoutA = findViewById(R.id.constraintLayoutA);
        constraintLayoutE = findViewById(R.id.constraintLayoutE);
        constraintLayoutX = findViewById(R.id.constraintLayoutX);


        constraintLayoutB.setBackgroundResource(R.drawable.bg_card_b);
        constraintLayoutN.setBackgroundResource(R.drawable.bg_card_n);
        constraintLayoutA.setBackgroundResource(R.drawable.bg_card_a);
        constraintLayoutE.setBackgroundResource(R.drawable.bg_card_e);
        constraintLayoutX.setBackgroundResource(R.drawable.bg_card_x);

        switch (MydbClass.getNextPrayer().prayerName) {
            case "bayani":
                constraintLayoutB.setBackgroundResource(R.drawable.bg_card_b);
                break;
            case "niwaro":
                constraintLayoutN.setBackgroundResource(R.drawable.bg_card_n);
                break;
            case "asr":
                constraintLayoutA.setBackgroundResource(R.drawable.bg_card_a);
                break;
            case "eywara":
                constraintLayoutE.setBackgroundResource(R.drawable.bg_card_e);
                break;
            case "esha":
                constraintLayoutX.setBackgroundResource(R.drawable.bg_card_x);
                break;
        }

       */


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers_time);
        new Utils(this);

        MydbClass.setNextPrayer(this);
        Utils.activeContext = this;

        // startActivity(new Intent(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:"+getPackageName())));

        katymawa = findViewById(R.id.katymawa);
        back = findViewById(R.id.back);
        setting = findViewById(R.id.setting);
        linearLayout = findViewById(R.id.linearLayout);
        back.setOnClickListener(view -> onBackPressed());


        ic_fajr = findViewById(R.id.ic_fajr);
        ic_dhuhr = findViewById(R.id.ic_dhuhr);
        ic_asr = findViewById(R.id.ic_asr);
        ic_maghrib = findViewById(R.id.ic_maghrib);
        ic_isha = findViewById(R.id.ic_isha);


        ic_fajr_alarm = findViewById(R.id.ic_fajrS);
        ic_dhuhr_alarm = findViewById(R.id.ic_dhuhr2);
        ic_asr_alarm = findViewById(R.id.ic_asr2);
        ic_maghrib_alarm = findViewById(R.id.ic_maghrib2);
        ic_isha_alarm = findViewById(R.id.ic_isha2);


        String pkg = getPackageName();
        PowerManager pm = getSystemService(PowerManager.class);

        prefe = getSharedPreferences("dialog", MODE_PRIVATE);
        numberOfOpens = prefe.getInt("numberOfOpens", 0);

        // Increment the number of times the app has been opened.
        numberOfOpens++;
        // Save the number of times the app has been opened to SharedPreferences.
        SharedPreferences.Editor prefeEditor = prefe.edit();
        prefeEditor.putInt("numberOfOpens", numberOfOpens);
        prefeEditor.apply();

        if (numberOfOpens < 4) {
            if (!pm.isIgnoringBatteryOptimizations(pkg) || !android.provider.Settings.canDrawOverlays(this)) {
                AgadariDasteLat();
            }
        }

        setting.setOnClickListener(v -> {
            Intent SettingIntent = new Intent(ActivityPrayersTime.this, AppSettings.class);
            startActivity(SettingIntent);
        });


        // create an instance of the snackbar

        snackbar = Snackbar.make(linearLayout, "", Snackbar.LENGTH_SHORT);
        // inflate the custom_snackbar_view created previously
        @SuppressLint("InflateParams") View customSnackView = getLayoutInflater().inflate(R.layout.toast_layout_done, null);

        // set the background of the default snackbar as transparent
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        // now change the layout of the snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0);

        // register the button from the custom_snackbar_view layout file
        snackbarText = customSnackView.findViewById(R.id.txtToast);

        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0);


        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View view1 = inflater.inflate(R.layout.toast_layout, findViewById(R.id.toast_view));

        TextView txtToast = view1.findViewById(R.id.txtToast);
        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view1);


        SharedPreferences sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        City = sharedPreferences.getString("City", "Kalar");
        City2 = sharedPreferences.getString("City2", "کەلار");


        changeCity = findViewById(R.id.changeCity);

        mCity = findViewById(R.id.city);
        mCity.setText(City2);
        changeCity.setOnClickListener(view -> {

            Intent mIntent = new Intent(ActivityPrayersTime.this, CitiesSetting.class);
            mIntent.putExtra("act", "pt");
            startActivity(mIntent);
            finish();

        });


        // ئەمانەش دوگمەی نۆتفیکەشنەکانە

        ic_fajr.setOnClickListener(_view -> {
            flipBangState(_view, "bayani", "notification");
        });


        ic_dhuhr.setOnClickListener(_view -> {
            flipBangState(_view, "niwaro", "notification");

        });


        ic_asr.setOnClickListener(_view -> {
            flipBangState(_view, "asr", "notification");
        });

        ic_maghrib.setOnClickListener(_view -> {
            flipBangState(_view, "eywara", "notification");
        });

        ic_isha.setOnClickListener(_view -> {

            flipBangState(_view, "esha", "notification");
        });


        // ئەمەش دوگمەی هاتنە سەر شاشە

        ic_fajr_alarm.setOnClickListener(_view -> flipBangState(_view, "bayani", "alarm"));


        ic_dhuhr_alarm.setOnClickListener(_view -> flipBangState(_view, "niwaro", "alarm"));


        ic_asr_alarm.setOnClickListener(_view -> flipBangState(_view, "asr", "alarm"));

        ic_maghrib_alarm.setOnClickListener(_view -> flipBangState(_view, "eywara", "alarm"));

        ic_isha_alarm.setOnClickListener(_view -> flipBangState(_view, "esha", "alarm"));

        Times();


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String dayOfWe = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
        switch (Objects.requireNonNull(dayOfWe)) {
            case "Saturday":
                dayOfWeek = "شەمە";
                break;
            case "Sunday":
                dayOfWeek = "یەک شەمە";
                break;
            case "Monday":
                dayOfWeek = "دوو شەمە";
                break;
            case "Tuesday":
                dayOfWeek = "سێ شەمە";
                break;
            case "Wednesday":
                dayOfWeek = "چوار شەمە";
                break;
            case "Thursday":
                dayOfWeek = "پێنج شەمە";
                break;
            case "Friday":
                dayOfWeek = "هەینی";
                break;
        }


        mDate = day + "/" + (month + 1) + "/" + year + "  " + dayOfWeek;
        date2.setText(mDate);
        int mon = month + 1;
        if (mon > 9) {
            if (day <= 9) {
                mDate2 = (month + 1) + "-0" + day;
            } else {
                mDate2 = (month + 1) + "-" + day;
            }
        } else if (mon <= 9) {
            if (day <= 9) {
                mDate2 = "0" + (month + 1) + "-0" + day;
            } else {
                mDate2 = "0" + (month + 1) + "-" + day;
            }
        }


        if (City == "" || City == null) {

            txtToast.setText("تکایە شارێک دیاری بکە");
            toast.show();

        }

        refreshAllView();

    }


    void Times() {

        date2 = findViewById(R.id.date2);
        time2 = findViewById(R.id.time2);
        fajr = findViewById(R.id.fajr2);
        sunrise = findViewById(R.id.sunrise2);
        dhuhr = findViewById(R.id.dhuhr2);
        asr = findViewById(R.id.asr2);
        maghrib = findViewById(R.id.maghrib2);
        isha = findViewById(R.id.isha2);
    }


    public static void flipBangState(View v, String whichPrayer, String alarmOrNotification) {
        ImageView img = (ImageView) v;

        String textToShow = "";

        //Agar am banga xoi ON bw (ch Alarm bet, yan notification xoi 7sabi bo akat)
        if (Utils.getBooleanPref(alarmOrNotification + "_" + whichPrayer, true)) {
            //harkamian bet (Alarm, yan notification) bika OFF
            Utils.put(alarmOrNotification + "_" + whichPrayer, false);

            //dwatr rasmakai bka bawai OFF
            //
            if (alarmOrNotification.contains("notification")) {
                img.setImageResource(R.drawable.ic_notifications_off_24);
            } else {
                img.setImageResource(R.drawable.ic_phonelink);
            }

        } else {
            //agar am bang OFF bw xoi bika ON
            Utils.put(alarmOrNotification + "_" + whichPrayer, true);

            //dwatr rasmakai bka bawai ON
            if (alarmOrNotification.contains("notification")) {
                img.setImageResource(R.drawable.ic_notifications_active);
            } else {
                img.setImageResource(R.drawable.ic_smartphone);
            }
        }

        if (alarmOrNotification.contains("notification")) {
            textToShow += "ئاگاداركردنەوە  ";
        } else {
            textToShow += "ئاگاداركردنەوەی سەرشاشە  ";
        }

        if (Utils.getBooleanPref(alarmOrNotification + "_" + whichPrayer, true)) {
            textToShow += " کارا كرا";
        } else {
            textToShow += " لەكار خرا";
        }

        textToShow += " بۆ بانگی ";
        switch (whichPrayer) {
            case "bayani":
                textToShow += "بەیانی";
                break;
            case "niwaro":
                textToShow += "نیواڕۆ";
                break;
            case "asr":
                textToShow += "عەسر";
                break;
            case "eywara":
                textToShow += "ئێوارە";
                break;
            case "esha":
                textToShow += "خەوتنان";
                break;
        }

        textToShow = textToShow.replace("  ", " ");

        snackbarText.setText(textToShow);
        snackbar.show();

        refreshAllView();

    }


    public static void refreshAllView() {
        refreshView(ic_fajr, "bayani", "notification");
        refreshView(ic_dhuhr, "niwaro", "notification");
        refreshView(ic_asr, "asr", "notification");
        refreshView(ic_maghrib, "eywara", "notification");
        refreshView(ic_isha, "esha", "notification");

        refreshView(ic_fajr_alarm, "bayani", "alarm");
        refreshView(ic_dhuhr_alarm, "niwaro", "alarm");
        refreshView(ic_asr_alarm, "asr", "alarm");
        refreshView(ic_maghrib_alarm, "eywara", "alarm");
        refreshView(ic_isha_alarm, "esha", "alarm");
    }

    public static void refreshView(ImageView v, String whichPrayer, String alarmOrNotification) {

        String key = alarmOrNotification + "_" + whichPrayer;
        Log.d("HAX", key + "=" + Utils.getBooleanPref(key, true));

        //agar bangaka kara bw
        if (Utils.getBooleanPref(key, true)) {
            switch (alarmOrNotification) {
                case "notification":
                    //agar notification ON bw
                    v.setImageResource(R.drawable.ic_notifications_active);
                    break;
                case "alarm":
                    //agar alrm ON bw
                    v.setImageResource(R.drawable.ic_smartphone);
                    break;
            }
        } else {
            switch (alarmOrNotification) {
                case "notification":
                    //agar notification OFF bw
                    v.setImageResource(R.drawable.ic_notifications_off_24);
                    break;
                case "alarm":
                    //agarm alarm OFF bw
                    v.setImageResource(R.drawable.ic_phonelink);
                    break;
            }
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ActivityPrayersTime.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;

        refreshTodayPrayerTimes();

        new Thread(() -> {
            while (isActive) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }

                runOnUiThread(() -> {
                    try {
                        PrayerInfo next = MydbClass.getNextPrayer();
                        int seconds = (int) ((next.milli - System.currentTimeMillis()) / 1000);
                        String c = Utils.formatSecondsToTimeKurdish(seconds) + "ماوە بۆ بانگی " + next.prayerNameKurdish;
                        katymawa.setText(c);
                    } catch (Exception e) {
                        katymawa.setText("");
                    }

                });
            }
        }).start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @SuppressLint("MissingInflatedId")
    public void AgadariDasteLat() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        Button btnSetting, btnCancel, btnCloseAll;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(ActivityPrayersTime.this).inflate(R.layout.dialog_no_pirmesion_layout, viewGroup, false);
        builder.setCancelable(false);
        builder.setView(view);


        btnSetting = view.findViewById(R.id.btnSetting);
        btnCancel = view.findViewById(R.id.btnCancelDialo);
        btnCloseAll = view.findViewById(R.id.btnCloseAll);

        final AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btnSetting.setOnClickListener(v -> {

            Intent intent = new Intent(ActivityPrayersTime.this, AppSettings.class);
            startActivity(intent);
            alertDialog.dismiss();


        });

        btnCancel.setOnClickListener(v -> {

            alertDialog.dismiss();
        });
        btnCloseAll.setOnClickListener(v -> {
            numberOfOpens = 4;
            SharedPreferences.Editor prefeEditor = prefe.edit();
            prefeEditor.putInt("numberOfOpens", numberOfOpens);
            prefeEditor.apply();
            alertDialog.dismiss();

        });
        alertDialog.show();
    }

    private void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
        android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
        GG.setColor(Color.parseColor(_focus));
        GG.setCornerRadius((float) _round);
        GG.setStroke((int) _stroke, Color.parseColor("#" + _strokeclr.replace("#", "")));
        android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#FF757575")}), GG, null);
        _view.setBackground(RE);
    }


}