package com.nessy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    final String TAG = SmsReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null){
                final Object[] pdusObj = (Object[]) bundle.get("puds");
                if (pdusObj != null){
                    for (Object aPdusObj : pdusObj){
                        SmsMessage currentMessage = getIncomingMessage(aPdusObj, bundle);
                        String senderNum = currentMessage.getDisplayOriginatingAddress();
                        String msg = currentMessage.getDisplayMessageBody();
                        Log.d(TAG, "senderNum: "+ senderNum + "; message: "+msg);
                        Intent showSmsIntent = new Intent(context,SmsReceiverActivity.class);
                        showSmsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum);
                        showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MSG, msg);
                        context.startActivity(showSmsIntent);
                    }
                }else {
                    Log.d(TAG, "onReceiver: SMS is null");
                }
            }
        }catch (Exception e){
            Log.d(TAG, "Exception smsReceiver" + e);
        }
    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle){
        SmsMessage currentSMS;
        String format = bundle.getString("format");
        currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        return currentSMS;
    }
}