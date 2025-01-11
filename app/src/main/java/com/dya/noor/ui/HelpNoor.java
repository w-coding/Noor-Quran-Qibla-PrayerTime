package com.dya.noor.ui;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dya.noor.R;

import java.util.Objects;

public class HelpNoor extends AppCompatActivity {

    ImageView btnBack , fastPayBtn , fibBtn , btnKorek , btnAsia;
    TextView   tvData , tvThree , tvFive , tvTen , tvIncrement , tvDecrement , tvSend , helpTextView;
    String phoneNumber = "7511935352";
    String n = "*123*19000*0770942824#";
    int Three = 3000;
    int Five = 5000;
    int Ten = 10000;
    int Data = 1000;

    LinearLayout SendLayout ;

    String companyName="Korek";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_noor);
        btnBack = findViewById(R.id.btnBack);
        fastPayBtn = findViewById(R.id.fastPayBtn);
        tvData = findViewById(R.id.tvData);
        fibBtn = findViewById(R.id.fibBtn);

        tvIncrement = findViewById(R.id.tvIncrement);
        tvDecrement = findViewById(R.id.tvDecrement);

        SendLayout = findViewById(R.id.SendLayout);


        tvThree = findViewById(R.id.tvThree);
        helpTextView = findViewById(R.id.helpTextView);
        tvFive = findViewById(R.id.tvFive);
        tvTen = findViewById(R.id.tvTen);
        tvSend = findViewById(R.id.tvSend);

        btnKorek = findViewById(R.id.btnKorek);
        btnAsia = findViewById(R.id.btnAsia);

        tvData.setText(formatData(Data));




        String arabicText = helpTextView.getText().toString();

        // Find the index of the part you want to change color for
        int startIndex = arabicText.indexOf("بسم الله الرحمن الرحيم");
        int endIndex = startIndex + "بسم الله الرحمن الرحيم".length();
        int startIndex2 = arabicText.indexOf("نور");
        int endIndex2 = startIndex2 + "نور".length();
        int startIndex3 = arabicText.indexOf("و صلاة وسلام على رسول الله");
        int endIndex3 = startIndex3 + "و صلاة وسلام على رسول الله".length();

        // Check if the index is valid
        if (startIndex != -1 && endIndex <= arabicText.length()) {
            // Create a SpannableString
            SpannableString spannableString = new SpannableString(arabicText);

            // Set the color for the specified part
            int color = R.color.textColor;
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(color)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(24, true), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(color)), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(20, true), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(color)), startIndex3, endIndex3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), startIndex3, endIndex3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(22, true), startIndex3, endIndex3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            // Set the SpannableString to the TextView
            helpTextView.setText(spannableString);
        } else {
            // Log or handle the case where the text to be colored is not found
        }








        btnKorek.setOnClickListener(v -> {
            if (SendLayout.getVisibility() == View.GONE ){
                SendLayout.setVisibility(View.VISIBLE);
            }
            companyName="Korek";
            btnAsia.setBackgroundResource(R.drawable.backgraownd_asi_korek_no_select);
            btnKorek.setBackgroundResource(R.drawable.backgraownd_asi_korek);
        });

        btnAsia.setOnClickListener(v -> {
            if (SendLayout.getVisibility() == View.GONE ){
                SendLayout.setVisibility(View.VISIBLE);
            }
            companyName="Asia";

            btnAsia.setBackgroundResource(R.drawable.backgraownd_asi_korek);
            btnKorek.setBackgroundResource(R.drawable.backgraownd_asi_korek_no_select);
        });


        tvIncrement.setOnClickListener(v -> {

            // Increment data point 1 by 1000 with a minimum of 0
            Data = Math.max(0, Data + 1000);
            tvData.setText(formatData(Data));

        });

        tvDecrement.setOnClickListener(v -> {
            if (Data>1000) {
                Data = Math.max(0, Data - 1000);
                tvData.setText(formatData(Data));
            }
        });



        tvThree.setOnClickListener(v -> {
            Data=Three;
            tvData.setText(formatData(Three));

            tvThree.setBackgroundResource(R.drawable.backgraownd_pay);
            tvFive.setBackgroundResource(R.drawable.backgraownd_pay_re);
            tvTen.setBackgroundResource(R.drawable.backgraownd_pay_re);
        });

        tvFive.setOnClickListener(v -> {
            Data=Five;
            tvData.setText(formatData(Five));

            tvFive.setBackgroundResource(R.drawable.backgraownd_pay);
            tvThree.setBackgroundResource(R.drawable.backgraownd_pay_re);
            tvTen.setBackgroundResource(R.drawable.backgraownd_pay_re);
        });

        tvTen.setOnClickListener(v -> {
            Data=Ten;
            tvData.setText(formatData(Ten));

            tvTen.setBackgroundResource(R.drawable.backgraownd_pay);
            tvThree.setBackgroundResource(R.drawable.backgraownd_pay_re);
            tvFive.setBackgroundResource(R.drawable.backgraownd_pay_re);
        });

        tvSend.setOnClickListener(v -> {
            sendMoney(companyName, String.valueOf(Data));
        });


        fastPayBtn.setOnClickListener(v -> {

            // Access the system clipboard
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

            // Create a new clip with the text
           android.content.ClipboardManager clipboardManager2 =
                   (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData2 = ClipData.newPlainText("text", phoneNumber);
            clipboardManager2.setPrimaryClip(clipData2);
            Toast.makeText(this, "کۆپی بوو", Toast.LENGTH_SHORT).show();
/**
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 101);
            } else {
                // Permission already granted, make call or launch dialer intent
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + Uri.encode("*123*19000*07709442824#"))); // Encode the # symbol
                startActivity(intent);

            }

 */

            openApp("com.sslwireless.fastpay");
        });
        fibBtn.setOnClickListener(v -> {

            // Access the system clipboard
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

            // Create a new clip with the text
           android.content.ClipboardManager clipboardManager2 =
                   (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData2 = ClipData.newPlainText("text", phoneNumber);
            clipboardManager2.setPrimaryClip(clipData2);
            Toast.makeText(this, "کۆپی بوو", Toast.LENGTH_SHORT).show();
            openApp("com.firstiraqibank.personal");
        });

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void openApp(String PackageName) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(PackageName);
        if (intent !=null){

            startActivity(intent);
        }
    }

    public String formatData(int data) {
        if (data >= 1000) {
            return String.format("%,d", data); // Use commas for thousands separators
        } else {
            return String.valueOf(data);
        }
    }

    public void sendMoney(String CompanyName , String money){



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 101);
        } else {
            // Permission already granted, make call or launch dialer intent
            Intent intent = new Intent(Intent.ACTION_CALL);
            if (Objects.equals(CompanyName, "Asia")) {
                intent.setData(Uri.parse("tel:" + Uri.encode("*123*"+money+"*07710500202#"))); // Encode the # symbol
            }else {
                intent.setData(Uri.parse("tel:" + Uri.encode("*215*07511935352*"+ money+"#"))); // Encode the # symbol
            }
            startActivity(intent);

        }

    }


}