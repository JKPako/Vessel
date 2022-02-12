package com.system.vessel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        context.startService(Intent(context, Main::class.java))
    }
}