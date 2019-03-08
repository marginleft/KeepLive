package com.margintop.anroid.library.utils

import android.content.*
import android.os.Build
import android.support.annotation.RequiresApi

/**
 * @author margintop
 * @date 2019/3/7
 */
fun Context.registerReceiverSafely(receiver: BroadcastReceiver, filter: IntentFilter) {
    catchExceptionByIgnore {
        this.registerReceiver(receiver, filter)
    }
}

fun Context.unregisterReceiverSafely(receiver: BroadcastReceiver) {
    catchExceptionByIgnore {
        this.unregisterReceiver(receiver)
    }
}

fun Context.bindServiceSafely(service: Intent, conn: ServiceConnection) {
    catchExceptionByIgnore {
        this.bindService(service, conn, Context.BIND_ABOVE_CLIENT)
    }
}

fun Context.unbindServiceSafely(conn: ServiceConnection) {
    catchExceptionByIgnore {
        this.unbindService(conn)
    }
}

fun Context.startServiceSafely(service: Intent) {
    catchExceptionByIgnore {
        this.startService(service)
    }
}

fun Context.stopServiceSafely(service: Intent) {
    catchExceptionByIgnore {
        this.stopService(service)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.startForegroundServiceSafely(service: Intent) {
    catchExceptionByIgnore {
        this.startForegroundService(service)
    }
}

fun catchExceptionByIgnore(block: () -> Unit) {
    try {
        block()
    } catch (ignore: Exception) {
        // ignore
    }
}