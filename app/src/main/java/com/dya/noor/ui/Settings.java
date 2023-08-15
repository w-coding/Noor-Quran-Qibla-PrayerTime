package com.dya.noor.ui;

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
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dya.noor.R;
import com.google.android.material.snackbar.Snackbar;

public class Settings extends AppCompatActivity {



    TextView tvB , tvE , tvActic , tvActic2 ;
    int AnimationDuration = 2000;
    ImageButton sBack ;
    LinearLayout linearLayout , quranSize ,textSize;
    SharedPreferences sharedPreferences ;
    SharedPreferences sharedPreferencesSize ;
    SharedPreferences.Editor editor ;
    SharedPreferences.Editor editorSize ;
    TextView btnQuranSizeMinus ,btnQuranSizePlus,txtTestQuran, txtQuranSizeValue , btnTextSizePlus ,
            btnTextSizeMinus , txtTextSizeValue , txtTestText;

    int quranSizeNum , textSizeNum  ;

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

        quranSize = findViewById(R.id.quranSize);
        textSize = findViewById(R.id.textSize);

        txtTestQuran = findViewById(R.id.txtTestQuran);
        txtTestText = findViewById(R.id.txtTestText);

        txtQuranSizeValue = findViewById(R.id.txtQuranSizeValue);
        btnQuranSizeMinus = findViewById(R.id.btnQuranSizeMinus);
        btnQuranSizePlus = findViewById(R.id.btnQuranSizePlus);

        btnTextSizePlus = findViewById(R.id.btnTextSizePlus);
        btnTextSizeMinus = findViewById(R.id.btnTextSizeMinus);
        txtTextSizeValue = findViewById(R.id.txtTextSizeValue);

        // texts size
        sharedPreferencesSize = getSharedPreferences("size",MODE_PRIVATE);
        editorSize = sharedPreferencesSize.edit();

        quranSizeNum = sharedPreferencesSize.getInt("quransize", 27);

        textSizeNum = sharedPreferencesSize.getInt("textSize", 20);

        txtQuranSizeValue.setText(String.valueOf(quranSizeNum));
        txtTestQuran.setTextSize(TypedValue.COMPLEX_UNIT_SP, quranSizeNum); // Set the text size in scaled pixels (sp)

        txtTextSizeValue.setText(String.valueOf(textSizeNum));
        txtTestText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeNum); // Set the text size in scaled pixels (sp)

        btnQuranSizePlus.setOnClickListener(v -> {
            if (quranSizeNum<38){
                quranSizeNum++;
                editorSize.putInt("quransize",quranSizeNum);
                editorSize.apply();
                txtQuranSizeValue.setText(String.valueOf(quranSizeNum));
                txtTestQuran.setTextSize(TypedValue.COMPLEX_UNIT_SP, quranSizeNum); // Set the text size in scaled pixels (sp)
            }

        });
        btnQuranSizeMinus.setOnClickListener(v -> {
            if (quranSizeNum>10){
                quranSizeNum--;
                editorSize.putInt("quransize",quranSizeNum);
                editorSize.apply();
                txtQuranSizeValue.setText(String.valueOf(quranSizeNum));
                txtTestQuran.setTextSize(TypedValue.COMPLEX_UNIT_SP, quranSizeNum); // Set the text size in scaled pixels (sp)

            }



        });


        btnTextSizePlus.setOnClickListener(v -> {
            if (textSizeNum<38){

                textSizeNum++;
                editorSize.putInt("textSize",textSizeNum);
                editorSize.apply();

                txtTextSizeValue.setText(String.valueOf(textSizeNum));
                txtTestText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeNum); // Set the text size in scaled pixels (sp)
            }

        });
        btnTextSizeMinus.setOnClickListener(v -> {
            if (textSizeNum>10){

                textSizeNum--;
                editorSize.putInt("textSize",textSizeNum);
                editorSize.apply();

                txtTextSizeValue.setText(String.valueOf(textSizeNum));
                txtTestText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeNum); // Set the text size in scaled pixels (sp)

            }



        });

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


        YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(quranSize);
        YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(textSize);


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