package com.example.delivery.core.port

import com.example.delivery.core.domain.model.aggregate.courier.Courier
import java.util.*

interface CourierRepository {

    fun add(courier: Courier)

    fun update(courier: Courier)

    fun get(id: UUID): Courier

    fun getAllInFreeStatus(): List<Courier>
}