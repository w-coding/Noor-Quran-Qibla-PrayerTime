package com.dya.noor;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

public class Quran extends AppCompatActivity {

    int AnimationDuration = 2000;
    MydbClass mydbClass;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    List<SurahItem> aSuratName;
    ImageButton back;
    EditText SearchRecyclerView;
    TextView txtSaveAya;
    SharedPreferences setPositionSharedPreferences ;
    String scrollPosition ;
    String suartName ;
    String suartId ;
    String suraLink ;
    ImageButton searchButton;
    ImageButton btnHidSearch;
    LinearLayout searchLinearLayout;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        back = findViewById(R.id.back);
        recyclerView =findViewById(R.id.MrecyclerView);
        txtSaveAya =findViewById(R.id.txtSaveAya);
        SearchRecyclerView=findViewById(R.id.SearchRecyclerView);
        searchButton = findViewById(R.id.searchButton);
        btnHidSearch = findViewById(R.id.btnHidSearch);
        searchLinearLayout = findViewById(R.id.searchLinearLayout);
        YoYo.with(Techniques.BounceInRight).duration(AnimationDuration).playOn(searchButton);
        YoYo.with(Techniques.BounceInRight).duration(AnimationDuration).playOn(txtSaveAya);
        btnHidSearch.setOnClickListener(v -> {
            SearchRecyclerView.setText("");
            searchButton.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.BounceInRight).duration(AnimationDuration).playOn(searchButton);
            searchLinearLayout.setVisibility(View.GONE);
        });

        searchButton.setOnClickListener(v -> {
            Agadari();

        });
        txtSaveAya.setOnClickListener(v -> {

                Intent intent = new Intent(Quran.this, SuratView.class);
                intent.putExtra("sura", suartName);
                intent.putExtra("id", suartId);
                intent.putExtra("link", suraLink);
                intent.putExtra("scrollPosition", scrollPosition);
                startActivity(intent);


        });

        mydbClass = new MydbClass(this);
      //  mydbClass.StartWork();
        db=mydbClass.getWritableDatabase();
        aSuratName = new ArrayList<>();

        StoreDataInArrayList();
        SurahAapter surahAapter = new SurahAapter(this,aSuratName);
        recyclerView.setAdapter(surahAapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SearchRecyclerView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                surahAapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        back.setOnClickListener(v -> {
            onBackPressed();

        });


    }
    void StoreDataInArrayList(){

        Cursor cursor = mydbClass.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                aSuratName.add(new SurahItem(cursor.getString(1),cursor.getString(2)
                        ,cursor.getString(3),cursor.getString(4)
                        ,cursor.getString(0),cursor.getString(5)
                ));
             /*   aId.add(cursor.getString(0));
                aSuratName.add(cursor.getString(1));
                aSuratIn.add(cursor.getString(2));
                aSuratIn2.add(cursor.getString(4));
                aSuratIn4.add(cursor.getString(3));
                aLink.add(cursor.getString(5));

              */


            }
        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();


        // Get the saved scroll position from Shared Preferences.
        setPositionSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        scrollPosition = setPositionSharedPreferences.getString("recycler_view_position", "");
        suartName = setPositionSharedPreferences.getString("suraName", "");
        suartId = setPositionSharedPreferences.getString("suraId", "");
        suraLink = setPositionSharedPreferences.getString("suraLink", "");

        if (suartName==null ||suartName.equals("")){
            txtSaveAya.setVisibility(View.GONE);
        }else {
            txtSaveAya.setVisibility(View.VISIBLE);
            txtSaveAya.setText("");
            txtSaveAya.setText("کۆتا خوێندنەوە "+suartName +" : ئایەتی :  "+scrollPosition);
        }
    }

    @SuppressLint("MissingInflatedId")
    public void  Agadari(){
        ViewGroup viewGroup =findViewById(android.R.id.content);

        Button btnSuratSearch;
        Button btnAyahSearch;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view= LayoutInflater.from(Quran.this).inflate(R.layout.dialog_layout_search,viewGroup,false);
        builder.setCancelable(false);
        builder.setView(view);


        btnSuratSearch =view.findViewById(R.id.btnSuratSearch);
        btnAyahSearch =view.findViewById(R.id.btnAyahSearch);

        final   AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btnAyahSearch.setOnClickListener(v -> {
            Intent intent = new Intent(Quran.this, SearchActivity.class);
            startActivity(intent);
            alertDialog.dismiss();
        });
        btnSuratSearch.setOnClickListener(v -> {
            searchButton.setVisibility(View.GONE);
            searchLinearLayout.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.BounceInRight).duration(AnimationDuration).playOn(searchLinearLayout);
            alertDialog.dismiss();
        });


        alertDialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}