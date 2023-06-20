package com.dya.noor;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class SalatWidget extends AppWidgetProvider {

    @RequiresApi(api = Build.VERSION_CODES.S)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        SharedPreferences sharedPreferences = context.getSharedPreferences("key", MODE_PRIVATE);
        String City = sharedPreferences.getString("City", "Kalar");
        String City2 = sharedPreferences.getString("City2", "کەلار");

        ArrayList<Long> prayer = MydbClass.getTodayPrayers(true);




        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.salat_widget);


        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the click listener for the widget
        views.setOnClickPendingIntent(R.id.salatWidgetLayout, pendingIntent);

        views.setTextViewText(R.id.city,City2);
        views.setTextViewText(R.id.fajr2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(0))));
        views.setTextViewText(R.id.dhuhr2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(2))));
        views.setTextViewText(R.id.asr2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(3))));
        views.setTextViewText(R.id.maghrib2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(4))));
        views.setTextViewText(R.id.isha2, new SimpleDateFormat("hh:mm").format(new Date(prayer.get(5))));
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @SuppressLint("NewApi")
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
}