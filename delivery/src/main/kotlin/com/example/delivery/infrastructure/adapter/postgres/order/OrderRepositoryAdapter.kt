package com.example.delivery.infrastructure.adapter.postgres.order

import com.example.delivery.core.domain.model.aggregate.order.Order
import com.example.delivery.core.domain.model.aggregate.order.OrderStatus
import com.example.delivery.core.port.OrderRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OrderRepositoryAdapter(private val orderJpaRepository: OrderJpaRepository) : OrderRepository {

    override fun add(order: Order) {
        val orderJpa = OrderJpa(
            id = order.id,
            location = order.location,
            status = order.status,
            courierId = order.courierId
        )

        orderJpaRepository.save(orderJpa)
    }

    override fun update(order: Order) {
        val orderJpa = OrderJpa(
            id = order.id,
            location = order.location,
            status = order.status,
            courierId = order.courierId
        )

        orderJpaRepository.save(orderJpa)
    }

    override fun get(id: UUID): Order {
        val orderJpa = orderJpaRepository.findById(id).get()

        val order = Order.restore(
            id = orderJpa.id,
            location = orderJpa.location,
            status = orderJpa.status,
            courierId = orderJpa.courierId
        )

        return order
    }

    override fun getFirstInCreatedStatus(): Order {
        val orderJpa = orderJpaRepository.findFirstByStatus(OrderStatus.CREATED)!!

        val order = Order.restore(
            id = orderJpa.id,
            location = orderJpa.location,
            status = orderJpa.status,
            courierId = orderJpa.courierId
        )

        return order
    }

    override fun getAllInAssignedStatus(): List<Order> {
        val ordersJpa = orderJpaRepository.findAllByStatus(OrderStatus.ASSIGNED)

        return ordersJpa.map {
            Order.restore(
                id = it.id,
                location = it.location,
                status = it.status,
                courierId = it.courierId
            )
        }
    }
}