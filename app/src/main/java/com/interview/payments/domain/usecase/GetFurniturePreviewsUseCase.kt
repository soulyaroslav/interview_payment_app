package com.interview.payments.domain.usecase

import com.interview.payments.data.repository.FurnitureRepositoryImpl
import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.domain.repository.FurnitureRepository

class GetFurniturePreviewsUseCase(
    private val repository: FurnitureRepository = FurnitureRepositoryImpl()
) : UseCase<List<Int>> {

    override suspend fun execute(): List<Int> = repository.getFurniturePreviews()
}