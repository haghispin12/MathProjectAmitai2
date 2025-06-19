package com.example.mathprojectamitai2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.IOException;

public class MyMusicService extends Service {

    private MediaPlayer mp;

    public MyMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createMusic();
        Log.d("testService","start");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("testService","end");
        super.onDestroy();
    }
    private void startForegroundService() {
        String channelId;
        //mp= new MediaPlayer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = createNotificationChannel();
        } else {
            // If earlier version channel ID is not used
            // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
            channelId = "";
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(101, notification);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        String channelId = "my_service";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_HIGH);
        chan.setLightColor(Color.BLUE);
        chan.setImportance(NotificationManager.IMPORTANCE_NONE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        service.createNotificationChannel(chan);
        return channelId;
    }


    public void createMusic(){
        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (!mp.isPlaying())
                    mp.start();
            }
        });
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
            MyMusicService.this.stopSelf();
            }
        });
                mp.reset();
        try {
            AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.projecttheme);
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mp.prepareAsync();
        }catch (IOException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }




}