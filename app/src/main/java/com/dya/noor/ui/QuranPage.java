package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.QuranPageAdapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.module.QuranItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class QuranPage extends AppCompatActivity implements  QuranPageAdapter.OnButtonClickListener{

    ImageView buttonBack;

    String id,SuaraName,fileUrl;
    RecyclerView QuranRecyclerView;
    ImageView btnSetting ;

    // Map/HashMap/LinkedHashMap hawshewai ArrayList/List balam atwanit data bapei shtek jia
    // bkaitawa la dwatr xera atwanit dastt bgat baw data babe garan basar hamw dataka
    //
    // bo nmwwna :
    // hamw aiatakan boian diari krawa ka la parai chann, ba List halian bgrit agar btawet hamw aw aiatana
    // ka akawna parai 25 bo nmwna bang bkait abet basar hamw ayatakan bgareit, kamian nrxakai 25 bw bakari bhenit.
    // agar 200 ayat habet abet loopakat basar 200 ayat bgaret ta la hala dwrbit (awa bala bnein ka harkat gashtina parai
    // 26 bwastin - ba shewaiaki gshti biri le bkawa)..
    // bam shewa w larei Map/HashMap/LinkedHashMap alein am datayana ba shteka bnasawa (lamai xwarawa ba zhmarai para - Integer- bo ema grnga)
    // la katy data aw ayatanai zhmarai para 1 aiankaina bashi 1, awanai 2 akaina 2 ...
    // dwatr ka bo nmwna wtman aw ayatanai para 25 yaksar babe hich loop w garanek rastawxo xoi awanat adate
    // zor xeratra w zor asantra esh lagali.
    public static LinkedHashMap<Integer, ArrayList<QuranItem>> mpAllAyah = new LinkedHashMap<>();
    //
    MydbClass myDbClass;
    QuranPageAdapter quranAdapter;
    TextView SuraName ,SuraNumber;
    ImageView btnBAck , bismillahImageView;
   // FlexboxLayout flexboxLayout;
    Typeface typeface;
    Switch switchNumer;
    ArrayList<QuranItem>quranItemArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_page);

        QuranRecyclerView = findViewById(R.id.pageRecyclerView);
        myDbClass = new MydbClass(this);

        SuraName = findViewById(R.id.txtSuratNAm);
        //SuraNumber = findViewById(R.id.SuraNumber);
        btnBAck = findViewById(R.id.buttonBack);
        btnSetting = findViewById(R.id.btnSetting);
        bismillahImageView = findViewById(R.id.bismillahImageView);
        // flexboxLayout = findViewById(R.id.flexboxLayout);

       // SuraName.setText(getIntent().getStringExtra("sura"));
       // SuraNumber.setText(getIntent().getStringExtra("id"));
        //quranAdapter.SurahLink = fileUrl;
        id = getIntent().getStringExtra("id");
       // SuaraName = getIntent().getStringExtra("id");
        //fileUrl = getIntent().getStringExtra("link");
        String sss = SuraName.getText().toString();

        switchNumer = findViewById(R.id.switchNumber);

        if (id.equals("1") || id.equals("9")){
            bismillahImageView.setVisibility(View.GONE);
        }
        StoreDataInArrayList();

        QuranPageAdapter.suraId= Integer.parseInt(id);
      //  QuranPageAdapter.suraId=QuranPageAdapter.suraId-1;
        quranAdapter = new QuranPageAdapter(this, new ArrayList<>(mpAllAyah.keySet()),this);





        QuranRecyclerView.setAdapter(quranAdapter);
        QuranRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        quranAdapter.SurahNAme = sss;
        quranAdapter.SurahId = id;
        //String scrollPosition = getIntent().getStringExtra("scrollPosition");
       // int scrollPosition2 = Integer.parseInt(scrollPosition);
        // Scroll the RecyclerView to the saved scroll position.
        //QuranRecyclerView.smoothScrollToPosition(scrollPosition2 -2);
      //  QuranRecyclerView.scrollToPosition(scrollPosition2);

        btnBAck.setOnClickListener(v -> {
            onBackPressed();
        });

        btnSetting.setOnClickListener(v -> {
            Intent intent = new Intent(QuranPage.this, AppSettings.class);
            startActivity(intent);
        });

        SharedPreferences preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        final boolean[] isSubItemVisible = {preferences.getBoolean("isSubItemVisible", false)};
        switchNumer.setChecked(isSubItemVisible[0]);
        switchNumer.setOnClickListener(v -> {
            // Toggle the visibility flag
            isSubItemVisible[0] = !isSubItemVisible[0];

            // Save the updated state to SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isSubItemVisible", isSubItemVisible[0]);
            editor.apply();

            // Notify the adapter that the data has changed
            quranAdapter.notifyDataSetChanged();
        });


        StoreNameData(id);


    }


    void StoreDataInArrayList() {

        // data pak akainawa nakw data peshw tya mabet.
        // chwnka ba static krdwmana data peshw amenetawa ka swraty tr bkatawa.
        // paki akainawa ta data am swrata halgret ka krawatawa.
        mpAllAyah.clear();

        Cursor cursor = myDbClass.readAllAyahData(id);

        while (cursor.moveToNext()) {
            // hawl bda dawai data ba zhmara maka - male index 9 bo wargrtni [aya_text], baw shewai xwarawa bika esra7at tra.
            // tanha la sarw nawi class (sari sarawa) am dera ziad bka ba error nadat hichkat :
            // @SuppressLint("Range")
            @SuppressLint("Range")
            QuranItem qt = new QuranItem(
                    cursor.getString(cursor.getColumnIndex("text")),
                    cursor.getString(cursor.getColumnIndex("suraId")),
                    cursor.getString(cursor.getColumnIndex("ayah")),
                    cursor.getString(cursor.getColumnIndex("text")),
                    cursor.getString(cursor.getColumnIndex("juzz")),
                    cursor.getString(cursor.getColumnIndex("page")),
                    cursor.getString(cursor.getColumnIndex("sura_name_ar")),
                    cursor.getString(cursor.getColumnIndex("aya_no_arabic")),
                    cursor.getString(cursor.getColumnIndex("aya_no_arabic_rev"))
            );

            if (mpAllAyah.containsKey(qt.getPagAsInt())) {
                mpAllAyah.get(qt.getPagAsInt()).add(qt);
            } else {
                ArrayList<QuranItem> ar = new ArrayList<>();
                ar.add(qt);
                mpAllAyah.put(qt.getPagAsInt(), ar);
            }
        }
    }
    @SuppressLint("Range")
    void StoreNameData(String id) {



        Cursor cursor = myDbClass.readAllNameData(id);

        while (cursor.moveToNext()) {
            SuraName.setText(cursor.getString(cursor.getColumnIndex("name")));
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        QuranPageAdapter.suraId= Integer.parseInt(id);
        QuranPageAdapter.suraId=QuranPageAdapter.suraId-1;
        finish();
    }

    @Override
    public void onButtonClick(int position) {
        int id2= Integer.parseInt(id);
        if (id2<114) {
            id2++;
            QuranPageAdapter.suraId=id2;
            id= String.valueOf(id2);
            Intent intent = new Intent(this,QuranPage.class);
            intent.putExtra("id",id);
            startActivity(intent);
            finish();
        }

    }
}