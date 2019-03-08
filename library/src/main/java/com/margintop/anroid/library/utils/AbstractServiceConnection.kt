package com.margintop.anroid.library.utils

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

/**
 * @author margintop
 * @date 2019/3/7
 */
class AbstractServiceConnection : ServiceConnection {

    private var mConnectedBlock: ((name: ComponentName?, service: IBinder?) -> Unit)? = null

    fun onServiceConnected(connectedBlock: (name: ComponentName?, service: IBinder?) -> Unit): AbstractServiceConnection {
        mConnectedBlock = connectedBlock
        return this@AbstractServiceConnection
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mConnectedBlock?.invoke(name, service)
    }

    private var mDisconnectedBlock: ((name: ComponentName?) -> Unit)? = null

    fun onServiceDisconnected(disconnectedBlock: (name: ComponentName?) -> Unit): AbstractServiceConnection {
        mDisconnectedBlock = disconnectedBlock
        return this@AbstractServiceConnection
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        mDisconnectedBlock?.invoke(name)
    }
}