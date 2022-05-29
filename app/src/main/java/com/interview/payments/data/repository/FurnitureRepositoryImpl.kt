package com.interview.payments.data.repository

import com.interview.payments.data.MockApiService
import com.interview.payments.data.dto.FurnitureDto
import com.interview.payments.domain.CoroutineDispatcherProvider
import com.interview.payments.domain.Mapper
import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.domain.repository.FurnitureRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FurnitureRepositoryImpl @Inject constructor(
    private val provider: CoroutineDispatcherProvider,
    private val service: MockApiService,
    private val mapper: Mapper<FurnitureDto, Furniture>
) : FurnitureRepository {

    override suspend fun getFurniture(): List<Furniture> =
        withContext(provider.io) {
            service.getMockFurniture()
                .map { mapper.map(it) }
        }

    override suspend fun getFurniturePreviews(): List<Int> =
        withContext(provider.io) { service.getMockFurniturePreviews() }
}