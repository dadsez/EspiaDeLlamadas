package com.example.root.espiadellamadas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ReceptorLLamadas extends BroadcastReceiver
{
    boolean sono;
    boolean contesto;
    boolean quieto;

    public ReceptorLLamadas() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String estadoTel = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);

        sono = estadoTel.equals(TelephonyManager.EXTRA_STATE_RINGING);
        contesto = estadoTel.equals(TelephonyManager.EXTRA_STATE_OFFHOOK);
        quieto = estadoTel.equals(TelephonyManager.EXTRA_STATE_IDLE);

        if( sono && !contesto && quieto )
        {
            Log.wtf("PEPE", "LAMOOO");
        }

        sono = false;
        contesto = false;
        quieto = false;

    }
}
