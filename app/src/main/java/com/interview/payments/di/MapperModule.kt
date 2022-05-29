package com.interview.payments.di

import com.interview.payments.data.FurnitureMapper
import com.interview.payments.data.dto.FurnitureDto
import com.interview.payments.domain.Mapper
import com.interview.payments.domain.pojo.Furniture
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindsFurnitureMapper(mapper: FurnitureMapper): Mapper<FurnitureDto, Furniture>
}
