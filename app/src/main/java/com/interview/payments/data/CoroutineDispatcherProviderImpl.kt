package com.interview.payments.data

import com.interview.payments.domain.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatcherProviderImpl @Inject constructor() : CoroutineDispatcherProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
}