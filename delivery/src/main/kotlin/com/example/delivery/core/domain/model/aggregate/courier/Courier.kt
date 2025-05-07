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

    val id = UUID.randomUUID()
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
}