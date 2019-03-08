package com.margintop.anroid.library.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Process
import android.text.TextUtils

/**
 * @author margintop
 * @date 2019/3/8
 */

fun isMainProcess(context: Context): Boolean {
    return TextUtils.equals(context.packageName, getCurrentProcessName(context))
}

fun getCurrentProcessName(context: Context): String {
    var processName = context.packageName
    val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    am.runningAppProcesses.forEach {
        if (it.pid == Process.myPid()) {
            processName = it.processName
        }
    }
    return processName
}

fun isDebug(context: Context): Boolean = try {
    (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
} catch (e: Exception) {
    false
}