package com.alimardon.alimardon

import android.app.Application
import com.yariksoffice.lingver.Lingver

class my:Application() {
    override fun onCreate() {
        super.onCreate()
        Lingver.init(this)
    }
}