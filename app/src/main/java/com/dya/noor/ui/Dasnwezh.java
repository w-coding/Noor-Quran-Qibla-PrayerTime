package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dya.noor.R;


public class Dasnwezh extends AppCompatActivity {
    ImageButton back ;
    TextView textView15 ,TV1 , TV2 ,TV3,TV4,TV6,TV7,TV12,TV13 , textView24 ,textView25 ,textView26
           ,textView27,textView28,textView29,textView43 ,textView30 , textView33 ,textView34
            ,textView35,textView36,textView37,textView38 , textView39,textView40,textView41
            ,textView42,textView45 , textView44,textView46,textView47,textView48,textView49,textView50
            ,textView51,textView52,textView53,textView54,textView55,textView56,textView57,textView58
            ,textView59,textView60,textView61,textView62,textView63,textView64,textView65,textView66
            ,textView67;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasnwezh);

        back =findViewById(R.id.back);
      //  TextViewNames();
        //TextViewSize();
        back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


   /** public void TextViewNames(){
        textView15 =findViewById(R.id.textViewIslamBawar1);
        TV1 =findViewById(R.id.textViewIslamBawar2);
        TV2=findViewById(R.id.TV2);
        TV3=findViewById(R.id.TV3);
        TV4=findViewById(R.id.TV4);
        TV6=findViewById(R.id.TV6);
        TV7=findViewById(R.id.TV7);
        TV12=findViewById(R.id.TV12);
        TV13=findViewById(R.id.TV13);
        textView24 =findViewById(R.id.textView24);
        textView25 =findViewById(R.id.textView25);
        textView26=findViewById(R.id.textView26);
        textView27=findViewById(R.id.textView27);
        textView28=findViewById(R.id.textView28);
        textView29=findViewById(R.id.textView29);
        textView43 =findViewById(R.id.textView43);
        textView30=findViewById(R.id.textViewIslamBawar17);
        textView33 =findViewById(R.id.textView33);
        textView34=findViewById(R.id.textView34);
        textView35=findViewById(R.id.textView35);
        textView36=findViewById(R.id.textView36);
        textView37=findViewById(R.id.textView37);
        textView38=findViewById(R.id.textView38);
        textView39=findViewById(R.id.textView39);
        textView40=findViewById(R.id.textView40);
        textView41=findViewById(R.id.textView41);
        textView42=findViewById(R.id.textView42);
        textView45=findViewById(R.id.textView45);
        textView44=findViewById(R.id.textView44);
        textView46=findViewById(R.id.textView46);
        textView47=findViewById(R.id.textView47);
        textView48=findViewById(R.id.textView48);
        textView49=findViewById(R.id.textView49);
        textView50=findViewById(R.id.textView50);
        textView51=findViewById(R.id.textView51);
        textView52=findViewById(R.id.textView52);
        textView53=findViewById(R.id.textView53);
        textView54=findViewById(R.id.textView54);
        textView55=findViewById(R.id.textView55);
        textView56=findViewById(R.id.textView56);
        textView57=findViewById(R.id.textView57);
        textView58=findViewById(R.id.textView58);
        textView59=findViewById(R.id.textView59);
        textView60=findViewById(R.id.textView60);
        textView61=findViewById(R.id.textView61);
        textView62=findViewById(R.id.textView62);
        textView63=findViewById(R.id.textView63);
        textView64=findViewById(R.id.textView64);
        textView65=findViewById(R.id.textView65);
        textView66=findViewById(R.id.textView66);
        textView67=findViewById(R.id.textView67);


    }
    public void TextViewSize(){

        SharedPreferences sharedPreferencesSize =getSharedPreferences("size",MODE_PRIVATE);
        int TextSize = sharedPreferencesSize.getInt("textSize", 20);


        textView15.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV1.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV2.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV3.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV4.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV6.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV7.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV12.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        TV13.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView24.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView25.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView26.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView27.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView28.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView29.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView43.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView30.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView33.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView34.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView35.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView36.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView37.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView38.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView39.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView40.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView41.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView42.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView45.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView44.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView46.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView47.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView48.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView49.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView50.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView51.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView52.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView53.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView54.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView55.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView56.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView57.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView58.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView59.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView60.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView61.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView62.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView63.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView64.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView65.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView66.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
        textView67.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);


    }

    */
}