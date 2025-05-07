package com.example.delivery.core.domain.model.aggregate.courier

import com.example.delivery.core.domain.model.aggregate.order.Order
import com.example.delivery.core.domain.sharedkernel.Location
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

class CourierTest {

    @Test
    fun `should create a courier with correct name, transport, location and status is FREE`() {
        val name = "Name"
        val transportName = "Transport"
        val speed = 3
        val location = Location.createRandom()
        val courier = Courier(
            name = name,
            transportName = transportName,
            transportSpeed = speed,
            location = location
        )

        assertThat(courier.name).isEqualTo(name)
        assertThat(courier.transport.name).isEqualTo(transportName)
        assertThat(courier.transport.speed).isEqualTo(speed)
        assertThat(courier.location).isEqualTo(location)
        assertThat(courier.status).isEqualTo(CourierStatus.FREE)
    }

    @Test
    fun `should be able to set status to BUSY and then to FREE`() {
        val name = "Name"
        val transportName = "Transport"
        val speed = 3
        val location = Location.createRandom()
        val courier = Courier(
            name = name,
            transportName = transportName,
            transportSpeed = speed,
            location = location
        )

        assertThat(courier.status).isEqualTo(CourierStatus.FREE)

        courier.status = CourierStatus.BUSY

        assertThat(courier.status).isEqualTo(CourierStatus.BUSY)

        courier.status = CourierStatus.FREE

        assertThat(courier.status).isEqualTo(CourierStatus.FREE)
    }

    @Test
    fun `should return correct number of steps`() {
        val name = "Name"
        val transportName = "Велосипед"
        val speed = 2
        val location = Location(
            x = 1,
            y = 1
        )
        val courier = Courier(
            name = name,
            transportName = transportName,
            transportSpeed = speed,
            location = location
        )
        val order = Order(
            id = UUID.randomUUID(),
            location = Location(
                x = 5,
                y = 5
            )
        )
        val expected = 4

        val actual = courier.countSteps(order)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should move one step closer when moveToOrder invoked`() {
        val name = "Name"
        val transportName = "Велосипед"
        val speed = 2
        val location = Location(
            x = 1,
            y = 1
        )
        val courier = Courier(
            name = name,
            transportName = transportName,
            transportSpeed = speed,
            location = location
        )
        val order = Order(
            id = UUID.randomUUID(),
            location = Location(
                x = 5,
                y = 5
            )
        )
        val expected = Location(
            x = 3,
            y = 1
        )

        courier.moveToOrder(order)

        assertThat(courier.location).isEqualTo(expected)
    }
}
