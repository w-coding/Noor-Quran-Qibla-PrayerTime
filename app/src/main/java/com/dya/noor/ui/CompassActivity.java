package com.dya.noor.ui;

import static android.view.View.INVISIBLE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import com.dya.noor.Compass.CompassClass;
import com.dya.noor.Compass.Constants;
import com.dya.noor.Compass.GPSTracker;
import com.dya.noor.R;

public class CompassActivity extends AppCompatActivity {

    private static final String TAG = CompassActivity.class.getSimpleName();
    private CompassClass compass;
    private ImageView qiblatIndicator;
    private ImageView imageDial;
    private TextView tvYourLocation , tvQiblaAngle;
    private float currentAzimuth;
    SharedPreferences prefs;
    GPSTracker gps;
    private final int RC_Permission = 1221;
    ImageButton back;

    boolean nightMod;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMod = sharedPreferences.getBoolean("nightMod",false);

        if (nightMod){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        back = findViewById(R.id.back);


        back.setOnClickListener(v -> {
            onBackPressed();

        });

        setUserChanges(getIntent());

        prefs = getSharedPreferences("", MODE_PRIVATE);
        gps = new GPSTracker(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        qiblatIndicator = findViewById(R.id.qibla_indicator);
        imageDial = findViewById(R.id.dial);
        tvYourLocation = findViewById(R.id.your_location);
        tvQiblaAngle = findViewById(R.id.QiblaAngle);

        qiblatIndicator.setVisibility(INVISIBLE);
        qiblatIndicator.setVisibility(View.GONE);

        setupCompass();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        if (compass != null) {
            compass.start(this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compass != null) {
            compass.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (compass != null) {
            compass.start(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        if (compass != null) {
            compass.stop();
        }
        if (gps != null) {
            gps.stopUsingGPS();
            gps = null;
        }
    }

    private void setUserChanges(Intent intent) {
        try {
            ((ImageView) findViewById(R.id.dial)).setImageResource(
                    (intent.getExtras() != null &&
                            intent.getExtras().containsKey(Constants.DRAWABLE_DIAL)) ?
                            intent.getExtras().getInt(Constants.DRAWABLE_DIAL) : R.drawable.compass_dail);

            ((ImageView) findViewById(R.id.qibla_indicator)).setImageResource(
                    (intent.getExtras() != null &&
                            intent.getExtras().containsKey(Constants.DRAWABLE_QIBLA)) ?
                            intent.getExtras().getInt(Constants.DRAWABLE_QIBLA) : R.drawable.compass_qibla);

            findViewById(R.id.your_location).setVisibility(
                    (intent.getExtras() != null &&
                            intent.getExtras().containsKey(Constants.LOCATION_TEXT_VISIBLE)) ?
                            intent.getExtras().getInt(Constants.LOCATION_TEXT_VISIBLE) : View.VISIBLE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void setupCompass() {
        Boolean permission_granted = GetBoolean("permission_granted");
        if (permission_granted) {
            getBearing();
        } else {
            tvYourLocation.setText(R.string.location_access_not_available_yet);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        RC_Permission);
            } else {
                fetch_GPS();
            }
        }


        compass = new CompassClass(this);
        CompassClass.CompassListener cl = azimuth -> {

                    adjustGambarDial(azimuth);
                    adjustArrowQiblat(azimuth);

        };
        compass.setListener(cl);
    }


    public void adjustGambarDial(float azimuth) {
        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = (azimuth);
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        imageDial.startAnimation(an);
    }

    public void adjustArrowQiblat(float azimuth) {
        float kiblat_derajat = GetFloat("kiblat_derajat");
        Animation an = new RotateAnimation(-(currentAzimuth) + kiblat_derajat, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = (azimuth);
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        qiblatIndicator.startAnimation(an);
        if (kiblat_derajat > 0) {
            qiblatIndicator.setVisibility(View.VISIBLE);
        } else {
            qiblatIndicator.setVisibility(INVISIBLE);
            qiblatIndicator.setVisibility(View.GONE);
        }
    }

    @SuppressLint({"MissingPermission", "SetTextI18n"})
    public void getBearing() {
        float kaabaDegs = GetFloat("kiblat_derajat");
        if (kaabaDegs > 0.0001) {
            String strYourLocation;
            if (gps.getLocation() != null)
                strYourLocation = getString(R.string.your_location)
                        + " " + gps.getLocation().getLatitude() + ", " + gps.getLocation().getLongitude();
            else
                strYourLocation = getString(R.string.unable_to_get_your_location);
            tvYourLocation.setText(strYourLocation);
            tvQiblaAngle.setText(getString(R.string.qibla_angle_for_your_location_is)+kaabaDegs);
            qiblatIndicator.setVisibility(View.VISIBLE);
        } else {
            fetch_GPS();
        }
    }

    private String getDirectionString(float azimuthDegrees) {
        String where = "NW";

        if (azimuthDegrees >= 350 || azimuthDegrees <= 10)
            where = "N";
        if (azimuthDegrees < 350 && azimuthDegrees > 280)
            where = "NW";
        if (azimuthDegrees <= 280 && azimuthDegrees > 260)
            where = "W";
        if (azimuthDegrees <= 260 && azimuthDegrees > 190)
            where = "SW";
        if (azimuthDegrees <= 190 && azimuthDegrees > 170)
            where = "S";
        if (azimuthDegrees <= 170 && azimuthDegrees > 100)
            where = "SE";
        if (azimuthDegrees <= 100 && azimuthDegrees > 80)
            where = "E";
        if (azimuthDegrees <= 80 && azimuthDegrees > 10)
            where = "NE";

        return where;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_Permission) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SaveBoolean("permission_granted", true);
                tvYourLocation.setText("Location Access Granted");
                qiblatIndicator.setVisibility(INVISIBLE);
                qiblatIndicator.setVisibility(View.GONE);

                fetch_GPS();
            } else {
                Toast.makeText(getApplicationContext(), "This app requires Access Location", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void SaveBoolean(String Judul, Boolean value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(Judul, value);
        edit.apply();
    }

    public Boolean GetBoolean(String Judul) {
        return prefs.getBoolean(Judul, false);
    }

    public void SaveFloat(String Judul, Float value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putFloat(Judul, value);
        edit.apply();
    }

    public Float GetFloat(String Judul) {
        return prefs.getFloat(Judul, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    public void fetch_GPS() {
        double result;
        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            double myLat = gps.getLatitude();
            double myLng = gps.getLongitude();
            String strYourLocation = "Your Location:"
                    + " " + myLat + ", " + myLng;
            tvYourLocation.setText(strYourLocation);
            Log.e("TAG", "GPS is on");
            if (myLat < 0.001 && myLng < 0.001) {
                qiblatIndicator.setVisibility(INVISIBLE);
                qiblatIndicator.setVisibility(View.GONE);
                tvYourLocation.setText("Location not ready.");
            } else {
                double kaabaLng = 39.826206;
                double kaabaLat = Math.toRadians(21.422487);
                double myLatRad = Math.toRadians(myLat);
                double longDiff = Math.toRadians(kaabaLng - myLng);
                double y = Math.sin(longDiff) * Math.cos(kaabaLat);
                double x = Math.cos(myLatRad) * Math.sin(kaabaLat) - Math.sin(myLatRad) * Math.cos(kaabaLat) * Math.cos(longDiff);
                result = (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
                SaveFloat("kiblat_derajat", (float) result);
                qiblatIndicator.setVisibility(View.VISIBLE);
            }
        } else {
            gps.showSettingsAlert();

            qiblatIndicator.setVisibility(INVISIBLE);
            qiblatIndicator.setVisibility(View.GONE);
            tvYourLocation.setText("Please turn on Location");
        }
    }
}