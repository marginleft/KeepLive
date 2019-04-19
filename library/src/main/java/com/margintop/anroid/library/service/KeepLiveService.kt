package com.margintop.anroid.library.service

import android.content.Intent
import com.margintop.anroid.library.manager.ForegroundManager
import com.margintop.anroid.library.manager.ScreenManager
import com.margintop.anroid.library.utils.AbstractServiceConnection
import com.margintop.anroid.library.utils.bindServiceSafely
import com.margintop.anroid.library.utils.startServiceSafely
import com.margintop.anroid.library.utils.unbindServiceSafely

class KeepLiveService : LoggerService() {

    private val mForegroundManager by lazy {
        ForegroundManager(this)
    }

    private val mScreenManager by lazy {
        ScreenManager(this)
    }

    private val mService by lazy {
        Intent(this, RemoteService::class.java)
    }

    private val mConn by lazy {
        AbstractServiceConnection()
    }

    override fun onCreate() {
        super.onCreate()
        mForegroundManager.setServiceForeground()
        mScreenManager.registerScreenReceiver()
        startServiceSafely(mService)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindServiceSafely(mService, mConn)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindServiceSafely(mConn)
        mScreenManager.unregisterScreenReceiver()
        mForegroundManager.cancelServiceForeground()
    }

}
