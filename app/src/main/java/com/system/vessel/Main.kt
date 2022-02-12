package com.system.vessel

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context


import android.os.Build
import android.os.Handler
import android.view.WindowManager

import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL


class Main : Service() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    companion object {
        const val channelId = "--your channel id--"
        const val notifyId = 395 // some number
    }

    private val notificationBuilder
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) NotificationCompat.Builder(this, channelId)
        else NotificationCompat.Builder(this, channelId)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //toast("LAMOGUS")
        mHandler = Handler()
        mRunnable = Runnable { showSomething() }
        mHandler.postDelayed(mRunnable, 100)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        sendStartNotification()
        var task = NetworkAsyncTask()
        task.execute()
        var buffer = "ADDA"

        toast(buffer)
    }


    private fun sendStartNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createChannel()
        val notification = buildNotification()
        this.startForeground(notifyId, notification)
    }

    private fun buildNotification(): Notification {
        val builder = notificationBuilder

        builder.setSmallIcon(R.drawable.ic_menu_add)
            .priority = NotificationCompat.PRIORITY_MIN
        return builder.build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name = "Name"// for user
        val channel = NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH)
        channel.description = "Description" //for user
        manager.createNotificationChannel(channel)
    }
    private fun showSomething(){
        //toast("Я негр и у меня справка есть")
        val display = (getSystemService(WINDOW_SERVICE) as WindowManager)
            .defaultDisplay.state
        mHandler.postDelayed(mRunnable, 100)
    }

}



