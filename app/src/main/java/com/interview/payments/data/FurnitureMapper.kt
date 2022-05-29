package com.interview.payments.data

import com.interview.payments.data.dto.FurnitureDto
import com.interview.payments.domain.Mapper
import com.interview.payments.domain.pojo.Furniture
import javax.inject.Inject

class FurnitureMapper @Inject constructor() : Mapper<FurnitureDto, Furniture> {

    override fun map(response: FurnitureDto): Furniture = Furniture(
        id = response.id,
        title = response.title,
        description = response.description,
        price = response.price,
        discountPrice = response.discountPrice,
        image = response.image
    )

}