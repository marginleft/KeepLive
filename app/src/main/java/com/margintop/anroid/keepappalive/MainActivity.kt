package com.margintop.anroid.keepappalive

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.margintop.anroid.library.startKeepLiveService
import com.margintop.anroid.library.stopKeepLiveService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            startKeepLiveService()
        }
        button2.setOnClickListener {
            stopKeepLiveService()
        }
    }
}
