package com.interview.payments.domain.repository

import com.interview.payments.domain.pojo.Furniture

interface FurnitureRepository {
    suspend fun getFurniture() : List<Furniture>
}