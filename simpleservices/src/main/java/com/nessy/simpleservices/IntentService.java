package com.nessy.simpleservices;

import android.content.Intent;
import android.util.Log;

public class IntentService extends android.app.IntentService {
    public static String EXTRA_DURATION = "extra_duration";
    public static final String TAG = IntentService.class.getSimpleName();

    public IntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: Start....");
        if (intent != null) {
            long duration = intent.getLongExtra(EXTRA_DURATION, 0);
            try {
                Thread.sleep(duration);
                Log.d(TAG, "onHandleIntent: End....");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
