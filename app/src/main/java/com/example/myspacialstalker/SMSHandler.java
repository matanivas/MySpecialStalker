package com.example.myspacialstalker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class SMSHandler extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("sent")){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "0")
                    .setContentTitle("note").setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("sms sent!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(0, builder.build());
        }


    }
}
