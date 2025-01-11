package com.dya.noor.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.dya.noor.R;
import com.dya.noor.adapters.ViewPagerAdapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.utlis.QuranPageUtils;

public class QuranImageActivity extends AppCompatActivity implements ViewPagerAdapter.SurahNameListener {

    ViewPager2 viewPager;
    int id ;
    String folderName = "quranPaage"; // Replace with your actual folder name
    ViewPagerAdapter adapter;

    ImageView btnBack , btnSave;
    TextView txtNamesTop;

    private static final String PREFS_NAME = "QuranPagePreferences";
    private static final String KEY_SAVED_PAGE = "savedPage";
    private static final String KEY_SAVED_ID = "savedId";
    private static final String KEY_SAVED_SURAH_NAME = "savedSurahName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_image);



        viewPager = findViewById(R.id.ViewPager);
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        txtNamesTop = findViewById(R.id.txtNamesTop);
        // Create ViewPagerAdapter with your folder name

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        adapter = new ViewPagerAdapter(this, folderName ,this);

        // Extract page number from intent
        Intent intent = getIntent();
        id =Integer.parseInt(intent.getStringExtra("id"));

        // Get the actual image count
        int actualImageCount = adapter.calculateImageCount(folderName);


        int requestedPage = id* 5; // Adjust multiplier if pages per Surah differ
        // Set initial page within bounds
        viewPager.setCurrentItem(Math.min(requestedPage - QuranPageUtils.pageNumber, actualImageCount - 1), false);
        viewPager.setAdapter(adapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // Update the Surah name when the page changes
                int currentPageNumber = position + QuranPageUtils.pageNumber;

                MydbClass mydbClass = new MydbClass(QuranImageActivity.this);
                String surahName = mydbClass.getSurahNameByPage(currentPageNumber);

                txtNamesTop.setText(surahName.isEmpty() ? "Unknown Surah" : surahName);
            }
        });

        btnSave.setOnClickListener(v -> saveCurrentPage());

    }

    @Override
    public void onSurahNameChanged(String surahName) {
        txtNamesTop.setText(surahName);
    }

    private void saveCurrentPage() {
        int currentPage = viewPager.getCurrentItem() + QuranPageUtils.pageNumber;
        String currentSurahName = txtNamesTop.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SAVED_PAGE, String.valueOf(currentPage));
        editor.putInt(KEY_SAVED_ID, id);
        editor.putString(KEY_SAVED_SURAH_NAME, currentSurahName);
        editor.apply();

        Toast.makeText(this, " کۆتا خوێندنەوە لاپەڕەی " + currentPage + " " + currentSurahName , Toast.LENGTH_SHORT).show();
    }
}