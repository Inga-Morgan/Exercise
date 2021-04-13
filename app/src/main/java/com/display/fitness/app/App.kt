package com.display.fitness.app

import androidx.multidex.MultiDexApplication

/**
 * @author : ye's
 * @date :   2021/4/12
 * @desc :
 */
class App : MultiDexApplication() {
    companion object {
        private lateinit var app: App

        // @JvmStatic 加上后可以直接 getInstance()，不然需要加上Companion
        @JvmStatic
        fun getInstance(): App {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}