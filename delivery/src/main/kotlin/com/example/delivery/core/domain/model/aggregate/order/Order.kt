package com.example.delivery.core.domain.model.aggregate.order

import com.example.delivery.core.domain.sharedkernel.Location
import java.util.UUID

class Order private constructor(
    val id: UUID,
    val location: Location,
    var status: OrderStatus,
    var courierId: UUID?
) {

    constructor(id: UUID, location: Location) : this(id, location, OrderStatus.CREATED, null)

    fun assign(courierId: UUID) {
        this.courierId = courierId
        status = OrderStatus.ASSIGNED
    }

    fun finish() {
        check(status == OrderStatus.ASSIGNED) { "Order status must be ASSIGNED" }

        status = OrderStatus.COMPLETED
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        return id == other.id
    }

    override fun hashCode() = id.hashCode()

    companion object {

        fun restore(id: UUID, location: Location, status: OrderStatus, courierId: UUID?) =
            Order(
                id = id,
                location = location,
                status = status,
                courierId = courierId
            )
    }
}