package com.dya.noor.widget;

import static android.content.ContentValues.TAG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UpdateWidgetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
         try {
             SalatWidget.updateAllWidgetViews(context);
             SalatWidgetVertical.updateAllWidgetViews(context);
             Log.d(TAG, "worked ...." );
              }catch (Exception e){
             Log.d(TAG, "not worked ...." );
              }


    }
}
