package com.dya.noor.splash_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.dya.noor.R;
import com.dya.noor.widget.SalatWidget;
import com.dya.noor.widget.SalatWidgetVertical;
import com.dya.noor.utlis.Utils;
import com.dya.noor.database.MydbClass;
import com.dya.noor.ui.ActivityPrayersTime;
import com.dya.noor.ui.MainActivity;
import com.makeramen.roundedimageview.RoundedImageView;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    RoundedImageView imageView;
    Animation fromTop;
    MydbClass mydbClass;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mydbClass = new MydbClass(this);
        mydbClass.StartWork();

        new Utils(this);
        Utils.activeContext = this;

        MydbClass.setNextPrayer(this);

        SalatWidget.updateAllWidgetViews(this);
        SalatWidgetVertical.updateAllWidgetViews(this);
        //bakardet bo goryny rangy status bar wata shryty nyshandany shabaka u sha7n
        /** Window window =this.getWindow();
         window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
         window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
         window.setStatusBarColor(this.getResources().getColor(R.color.SplashScreenColor));
         */

        imageView = findViewById(R.id.cardeView);
        fromTop = AnimationUtils.loadAnimation(this, R.anim.frometope);

        imageView.setAnimation(fromTop);

        Thread splashThread = new Thread(() -> {
            try {
                Thread.sleep(2000);


                if (getIntent().hasExtra("open_prayer")) {
                    Log.d("HAX","has open prayer.");
                    intent = new Intent(getApplicationContext(), ActivityPrayersTime.class);
                    intent.putExtra("open_prayer", getIntent().getBooleanExtra("open_prayer", false));


                }else {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("dowmload", "f");
                }

                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        splashThread.start();

    }
}