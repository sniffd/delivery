package com.example.delivery.core.domain.service

import com.example.delivery.core.domain.model.aggregate.courier.Courier
import com.example.delivery.core.domain.model.aggregate.order.Order

interface DispatchService {

    fun dispatch(order: Order, couriers: List<Courier>): Courier
}