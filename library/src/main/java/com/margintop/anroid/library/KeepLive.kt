package com.margintop.anroid.library

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.margintop.anroid.library.service.KeepLiveService
import com.margintop.anroid.library.service.RemoteService
import com.margintop.anroid.library.service.TimingService
import com.margintop.anroid.library.utils.*

/**
 * @author margintop
 * @date 2019/3/7
 */

@SuppressLint("StaticFieldLeak")
private var mContext: Context? = null

// 紧跟着Application的super.onCreate()下调用。
fun init(context: Context): Boolean {
    mContext = context.applicationContext
    return isMainProcess(context)
}

// 在主进程中调用。
fun startKeepLiveService() {
    val context = mContext.assertNull()
    if (isMainProcess(context)) {
        val intent = Intent(context, KeepLiveService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundServiceSafely(intent)
        } else {
            context.startServiceSafely(intent)
        }
    }
}

// 在主进程中调用。
fun stopKeepLiveService() {
    val context = mContext.assertNull()
    if (isMainProcess(context)) {
        context.apply {
            stopServiceSafely(Intent(this, TimingService::class.java))
            stopServiceSafely(Intent(this, RemoteService::class.java))
            stopServiceSafely(Intent(this, KeepLiveService::class.java))
        }
    }
}

internal fun logger(message: String) {
    val context = mContext.assertNull()
    if (isDebug(context)) {
        Log.d("KeepLive", message)
    }
}

private fun Context?.assertNull(): Context {
    return this ?: throw IllegalStateException("please init first")
}