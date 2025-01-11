package com.dya.noor.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.ActivityAdapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.database.PrayerInfo;
import com.dya.noor.module.ActivityItem;
import com.dya.noor.notifications.ScheduleNotification;
import com.dya.noor.utlis.Utils;
import com.dya.noor.widget.CallaUpdateWidget;
import com.dya.noor.widget.SalatWidget;
import com.dya.noor.widget.SalatWidgetVertical;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import softpro.naseemali.ShapedNavigationView;

public class MainActivity extends AppCompatActivity {



    File file12;
    Context context = this;
    int bHours, eHours, xHours, mHours;
    int bMunit, eMunit, xMunit, mMunit;
    DownloadManager manager;
    AlarmManager alarmManager;
    CardView Bayanyan, Ewara, Xawtn;
    Animation open;
    DrawerLayout drawerLayout;
    ShapedNavigationView navigationView;
    ImageView btnMenu, btnAya, btnBang, calender;
    RoundedImageView btnSuratFb;
    String City, City2;
    String[] permissions = {"android.permission.ACCESS_FINE_LOCATION",
            "Manifest.permission.ACCESS_COARSE_LOCATION", "android.permission.POST_NOTIFICATIONS"};
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    BottomSheetDialog sheetDialog;
    HijrahDate hijrahDate;
    DateTimeFormatter formatter , formatterM ,formatterY;
    String formatted , formattedM , formattedY;
    static boolean isActive = true;
    TextView date2, fajr, sunrise, dhuhr, asr, maghrib, isha, mCity, katymawa;
    TextClock time2;
    String mDate, mDate2, dayOfWeek;
    ImageView ic_fajr, ic_dhuhr, ic_asr, ic_maghrib, ic_isha;

    private static final int RCP_AP_UPDATE = 100;
    private AppUpdateManager mAppUpdateManager;
    RecyclerView ActRecyclerView;
    DownloadManager downloadManager;
    Snackbar snackbar;
    TextView snackbarText;
    CardView cardviewTime;

    static PowerManager powerManager;
    static String pkgs;


    SharedPreferences prefe;
    private int numberOfOpens ;

    LinearLayout linearLayoutGotoSeeting ,donationLayout;


    @SuppressLint("SimpleDateFormat")
    public void refreshTodayPrayerTimes() {
        ArrayList<Long> prayer = MydbClass.getTodayPrayers(true);

        if (prayer.size() >= 6) {

            fajr.setText(new SimpleDateFormat("hh:mm").format(new Date(prayer.get(0))));
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
    }

    /* ActivityMainBinding bind;*/




    @RequiresApi(api = Build.VERSION_CODES.S)
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         *  Bakarhenani View binding (la sarawa 'ActivityMainBinding bind;' bir nachet)
         bind = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(bind.getRoot());

         bind.btnBang.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {

        }
        });
         */
        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }

      /**  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
            }
        }*/

        pkgs =getPackageName();
        powerManager =getSystemService(PowerManager.class);
        AlarmManager alarmManager   = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);


        donationLayout = findViewById(R.id.donationLayout);
        donationLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this , HelpNoor.class);
            startActivity(intent);
        });

        linearLayoutGotoSeeting = findViewById(R.id.linearLayoutGotoSeeting);
        new Utils(this);
        Utils.activeContext = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (alarmManager.canScheduleExactAlarms()) {
                MydbClass.setNextPrayer(this);
                SalatWidget.updateAllWidgetViews(this);
                SalatWidgetVertical.updateAllWidgetViews(this);
                // nwe krdnaway widget la sa3at 12 y shaw baw lawa bo away katy bange kan bgoryt bo rozht dagatw
                CallaUpdateWidget.setRepeatingAlarm(this);
            }
        }
        else {

                MydbClass.setNextPrayer(this);
                SalatWidget.updateAllWidgetViews(this);
                SalatWidgetVertical.updateAllWidgetViews(this);
                // nwe krdnaway widget la sa3at 12 y shaw baw lawa bo away katy bange kan bgoryt bo rozht dagatw
                CallaUpdateWidget.setRepeatingAlarm(this);

        }



        drawerLayout = findViewById(R.id.navigation_drawer);
        Times();
        cardviewTime = findViewById(R.id.cardviewTime);
        manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
