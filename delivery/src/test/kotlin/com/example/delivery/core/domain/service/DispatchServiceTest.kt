package com.example.delivery.core.domain.service

import com.example.delivery.core.domain.model.aggregate.courier.Courier
import com.example.delivery.core.domain.model.aggregate.courier.CourierStatus
import com.example.delivery.core.domain.model.aggregate.order.Order
import com.example.delivery.core.domain.model.aggregate.order.OrderStatus
import com.example.delivery.core.domain.sharedkernel.Location
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

class DispatchServiceTest {

    val dispatchService = DispatchServiceImpl()

    @Test
    fun `should assign closest courier to order and mark him as BUSY`() {
        val orderLocation = Location(
            x = 7,
            y = 7
        )
        val order = Order(UUID.randomUUID(), orderLocation)

        val courier1 = Courier(
            name = "Ivan",
            transportName = "Bike",
            transportSpeed = 1,
            location = Location(
                x = 1,
                y = 1
            )
        )

        val courier2 = Courier(
            name = "Petr",
            transportName = "Scooter",
            transportSpeed = 2,
            location = Location(
                x = 5,
                y = 5
            )
        )

        val courier3 = Courier(
            name = "Anna",
            transportName = "Boots",
            transportSpeed = 1,
            location = Location(
                x = 10,
                y = 10
            )
        )

        val couriers = listOf(courier1, courier2, courier3)

        dispatchService.dispatch(order, couriers)

        assertThat(order.courierId).isEqualTo(courier2.id)
        assertThat(order.status).isEqualTo(OrderStatus.ASSIGNED)
        assertThat(courier2.status).isEqualTo(CourierStatus.BUSY)

        assertThat(courier1.status).isNotEqualTo(CourierStatus.BUSY)
        assertThat(courier3.status).isNotEqualTo(CourierStatus.BUSY)
    }
}