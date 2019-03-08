package com.margintop.anroid.library.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * @author margintop
 * @date 2019/3/7
 */
open class AbstractReceiver : BroadcastReceiver() {

    private var mBlock: ((context: Context?, intent: Intent?) -> Unit)? = null

    fun onReceive(block: (context: Context?, intent: Intent?) -> Unit): AbstractReceiver {
        mBlock = block
        return this@AbstractReceiver
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        mBlock?.invoke(context, intent)
    }
}