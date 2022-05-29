package com.interview.payments.domain.usecase

import androidx.annotation.WorkerThread

interface UseCaseParams<T, Params> {
    @WorkerThread
    suspend fun execute(params: Params): T
}