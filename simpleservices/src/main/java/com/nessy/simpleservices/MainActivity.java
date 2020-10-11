package com.nessy.simpleservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStartService;
    Button btnStartIntentService;
    Button btnStartBoundService;
    Button btnStopBoundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btnStartService);
        btnStartService.setOnClickListener(this);

        btnStartIntentService = findViewById(R.id.btnStartIntentService);
        btnStartIntentService.setOnClickListener(this);

        btnStartBoundService = findViewById(R.id.btnStartBoundService);
        btnStartBoundService.setOnClickListener(this);

        btnStopBoundService = findViewById(R.id.btnStopBoundService);
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

                break;
            case R.id.btnStartBoundService:

                break;
            case R.id.btnStopBoundService:

                break;
        }
    }
}
