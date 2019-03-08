package com.margintop.anroid.library.manager

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import com.margintop.anroid.library.utils.GRAY_SERVICE_ID
import kotlin.concurrent.thread

/**
 * @author margintop
 * @date 2019/3/7
 */
internal class ForegroundManager(private val mService: Service) {

    /**
     * 设置服务为前台进程。
     */
    fun setServiceForeground() {
        mService.startForeground(GRAY_SERVICE_ID, Notification())
        mService.startService(Intent(mService, GrayService::class.java))
    }

    fun cancelNotification() {
        (mService.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.cancel(GRAY_SERVICE_ID)
    }

    class GrayService : Service() {

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            startForeground(GRAY_SERVICE_ID, Notification())
            thread {
                SystemClock.sleep(500)
                stopForeground(true)
                (getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.cancel(GRAY_SERVICE_ID)
                stopSelf()
            }
            return super.onStartCommand(intent, flags, startId)
        }

        override fun onBind(intent: Intent?): IBinder? = null

    }
}