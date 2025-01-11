package com.dya.noor.notifications;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dya.noor.R;
import com.dya.noor.ui.SuratView;
import com.dya.noor.ui.dhikrView2;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "channel_id";
    PendingIntent pendingIntent;
    String title;
    String message;
    int notificationId;
    NotificationManagerCompat notificationManager;
    NotificationCompat.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onReceive(Context context, Intent intent) {


        // Extract notification data from the intent
        title = intent.getStringExtra("title");
        message = intent.getStringExtra("message");
        notificationId = intent.getIntExtra("notificationId", 0);




        // Create the notification channel
        createNotificationChannel(context);


        switch (notificationId) {
            case 2:
                Intent repeating_Intent = new Intent(context, dhikrView2.class);
                repeating_Intent.putExtra("sura", "بەیانیان");
                repeating_Intent.putExtra("id", "27");
                pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
                break;
            case 3:
                Intent repeating_Intent2 = new Intent(context, dhikrView2.class);
                repeating_Intent2.putExtra("sura", "ئێواران");
                repeating_Intent2.putExtra("id", "28");
                pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent2,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
                break;
            case 4:
                Intent repeating_Intent3 = new Intent(context, dhikrView2.class);
                repeating_Intent3.putExtra("sura", "پێشخەوتن");
                repeating_Intent3.putExtra("id", "29");
                pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent3,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
                break;
            case 5:

                Intent repeating_Intent4 = new Intent(context, SuratView.class);
                repeating_Intent4.putExtra("sura", "سورەتی الملك");
                repeating_Intent4.putExtra("id", "67");
                repeating_Intent4.putExtra("link", "067.mp3");
                repeating_Intent4.putExtra("scrollPosition", "0");
                pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent4,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

                break;
        }


        // Create a notification
        builder = new NotificationCompat.Builder(context, CHANNEL_ID)

                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon_nbb)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_nbb))
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setDefaults(Notification.DEFAULT_LIGHTS);


        // Show the notification
        notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(notificationId, builder.build());


    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        CharSequence name = "ویردەکان";
        String description = "یادخستنەوەی ویردەکان";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }




}