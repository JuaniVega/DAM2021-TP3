package com.example.tp3;

import static com.example.tp3.MainActivity.CANAL_MENSAJES_ID;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class RecordatorioReceiver extends BroadcastReceiver {

    public static String RECORDATORIO = "com.example.tp3.RECORDATORIO";
    public static String TEXTO="TEXTO";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(RECORDATORIO)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CANAL_MENSAJES_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("RECORDATORIO DE NOTA")
                    .setContentText(intent.getExtras().getString("TEXTO"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(0,builder.build());
        }
    }
}
