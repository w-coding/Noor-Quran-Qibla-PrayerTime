package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dya.noor.R;
import com.google.android.gms.maps.MapView;


public class About extends AppCompatActivity {

    ImageButton back;
    ImageView fb,ig;
    TextView TvV;

    MapView mapView ;
    // Get the package manager
    PackageManager packageManager;
    // Get the package info for the current app
    PackageInfo packageInfo = null;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        back = findViewById(R.id.back);
        fb = findViewById(R.id.btnSfb);
        ig = findViewById(R.id.btnSig);
        TvV = findViewById(R.id.textViewVer);
        back.setOnClickListener(v -> onBackPressed());

        packageManager = getPackageManager();


        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }

        TvV.setText( " وەشانی : "+packageInfo.versionName);


        fb.setOnClickListener(view -> {

            //https://www.facebook.com/noor.page.officiall/

            String facbookUrl = "https://www.facebook.com/noor.page.officiall/";
            try {
                int versionCode = getPackageManager().getPackageInfo("com.facebook.katana",0).versionCode;
                if (versionCode>=399002493){
                    Uri uri = Uri.parse("fb://facewebmodal/f?href="+facbookUrl);
                    startActivity(new Intent(Intent.ACTION_VIEW,uri));
                }else{
                    Uri uri = Uri.parse("fb://facewebmodal/f?href="+facbookUrl);
                    //startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/100063832522131")));
                    startActivity(new Intent(Intent.ACTION_VIEW,uri));
                }
            } catch (PackageManager.NameNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(facbookUrl)));
            }






        });

       // https://instagram.com/noor.page.official
        ig.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://instagram.com/_u/noor.page.official/");
            Intent linkIg = new Intent(Intent.ACTION_VIEW,uri);
            linkIg.setPackage("com.instagram.android");

            try {
                startActivity(linkIg);

            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/noor.page.official/")));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}