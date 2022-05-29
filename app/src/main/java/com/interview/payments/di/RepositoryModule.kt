package com.interview.payments.di

import com.interview.payments.data.CoroutineDispatcherProviderImpl
import com.interview.payments.data.repository.FurnitureRepositoryImpl
import com.interview.payments.data.repository.PaymentCardRepositoryImpl
import com.interview.payments.domain.CoroutineDispatcherProvider
import com.interview.payments.domain.repository.FurnitureRepository
import com.interview.payments.domain.repository.PaymentCardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsCoroutineDispatcherProvider(provider: CoroutineDispatcherProviderImpl): CoroutineDispatcherProvider

    @Binds
    fun bindsPaymentCardRepository(repository: PaymentCardRepositoryImpl): PaymentCardRepository

    @Binds
    fun bindsFurnitureRepository(repository: FurnitureRepositoryImpl): FurnitureRepository
}