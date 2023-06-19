package com.dya.noor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PrayerService extends Service {
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        return super.onStartCommand(intent, flags, startId);
        
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}