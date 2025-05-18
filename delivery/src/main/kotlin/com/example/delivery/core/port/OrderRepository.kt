package com.example.delivery.core.port

import com.example.delivery.core.domain.model.aggregate.order.Order
import java.util.UUID

interface OrderRepository {

    fun add(order: Order)

    fun update(order: Order)

    fun get(id: UUID): Order

    fun getFirstInCreatedStatus(): Order

    fun getAllInAssignedStatus(): List<Order>
}