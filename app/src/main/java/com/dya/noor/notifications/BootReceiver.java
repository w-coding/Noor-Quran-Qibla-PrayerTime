package com.dya.noor.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dya.noor.utlis.Utils;
import com.dya.noor.database.MydbClass;
import com.dya.noor.widget.CallaUpdateWidget;
import com.dya.noor.widget.SalatWidget;
import com.dya.noor.widget.SalatWidgetVertical;

public class BootReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //la kati restart am basha code run abet w disan bangi dahatw miqat akat
            new Utils(context);
            new MydbClass(context);
            MydbClass.setNextPrayer(context);
            SalatWidget.updateAllWidgetViews(context);
            SalatWidgetVertical.updateAllWidgetViews(context);

        } catch (Exception e) {
            Log.e("HAX", "", e);
        }

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Reschedule all notifications
            // zikrakan
            ScheduleNotification.scheduleNotification(context,2, 8, 0, "ویردەکانی بەیانیان", "ئیستا کاتی خویندنی ویردەکانی بەیانیانە ☀");
            //
            ScheduleNotification.scheduleNotification(context,3, 16, 40, "ویردەکانی ئیواران", "ئیستا کاتی خویندنی ویردەکانی ئیوارانە ✨");
            //
            ScheduleNotification. scheduleNotification(context,4, 21, 30, "ویردەکانی خەوتنان", "ئیستا کاتی خویندنی ویردەکانی خەوتنانە 💤");
            //
            ScheduleNotification. scheduleNotification(context,5, 22, 10, "سورەتی مولک", "شەوانە پێش خەوتن 🛌 سورەتی { الملک } بخوێنن چونکه  ① دەبێتە ڕێگر لە سزای گـۆڕ ② دەبێتە شەفاعەت و تکاکار بۆخوێنەرەکەی تاوەکو خوای گەورە لێی خۆش دەبێت ");

            CallaUpdateWidget.setRepeatingAlarm(context);

        }

    }


}

