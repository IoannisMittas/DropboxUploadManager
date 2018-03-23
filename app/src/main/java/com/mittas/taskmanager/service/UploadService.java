package com.mittas.taskmanager.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;
import com.mittas.taskmanager.BuildConfig;
import com.mittas.taskmanager.R;
import com.mittas.taskmanager.ui.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by John on 22-Mar-18.
 */

public class UploadService extends Service {
    private static final int ONGOING_NOTIFICATION_ID = 1;
    private NotificationChannel channel;
    private DbxClientV2 client ;
    private final String channel_ID = "my_channel_01";
    private int result = Activity.RESULT_CANCELED;
    public static final String TASK_ID = "taskId";
    public static final String TASKNAME = "taskname";
    public static final String FILEPATH = "filepath";
    public static final String TIME = "time";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.mittas.taskmanager.service.receiver";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        client =  new DbxClientV2(new DbxRequestConfig("task_manager", "en_US"), BuildConfig.DROPBOX_ACCESS_TOKEN);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String taskName = intent.getStringExtra(TASKNAME);
        Notification notification = createNotification(taskName);
        startForeground(ONGOING_NOTIFICATION_ID, notification);

        final String filePath = intent.getStringExtra(FILEPATH);

        final int taskId = intent.getIntExtra(TASK_ID, -1);

        new Thread() {
            @Override
            public void run() {
                long tStart = System.currentTimeMillis();

                uploadFile(filePath);

                long tEnd = System.currentTimeMillis();

                long time = tEnd - tStart;

                // successfully finished
                result = Activity.RESULT_OK;

                publishResults(taskId, time, result);
            }
        }.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {

    }

    private void uploadFile(String filePath) {
        File file = new File(filePath);

        try (InputStream inputStream = new FileInputStream(file)) {
             client.files().uploadBuilder(filePath)
                    .withMode(WriteMode.OVERWRITE)
                    .uploadAndFinish(inputStream);
        } catch (DbxException | IOException e) {
            // handle exception
        }
    }

    private void publishResults(int taskId, long time, int result) {
        Intent intent = new Intent();
        intent.putExtra(TASK_ID, taskId);
        intent.putExtra(TIME, time);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }


    private Notification createNotification(String taskName) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        doCreateNotificationChannel();

        return new NotificationCompat.Builder(this, channel_ID)
                .setContentTitle(taskName)
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
