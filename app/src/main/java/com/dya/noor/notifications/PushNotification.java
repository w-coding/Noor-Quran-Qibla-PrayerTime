package com.dya.noor.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.dya.noor.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotification extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

      String title =  message.getNotification().getTitle();
      String text =  message.getNotification().getBody();
      final String CHANNEL_ID = "Noor_Notification";

      NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID ,
                "Noor_Notification",
                NotificationManager.IMPORTANCE_HIGH


        );

      getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder builder = new Notification.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,builder.build());
        super.onMessageReceived(message);
    }
}
