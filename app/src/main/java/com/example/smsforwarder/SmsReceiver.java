package com.example.smsforwarder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String inputNumber = intent.getStringExtra("inputNumber");
        String outputNumber = intent.getStringExtra("outputNumber");

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String sender = msgs[i].getOriginatingAddress();
                String message = msgs[i].getMessageBody();

                // Check if the message is from the input number
                if (sender != null && sender.equals(inputNumber)) {
                    // Forward the message to the output number
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(outputNumber, null, message, null, null);
                }
            }
        }
    }
}
