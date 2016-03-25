package com.basasa.mydata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import org.json.JSONException;

/**
 * Created by basasa on 3/24/2016.
 */
public class MySMSReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context ctx, Intent intent) {


        if(intent.getAction() != null && intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            SmsMessage smsMessage;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                smsMessage = msgs[0];
            } else {
                Object[] pdus = (Object[]) intent.getExtras().get("pdus");
                smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
            }
            String numberMNS = smsMessage.getDisplayOriginatingAddress();

            if (smsMessage.getDisplayMessageBody().contains("100MB")) {
                abortBroadcast();
                }
            }
        }
    }

