package com.mittas.taskmanager.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.ui.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by John on 22-Mar-18.
 */

public class UploadService extends Service{
    private static final int ONGOING_NOTIFICATION_ID = 1;
    private final String channel_ID = "my_channel_01";
    private NotificationChannel channel;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = createNotification();
        startForeground(ONGOING_NOTIFICATION_ID, notification);

        new Thread() {
            @Override
            public void run() {
                // Get asset
                InputStream is;
                try {
                    File f = new File(path);
                    String name = f.getName();
                    is = new FileInputStream(f);
                    long size = f.length();
                    System.out.println("IS ["+is+"] - Size ["+size+"]");
                    cs.upload("/" + name, is, size, true);
                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {

    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        doCreateNotificationChannel();

        return new NotificationCompat.Builder(this, channel_ID)
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
    }

    private void doCreateNotificationChannel() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
            return;
        }

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // The user-visible name of the channel.
        CharSequence name = getString(R.string.channel_name);

        int importance = NotificationManager.IMPORTANCE_HIGH;

        channel = new NotificationChannel(channel_ID, name, importance);

        // The user-visible description of the channel.
        String description = getString(R.string.channel_description);
        channel.setDescription(description);

        mNotificationManager.createNotificationChannel(channel);
    }

}
