package com.example.delivery.core.domain.model.aggregate.courier

import com.example.delivery.core.domain.model.aggregate.order.Order
import com.example.delivery.core.domain.sharedkernel.Location
import java.util.UUID

class Courier(
    val name: String,
    transportName: String,
    transportSpeed: Int,
    var location: Location
) {

    val id: UUID = UUID.randomUUID()
    var status = CourierStatus.FREE
    val transport = Transport(
        name = transportName,
        speed = transportSpeed
    )

    fun countSteps(order: Order): Int {
        val diff = location.countStepsTo(order.location)

        return if (diff % transport.speed == 0) {
            diff / transport.speed
        } else {
            diff / transport.speed + 1
        }
    }

    fun moveToOrder(order: Order) {
        location = transport.moveCloserTo(location, order.location)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Courier

        return id == other.id
    }

    override fun hashCode() = id.hashCode()
}