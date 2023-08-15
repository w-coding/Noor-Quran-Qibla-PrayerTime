package com.dya.noor.widget;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.dya.noor.R;
import com.dya.noor.database.MydbClass;
import com.dya.noor.splash_screen.SplashScreen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class SalatWidgetVertical extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("key", MODE_PRIVATE);
        String City2 = sharedPreferences.getString("City2", "کەلار");
        MydbClass.setNextPrayer(context);
        ArrayList<Long> prayer = MydbClass.getTodayPrayers(true);
        // Construct the RemoteViews object
        RemoteViews viewsVertical = new RemoteViews(context.getPackageName(), R.layout.salat_widget_vertical);


        Intent intent = new Intent(context, SplashScreen.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        intent.putExtra("open_prayer",true);

        // Set the click listener for the widget
        viewsVertical.setOnClickPendingIntent(R.id.salatWidgetVerticalLayout, pendingIntent);

        //viewsVertical.setTextViewText(R.id.city,City2);
        viewsVertical.setTextViewText(R.id.fajr2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(0))));
        viewsVertical.setTextViewText(R.id.dhuhr2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(2))));
        viewsVertical.setTextViewText(R.id.asr2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(3))));
        viewsVertical.setTextViewText(R.id.maghrib2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(4))));
        viewsVertical.setTextViewText(R.id.isha2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(5))));

        switch (MydbClass.getNextPrayer().prayerName) {
            case "bayani":
                viewsVertical.setTextColor(R.id.fajr2,context.getResources().getColor(R.color.colorSun));
                viewsVertical.setTextColor(R.id.fajr,context.getResources().getColor(R.color.colorSun));

                viewsVertical.setTextColor(R.id.dhuhr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.dhuhr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib,context.getResources().getColor(R.color.white));
                break;
            case "niwaro":

                viewsVertical.setTextColor(R.id.dhuhr2,context.getResources().getColor(R.color.colorSun));
                viewsVertical.setTextColor(R.id.dhuhr,context.getResources().getColor(R.color.colorSun));

                viewsVertical.setTextColor(R.id.fajr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.fajr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib,context.getResources().getColor(R.color.white));

                break;
            case "asr":
                viewsVertical.setTextColor(R.id.asr2,context.getResources().getColor(R.color.colorSun));
                viewsVertical.setTextColor(R.id.asr,context.getResources().getColor(R.color.colorSun));

                viewsVertical.setTextColor(R.id.dhuhr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.dhuhr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.fajr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.fajr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib,context.getResources().getColor(R.color.white));

                break;
            case "eywara":
                viewsVertical.setTextColor(R.id.maghrib2,context.getResources().getColor(R.color.colorSun));
                viewsVertical.setTextColor(R.id.maghrib,context.getResources().getColor(R.color.colorSun));

                viewsVertical.setTextColor(R.id.dhuhr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.dhuhr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.fajr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.fajr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.isha,context.getResources().getColor(R.color.white));
                break;
            case "esha":
                viewsVertical.setTextColor(R.id.isha2,context.getResources().getColor(R.color.colorSun));
                viewsVertical.setTextColor(R.id.isha,context.getResources().getColor(R.color.colorSun));

                viewsVertical.setTextColor(R.id.dhuhr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.dhuhr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.fajr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.fajr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.asr,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib2,context.getResources().getColor(R.color.white));
                viewsVertical.setTextColor(R.id.maghrib,context.getResources().getColor(R.color.white));

                break;
        }

        // Add a log statement to indicate that the method has been called
        Log.d("SalatWidget", "updateAppWidget called");

        // Add some more log statements to check if the data is being retrieved correctly
        Log.d("SalatWidget", "City2: " + City2);
        Log.d("SalatWidget", "prayer size: " + prayer.size());
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, viewsVertical);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }



    @SuppressLint("NewApi")
    public static void updateAllWidgetViews(Context context){
        try {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, SalatWidgetVertical.class));

            for (int appWidgetId :widgetIds){
                SalatWidgetVertical.updateAppWidget(context,appWidgetManager,appWidgetId);
            }

        }catch (Exception e){

        }
    }
}