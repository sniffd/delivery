package com.example.delivery.infrastructure.adapter.postgres.order

import com.example.delivery.core.domain.model.aggregate.order.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderJpaRepository: JpaRepository<OrderJpa, UUID> {

    fun findFirstByStatus(status: OrderStatus): OrderJpa?

    fun findAllByStatus(status: OrderStatus): List<OrderJpa>
}