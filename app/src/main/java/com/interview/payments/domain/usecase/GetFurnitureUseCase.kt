package com.interview.payments.domain.usecase

import com.interview.payments.data.repository.FurnitureRepositoryImpl
import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.domain.repository.FurnitureRepository

class GetFurnitureUseCase(private val repository: FurnitureRepository = FurnitureRepositoryImpl()) : UseCase<List<Furniture>> {
    override suspend fun execute(): List<Furniture> = repository.getFurniture()
}