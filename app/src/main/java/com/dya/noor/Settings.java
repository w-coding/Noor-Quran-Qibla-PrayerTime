package com.dya.noor;

import static android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.snackbar.Snackbar;

public class Settings extends AppCompatActivity {



    TextView tvB , tvE , tvActic , tvActic2 ;
    int AnimationDuration = 2000;
    ImageButton sBack ;
    LinearLayout linearLayout;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @SuppressLint({"MissingInflatedId", "SetTextI18n", "BatteryLife"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        sBack = findViewById(R.id.sBack);
        tvB = findViewById(R.id.tvB);
        tvE = findViewById(R.id.tvE);
        tvActic = findViewById(R.id.tvActic);
        tvActic2 = findViewById(R.id.tvActic2);
        linearLayout = findViewById(R.id.linearLayout);



        // create an instance of the snackbar
        final Snackbar snackbar = Snackbar.make( linearLayout,"", Snackbar.LENGTH_LONG);

        // inflate the custom_snackbar_view created previously
        @SuppressLint("InflateParams")
        View customSnackView = getLayoutInflater().inflate(R.layout.toast_layout_done, null);

        // set the background of the default snackbar as transparent
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        // now change the layout of the snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0);

        // register the button from the custom_snackbar_view layout file
        TextView  snackbarText = customSnackView.findViewById(R.id.txtToast);

        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0);


      //  snackbarText.setText("کارا کراوە");
      //  snackbar.show();

        YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tvB);
        YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tvE);
        YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tvActic);
        YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tvActic2);
        sBack.setOnClickListener(v -> onBackPressed());

        tvB.setOnClickListener(v ->
                {
                     sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
                     editor = sharedPreferences.edit();
                     editor.putString("bang", "1");
                     editor.putString("name", "بانگی کورت");
                     editor.apply();
                    snackbarText.setText("بانگی کورت جێگیرکرا");
                    snackbar.show();
                    YoYo.with(Techniques.Pulse).duration(AnimationDuration).playOn(tvB);

                }
                );


        tvE.setOnClickListener(v ->
                {


                    sharedPreferences = getSharedPreferences("key",MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("bang", "2");
                    editor.putString("name", "بانگی تەواو");
                    editor.apply();
                    snackbarText.setText("بانگی تەواو جێگیرکرا");
                    snackbar.show();
                    YoYo.with(Techniques.Pulse).duration(AnimationDuration).playOn(tvE);
                }
                );
        tvActic.setOnClickListener(v -> {

            if (!android.provider.Settings.canDrawOverlays(this)) {

                    startActivity(new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));

                }else {
                snackbarText.setText("کارا کراوە");
                snackbar.show();
                YoYo.with(Techniques.Pulse).duration(AnimationDuration).playOn(tvActic);

            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WAKE_LOCK}, 225);
            }else {
                snackbarText.setText("کارا کراوە");
                snackbar.show();
                YoYo.with(Techniques.Pulse).duration(AnimationDuration).playOn(tvActic);

            }

        });
        tvActic2.setOnClickListener(v -> {


            String pkg=getPackageName();
            PowerManager pm=getSystemService(PowerManager.class);

            if (!pm.isIgnoringBatteryOptimizations(pkg)) {
                startActivity(new Intent(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:"+pkg)));
            }else {
                snackbarText.setText("کارا کراوە");
                snackbar.show();
                YoYo.with(Techniques.Pulse).duration(AnimationDuration).playOn(tvActic2);
            }


        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}