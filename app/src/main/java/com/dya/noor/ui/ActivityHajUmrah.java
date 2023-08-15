package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dya.noor.R;

public class ActivityHajUmrah extends AppCompatActivity {

    ImageButton back,back2;

    TextView t1,t2,t3,t4,t5,t6,tv,tv2,tv3;
    LinearLayout layout1;

    int AnimationDuration = 2000;
    SharedPreferences sharedPreferencesSize;
    int TextSize;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haj_umrah);

        back = findViewById(R.id.back);
        back2 = findViewById(R.id.back2);
        layout1 = findViewById(R.id.layout1);

        t1= findViewById(R.id.t1);
        t2= findViewById(R.id.t2);
        t3= findViewById(R.id.t3);
        t4= findViewById(R.id.t4);
        t5= findViewById(R.id.t5);
        t6= findViewById(R.id.t6);

        tv= findViewById(R.id.tv);
        tv2= findViewById(R.id.tv2);
        tv3= findViewById(R.id.tv3);

        sharedPreferencesSize = getSharedPreferences("size",MODE_PRIVATE);
        TextSize = sharedPreferencesSize.getInt("textSize", 20);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        t1.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        t2.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        t3.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        t4.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        t5.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        t6.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);




        back.setOnClickListener(v -> onBackPressed());
        back2.setOnClickListener(v -> {


            tv.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            back2.setVisibility(View.GONE);



            YoYo.with(Techniques.BounceInUp).duration(AnimationDuration).playOn(back);
            YoYo.with(Techniques.BounceInUp).duration(AnimationDuration).playOn(tv2);
            YoYo.with(Techniques.BounceInUp).duration(AnimationDuration).playOn(layout1);


            back.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);


        });



        t1.setOnClickListener(v -> {

            tv.setText(R.string.sunatakany_safar_krdn2);
            tv3.setText("سونه\u200Cته\u200Cكانی سه\u200Cفه\u200Cر كردن");
            back.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);

            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv3);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(back2);
            tv.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            back2.setVisibility(View.VISIBLE);

        });
        t2.setOnClickListener(v -> {

            tv.setText(R.string.hukma_kany_haj_w_umra2);
            tv3.setText("حوكمه\u200Cكانی حه\u200Cج و عومره\u200Cو گه\u200Cوره\u200Cییان");
            back.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);

            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv3);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(back2);

            tv.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            back2.setVisibility(View.VISIBLE);

        });
        t3.setOnClickListener(v -> {

            tv.setText(R.string.karakany_haj_w_umra2);
            tv3.setText("كاره\u200Cكانی حه\u200Cج و عومڕه");
            back.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);

            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv3);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(back2);

            tv.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            back2.setVisibility(View.VISIBLE);

        });
        t4.setOnClickListener(v -> {

            tv.setText(R.string.ark_w_rwknakany_haj2);
            tv3.setText("ئه\u200Cرك و ڕوكنه\u200Cكانی حه\u200Cج");
            back.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);

            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv3);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(back2);

            tv.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            back2.setVisibility(View.VISIBLE);

        });
        t5.setOnClickListener(v -> {

            tv.setText(R.string.anjamdany_umrah2);
            tv3.setText("ئه\u200Cنجامدانی عومڕه");
            back.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);

            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv3);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(back2);

            tv.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            back2.setVisibility(View.VISIBLE);

        });
        t6.setOnClickListener(v -> {

            tv.setText(R.string.zyaraty_madyna2);
            tv3.setText("زیاره\u200Cتی مه\u200Cدینه\u200Cی مونه\u200Cوه\u200Cره");
            back.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);

            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(tv3);
            YoYo.with(Techniques.BounceInDown).duration(AnimationDuration).playOn(back2);

            tv.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            back2.setVisibility(View.VISIBLE);

        });



    }

    @Override
    public void onBackPressed() {

        if (layout1.getVisibility()==View.GONE){
            tv.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            back2.setVisibility(View.GONE);

            YoYo.with(Techniques.BounceInUp).duration(AnimationDuration).playOn(back);
            YoYo.with(Techniques.BounceInUp).duration(AnimationDuration).playOn(tv2);
            YoYo.with(Techniques.BounceInUp).duration(AnimationDuration).playOn(layout1);

            back.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);

        }else {
            super.onBackPressed();
            finish();
        }


    }
}