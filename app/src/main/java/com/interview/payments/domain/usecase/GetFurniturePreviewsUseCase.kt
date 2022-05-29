package com.interview.payments.domain.usecase

import com.interview.payments.domain.repository.FurnitureRepository
import javax.inject.Inject

class GetFurniturePreviewsUseCase @Inject constructor(private val repository: FurnitureRepository) : UseCase<List<Int>> {
    override suspend fun execute(): List<Int> = repository.getFurniturePreviews()
}