package com.dya.noor.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.dya.noor.R;
import com.dya.noor.widget.SalatWidget;
import com.dya.noor.widget.SalatWidgetVertical;


public class CitiesSetting extends AppCompatActivity {

    String City , City2 ;
    Intent intent;
    CardView HawlerCity , SlemanyCity , SoranyCity ,DhokCity , KarkukCity , HalabjaCity , KalarCity , KfriCity
            , DarbandyxanCity , ChamchamalnCity ,QaladzeCity , RanyaCity , KoyaCity , AkreCity ,
            ZaxoCity,
            DukanCity , PenjuinCity , TaqTaqCity , XanaqinCity , HajiawaCity , AmediCity ,
            ArbatCity, ChwartaCity , PenjinCity ,PiramagrunCity , SaydSadqCity , TaKyaCity ,
            TaslujaCity ,XalakanCity , MosulCity , DuzxurmatuCity ,HalabjaNCity ,
            BazyanCity;


    String activityName ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_setting);

        Cities();

        activityName = getIntent().getStringExtra("act");
          HawlerCity.setOnClickListener(view -> {

            City="Hawler";
            City2="هەولێر";

              SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPreferences.edit();
              editor.putString("City",City);
              editor.putString("City2",City2);
              editor.apply();
              onBackPressed();

          });

        SlemanyCity.setOnClickListener(view -> {

            City="Slemany";
            City2="سلێمانی";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        DhokCity.setOnClickListener(view -> {
            City="Duhok";
            City2="دهۆک";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        KarkukCity.setOnClickListener(view -> {
            City = "Kirkuk";
            City2="کەرکوک";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();

        });

        HalabjaCity.setOnClickListener(view -> {
            City = "Halabja";
            City2="هەڵەبجە";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        KalarCity.setOnClickListener(view -> {
            City = "Kalar";
            City2="کەلار";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        KfriCity.setOnClickListener(view -> {
            City ="kfri";
            City2="کفری";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        DarbandyxanCity.setOnClickListener(view -> {

            City = "Darbandixan";
            City2="دەربەندی خان";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        ChamchamalnCity.setOnClickListener(view -> {
            City = "Chamchamal";
            City2="چەمچەماڵ";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        QaladzeCity.setOnClickListener(view -> {
            City = "Qaladze";
            City2="قەڵادزێ";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        RanyaCity.setOnClickListener(view -> {
            City="Ranya";
            City2="ڕانیە";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        KoyaCity.setOnClickListener(view -> {
            City = "Koya";
            City2="کۆیە";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        AkreCity.setOnClickListener(view -> {
            City = "Akre";
            City2="ئاکرێ";

            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        ZaxoCity.setOnClickListener(view -> {
            City = "Zaxo";
            City2="زاخۆ";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });



       DukanCity.setOnClickListener(view -> {
            City = "Dukan";
            City2="دوکان";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
           onBackPressed();
        });

        PenjuinCity.setOnClickListener(view -> {
            City = "Penjuin";
            City2="پێنجوێن";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        TaqTaqCity.setOnClickListener(view -> {
            City = "TaqTaq";
            City2="تەق تەق";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        XanaqinCity.setOnClickListener(view -> {
            City = "Xanaqin";
            City2="خانەقین";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        HalabjaNCity .setOnClickListener(view -> {
            City = "HalabjaN";
            City2="هەڵەبجەی تازە";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        HajiawaCity .setOnClickListener(view -> {
            City = "HajiAwa";
            City2="حاجیاوا";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        AmediCity .setOnClickListener(view -> {
            City = "Amedi";
            City2="ئامێدی";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        ArbatCity  .setOnClickListener(view -> {
            City = "Arbat";
            City2="عەربەت";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        ChwartaCity .setOnClickListener(view -> {
            City = "Chwarta";
            City2="چوارتا";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });

        SaydSadqCity .setOnClickListener(view -> {
            City = "SaidSadiq";
            City2="سەید سادق";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        TaKyaCity .setOnClickListener(view -> {
            City = "Takya";
            City2="تەکیە";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        TaslujaCity .setOnClickListener(view -> {
            City = "Tasluja";
            City2="تاسڵوجە";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        XalakanCity .setOnClickListener(view -> {
            City = "Xalakan";
            City2="خەلەکان";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        MosulCity .setOnClickListener(view -> {
            City = "mosul";
            City2="موسڵ";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        DuzxurmatuCity .setOnClickListener(view -> {
            City = "tuzxurmatu";
            City2="دوزخورماتو";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        PiramagrunCity .setOnClickListener(view -> {
            City = "Piramagrun";
            City2="پیرەمەگروون";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });


        BazyanCity.setOnClickListener(view -> {
            City = "Bazyan";
            City2="بازیان";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
        SoranyCity.setOnClickListener(view -> {
            City = "Soran";
            City2="سۆران";
            SharedPreferences sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("City",City);
            editor.putString("City2",City2);
            editor.apply();
            onBackPressed();
        });
    }


    void Cities(){

        HawlerCity = findViewById(R.id.HawlerCity);
        SlemanyCity = findViewById(R.id.SlemanyCity);
        DhokCity = findViewById(R.id.DhokCity);
        KarkukCity = findViewById(R.id.KarkukCity);
        HalabjaCity = findViewById(R.id.HalabjaCity);
        KalarCity = findViewById(R.id.KalarCity);
        KfriCity = findViewById(R.id.KfriCity);
        DarbandyxanCity = findViewById(R.id.DarbandyxanCity);
        ChamchamalnCity = findViewById(R.id.ChamchamalnCity);
        QaladzeCity = findViewById(R.id.QaladzeCity);
        RanyaCity = findViewById(R.id.RanyaCity);
        KoyaCity = findViewById(R.id.KoyaCity);
        AkreCity = findViewById(R.id.AkreCity);
        ZaxoCity = findViewById(R.id.ZaxoCity);
        SoranyCity = findViewById(R.id.SoranCity);
        // new
        DukanCity = findViewById(R.id.DukanCity);
        PenjuinCity = findViewById(R.id.PenjuinCity);
        TaqTaqCity = findViewById(R.id.TaqTaqCity);
        XanaqinCity = findViewById(R.id.XanaqinCity);
        HalabjaNCity = findViewById(R.id.HalabjaCityN);
        HajiawaCity = findViewById(R.id.HajiawaCity);
        AmediCity = findViewById(R.id.AmediCity);
        ArbatCity = findViewById(R.id.ArbatCity);
        ChwartaCity = findViewById(R.id.ChwartaCity);
        SaydSadqCity = findViewById(R.id.SaidSadiqCity);
        TaKyaCity = findViewById(R.id.TakyaCity);
        TaslujaCity = findViewById(R.id.TaslujaCity);
        XalakanCity = findViewById(R.id.XalakanCity);
        MosulCity = findViewById(R.id.mosulCity);
        DuzxurmatuCity = findViewById(R.id.tuzxurmatuCity);
        PiramagrunCity = findViewById(R.id.PiramagrunCity);
        BazyanCity = findViewById(R.id.BazyanCity);

    }

    @Override
    public void onBackPressed() {
        Intent intent;
        if (activityName.equals("main")){
            intent = new Intent(CitiesSetting.this, MainActivity.class);
        }else {
            intent = new Intent(CitiesSetting.this, ActivityPrayersTime.class);
        }
        startActivity(intent);
        SalatWidget.updateAllWidgetViews(this);
        SalatWidgetVertical.updateAllWidgetViews(this);
        finish();
    }
}
