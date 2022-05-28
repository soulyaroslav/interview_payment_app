package com.interview.payments.domain.usecase

import androidx.annotation.WorkerThread

interface UseCase<T> {
    @WorkerThread
    suspend fun execute(): T
}