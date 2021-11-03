package com.example.tp3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String CANAL_MENSAJES_ID= "10001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i= new Intent (MainActivity.this, crear_record.class);
        startActivity(i);
        this.createNotificationChannel();
    }

    private void createNotificationChannel () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 =
                    new NotificationChannel(CANAL_MENSAJES_ID, "CANAL 1", NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("description");
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
        }
    }
}