package com.example.delivery.infrastructure.adapter.postgres.order

import com.example.delivery.core.domain.model.aggregate.order.OrderStatus
import com.example.delivery.core.domain.sharedkernel.Location
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "order")
class OrderJpa(
    @Id
    val id: UUID,
    @Embedded
    val location: Location,
    var status: OrderStatus,
    var courierId: UUID?
)