package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dya.noor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Babatyrozh extends AppCompatActivity {



    WebView webView;
    ProgressBar progressBarWeb;
    ProgressDialog progressDialog;
    RelativeLayout relativeLayout;
    Button btnNoInternetConnection;
    ImageView back;
    String webUrl;
    // Bundle getUrl;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference childReference = reference.child("url");


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babatyrozh);

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());


        webView =  findViewById(R.id.myWebView);
        progressBarWeb =  findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("تکایە چاوەروانبە");

        btnNoInternetConnection = findViewById(R.id.btnNoConnection);
        relativeLayout =  findViewById(R.id.relativeLayout);

        webView.setWebViewClient(new WebViewClient());

        if(savedInstanceState !=null){
            webView.restoreState(savedInstanceState);
        }
        else
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
           // webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

            checkConnection();

        }







        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progressBarWeb.setVisibility(View.VISIBLE);
                progressBarWeb.setProgress(newProgress);
                setTitle("لەبارکردندایا ....");
                progressDialog.show();
                if(newProgress ==100){

                    progressBarWeb.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                    progressDialog.dismiss();

                }


                super.onProgressChanged(view, newProgress);
            }
        });




        btnNoInternetConnection.setOnClickListener(view -> checkConnection());


    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
            finish();
        }


    }

    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);



        if(wifi.isConnected()){
            // webView.loadUrl(webUrl);
            webView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);

        }
        else if (mobileNetwork.isConnected()){
            //  webView.loadUrl(webUrl);
            webView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }
        else{

            webView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String message = snapshot.getValue(String.class);
                webUrl=message;
                assert webUrl != null;
                if (webUrl.startsWith("http:") || webUrl.startsWith("https:")) {
                    webView.loadUrl(webUrl);

                }

                // Otherwise allow the OS to handle it

                else if (webUrl.startsWith("t.me:")) {
                    Intent tel = new Intent(Intent.ACTION_DIAL, Uri.parse(webUrl));
                    startActivity(tel);

                } else if (webUrl.toLowerCase().startsWith("mailto:")) {
                    MailTo mt = MailTo.parse(webUrl);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}