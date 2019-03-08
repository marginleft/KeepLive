package com.margintop.anroid.library.manager

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import com.margintop.anroid.library.R
import com.margintop.anroid.library.logger
import com.margintop.anroid.library.utils.getCurrentProcessName
import kotlin.concurrent.thread

/**
 * @author margintop
 * @date 2019/3/7
 */

internal class AudioManager(private val context: Context) {

    private val mHandler by lazy {
        Handler()
    }

    private val mMediaPlayer by lazy {
        MediaPlayer.create(context, R.raw.empty)
    }

    init {
        mMediaPlayer.isLooping = true
    }

    fun startMusic() {
        thread {
            mMediaPlayer.start()
        }
        startTask()
    }

    fun stopMusic() {
        mMediaPlayer.stop()
        stopTask()
    }

    private fun startTask() {
        mHandler.postDelayed(object : Runnable {
            override fun run() {
                logger("音频是否在播放：${mMediaPlayer.isPlaying}")
                logger("processName: ${getCurrentProcessName(context)}")
                mHandler.postDelayed(this, 30 * 1000)
            }

        }, 30 * 1000)
    }

    private fun stopTask() {
        mHandler.removeCallbacksAndMessages(null)
    }

}