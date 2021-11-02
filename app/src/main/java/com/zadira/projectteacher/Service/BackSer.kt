package com.zadira.projectteacher.Service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.zadira.projectteacher.Service.BackgroundService.runnable
import com.zadira.projectteacher.SharedPref.MyShafePreferens
import java.text.SimpleDateFormat
import java.util.*


class BackSer:Service() {
    private lateinit var mHandler: Handler
    val notificationHelper = NotificationHelper()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
    @SuppressLint("SimpleDateFormat")
    override fun onStart(intent: Intent?, startId: Int) {
        var a=0
        super.onStart(intent, startId)
        MyShafePreferens.init(this)
        val list = MyShafePreferens.objectsString
        mHandler = Handler()
        runnable = Runnable {
            val time=list[a].time1

            Toast.makeText(this, time, Toast.LENGTH_SHORT).show()
            if (SimpleDateFormat("HH:mm").format(Date())==time) {
                notificationHelper.sendNotification(this, "Hello", "my first notification")
                a++
                turnOnScreen()
            }

            mHandler.postDelayed(runnable, 1000)
            }
        mHandler.postDelayed(runnable, 5000)
    }
    override fun onBind(p0: Intent?): IBinder? {
        Toast.makeText(this, "go", Toast.LENGTH_SHORT).show()
        return null
    }
    @SuppressLint("InvalidWakeLockTag")
    private fun turnOnScreen() {
        var screenLock: WakeLock? = null
        if (getSystemService(POWER_SERVICE) != null) {
            screenLock = (getSystemService(POWER_SERVICE) as PowerManager).newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG"
            )
            screenLock.acquire(1 * 60 * 1000L /*10 minutes*/)
            screenLock.release()
        }
    }

}