// Create a list of activities.
        List<ActivityItem> activities = new ArrayList<>();

// Add activities to the list.
        activities.add(new ActivityItem("Quran", "قورئانی پیرۆز", R.drawable.ic_reading_quran));
        activities.add(new ActivityItem("FarmwdaView", "فەرمودە", R.drawable.ic_farmwda));
        activities.add(new ActivityItem("ActivityQuranPdf", "قورئانی پیرۆز(کتێب)", R.drawable.ic_quran_icon_new));
        activities.add(new ActivityItem("Zhian_Nama", "ژیان نامەی پێغمبەرﷺ", R.drawable.muhammad));
        activities.add(new ActivityItem("ActivityHajUmrah", "حەج و عمرە", R.drawable.umrah));
        activities.add(new ActivityItem("Hawalan", "هاوەڵان", R.drawable.muslim_man));
        activities.add(new ActivityItem("Tasbih", "تەسبیح", R.drawable.ic_tasbih));
        activities.add(new ActivityItem("PayaKan", "پایەکانی باوەڕ", R.drawable.ic_man));
        activities.add(new ActivityItem("Zikr_Activity", "زیکر", R.drawable.ic_dua_hands));
        activities.add(new ActivityItem("Zakat", "زەکات", R.drawable.zakat));
        activities.add(new ActivityItem("Mirat", "میرات", R.drawable.islamic_icon));
        activities.add(new ActivityItem("Nawakany_xuda", "ناوەکانی خودا", R.drawable.allah));
        activities.add(new ActivityItem("Salah", "نوێژکردن", R.drawable.ic_praying_mat));
        activities.add(new ActivityItem("NameP", "پێغەمبەران", R.drawable.ic_kaaba_mecca));
        activities.add(new ActivityItem("CompassActivity", "قیبلە نما", R.drawable.ic_qibla_doz));
        activities.add(new ActivityItem("Kteb", "کتێبە ئاسمانیەکان", R.drawable.ic_book));
        activities.add(new ActivityItem("ActivityPrayersTime", "کاتەکانی بانگ", R.drawable.ic_prayer_mat));
        activities.add(new ActivityItem("YoutubeChuser", "یوتیوب", R.drawable.ic_youtube));
        activities.add(new ActivityItem("Babatyrozh", "سایتی نور", R.drawable.noorlogo));

        cardviewTime.setOnClickListener(v -> {


            SharedPreferences sharedPreferences = context.getSharedPreferences("key", MODE_PRIVATE);
            String City = sharedPreferences.getString("City", "Kalar");
            Intent intent;
            if (City.equals("")) {
                intent = new Intent(MainActivity.this, CitiesSetting.class);
            } else {
                intent = new Intent(MainActivity.this, ActivityPrayersTime.class);
            }
            startActivity(intent);
            finish();

        });

        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),RECEIVER_EXPORTED);


        ActRecyclerView = findViewById(R.id.ActRecyclerView);
        ActivityAdapter adapter = new ActivityAdapter(MainActivity.this, activities);
        ActRecyclerView.setAdapter(adapter);
        ActRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        ActRecyclerView.setHasFixedSize(true);


        mAppUpdateManager = AppUpdateManagerFactory.create(this);
        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener((OnSuccessListener<? super AppUpdateInfo>) appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                    appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                try {
                    mAppUpdateManager.startUpdateFlowForResult(appUpdateInfo,
                            AppUpdateType.FLEXIBLE, MainActivity.this, RCP_AP_UPDATE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });

        mAppUpdateManager.registerListener(installStateUpdatedListener);

         //checkForUpdates();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

            if (alarmManager.canScheduleExactAlarms()) {
            // zikrakan
            ScheduleNotification.scheduleNotification(this, 2, 8, 0, "ویردەکانی بەیانیان", "ئیستا کاتی خویندنی ویردەکانی بەیانیانە ☀");
            //
            ScheduleNotification.scheduleNotification(this, 3, 16, 40, "ویردەکانی ئیواران", "ئیستا کاتی خویندنی ویردەکانی ئیوارانە ✨");
            //
            ScheduleNotification.scheduleNotification(this, 4, 21, 30, "ویردەکانی خەوتنان", "ئیستا کاتی خویندنی ویردەکانی خەوتنانە 💤");
            //
            ScheduleNotification.scheduleNotification(this, 5, 22, 10, "سورەتی مولک", "شەوانە پێش خەوتن 🛌 سورەتی { الملک } بخوێنن چونکه  ① دەبێتە ڕێگر لە سزای گـۆڕ ② دەبێتە شەفاعەت و تکاکار بۆخوێنەرەکەی تاوەکو خوای گەورە لێی خۆش دەبێت ");


        }}else {
            // zikrakan
            ScheduleNotification.scheduleNotification(this, 2, 8, 0, "ویردەکانی بەیانیان", "ئیستا کاتی خویندنی ویردەکانی بەیانیانە ☀");
            //
            ScheduleNotification.scheduleNotification(this, 3, 16, 40, "ویردەکانی ئیواران", "ئیستا کاتی خویندنی ویردەکانی ئیوارانە ✨");
            //
            ScheduleNotification.scheduleNotification(this, 4, 21, 30, "ویردەکانی خەوتنان", "ئیستا کاتی خویندنی ویردەکانی خەوتنانە 💤");
            //
            ScheduleNotification.scheduleNotification(this, 5, 22, 10, "سورەتی مولک", "شەوانە پێش خەوتن 🛌 سورەتی { الملک } بخوێنن چونکه  ① دەبێتە ڕێگر لە سزای گـۆڕ ② دەبێتە شەفاعەت و تکاکار بۆخوێنەرەکەی تاوەکو خوای گەورە لێی خۆش دەبێت ");

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_DENIED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS
                }, 100);
            }

        }

        // Intent callSettingIntent= new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION); startActivity(callSettingIntent);


        // startActivity(new Intent(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:"+getPackageName())));

      /*  if (!android.provider.Settings.canDrawOverlays(this)) {

            startActivity(new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));

        }

       */

        linearLayoutGotoSeeting.setOnClickListener(v -> {
            Intent SettingIntent = new Intent(MainActivity.this, AppSettings.class);
            startActivity(SettingIntent);
        });


        prefe = getSharedPreferences("dialog", MODE_PRIVATE);
        numberOfOpens = prefe.getInt("numberOfOpens", 0);

        // Increment the number of times the app has been opened.
        numberOfOpens++;
        // Save the number of times the app has been opened to SharedPreferences.
        SharedPreferences.Editor prefeEditor = prefe.edit();
        prefeEditor.putInt("numberOfOpens", numberOfOpens);
        prefeEditor.apply();
        pkgs = getPackageName();
        powerManager = getSystemService(PowerManager.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!powerManager.isIgnoringBatteryOptimizations(pkgs) || alarmManager.canScheduleExactAlarms() ||
                    !android.provider.Settings.canDrawOverlays(this)) {
                linearLayoutGotoSeeting.setVisibility(View.VISIBLE);
            } else {
                linearLayoutGotoSeeting.setVisibility(View.GONE);
            }
        }
        else {
            if (!powerManager.isIgnoringBatteryOptimizations(pkgs) ||
                !android.provider.Settings.canDrawOverlays(this)) {
                linearLayoutGotoSeeting.setVisibility(View.VISIBLE);
            }
            else {
                linearLayoutGotoSeeting.setVisibility(View.GONE);
            }

        }
        if (numberOfOpens < 4) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                if (!powerManager.isIgnoringBatteryOptimizations(pkgs) ||alarmManager.canScheduleExactAlarms()||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                                PackageManager.PERMISSION_DENIED  || !android.provider.Settings.canDrawOverlays(this)) {
                    AgadariDasteLat();
                }
            }else {
                if (!powerManager.isIgnoringBatteryOptimizations(pkgs)||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                                PackageManager.PERMISSION_DENIED  || !android.provider.Settings.canDrawOverlays(this)) {
                    AgadariDasteLat();
                }
            }

        }
        katymawa = findViewById(R.id.katymawa);

        //requestPermissions(permissions,80);


        open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.frometope);

        btnMenu = findViewById(R.id.btnNav);
        btnSuratFb = findViewById(R.id.btnSuratFb);
        navigationView = findViewById(R.id.nav_view);


        btnAya = findViewById(R.id.btnAya);
        btnBang = findViewById(R.id.btnBang);
        calender = findViewById(R.id.calender);


        Bayanyan = findViewById(R.id.Bayanyan);
        Ewara = findViewById(R.id.Ewaran);
        Xawtn = findViewById(R.id.xewtnan);

        SharedPreferences sharedPreferences3 = getSharedPreferences("pref", MODE_PRIVATE);
        boolean ShowOnStart = sharedPreferences3.getBoolean("show", true);

        if (ShowOnStart) {
            Agadari();
        }

        // check krdny internet
        ConnectivityManager connectivityManager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //////////
        preferences = getSharedPreferences("Save", MODE_PRIVATE);
        editor = preferences.edit();


        hijrahDate = HijrahDate.now();

        formatter = DateTimeFormatter.ofPattern("dd");
        formatterM = DateTimeFormatter.ofPattern("MMMM");
        formatterY = DateTimeFormatter.ofPattern("yyyy");
        formatted = formatter.format(hijrahDate);
        formattedM = formatterM.format(hijrahDate);
        formattedY = formatterY.format(hijrahDate);

        String formattedM2 = (formattedM + "").replaceAll("Muharram", "مُحَرَّم")
        .replaceAll("Safar", "صَفَر")
        .replaceAll("Rabiʻ I", "رَبِيع ٱلْأَوَّل").replaceAll("Rabiʻ II", "رَبِيع ٱلْآخِر")
        .replaceAll("Jumada I", "جُمَادَىٰ ٱلْأُولَىٰ").replaceAll("Jumada II", "جُمَادَىٰ ٱلْآخِرَة")
        .replaceAll("Rajab", "رَجَب").replaceAll("Shaʻban", "شَعْبَان")
        .replaceAll("Ramadan", "رَمَضَان").replaceAll("Shawwal", "شَوَّال")
         .replaceAll("Dhuʻl-Qiʻdah", "ذُو ٱلْقَعْدَة").replaceAll("Dhuʻl-Hijjah", "ذُو ٱلْحِجَّة");
