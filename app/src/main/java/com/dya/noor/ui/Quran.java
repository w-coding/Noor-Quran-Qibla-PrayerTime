package com.dya.noor.ui;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import com.dya.noor.R;
import com.dya.noor.adapters.SurahAapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.download.DownloadFilesTask;
import com.dya.noor.module.SurahItem;
import com.dya.noor.utlis.QuranPageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Quran extends AppCompatActivity {

    int AnimationDuration = 2000;
    MydbClass mydbClass;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    List<SurahItem> aSuratName;
    ImageButton back;
    EditText SearchRecyclerView;
    TextView txtSaveAya , txtSavePage;
    SharedPreferences PageSharedPreferences ;
    String scrollPosition ;
    String suartName ;
    String suartId ;
    String suraLink ;
    ImageButton searchButton;
    ImageButton btnHidSearch;
    LinearLayout searchLinearLayout;
    String suraName ;
    String suraId ;
    String surahLink;
    String suraPage , suraStartedPage;
    TextView txtQuranPage,txtTafseerPAge ,txtQuranPageImage;
    SurahAapter surahAapter;

    private static final String PREFS_NAME = "QuranPagePreferences";
    private static final String KEY_SAVED_PAGE = "savedPage";
    private static final String KEY_SAVED_STARTED_PAGE = "savedStartedPage";
    private static final String KEY_SAVED_ID = "savedId";
    private static final String KEY_SAVED_SURAH_NAME = "savedSurahName";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    SharedPreferences.Editor editorPge ;

    int types;

    // check krdny internet
    ConnectivityManager connectivityManager ;
    NetworkInfo wifi ;
    NetworkInfo mobileNetwork;
    //////////

    String folderName = "quranPaage";
    String pngLink = "https://raw.githubusercontent.com/w-coding/Holy-Quran/main/Quran_per_page/";
    HashMap<Integer, List<String>> fileUrlsMap =new HashMap<>();
    List<String> fileUrls = new ArrayList<>();
    List<String> urls ;

    List<String> missingFiles = new ArrayList<>();

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
        txtSavePage = findViewById(R.id.txtSavePage);
        txtQuranPage = findViewById(R.id.txtQuranPage);
        txtTafseerPAge = findViewById(R.id.txtTafseerPAge);
        txtQuranPageImage = findViewById(R.id.txtQuranPageImage);
        searchLinearLayout = findViewById(R.id.searchLinearLayout);

        YoYo.with(Techniques.BounceInRight).duration(AnimationDuration).playOn(searchButton);
        YoYo.with(Techniques.BounceInRight).duration(AnimationDuration).playOn(txtSaveAya);

        btnHidSearch.setOnClickListener(v -> {
            SearchRecyclerView.setText("");
            searchButton.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.BounceInRight).duration(AnimationDuration).playOn(searchButton);
            searchLinearLayout.setVisibility(View.GONE);
        });

        sharedPreferences = getSharedPreferences("PageType",MODE_PRIVATE);
      //  sharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        types = sharedPreferences.getInt("type",1);



        // Get the saved scroll position from Shared Preferences.
        PageSharedPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);;
        editorPge= PageSharedPreferences.edit();


        scrollPosition = PageSharedPreferences.getString("recycler_view_position", "");
        suartName = PageSharedPreferences.getString("suraName", "");
        suartId = PageSharedPreferences.getString("suraId", "");
        suraLink = PageSharedPreferences.getString("suraLink", "");

        //
        suraName = PageSharedPreferences.getString(KEY_SAVED_SURAH_NAME,"") ;
        suraId = String.valueOf(PageSharedPreferences.getInt(KEY_SAVED_ID,0));
        suraPage = PageSharedPreferences.getString(KEY_SAVED_PAGE,"") ;
        suraStartedPage = PageSharedPreferences.getString(KEY_SAVED_STARTED_PAGE,"") ;


        // check krdny internet
         connectivityManager  = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
         wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
         mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //////////

        if (types==1){

            UpdateUi();

            txtTafseerPAge.setBackgroundResource(R.drawable.btn_yes_bg);
            txtQuranPage.setBackgroundResource(R.drawable.backgrownd_btn_taf);
            txtQuranPageImage.setBackgroundResource(R.drawable.backgrownd_btn_taf);

            txtTafseerPAge.setTextColor(getResources().getColor(R.color.TextColor));
            txtQuranPage.setTextColor(getResources().getColor(R.color.textColorBlack));
            txtQuranPageImage.setTextColor(getResources().getColor(R.color.textColorBlack));


        } else if (types==0)
        {
            UpdateUi();
            txtQuranPage.setBackgroundResource(R.drawable.btn_yes_bg);
            txtTafseerPAge.setBackgroundResource(R.drawable.backgrownd_btn_taf);
            txtQuranPageImage.setBackgroundResource(R.drawable.backgrownd_btn_taf);

            txtQuranPage.setTextColor(getResources().getColor(R.color.TextColor));
            txtTafseerPAge.setTextColor(getResources().getColor(R.color.textColorBlack));
            txtQuranPageImage.setTextColor(getResources().getColor(R.color.textColorBlack));



        }
        else if (types==2)
        {
            UpdateUi();
            txtQuranPageImage.setBackgroundResource(R.drawable.btn_yes_bg);
            txtQuranPage.setBackgroundResource(R.drawable.backgrownd_btn_taf);
            txtTafseerPAge.setBackgroundResource(R.drawable.backgrownd_btn_taf);

            txtQuranPageImage.setTextColor(getResources().getColor(R.color.TextColor));
            txtQuranPage.setTextColor(getResources().getColor(R.color.textColorBlack));
            txtTafseerPAge.setTextColor(getResources().getColor(R.color.textColorBlack));




        }
        QuranPageUtils.folderName = folderName;


        txtQuranPage.setOnClickListener(v -> {

            editor.putInt("type",0);
            editor.apply();
            SurahAapter.type=0;
            txtQuranPage.setBackgroundResource(R.drawable.btn_yes_bg);
            txtTafseerPAge.setBackgroundResource(R.drawable.backgrownd_btn_taf);
            txtQuranPageImage.setBackgroundResource(R.drawable.backgrownd_btn_taf);

            txtQuranPage.setTextColor(getResources().getColor(R.color.TextColor));
            txtTafseerPAge.setTextColor(getResources().getColor(R.color.textColorBlack));
            txtQuranPageImage.setTextColor(getResources().getColor(R.color.textColorBlack));

            UpdateUi();


        });

        txtTafseerPAge.setOnClickListener(v -> {


            editor.putInt("type",1);
            editor.apply();
            SurahAapter.type=1;
            txtTafseerPAge.setBackgroundResource(R.drawable.btn_yes_bg);
            txtQuranPage.setBackgroundResource(R.drawable.backgrownd_btn_taf);
            txtQuranPageImage.setBackgroundResource(R.drawable.backgrownd_btn_taf);

            txtTafseerPAge.setTextColor(getResources().getColor(R.color.TextColor));
            txtQuranPage.setTextColor(getResources().getColor(R.color.textColorBlack));
            txtQuranPageImage.setTextColor(getResources().getColor(R.color.textColorBlack));

            UpdateUi();
        });
        txtQuranPageImage.setOnClickListener(v -> {



            for (int i = 1; i <= 604; i++) {
                // Format the number to ensure it's always three digits
                //  String formattedNumber = String.format("%03d", i);
                // Construct the URL with the formatted number
                // String url = pngLink + i + ".png";
                // Add the URL to the list
                // fileUrls.add(url);

                // Format the number to ensure it's always three digits
                String formattedNumber = String.format(Locale.ENGLISH,"%03d", i);
                // Construct the URL with the formatted number
                String url = pngLink + "page" + formattedNumber + ".png";
                // Add the URL to the list
                fileUrls.add(url);
            }


            fileUrlsMap.put(1,fileUrls);

            urls = fileUrlsMap.get(1);
            // Create a File object for the folder
            File folder = new File(this.getExternalFilesDir(null), folderName);

            missingFiles = new ArrayList<>();
            for (String url : urls) {
                // Extract file name from URL
                String fileName = url.substring(url.lastIndexOf('/') + 1);
                // Assuming your files are saved in the external storage directory
                File file = new File(folder, fileName);
                if (!file.exists()) {
                    missingFiles.add(url);
                }
            }

            if (missingFiles.isEmpty()) {
                // Toast.makeText(this, "Reciter selected ", Toast.LENGTH_SHORT).show();


                editor.putInt("type",2);
                editor.apply();
                SurahAapter.type=2;
                txtQuranPageImage.setBackgroundResource(R.drawable.btn_yes_bg);
                txtTafseerPAge.setBackgroundResource(R.drawable.backgrownd_btn_taf);
                txtQuranPage.setBackgroundResource(R.drawable.backgrownd_btn_taf);

                txtQuranPageImage.setTextColor(getResources().getColor(R.color.TextColor));
                txtTafseerPAge.setTextColor(getResources().getColor(R.color.textColorBlack));
                txtQuranPage.setTextColor(getResources().getColor(R.color.textColorBlack));


                UpdateUi();


            } else {
                AgadariFile();
            }



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
        txtSavePage.setOnClickListener(v -> {



                Intent intent = new Intent(Quran.this, QuranImageActivity.class);
                intent.putExtra("sura", suraName);
                intent.putExtra("id", suraId);
                //intent.putExtra("id", suraPage);
                QuranPageUtils.pageNumber = Integer.parseInt(suraPage);
                intent.putExtra("link", suraLink);
                startActivity(intent);



        });

        mydbClass = new MydbClass(this);
      //  mydbClass.StartWork();
        db=mydbClass.getWritableDatabase();
        aSuratName = new ArrayList<>();

        StoreDataInArrayList();
         surahAapter = new SurahAapter(this,aSuratName);
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
                        ,cursor.getString(0),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),cursor.getString(8)
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

        surahAapter.notifyDataSetChanged();

        types = sharedPreferences.getInt("type",1);

        SurahAapter.type=types;
        scrollPosition = PageSharedPreferences.getString("recycler_view_position", "");
        suartName = PageSharedPreferences.getString("suraName", "");
        suartId = PageSharedPreferences.getString("suraId", "");
        suraLink = PageSharedPreferences.getString("suraLink", "");

        //
        suraName = PageSharedPreferences.getString(KEY_SAVED_SURAH_NAME,"") ;
        suraId = String.valueOf(PageSharedPreferences.getInt(KEY_SAVED_ID,0));
        suraPage = PageSharedPreferences.getString(KEY_SAVED_PAGE,"") ;


        UpdateUi();
    }


    public void UpdateUi(){


        // Reset visibility
        txtSaveAya.setVisibility(View.GONE);
        txtSavePage.setVisibility(View.GONE);
        txtSaveAya.setText("");
        txtSavePage.setText("");

        if (suartName==null && suartName.isEmpty() && suartName=="" && SurahAapter.type!=1){
            txtSaveAya.setVisibility(View.GONE);
        }else if (suartName!=null && suartName!=""&& SurahAapter.type==1) {
            txtSaveAya.setVisibility(View.VISIBLE);
            txtSavePage.setVisibility(View.GONE);
            txtSaveAya.setText("");
            txtSaveAya.setText("کۆتا خوێندنەوە :   "+suartName +" : ئایەتی - "+scrollPosition);
        }// SavedPage
        else if (suraName==null && suraName.isEmpty()&& suraName=="" && SurahAapter.type!=2){
            txtSavePage.setVisibility(View.GONE);
        }else if (suraName!=null && suraName!="" && SurahAapter.type==2) {
            txtSavePage.setVisibility(View.VISIBLE);
            txtSaveAya.setVisibility(View.GONE);
            txtSavePage.setText("");
            txtSavePage.setText("کۆتا خوێندنەوە : "+suraName +" | لاپەڕە :  "+suraPage);
        }else if (SurahAapter.type==0) {
            txtSavePage.setVisibility(View.GONE);
            txtSaveAya.setVisibility(View.GONE);
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


    @SuppressLint("MissingInflatedId")
    public void  AgadariFile(){
        ViewGroup viewGroup =findViewById(android.R.id.content);

        Button btnSetting;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view= LayoutInflater.from(Quran.this).inflate(R.layout.dialog_no_file_internet_layout,viewGroup,false);
        builder.setCancelable(false);
        builder.setView(view);


        btnSetting =view.findViewById(R.id.btnSetting);

        final   AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btnSetting.setOnClickListener(v -> {
            downloadFiles();
            alertDialog.dismiss();
        });


        alertDialog.show();

    }


    @SuppressLint("MissingInflatedId")
    public void AgadariInternet() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        Button btnNo;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_no_internet_layout, viewGroup, false);
        builder.setCancelable(false);
        builder.setView(view);


        btnNo = view.findViewById(R.id.btnSetting);

        final AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btnNo.setOnClickListener(v -> alertDialog.dismiss());


        alertDialog.show();

    }


    public void downloadFiles(){

        //DownloadFilesTask downloadTask = new DownloadFilesTask(this, missingFiles);
       // downloadTask.startDownload();
        if (missingFiles.isEmpty()) {
            // Toast.makeText(this, "Reciter selected ", Toast.LENGTH_SHORT).show();

        } else {

            // gar wifi yan mobile data habw ba download dastpebkat
            if (wifi.isConnected()|| mobileNetwork.isConnected()) {
                //  Toast.makeText(this, " " + missingFiles.toString(), Toast.LENGTH_LONG).show();
                new DownloadFilesTask(this, missingFiles).execute();
            }else {
                AgadariInternet();
            }
        }


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}