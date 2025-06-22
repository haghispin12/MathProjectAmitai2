package com.example.mathprojectamitai2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class BroadCastProject extends BroadcastReceiver {
    /**
     * פעולה שעושה Toast כל פעם שהאחוז בטלפון משתנה
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
    int battery = intent.getIntExtra("level",0);
    Toast.makeText(context, "Battery Level: " + battery + "%", Toast.LENGTH_SHORT).show();
    }

}
