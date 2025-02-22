package com.dya.noor.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.adapters.AyahAdapter;
import com.dya.noor.adapters.SpinnerItemAdapter;
import com.dya.noor.database.MydbClass;
import com.dya.noor.module.AyahItem;
import com.dya.noor.utlis.Utils;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SuratView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context context = this;
    TextView txtSuaratName , txtbismila;
    ImageView btnPlay ,btnDownload ;
    MediaPlayer mediaPlayer;
    MydbClass mydbClass;
    TextView duration;
    long downloadId;
    String id,filename,fileUrl;
    private final double timeElapsed = 0;
    private final double finalTime = 0;
    double timeRemaining;
    private final int forwardTime = 2000;
    private final int backwardTime = 2000;
    RecyclerView sRecyclerView;
    ImageButton back , btnTafsir , btnSetting;
    CardView cardViewPlay;
    List<AyahItem> aText;
    SeekBar seekBar;
    Handler handler = new Handler();
    Runnable runnable;
    String cPath;
    Uri url;
    CardView btnDownloadLayout;
    boolean ac = false;
    SharedPreferences LastSelected ;
    SharedPreferences.Editor editor ;
    TextView txtTafsirName;
    private int recyclerViewPosition;
    String[] name ;
    EditText SearchRecyclerView;
    String[] link ;

    String nameS;
    String linkS;
    String TafsirS;
    TextView txtToast;
    Spinner spinner;
    String getItem;
    public static Snackbar snackbar ;
    @SuppressLint("StaticFieldLeak")
    public static TextView  snackbarText;
    CoordinatorLayout coordinatorLayout;
    AyahAdapter ayahAdapter;
    Switch krdSwitch ;
    TextView txtKrdRepo;
    ImageView AmazhaImageView;
    SharedPreferences preferencesAmazha;

    SharedPreferences.Editor editorAmazha;

   boolean ShowAamzha;
    int colorSet; // Use 'context' appropriately
    int colorBAlck ;



    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_view);

        AmazhaImageView = findViewById(R.id.AmazhaImageView);

        getItem = getIntent().getStringExtra("tafser");
        name = getResources().getStringArray(R.array.nameQ);
        link = getResources().getStringArray(R.array.linkQ);
        Utils.activeContext = this;
        new Utils(this);
        LastSelected = getSharedPreferences("LastSelecting", Context.MODE_PRIVATE);
        editor = LastSelected.edit();
        final int LastClick = LastSelected.getInt("LastClick", 0);

        spinner = findViewById(R.id.mSpinner);
        btnSetting = findViewById(R.id.btnSetting);
        krdSwitch = findViewById(R.id.krdSwitch);

        txtKrdRepo = findViewById(R.id.txtKrdRepo);
        txtKrdRepo.setOnClickListener(v -> {
            Intent intent = new Intent(SuratView.this,KurdishTaranslite.class);
            startActivity(intent);
        });

        btnSetting.setOnClickListener(v -> {

            AppSettings.activityName="sura";
            Intent intent = new Intent(SuratView.this, AppSettings.class);
            startActivity(intent);
        });
        preferencesAmazha = getSharedPreferences("amazha", MODE_PRIVATE);
        editorAmazha = preferencesAmazha.edit();
        colorSet = ContextCompat.getColor(context, R.color.textColorT); // Use 'context' appropriately
        colorBAlck = ContextCompat.getColor(context, R.color.colorPrimaryDark1); // Use 'context' appropriately




        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        SearchRecyclerView = findViewById(R.id.SearchRecyclerView);
        SharedPreferences preferences3 = getSharedPreferences("pref", MODE_PRIVATE);
        boolean ShowOnStart = preferences3.getBoolean("showDialog", true);

        if (ShowOnStart) {
            Agadari();
        }


        spinner.setOnItemSelectedListener(this);

        SpinnerItemAdapter adapter = new SpinnerItemAdapter(this, name, link);

        spinner.setAdapter(adapter);

        spinner.setSelection(LastClick);
        btnDownloadLayout = findViewById(R.id.cardView33);
        back = findViewById(R.id.sBack);
        btnTafsir = findViewById(R.id.btnTafsir);
        sRecyclerView = findViewById(R.id.sRecyclerView);
        txtSuaratName = findViewById(R.id.txtSuratNAm);
        txtbismila = findViewById(R.id.txtbismila);
        cardViewPlay = findViewById(R.id.cardViewPlay);
        btnPlay = findViewById(R.id.btnPlay);
        txtTafsirName = findViewById(R.id.txtTafsirName);
        btnDownload = findViewById(R.id.btnDownload);
        seekBar = findViewById(R.id.seek_bar);
        mydbClass = new MydbClass(this);

        mediaPlayer = new MediaPlayer();

        aText = new ArrayList<>();


        id = getIntent().getStringExtra("id");
        fileUrl = getIntent().getStringExtra("link");
        back.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        // create an instance of the snackbar

        snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_SHORT);
        // inflate the custom_snackbar_view created previously
        @SuppressLint("InflateParams")
        View customSnackView = getLayoutInflater().inflate(R.layout.toast_layout_done, null);

        // set the background of the default snackbar as transparent
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        // now change the layout of the snackbar
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        // set padding of the all corners as 0
        snackbarLayout.setPadding(0, 0, 0, 0);

        // register the button from the custom_snackbar_view layout file
        snackbarText = customSnackView.findViewById(R.id.txtToast);

        // add the custom snack bar layout to snackbar layout
        snackbarLayout.addView(customSnackView, 0);

        duration = findViewById(R.id.duration);
        btnTafsir.setOnClickListener(v -> {
            ViewGroup viewGroup = findViewById(android.R.id.content);

            TextView puxta, asan, raman, rebar, sanahi, jin, hajar, hich ,
                    tawhid , roshn, maisar , runahi , mokhtasar;
            ImageButton d_close;


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(SuratView.this).inflate(R.layout.tafsir_dialog_layout, viewGroup, false);
            builder.setCancelable(false);
            builder.setView(view);

            puxta = view.findViewById(R.id.t1);
            asan = view.findViewById(R.id.t2);
            raman = view.findViewById(R.id.t3);
            rebar = view.findViewById(R.id.t7);
            sanahi = view.findViewById(R.id.t5);
            jin = view.findViewById(R.id.t6);
            hajar = view.findViewById(R.id.t4);
            hich = view.findViewById(R.id.t8);
            tawhid = view.findViewById(R.id.t9);
            roshn = view.findViewById(R.id.t10);
            maisar = view.findViewById(R.id.t11);
            runahi = view.findViewById(R.id.t13);
            mokhtasar = view.findViewById(R.id.t14);
            d_close = view.findViewById(R.id.d_close);


            final AlertDialog alertDialog = builder.create();

            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            int TextColor = R.color.TextColor;
            // Show the BottomSheetDialog.
            if (Utils.getStringPref("help", "").equals("raman")) {
                raman.setBackgroundResource(R.drawable.backgrownd_done);
                raman.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("puxta")) {
                puxta.setBackgroundResource(R.drawable.backgrownd_done);
                puxta.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("asan")) {
                asan.setBackgroundResource(R.drawable.backgrownd_done);
                asan.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("sanahi")) {
                sanahi.setBackgroundResource(R.drawable.backgrownd_done);
                sanahi.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("zhin")) {
                jin.setBackgroundResource(R.drawable.backgrownd_done);
                jin.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("hazhar")) {
                hajar.setBackgroundResource(R.drawable.backgrownd_done);
                hajar.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("rebar")) {
                rebar.setBackgroundResource(R.drawable.backgrownd_done);
                rebar.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("tawhid")) {
                tawhid.setBackgroundResource(R.drawable.backgrownd_done);
                tawhid.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("roshn")) {
                roshn.setBackgroundResource(R.drawable.backgrownd_done);
                roshn.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("maisar")) {
                maisar.setBackgroundResource(R.drawable.backgrownd_done);
                maisar.setTextColor(getResources().getColor(TextColor));
            }else if (Utils.getStringPref("help", "").equals("runahi")) {
                runahi.setBackgroundResource(R.drawable.backgrownd_done);
                runahi.setTextColor(getResources().getColor(TextColor));
            }else if (Utils.getStringPref("help", "").equals("mokhtasar")) {
                mokhtasar.setBackgroundResource(R.drawable.backgrownd_done);
                mokhtasar.setTextColor(getResources().getColor(TextColor));
            } else if (Utils.getStringPref("help", "").equals("hich")) {
                hich.setBackgroundResource(R.drawable.backgrownd_done);
                hich.setTextColor(getResources().getColor(TextColor));
            }else{
                hich.setBackgroundResource(R.drawable.backgrownd_done);
                hich.setTextColor(getResources().getColor(TextColor));
            }

            d_close.setOnClickListener(view1 -> {
                alertDialog.dismiss();
            });
            puxta.setOnClickListener(view1 -> {

                Utils.put("help", "puxta");
                //finish();
                //startActivity(getIntent());
                String txtToast = puxta.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });

            asan.setOnClickListener(view1 -> {
                Utils.put("help", "asan");
                //finish();
                //startActivity(getIntent());
                String txtToast = asan.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });

            raman.setOnClickListener(view1 -> {

                Utils.put("help", "raman");
                //finish();
                //startActivity(getIntent());
                String txtToast = raman.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });
            rebar.setOnClickListener(view1 -> {
                Utils.put("help", "rebar");
                //finish();
                //startActivity(getIntent());
                String txtToast = rebar.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });
            sanahi.setOnClickListener(view1 -> {

                Utils.put("help", "sanahi");
                //finish();
                //startActivity(getIntent());
                String txtToast = sanahi.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });
            jin.setOnClickListener(view1 -> {

                Utils.put("help", "zhin");
                //finish();
                //startActivity(getIntent());
                String txtToast = jin.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });
            hajar.setOnClickListener(view1 -> {

                Utils.put("help", "hazhar");
                //finish();
                //startActivity(getIntent());
                String txtToast = hajar.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });

            tawhid.setOnClickListener(view1 -> {

                Utils.put("help", "tawhid");
                //finish();
                //startActivity(getIntent());
                String txtToast = tawhid.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });

            roshn.setOnClickListener(view1 -> {

                Utils.put("help", "roshn");
                //finish();
                //startActivity(getIntent());
                String txtToast = roshn.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });

            maisar.setOnClickListener(view1 -> {

                Utils.put("help", "maisar");
                //finish();
                //startActivity(getIntent());
                String txtToast = maisar.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });
            runahi.setOnClickListener(view1 -> {

                Utils.put("help", "runahi");
                //finish();
                //startActivity(getIntent());
                String txtToast = runahi.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });
            mokhtasar.setOnClickListener(view1 -> {

                Utils.put("help", "mokhtasar");
                //finish();
                //startActivity(getIntent());
                String txtToast = mokhtasar.getText() + " جێگیرکرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });


            hich.setOnClickListener(view1 -> {

                Utils.put("help", "hich");
                //finish();
                // startActivity(getIntent());
                String txtToast = hich.getText() + "تەفسیر لەکارخرا";
                updateTafsirName();
                snackbarText.setText(txtToast);
                snackbar.show();
                alertDialog.dismiss();
            });

            alertDialog.show();

        });

        TafsirS = Utils.getStringPref("help", "hich");


        StoreDataInArrayList();


        ayahAdapter = new AyahAdapter(this, aText);
        sRecyclerView.setAdapter(ayahAdapter);
        sRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtSuaratName.setText(getIntent().getStringExtra("sura"));
       
        SharedPreferences preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        boolean isSubItemVisible = preferences.getBoolean("isSubItemVisible", true);
        ayahAdapter.setSubItemVisibility(isSubItemVisible ? View.VISIBLE : View.GONE);

        ShowAamzha = preferencesAmazha.getBoolean("showAmazha", false);
        ayahAdapter.setShowAmazha(ShowAamzha); // Update and notify adapter!
        updateAmazhaImageTint(); // Call this method to set the initial state

        AmazhaImageView.setOnClickListener(v -> {


            // Toggle the boolean value AND update the SharedPreferences
            ShowAamzha = !ShowAamzha; // This is the KEY change
            editorAmazha.putBoolean("showAmazha", ShowAamzha);
            editorAmazha.apply();
            ayahAdapter.setShowAmazha(ShowAamzha); // Update and notify adapter!
            updateAmazhaImageTint(); // Update the image tint immediately
        });




        krdSwitch.setChecked(isSubItemVisible);
        
        krdSwitch.setOnClickListener(v -> {
            toggleSubItemVisibility();
            if (krdSwitch.isChecked()){
                txtKrdRepo.setVisibility(View.VISIBLE);
            }else {
                txtKrdRepo.setVisibility(View.GONE);
            }

        });





        String sss = txtSuaratName.getText().toString();
        ayahAdapter.SurahNAme = sss;
        ayahAdapter.SurahId = id;
        ayahAdapter.SurahLink = fileUrl;
        if (sss.equals("سُورَةُ الفَاتِحَةِ")) {
            txtbismila.setVisibility(View.GONE);
        } else if (sss.equals("سُورَةُ التَّوْبَةِ")) {
            txtbismila.setVisibility(View.GONE);
        } else {
            txtbismila.setVisibility(View.VISIBLE);
        }

        // Get the saved scroll position from Shared Preferences.

        String scrollPosition = getIntent().getStringExtra("scrollPosition");
        int scrollPosition2 = Integer.parseInt(scrollPosition);
        // Scroll the RecyclerView to the saved scroll position.
         sRecyclerView.scrollToPosition(scrollPosition2-1);
        //sRecyclerView.smoothScrollToPosition(scrollPosition2 + 1);
        //sRecyclerView.scrollTo(scrollPosition2,0);


        SearchRecyclerView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ayahAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        filename = getIntent().getStringExtra("file_name") + ".mp3";


        // file aka bda ba medya playar


        // check krdny internet
        ConnectivityManager connectivityManager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        // download files
        btnDownload.setOnClickListener(v -> {

            // agar file a ka habet ba dwbara download y  nakat .......

            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC), nameS);
            if (file.exists()) {
                txtToast.setText("ئەم فایلە پێشتر دابەزێنراوە..");
                snackbarText.setText(txtToast.getText());
                snackbar.show();
            } else {
                // gar wifi yan mobile data habw ba download dastpebkat
                if (wifi.isConnected() || mobileNetwork.isConnected()) {
                    downloadFiles(linkS, nameS);
                    snackbarText.setText("داونلۆد دەستی پێکرد ...");
                    snackbar.show();
                }
                // agar wifi yan mobile data nabww massagek pishan bat
                else {

                    snackbarText.setText("تکایە دلنیابەرەوە لە هەبونی ئینتەرنێنت");
                    snackbar.show();
                }
            }


        });


        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),RECEIVER_EXPORTED);

        btnPlay.setOnClickListener(v -> {


            if (!mediaPlayer.isPlaying()) {

                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause);
                seekBar.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable, 0);

            } else {
                mediaPlayer.pause();
                btnPlay.setImageResource(R.drawable.ic_play);
            }


        });
