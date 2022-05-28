package com.interview.payments.data.repository

import com.interview.payments.data.FurnitureMapper
import com.interview.payments.data.dto.FurnitureDto
import com.interview.payments.domain.Mapper
import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.domain.repository.FurnitureRepository

class FurnitureRepositoryImpl constructor(
    private val service: MockApiService = MockApiService(),
    private val mapper: Mapper<FurnitureDto, Furniture> = FurnitureMapper()
) : FurnitureRepository {
    override suspend fun getFurniture(): List<Furniture> = service.getMockFurniture()
        .map { mapper.map(it) }
}