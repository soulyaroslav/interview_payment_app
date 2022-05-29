package com.interview.payments.data.repository

import com.interview.payments.R
import com.interview.payments.data.dto.FurnitureDto
import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.pojo.Furniture
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.suspendCoroutine

class MockApiService {

    suspend fun getMockFurniture() = coroutineScope {
        delay(DELAY)

        listOf(
            FurnitureDto(
                id = 1,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                discountPrice = 200,
                image = randImage()
            ),
            FurnitureDto(
                id = 2,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 220,
                image = randImage()
            ),
            FurnitureDto(
                id = 3,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 330,
                image = randImage()
            ),
            FurnitureDto(
                id = 4,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 530,
                image = randImage()
            ),
            FurnitureDto(
                id = 5,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 130,
                image = randImage()
            ),
            FurnitureDto(
                id = 6,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 270,
                image = randImage()
            ),
            FurnitureDto(
                id = 7,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 430,
                image = randImage()
            ),
            FurnitureDto(
                id = 8,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            FurnitureDto(
                id = 9,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            FurnitureDto(
                id = 10,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            FurnitureDto(
                id = 11,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 330,
                image = randImage()
            ),
            FurnitureDto(
                id = 12,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 430,
                discountPrice = 350,
                image = randImage()
            ),
            FurnitureDto(
                id = 13,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            FurnitureDto(
                id = 14,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            FurnitureDto(
                id = 15,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            FurnitureDto(
                id = 16,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            )
        )
    }

    suspend fun getMockFurniturePreviews() = coroutineScope {
        delay(DELAY)

        listOf(R.drawable.chair_0, R.drawable.chair_1, R.drawable.chair_2)
    }

    suspend fun getMockCard() = listOf(
        Card(
            number = "5555 6666 0000 1111",
            expirationDay = "02/06/24",
            secureCode = "453",
            "Test User"
        ),
        Card(
            number = "5555 6666 0000 2222",
            expirationDay = "02/06/24",
            secureCode = "453",
            "Test User"
        )
    )

    private fun randImage(): Int {
        val rand = (0..2).random()
        return furnitureImages[rand]
    }

    private val furnitureImages = listOf(
        R.drawable.chair_0,
        R.drawable.chair_1,
        R.drawable.chair_2
    )

    companion object {
        const val DELAY = 3000L
    }
}