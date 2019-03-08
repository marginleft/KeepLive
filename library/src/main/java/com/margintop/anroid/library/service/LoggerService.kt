package com.margintop.anroid.library.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.margintop.anroid.library.logger

/**
 * @author margintop
 * @date 2019/3/8
 */
open class LoggerService : Service() {

    private val mClassName by lazy {
        this.javaClass.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        logger("${mClassName}调用了onCreate")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        logger("${mClassName}调用了onStart")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logger("${mClassName}调用了onStartCommand")
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        logger("${mClassName}调用了onBind")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        logger("${mClassName}调用了onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        logger("${mClassName}调用了onDestroy")
    }

}