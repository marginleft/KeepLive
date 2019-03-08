package com.margintop.anroid.library.service

import android.content.Intent
import com.margintop.anroid.library.manager.AudioManager
import com.margintop.anroid.library.manager.ForegroundManager
import com.margintop.anroid.library.manager.TimingManager
import com.margintop.anroid.library.manager.WakeUpBroadcastManager
import com.margintop.anroid.library.utils.AbstractServiceConnection
import com.margintop.anroid.library.utils.bindServiceSafely
import com.margintop.anroid.library.utils.startServiceSafely
import com.margintop.anroid.library.utils.unbindServiceSafely

/**
 * @author margintop
 * @date 2019/3/7
 */
class RemoteService : LoggerService() {

    private val mForegroundManager by lazy {
        ForegroundManager(this)
    }

    private val mAudioManager by lazy {
        AudioManager(this)
    }

    private val mTimingManager by lazy {
        TimingManager(this)
    }

    private val mWakeUpBroadcastManager by lazy {
        WakeUpBroadcastManager(this)
    }

    private val mConn by lazy {
        AbstractServiceConnection()
    }

    override fun onCreate() {
        super.onCreate()
        mForegroundManager.setServiceForeground()
        mAudioManager.startMusic()
        mTimingManager.startJob()
        mWakeUpBroadcastManager.registerWakeUpReceiver()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindServiceSafely(Intent(this, KeepLiveService::class.java), mConn)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindServiceSafely(mConn)
        mAudioManager.stopMusic()
        mForegroundManager.cancelNotification()
        mTimingManager.stopJob()
        mWakeUpBroadcastManager.unregisterWakeUpReceiver()
        // 调用stopKeepLiveService方法会杀不死服务，但是如果不关心服务的关闭，可取消注释。
//        restart()
    }

    private fun restart() {
        startServiceSafely(Intent(this, RemoteService::class.java))
    }
}