package com.margintop.anroid.library.manager

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.margintop.anroid.library.activity.OnePixelActivity
import com.margintop.anroid.library.logger
import com.margintop.anroid.library.utils.ACTION_FINISH_ONE_PIXEL_ACTIVITY
import com.margintop.anroid.library.utils.AbstractReceiver
import com.margintop.anroid.library.utils.registerReceiverSafely
import com.margintop.anroid.library.utils.unregisterReceiverSafely

/**
 * @author margintop
 * @date 2019/3/7
 */
internal class ScreenManager(private val mContext: Context) {

    private val mScreenReceiver by lazy {
        AbstractReceiver().onReceive { _, intent ->
            intent?.action?.let {
                when (it) {
                    Intent.ACTION_SCREEN_ON -> finishOnePixelActivity()
                    Intent.ACTION_SCREEN_OFF -> startOnePixelActivity()
                    Intent.ACTION_USER_PRESENT -> {
                        // 解锁，暂时不用，保留
                    }
                }
                Unit
            }
        }
    }

    private fun startOnePixelActivity() {
        logger("开启一像素")
        mContext.startActivity(Intent(mContext, OnePixelActivity::class.java))
    }

    private fun finishOnePixelActivity() {
        logger("关闭一像素")
        mContext.sendBroadcast(Intent(ACTION_FINISH_ONE_PIXEL_ACTIVITY))
    }

    fun registerScreenReceiver() {
        mContext.registerReceiverSafely(mScreenReceiver, IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_USER_PRESENT)
        })
    }

    fun unregisterScreenReceiver() {
        mContext.unregisterReceiverSafely(mScreenReceiver)
    }

}