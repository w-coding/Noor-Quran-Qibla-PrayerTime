package com.dya.noor;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ActivityQuranPdf extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    PDFView pdfView;
    int pageNumber = 0;

    ImageButton back , retry;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_pdf);
        pdfView = findViewById(R.id.pdfView);
        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();


        back = findViewById(R.id.back);
        retry = findViewById(R.id.retry);
        back.setOnClickListener(v -> onBackPressed());

        retry.setOnClickListener(v -> {
            pdfView.jumpTo(0);
        });



        File src = new File(getFilesDir() + "/DOCUMENTS/Quran.pdf");
        File dst = new File(getExternalFilesDir(null) + "/DOCUMENTS/Quran.pdf");
        try {
            FileInputStream inStream = new FileInputStream(src);
            FileOutputStream outStream = new FileOutputStream(dst);
            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inStream.close();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pageNumber = prefs.getInt("pageNumber", 0);
        pdfView.fromFile(new File(getExternalFilesDir(null) + "/DOCUMENTS/Quran.pdf"))
                .defaultPage(pageNumber)
                .onLoad(nbPages -> pdfView.fromFile(new File(getExternalFilesDir(null) + "/DOCUMENTS/Quran.pdf"))
                        .defaultPage(pageNumber)
                        .enableSwipe(true)
                        .enableDoubletap(true)
                        .swipeHorizontal(true)
                        .pageSnap(true)
                        .spacing(5)
                        .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                        .fitEachPage(true)
                        .autoSpacing(true)
                        .pageFling(true)
                        .load())
                .load();
    }

    @Override
    public void onBackPressed() {
        int page = pdfView.getCurrentPage();
        editor.putInt("pageNumber", page);
        editor.apply();
        super.onBackPressed();
    }
}