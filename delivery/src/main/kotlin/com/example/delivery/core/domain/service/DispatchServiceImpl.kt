package com.example.delivery.core.domain.service

import com.example.delivery.core.domain.model.aggregate.courier.Courier
import com.example.delivery.core.domain.model.aggregate.courier.CourierStatus
import com.example.delivery.core.domain.model.aggregate.order.Order
import com.example.delivery.core.domain.model.aggregate.order.OrderStatus
import org.springframework.stereotype.Service

@Service
class DispatchServiceImpl: DispatchService {

    override fun dispatch(order: Order, couriers: List<Courier>) =
        couriers.map { it to it.countSteps(order) }
            .minBy { it.second }
            .first
            .also {
                order.courierId = it.id
                order.status = OrderStatus.ASSIGNED
                it.status = CourierStatus.BUSY
            }
}