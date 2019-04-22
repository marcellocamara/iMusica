package dev.marcello.imusica;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/**
 * Marcello
 * 2019
 */

public class AppMVP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setUpNotificationChannel();
    }

    private void setUpNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //API 26+ requires a channel
            NotificationChannel channel = new NotificationChannel(
                    "channel",
                    "Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("20s");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}