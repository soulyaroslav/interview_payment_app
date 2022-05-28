package com.interview.payments

import android.app.Application
import android.content.Context

class PaymentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit  var appContext: Context
    }
}