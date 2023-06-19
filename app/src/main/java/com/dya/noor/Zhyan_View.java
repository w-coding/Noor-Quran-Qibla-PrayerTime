package com.dya.noor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Zhyan_View extends AppCompatActivity {

    String getItem;
    TextView FirstView,FullView;
    ImageButton back ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhyan__view);
        back =findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());

        FirstView = findViewById(R.id.FirsView);
        FullView = findViewById(R.id.fullView);

        getData();

    }

    public void getData(){
        getItem =getIntent().getStringExtra("fileName");
        switch (getItem) {
            case "ناوی تەواوی":

                FullView.setText(getResources().getString(R.string.FullName));
                FirstView.setText(getResources().getString(R.string.FName));

                break;
            case "ڕەچەڵەکی":

                FullView.setText(getResources().getString(R.string.FullRachalak));
                FirstView.setText(getResources().getString(R.string.FirstRachalak));
                break;
            case "پشت و نەسەبی پێغەمبەرﷺ":

                FullView.setText(getResources().getString(R.string.FullNasab));
                FirstView.setText(getResources().getString(R.string.FirstNasab));

                break;
            case "ژیانی لە پێشبانگەواز":

                FullView.setText(getResources().getString(R.string.FullZhyany));
                FirstView.setText(getResources().getString(R.string.FirstZhyany));

                break;
            case "ساڵی فیل":

                FullView.setText(getResources().getString(R.string.FullFil));
                FirstView.setText(getResources().getString(R.string.FirstFil));

                break;
            case "خواپەرستی پێغەمبەرﷺ لە ئەشکەوتی حراء":

                FullView.setText(getResources().getString(R.string.FullHara));
                FirstView.setText(getResources().getString(R.string.FirstHara));

                break;
            case "ژیانی کۆمەڵایەتی لە شاری مەککە":

                FullView.setText(getResources().getString(R.string.FullKomalayaty));
                FirstView.setText(getResources().getString(R.string.FirstKomalayty));

                break;
            case "ژنهێنانی":

                FullView.setText(getResources().getString(R.string.FullZhnhenan));
                FirstView.setText(getResources().getString(R.string.FirstZhnhenan));

                break;
            case "بەڵگەو نیشانەکانی پێغەمبەرایەتی":

                FullView.setText(getResources().getString(R.string.FullNishana));
                FirstView.setText(getResources().getString(R.string.FirstNishana));
                break;
            case "بانگەوازی":

                FullView.setText(getResources().getString(R.string.FullBangawzy));
                FirstView.setText(getResources().getString(R.string.FirstBangawazy));

                break;
            case "سەرەتای بانگەواز":

                FullView.setText(getResources().getString(R.string.FullSerta));
                FirstView.setText(getResources().getString(R.string.FirstSerta));

                break;
            case "ئەوانەی بەدەم بانگەوازی ئیسلامەوەهاتن":

                FullView.setText(getResources().getString(R.string.FullBadam));
                FirstView.setText(getResources().getString(R.string.FirstBadam));

                break;
            case "کۆچکردن":

                FullView.setText(getResources().getString(R.string.FullKoChKrdn));
                FirstView.setText(getResources().getString(R.string.FirstKochKrdn));

                break;
            case "مەدینە پێش کۆجکردن":

                FullView.setText(getResources().getString(R.string.FullMadina));
                FirstView.setText(getResources().getString(R.string.FirstMadina
                ));
                break;
            case "کۆچی پێغەمبەر ﷺ و وەرچەرخانێکی مێژوویی":

                FullView.setText(getResources().getString(R.string.FullMezhw));
                FirstView.setText(getResources().getString(R.string.FirstMezhw));

                break;
            case "گەیشتنی پێغمبەر ﷺ بە مەدینە":

                FullView.setText(getResources().getString(R.string.FullGaishtn));
                FirstView.setText(getResources().getString(R.string.FirstGaishtn));

                break;
            case "دامەزراندنی دەوڵەتی ئیسلامی":

                FullView.setText(getResources().getString(R.string.FullDamazrandn));
                FirstView.setText(getResources().getString(R.string.FirstDamazrandn));

                break;
            case "دروستکردنی مزگەوتی پێغەمبەرﷺ":

                FullView.setText(getResources().getString(R.string.FullDrustKrdn));
                FirstView.setText(getResources().getString(R.string.FirstDrustKrdn));

                break;
            case "برایەتی نێوان کۆچکەران و پشتیوانان":

                FullView.setText(getResources().getString(R.string.FUllBrayaty));
                FirstView.setText(getResources().getString(R.string.FirstBrayaty));

                break;
            case "دەرئەنجامەکانی کۆچ":

                FullView.setText(getResources().getString(R.string.FullDarAnjam));
                FirstView.setText(getResources().getString(R.string.FirstDarAnjam));

                break;
            case "کۆچی دوای":

                FullView.setText(getResources().getString(R.string.FullKochyDway));
                FirstView.setText(getResources().getString(R.string.FirstKochyDway));

                break;
            case "پاش مردنی":

                FullView.setText(getResources().getString(R.string.FullPash));
                FirstView.setText(getResources().getString(R.string.FirstPash));

                break;
            case "٣٠ فەرمودەی پێغمبەری خوداﷺ":

                FullView.setText(getResources().getString(R.string.FullFarmuda));
                FirstView.setText(getResources().getString(R.string.FirsFarmuda));

                break;
            case "ئیسرا و میعراج":
                FullView.setText(getResources().getString(R.string.FullEsra));
                FirstView.setText(getResources().getString(R.string.FirstEsra));

                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}