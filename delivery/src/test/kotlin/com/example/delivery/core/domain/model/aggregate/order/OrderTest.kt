package com.example.delivery.core.domain.model.aggregate.order

import com.example.delivery.core.domain.sharedkernel.Location
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import java.util.UUID
import kotlin.test.Test

class OrderTest {

    @Test
    fun `should create order with passed id, location and created status and courierId is null`() {
        val id = UUID.randomUUID()
        val location = Location.createRandom()
        val order = Order(
            id = id,
            location = location
        )

        assertThat(order.id).isEqualTo(id)
        assertThat(order.location).isEqualTo(location)
        assertThat(order.status).isEqualTo(OrderStatus.CREATED)
        assertThat(order.courierId).isNull()
    }

    @Test
    fun `should contain courierId and status ASSIGNED after assign`() {
        val id = UUID.randomUUID()
        val courierId = UUID.randomUUID()
        val location = Location.createRandom()
        val order = Order(
            id = id,
            location = location
        )

        assertThat(order.status).isEqualTo(OrderStatus.CREATED)
        assertThat(order.courierId).isNull()

        order.assign(courierId)

        assertThat(order.status).isEqualTo(OrderStatus.ASSIGNED)
        assertThat(order.courierId).isEqualTo(courierId)
    }

    @Test
    fun `should contain status COMPLETED after finish when current status is ASSIGNED`() {
        val id = UUID.randomUUID()
        val courierId = UUID.randomUUID()
        val location = Location.createRandom()
        val order = Order(
            id = id,
            location = location
        )

        assertThat(order.status).isEqualTo(OrderStatus.CREATED)

        order.assign(courierId)

        assertThat(order.status).isEqualTo(OrderStatus.ASSIGNED)

        order.finish()

        assertThat(order.status).isEqualTo(OrderStatus.COMPLETED)
    }

    @Test
    fun `should throw when finish with status CREATED`() {
        val id = UUID.randomUUID()
        val location = Location.createRandom()
        val order = Order(
            id = id,
            location = location
        )

        assertThat(order.status).isEqualTo(OrderStatus.CREATED)

        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy { order.finish() }
    }
}
