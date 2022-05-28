package com.interview.payments.data.repository

import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.domain.repository.FurnitureRepository

class FurnitureRepositoryImpl constructor(private val service: MockApiService = MockApiService()) : FurnitureRepository {
    override suspend fun getFurniture(): List<Furniture> = service.getMockFurniture()
}