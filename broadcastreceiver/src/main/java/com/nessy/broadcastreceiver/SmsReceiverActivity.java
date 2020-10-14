package com.nessy.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsReceiverActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvSmsFrom;
    private TextView tvSmsMsg;
    private Button btnClose;

    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_MSG = "extra_sms_msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        setTitle(getString(R.string.incoming_msg));

        tvSmsFrom = findViewById(R.id.tv_from);
        tvSmsMsg = findViewById(R.id.tv_msg);
        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);

        String senderNo = getIntent().getStringExtra(EXTRA_SMS_NO);
        String senderMsg = getIntent().getStringExtra(EXTRA_SMS_MSG);

        tvSmsFrom.setText(String.format("from : %s", senderNo));
        tvSmsMsg.setText(senderMsg);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_close){
            finish();
        }
    }
}