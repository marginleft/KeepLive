package com.margintop.anroid.keepappalive

import android.app.Application
import com.margintop.anroid.library.init

/**
 * @author margintop
 * @date 2019/3/8
 */
class KeepLiveApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        if (init(this)) {
            return
        }
        // 初始化其他操作
    }
}