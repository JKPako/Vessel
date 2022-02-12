package com.system.vessel


import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.BufferedOutputStream
import java.io.BufferedWriter
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class Main : Service() {

    val calendar: Calendar = Calendar.getInstance()
    private var wasScreenPowered: Boolean = false
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
        val display = (getSystemService(WINDOW_SERVICE) as WindowManager)
            .defaultDisplay.state
        if (calendar.get(Calendar.HOUR_OF_DAY)<13){
            GlobalScope.async {
                connect()
            }
        }
        mHandler.postDelayed(mRunnable, 100)

    }

    suspend fun connect(){
        Log.println(Log.INFO,"DebugInfo","Async Task Began")

        val url = URL("https://karitaserver.000webhostapp.com/wakeup.php")


        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {

            }
        }
    }

}



