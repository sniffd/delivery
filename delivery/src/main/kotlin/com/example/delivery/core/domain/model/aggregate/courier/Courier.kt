package com.example.delivery.core.domain.model.aggregate.courier

import com.example.delivery.core.domain.model.aggregate.order.Order
import com.example.delivery.core.domain.sharedkernel.Location
import java.util.UUID

class Courier private constructor(
    val id: UUID,
    val name: String,
    val transport: Transport,
    var location: Location,
    var status: CourierStatus
) {

    constructor(name: String, transportName: String, transportSpeed: Int, location: Location) :
            this(
                id = UUID.randomUUID(),
                name = name,
                transport = Transport(
                    name = transportName,
                    speed = transportSpeed
                ),
                location = location,
                status = CourierStatus.FREE
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

    companion object {

        fun restore(id: UUID, name: String, transport: Transport, location: Location, status: CourierStatus) =
            Courier(
                id = id,
                name = name,
                transport = transport,
                location = location,
                status = status
            )
    }
}