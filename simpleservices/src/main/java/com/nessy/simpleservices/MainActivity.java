package com.nessy.simpleservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import com.nessy.simpleservices.BoundService.MyBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartService = findViewById(R.id.btnStartService);
        btnStartService.setOnClickListener(this);

        Button btnStartIntentService = findViewById(R.id.btnStartIntentService);
        btnStartIntentService.setOnClickListener(this);

        Button btnStartBoundService = findViewById(R.id.btnStartBoundService);
        btnStartBoundService.setOnClickListener(this);

        Button btnStopBoundService = findViewById(R.id.btnStopBoundService);
        btnStopBoundService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartService:
                Intent mStartServiceIntent = new Intent(MainActivity.this, MyService.class);
                startService(mStartServiceIntent);
                break;
            case R.id.btnStartIntentService:
                Intent mStartIntentService = new Intent(MainActivity.this, IntentService.class);
                mStartIntentService.putExtra(IntentService.EXTRA_DURATION, 5000L);
                startService(mStartIntentService);
                break;
            case R.id.btnStartBoundService:
                Intent boundServiceIntent = new Intent(MainActivity.this, BoundService.class);
                bindService(boundServiceIntent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btnStopBoundService:
                unbindService(serviceConnection);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        pemanggilan unbind di dalam onDestroy ditunjukan untuk mencegah memory leaks dari bound services
        if (serviceBound){
            unbindService(serviceConnection);
        }
    }

    private boolean serviceBound = false;

//    services connection = interface yang digunakan untuk menghubungkan antara boundService dengan activity
    private final ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBound = false;
        }
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBinder myBinder = (MyBinder) iBinder;
            myBinder.getService();
            serviceBound = true;
        }
    };
}
