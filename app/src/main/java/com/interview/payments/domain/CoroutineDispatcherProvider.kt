package com.interview.payments.domain

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}