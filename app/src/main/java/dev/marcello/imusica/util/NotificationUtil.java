package dev.marcello.imusica.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.app.Notification;

import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.main.MainActivity;

/**
 * Marcello
 * 2019
 */

public class NotificationUtil {

    public static void Send(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        Uri uriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(context, "channel")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentTitle(context.getString(R.string.inactivity_title))
                .setContentText(context.getString(R.string.inactivity_message))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.icon)
                .setSound(uriSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

}