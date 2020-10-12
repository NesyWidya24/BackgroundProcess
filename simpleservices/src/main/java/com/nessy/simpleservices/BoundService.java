package com.nessy.simpleservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service {
    private final String TAG = BoundService.class.getSimpleName();
    private final MyBinder myBinder = new MyBinder();
    private final long startTime = System.currentTimeMillis();

//    countdown timer akan berjalan sampai 100000 ms. dengan interval setiap 1000 ms akan menampilkan log
    private final CountDownTimer timer = new CountDownTimer(100000,1000) {
        @Override
        public void onTick(long l) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            Log.d(TAG, "onTick: " + elapsedTime);
        }

        @Override
        public void onFinish() {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

//    method yang dipanggil ketika service diikatkan ke activity
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        timer.start();
        return myBinder;
    }

//    ketika semua ikatan sudah dilepas maka onDestroy akan secara otomatis dipanggil
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

//    method yang akan dipanggil ketika service dilepas dari activity
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        timer.cancel();
        return super.onUnbind(intent);
    }

//    method yang akan dipanggil ketika service diikatkan kembali
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: ");
    }

    public class MyBinder extends Binder{
        BoundService getService(){
            return BoundService.this;
        }
    }
}
