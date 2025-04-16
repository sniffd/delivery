package com.example.delivery.core.domain.model.aggregate.courier

import com.example.delivery.core.domain.sharedkernel.Location
import java.util.*
import kotlin.math.absoluteValue

private const val SPEED_MIN_VALUE = 1
private const val SPEED_MAX_VALUE = 3

class Transport(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val speed: Int
) {

    init {
        require(speed in SPEED_MIN_VALUE..SPEED_MAX_VALUE) { "Transport speed must be between $SPEED_MIN_VALUE and $SPEED_MAX_VALUE" }
        require(name.isNotBlank()) { "Transport name must not be blank" }
    }

    fun moveCloserTo(from: Location, to: Location): Location {
        val difX = to.x - from.x
        val difY = to.y - from.y
        var cruisingRange = speed

        val moveX = difX.coerceIn(-cruisingRange, cruisingRange)
        cruisingRange -= moveX.absoluteValue

        val moveY = difY.coerceIn(-cruisingRange, cruisingRange)

        val newLocation = Location(
            x = from.x + moveX,
            y = from.y + moveY
        )

        return newLocation
    }

    override fun equals(other: Any?): Boolean {
        return id == (other as Transport).id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}