///////////////////////////////////////////
        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());


                int currentDuration;
                // if (mediaPlayer.isPlaying()) {
                currentDuration = mediaPlayer.getCurrentPosition();
                updatePlayer(currentDuration);
                duration.postDelayed(this, 1000);
                //   }else {
                duration.removeCallbacks(this);
                //  }
                handler.postDelayed(this, 500);

            }
        };


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Check condition
                if (fromUser) {
                    // when drag the seek bar
                    // set progress on seek bar
                    mediaPlayer.seekTo(progress);
                    //  sRecyclerView.scrollToPosition(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                sRecyclerView.scrollToPosition(seekBar.getProgress());

            }
        });


        mediaPlayer.setOnCompletionListener(mp -> {
            btnPlay.setImageResource(R.drawable.ic_play);
            mediaPlayer.seekTo(0);
        });

        /**
         // Get the current position of the MediaPlayer.
         int mediaPlayerPosition = mediaPlayer.getCurrentPosition();

         // Check if the RecyclerView is empty.
         if (sRecyclerView.getAdapter().getItemCount() != 0) {



         // Get the height of the RecyclerView items.
         int itemHeight = sRecyclerView.getChildAt(0).getHeight();

         // Calculate the number of items that need to be scrolled.
         int numberOfItemsToScroll = mediaPlayerPosition / itemHeight;

         // Scroll the RecyclerView by the calculated number of items.
         sRecyclerView.scrollBy(0, numberOfItemsToScroll * itemHeight);

         }


        // aouto scrool
        if (mediaPlayer.isPlaying()){
        final long totalScrollTime = Long.MAX_VALUE;
        int duration = mediaPlayer.getDuration();
        int numberOfItems = sRecyclerView.getAdapter().getItemCount();

        int scrollPeriod = duration / numberOfItems;
        int heightToScroll = sRecyclerView.getHeight();

        sRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(totalScrollTime, scrollPeriod) {
                    public void onTick(long millisUntilFinished) {

                       // sRecyclerView.scrollToPosition(heightToScroll);
                        sRecyclerView.smoothScrollToPosition( heightToScroll);

                    }

                    public void onFinish() {
                        //do something
                    }
                }.start();
            }
        });
        // paused = false;
         */
    }

        // Create a listener to listen for changes in the MediaPlayer position.
       /** mediaPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Scroll the RecyclerView to the current position of the MediaPlayer.
                sRecyclerView.scrollToPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        */








    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100 && (grantResults.length>0)&&(grantResults[0]==
                PackageManager.PERMISSION_GRANTED)){
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();

        }
    }


    @SuppressLint("SetTextI18n")
    private void updatePlayer(int currentDuration){

        duration.setText("" + milliSecondsToTimer((long) currentDuration));
    }





    /**
     * Function to convert milliseconds time to Timer Format
     * Hours:Minutes:Seconds
     * */
    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    // war frdnaway data

    void StoreDataInArrayList() {

        Cursor cursor = mydbClass.readAllAyahData(id);


        while (cursor.moveToNext()) {
            aText.add(new AyahItem(cursor.getString(6),cursor.getString(5)
                    , cursor.getString(7), cursor.getString(8)
                    ,cursor.getString(9),cursor.getString(10)
                    ,cursor.getString(11),cursor.getString(12)
                    , cursor.getString(13), cursor.getString(14)
                    ,cursor.getString(15) , cursor.getString(16)
                    ,cursor.getString(17)
                    ,cursor.getString(18),
                    cursor.getString(19),
                    cursor.getString(20)
            ));
        }


    }

    // dabazandny dang

    public void downloadFiles(String url , String outPutFileName){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(nameS);
        request.setDescription("دابەزاندن : " + nameS );
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalFilesDir(context,Environment.DIRECTORY_MUSIC,outPutFileName);
        DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);

    }


    BroadcastReceiver onComplete=new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {




            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),nameS);

            if (file.exists()){
                btnDownload.setImageResource(R.drawable.ic_download_done);
                btnDownloadLayout.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.VISIBLE);
                duration.setVisibility(View.VISIBLE);
                cardViewPlay.setVisibility(View.VISIBLE);
                cPath = file.getPath();
                url = Uri.parse(cPath);
                mediaPlayer=new MediaPlayer();
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build());
                try {
                    mediaPlayer.setDataSource(SuratView.this, url);
                    mediaPlayer.prepare();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            if(!file.exists()) {
                btnDownload.setImageResource(R.drawable.ic_download);
                btnDownloadLayout.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.GONE);
                cardViewPlay.setVisibility(View.GONE);
                seekBar.setVisibility(View.GONE);
                duration.setVisibility(View.GONE);
            }


            mediaPlayer.setOnCompletionListener(mp -> {
                btnPlay.setImageResource(R.drawable.ic_play);
                mediaPlayer.seekTo(0);
            });



        }
    };







    @SuppressLint("MissingInflatedId")
    public void  Agadari(){
        ViewGroup viewGroup =findViewById(android.R.id.content);

        Button btnNo;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view= LayoutInflater.from(SuratView.this).inflate(R.layout.dialog_layout_surah,viewGroup,false);
        builder.setCancelable(false);
        builder.setView(view);


        btnNo =view.findViewById(R.id.btnSetting);

        final   AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btnNo.setOnClickListener(v -> alertDialog.dismiss());


        alertDialog.show();

        SharedPreferences preferences4 = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editorShow2 = preferences4.edit();
        editorShow2.putBoolean("showDialog",false);
        editorShow2.apply();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        editor.putInt("LastClick",i).commit();


        nameS = name[i]+" "+filename;
        linkS = link[i]+fileUrl;
        File file1 = new File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),nameS);
        cPath = file1.getPath();
        url = Uri.parse(cPath);
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
        try {
            mediaPlayer.setDataSource(SuratView.this, url);
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }


        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),nameS);

        if (file.exists()){
            btnDownload.setImageResource(R.drawable.ic_download_done);
            btnDownloadLayout.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
            seekBar.setVisibility(View.VISIBLE);
            duration.setVisibility(View.VISIBLE);
            cardViewPlay.setVisibility(View.VISIBLE);
        }
        if(!file.exists()) {
            btnDownload.setImageResource(R.drawable.ic_download);
            btnDownloadLayout.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
            cardViewPlay.setVisibility(View.GONE);
            seekBar.setVisibility(View.GONE);
            duration.setVisibility(View.GONE);
        }
        mediaPlayer.setOnCompletionListener(mp -> {
            btnPlay.setImageResource(R.drawable.ic_play);
            mediaPlayer.seekTo(0);
        });

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }


    public void updateTafsirName() {
        TafsirS = Utils.getStringPref("help", "");

        assert TafsirS != null;
        TafsirS = TafsirS.replaceAll("hich", "هیج تەفسیرێک کارا نییە")
                .replaceAll("hazhar", "تەفسیری هەژار")
                .replaceAll("zhin", "تەفسیری ژیان")
                .replaceAll("sanahi", "تەفسیری سەناهی")
                .replaceAll("rebar", "تەفسیری ڕێبەر")
                .replaceAll("raman", "تەفسیری ڕامان")
                .replaceAll("asan", "تەفسیری ئاسان")
                .replaceAll("puxta", "تەفسیری پوختە")
                .replaceAll("tawhid", "تەفسیری تەوحیدی")
                .replaceAll("roshn", "تەفسیری ڕۆشن")
                .replaceAll("maisar", "تەفسیری مویەسەر")
                .replaceAll("runahi", "تەفسیری ڕوناهی")
                .replaceAll("mokhtasar", "تەفسیری موختەسەر");

        txtTafsirName.setText(TafsirS);
    }

    private void toggleSubItemVisibility() {
        SharedPreferences preferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        boolean isSubItemVisible = preferences.getBoolean("isSubItemVisible", true); // Default to visible

        if (isSubItemVisible) {
            // Hide subItemTextview and save preference
            ayahAdapter.setSubItemVisibility(View.GONE);
            preferences.edit().putBoolean("isSubItemVisible", false).apply();
        } else {
            // Show subItemTextview and save preference
            ayahAdapter.setSubItemVisibility(View.VISIBLE);
            preferences.edit().putBoolean("isSubItemVisible", true).apply();
        }

        ayahAdapter.notifyDataSetChanged(); // Update RecyclerView
    }

    private void updateAmazhaImageTint() {
        if (ShowAamzha) {
            AmazhaImageView.setColorFilter(colorSet, PorterDuff.Mode.SRC_IN);
        } else {
            AmazhaImageView.setColorFilter(colorBAlck, PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (krdSwitch.isChecked()){
            txtKrdRepo.setVisibility(View.VISIBLE);
        }else {
            txtKrdRepo.setVisibility(View.GONE);
        }
        if (ShowAamzha) {
            AmazhaImageView.setColorFilter(colorSet, PorterDuff.Mode.SRC_IN);// SRC_IN is a common mode

        } else {
            AmazhaImageView.setColorFilter(colorBAlck, PorterDuff.Mode.SRC_IN);// remove color

        }
        ayahAdapter.notifyDataSetChanged();
        updateTafsirName();

    }



}