String formatted2 = (formatted + "").replaceAll("/", " ")
                .replaceAll("1", "١").replaceAll("2", "٢")
                .replaceAll("3", "٣").replaceAll("4", "٤")
                .replaceAll("5", "٥").replaceAll("6", "٦")
                .replaceAll("7", "٧").replaceAll("8", "٨")
                .replaceAll("9", "٩").replaceAll("0", "٠");

String formattedY2 = (formattedY + "").replaceAll("/", " ")
                .replaceAll("1", "١").replaceAll("2", "٢")
                .replaceAll("3", "٣").replaceAll("4", "٤")
                .replaceAll("5", "٥").replaceAll("6", "٦")
                .replaceAll("7", "٧").replaceAll("8", "٨")
                .replaceAll("9", "٩").replaceAll("0", "٠");



        sheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetStyle);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View vi = LayoutInflater.from(MainActivity.this).inflate(R.layout.bottomshet_dialog,
                findViewById(R.id.sheet_calnder));
        TextView tvH = vi.findViewById(R.id.tvH);
        tvH.setText(formatted2+ " ی "+formattedM2 + "ی "+formattedY2+ " ی هجری ");

        /*if (tvH.getText().toString().contains("جُمَادَىٰ ٱلْآخِرَة")){

            tvH.setText(String.format("%s\nmawa", tvH.getText()));

        }

         */
        sheetDialog.setContentView(vi);


        // create an instance of the snackbar
        snackbar = Snackbar.make(drawerLayout, "", Snackbar.LENGTH_LONG);

        // inflate the custom_snackbar_view created previously
        View customSnackView = getLayoutInflater().inflate(R.layout.toast_layout_done, null);

        // set the background of the default snackbar as transparent
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        // now change the layout of the snackbar
        @SuppressLint("RestrictedApi")
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0);

        // register the button from the custom_snackbar_view layout file
        snackbarText = customSnackView.findViewById(R.id.txtToast);

        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0);


        mCity = findViewById(R.id.city);

        SharedPreferences sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        City = sharedPreferences.getString("City", "Kalar");
        City2 = sharedPreferences.getString("City2", "کەلار");


        mCity.setText(City2);

        mCity.setOnClickListener(view1 -> {

            Intent mIntent = new Intent(MainActivity.this, CitiesSetting.class);
            mIntent.putExtra("act", "main");
            startActivity(mIntent);
            finish();

        });


        ////////////
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.web) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://noor.pages.dev/"));
                startActivity(intent);
            } else if (id == R.id.about) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            } else if (id == R.id.setting) {

                Intent intent = new Intent(MainActivity.this, AppSettings.class);
                startActivity(intent);

            }else if (id == R.id.help) {

                Intent intent = new Intent(MainActivity.this, HelpNoor.class);
                startActivity(intent);

            } else if (id == R.id.Share) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareSubject = "noor Application  Download now";
                String shareBode = " ئەپڵیکەیشنی نور  \n  لەڕێگەی ئەم لینکەوە دایبەزێنە *** \n";
                String shareBode1 = "https://play.google.com/store/apps/details?id=com.dya.noor";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBode + "\n" + shareBode1);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(shareIntent, "share using"));

            } else if (id == R.id.Star) {
                // review and rating
                try {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));

                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.googl.com/store/apps/details?id=" + getPackageName())));
                }
            } else if (id == R.id.Exit) {
                onBackPressed();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;

        });
        navigationView.setItemIconTintList(null);


        calender.setOnClickListener(v -> {

            sheetDialog.show();

        });
        Bayanyan.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, dhikrView2.class);
            intent.putExtra("sura", "بەیانیان");
            intent.putExtra("id", "27");
            startActivity(intent);

        });
        Ewara.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, dhikrView2.class);
            intent.putExtra("sura", "ئێواران");
            intent.putExtra("id", "28");
            startActivity(intent);

        });
        Xawtn.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, dhikrView2.class);
            intent.putExtra("sura", "پێشخەوتن");
            intent.putExtra("id", "29");
            startActivity(intent);

        });

        btnBang.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, ActivityBangdan.class);
            startActivity(intent);

        });


        btnAya.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, AyatKursyActivity.class);
            startActivity(intent);

        });


        btnSuratFb.setOnClickListener(v -> {

            String facbookUrl = "https://www.facebook.com/noor.page.officiall";
            try {
                int versinCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                if (versinCode >= 3002850) {
                    Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facbookUrl);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + facbookUrl)));
                }
            } catch (PackageManager.NameNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facbookUrl)));
            }
        });

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

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


        if (getIntent().hasExtra("open_prayer") && getIntent().getBooleanExtra("open_prayer", false)) {
            //agar la Widget'awa hatbwa barnamaka, yaksar bashi katakani bang bkawa.
            startActivity(new Intent(this, ActivityPrayersTime.class));
        }

    }

    public void downloadFiles() {
        snackbarText.setText("داونلۆد دەستی پێکرد");
        snackbar.show();

        String urls = "https://github.com/DdGit0/tafser_tewhidiy/raw/main/t2.pdf";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urls));

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOCUMENTS, "QuranTafseer.pdf");
        manager.enqueue(request);

    }

    public void downloadQuranFiles() {
        snackbarText.setText("داونلۆد دەستی پێکرد");
        snackbar.show();
        String urls = "https://github.com/DdGit0/quranPdf/raw/main/q1.pdf";
        DownloadManager.Request request = new
                DownloadManager.Request(Uri.parse(urls));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOCUMENTS, "Quran.pdf");
        downloadManager.enqueue(request);

    }


    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {

            file12 = new File(ctxt.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "QuranTafseer.pdf");

            if (file12.exists()) {

            }


        }
    };


    void Times() {

        date2 = findViewById(R.id.date2);
        time2 = findViewById(R.id.time2);
        fajr = findViewById(R.id.fajr2);
        dhuhr = findViewById(R.id.dhuhr2);
        asr = findViewById(R.id.asr2);
        maghrib = findViewById(R.id.maghrib2);
        isha = findViewById(R.id.isha2);
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        ViewGroup viewGroup = findViewById(android.R.id.content);

        Button btnYes, btnNo;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.close_dialog_layout, viewGroup, false);
        builder.setCancelable(false);
        builder.setView(view);

        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);

        final AlertDialog alertDialog = builder.create();

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnYes.setOnClickListener(v -> System.exit(0));

        btnNo.setOnClickListener(v -> alertDialog.dismiss());


        alertDialog.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 80) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            } else {

            }
        }
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
                    PrayerInfo next = MydbClass.getNextPrayer();
                    int seconds = (int) ((next.milli - System.currentTimeMillis()) / 1000);
                    String c = Utils.formatSecondsToTimeKurdish(seconds) + "ماوە بۆ بانگی " + next.prayerNameKurdish;
                    katymawa.setText(c);

                });
            }
        }).start();


        powerManager = getSystemService(PowerManager.class);
        if (!powerManager.isIgnoringBatteryOptimizations(pkgs) || !android.provider.Settings.canDrawOverlays(this)) {
            linearLayoutGotoSeeting.setVisibility(View.VISIBLE);
        } else {
            linearLayoutGotoSeeting.setVisibility(View.GONE);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @SuppressLint("MissingInflatedId")
    public void Agadari() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        Button btnNo;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_layout, viewGroup, false);
        builder.setCancelable(false);
        builder.setView(view);


        btnNo = view.findViewById(R.id.btnSetting);

        final AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btnNo.setOnClickListener(v -> alertDialog.dismiss());


        alertDialog.show();

        SharedPreferences sharedPreferences4 = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editorShow = sharedPreferences4.edit();
        editorShow.putBoolean("show", false);
        editorShow.apply();
    }


    @SuppressLint("MissingInflatedId")
    public void AgadariInternet() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        Button btnNo;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_no_internet_layout, viewGroup, false);
        builder.setCancelable(false);
        builder.setView(view);


        btnNo = view.findViewById(R.id.btnSetting);

        final AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btnNo.setOnClickListener(v -> alertDialog.dismiss());


        alertDialog.show();

    }

    @SuppressLint("MissingInflatedId")
    public void AgadariDasteLat() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        Button btnSetting, btnCancel, btnCloseAll;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_no_pirmesion_layout, viewGroup, false);
        builder.setCancelable(false);
        builder.setView(view);


        btnSetting = view.findViewById(R.id.btnSetting);
        btnCancel = view.findViewById(R.id.btnCancelDialo);
        btnCloseAll = view.findViewById(R.id.btnCloseAll);

        final AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnSetting.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, AppSettings.class);
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


    private final InstallStateUpdatedListener installStateUpdatedListener = installState -> {
        if (installState.installStatus() == InstallStatus.DOWNLOADED) {
            showCompletedUpdate();
        }
    };

    @Override
    protected void onStop() {
        if (mAppUpdateManager != null)
            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
        super.onStop();
    }

    private void showCompletedUpdate() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "ڤێرژنی نوێ بەردەستە", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("دابەزاندن", view -> mAppUpdateManager.completeUpdate());
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RCP_AP_UPDATE && resultCode != RESULT_OK) {
            Toast.makeText(this, "ڕەتکردنەوە", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}