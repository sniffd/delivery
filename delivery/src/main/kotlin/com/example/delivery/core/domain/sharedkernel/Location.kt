package com.example.delivery.core.domain.sharedkernel

import kotlin.math.abs
import kotlin.random.Random
import kotlin.random.nextInt

private const val MIN_VALUE = 1
private const val MAX_VALUE = 10

data class Location(
    val x: Int,
    val y: Int
) {

    init {
        require(x in MIN_VALUE..MAX_VALUE) { "Location x must be between $MIN_VALUE and $MAX_VALUE" }
        require(y in MIN_VALUE..MAX_VALUE) { "Location y must be between $MIN_VALUE and $MAX_VALUE" }
    }

    fun countStepsTo(other: Location) = abs(x - other.x) + abs(y - other.y)

    companion object {

        fun createRandom(): Location {
            val x = Random.nextInt(MIN_VALUE..MAX_VALUE)
            val y = Random.nextInt(MIN_VALUE..MAX_VALUE)

            return Location(x, y)
        }
    }
}
