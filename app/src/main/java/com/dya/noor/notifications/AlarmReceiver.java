package com.dya.noor.notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.dya.noor.R;
import com.dya.noor.utlis.Utils;
import com.dya.noor.utlis.UtilsT;
import com.dya.noor.database.MydbClass;
import com.dya.noor.ui.ActivityPrayersTime;
import com.dya.noor.ui.NowIsPrayerTimeActivity;
import com.dya.noor.widget.SalatWidget;
import com.dya.noor.widget.SalatWidgetVertical;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AlarmReceiver extends BroadcastReceiver {


    public static void sendPrayerNotification(Context context) {
        try {
            String channel_id = "762001";
            String title = "كاتەکانی بانگ";

            if (UtilsT.activeContext == null) {
                UtilsT.activeContext = context;
            }

            new MydbClass(UtilsT.activeContext);
            //     Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.bang2);

            //    AudioAttributes attributes = new AudioAttributes.Builder()
            //         .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            //       .build();

            @SuppressLint("SimpleDateFormat") String description = " ئێستا کاتی بانگی [ " + MydbClass.getCurrentClosestPrayer().prayerNameKurdish + "]\n" +
                    "بانگی دواتر لە " + new SimpleDateFormat("hh:mm").format(new Date(Objects.requireNonNull(MydbClass.getNextPrayer()).milli)) + " " + MydbClass.getNextPrayer().prayerNameKurdish + " دەبێت";

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent myIntent = new Intent(context, ActivityPrayersTime.class);

            myIntent.putExtra("open_prayer_time", true);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    762001,
                    myIntent,
                    PendingIntent.FLAG_IMMUTABLE);

            builder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.icon_nbb)
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_nbb))
                    .setContentText(description)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(description))
                    .setDefaults(Notification.DEFAULT_LIGHTS)
                    // .setSound(sound)
                    .setContentInfo("...");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channel_id,
                        title,
                        NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("یادخستنەوەی كاتەكانی بانگ");
                builder.setChannelId(channel_id);
                channel.enableVibration(true);
                // channel.setSound(sound, attributes);
                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(762001, builder.build());
        } catch (Exception e) {
            Log.e("HAX", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            new Utils(context);
            new MydbClass(context);
            String whatPrayer = null;

            if (intent.hasExtra("what_prayer")) {
                whatPrayer = intent.getStringExtra("what_prayer");
            }

            if (whatPrayer == null) {
                whatPrayer = MydbClass.getCurrentClosestPrayer().prayerName;
            }
            /*
            bayani
            niwaro
            asr
            eywara
            esha
         */
            if (whatPrayer != null) {
                if (Utils.getBooleanPref("notification_" + whatPrayer, true)) {
                    sendPrayerNotification(context);
                }

                if (Utils.getBooleanPref("alarm_" + whatPrayer, true)) {
                    try {
                        Intent intentNowIsPrayerTime = new Intent(context, NowIsPrayerTimeActivity.class);
                        intentNowIsPrayerTime.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intentNowIsPrayerTime.putExtra("what_prayer", whatPrayer);
                        context.startActivity(intentNowIsPrayerTime);

                    } catch (Exception ignored) {
                        Log.d("HAX", ignored.getMessage().toString());

                    }
                }
            }


        } catch (Exception ignored) {
            Log.d("HAX",ignored.getMessage().toString());

        }

        try {
            MydbClass.setNextPrayer(context);
            SalatWidget.updateAllWidgetViews(context);
            SalatWidgetVertical.updateAllWidgetViews(context);
        } catch (Exception ignored) {
            Log.d("HAX",ignored.getMessage().toString());

        }
    }

}