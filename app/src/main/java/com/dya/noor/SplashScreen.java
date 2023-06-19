package com.dya.noor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.makeramen.roundedimageview.RoundedImageView;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    RoundedImageView imageView;
    Animation  fromTop;
    MydbClass mydbClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mydbClass = new MydbClass(this);
        new Utils(this);

        //bakardet bo goryny rangy status bar wata shryty nyshandany shabaka u sha7n
        Window window =this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.SplashScreenColor));

        imageView =findViewById(R.id.cardeView);
        fromTop = AnimationUtils.loadAnimation(this, R.anim.frometope);

        imageView.setAnimation(fromTop);

        Thread splashThread = new Thread(() -> {
            try {
                Thread.sleep(2500);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("dowmload","f");
                startActivity(intent);
                finish();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        splashThread.start();

    }
}