package com.interview.payments.domain.usecase

import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.domain.repository.FurnitureRepository
import javax.inject.Inject

class GetFurnitureUseCase @Inject constructor(private val repository: FurnitureRepository) : UseCase<List<Furniture>> {
    override suspend fun execute(): List<Furniture> = repository.getFurniture()
}