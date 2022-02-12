package com.system.vessel

import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val serviceClass = Main::class.java

        val intent = Intent(this, serviceClass)

        val startButton: Button = findViewById(R.id.button_start)
        val stopButton: Button = findViewById(R.id.button_stop)
        val statButton: Button = findViewById(R.id.button_stat)
        startButton.setOnClickListener {
            // If the service is not running then start it
            if (!isServiceRunning(serviceClass)) {
                startService(intent)
            } else {
                toast("Service already running.")
            }
        }

        stopButton.setOnClickListener {
            if (isServiceRunning(serviceClass)) {
                stopService(intent)
            } else {
                toast("Service already stopped.")
            }
        }

        statButton.setOnClickListener {
            if (isServiceRunning(serviceClass)) {
                toast("Service is running.")
            } else {
                toast("Service is stopped.")
            }
        }
    }

    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }

}

// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

