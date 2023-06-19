package com.dya.noor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //la kati restart am basha code run abet w disan bangi dahatw miqat akat
            new Utils(context);
            new MydbClass(context);

            MydbClass.setNextPrayer(context);


        } catch (Exception e) {
            Log.e("HAX", "", e);
        }
    }


}

