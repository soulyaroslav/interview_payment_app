package com.interview.payments.data.repository

import com.interview.payments.R
import com.interview.payments.domain.pojo.Furniture
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.suspendCoroutine

class MockApiService {

    // Note: Here I'm using Furniture to not complicate existed implementation
    // in the real project here will be another object like FurnitureDto
    // to represent data from backend service. Then it will map to the Furniture object
    suspend fun getMockFurniture() = coroutineScope {
        delay(DELAY)

        listOf(
            Furniture(
                id = 1,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                discountPrice = 200,
                image = randImage()
            ),
            Furniture(
                id = 2,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 220,
                image = randImage()
            ),
            Furniture(
                id = 3,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 330,
                image = randImage()
            ),
            Furniture(
                id = 4,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 530,
                image = randImage()
            ),
            Furniture(
                id = 5,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 130,
                image = randImage()
            ),
            Furniture(
                id = 6,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 270,
                image = randImage()
            ),
            Furniture(
                id = 7,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 430,
                image = randImage()
            ),
            Furniture(
                id = 8,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            Furniture(
                id = 9,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            Furniture(
                id = 10,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            Furniture(
                id = 11,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 330,
                image = randImage()
            ),
            Furniture(
                id = 12,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 430,
                discountPrice = 350,
                image = randImage()
            ),
            Furniture(
                id = 13,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            Furniture(
                id = 14,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            Furniture(
                id = 15,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            ),
            Furniture(
                id = 16,
                title = "Vitra DAW",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                price = 230,
                image = randImage()
            )
        )
    }

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