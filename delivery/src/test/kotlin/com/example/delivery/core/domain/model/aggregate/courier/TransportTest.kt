package com.example.delivery.core.domain.model.aggregate.courier

import com.example.delivery.core.domain.sharedkernel.Location
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.*

class TransportTest {

    @Test
    fun `should create a new transport with correct name and speed with implicit id`() {
        Transport(
            name = "Transport 1",
            speed = 2
        )
    }

    @Test
    fun `should create a new transport with correct name and speed with explicit id`() {
        Transport(
            id = UUID.randomUUID(),
            name = "Transport 1",
            speed = 2
        )
    }

    @Test
    fun `should throw an exception when creating a new transport with speed less than min value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Transport(
                name = "Transport 1",
                speed = 0
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new transport with speed more than max value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Transport(
                name = "Transport 1",
                speed = 4
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new transport with empty name`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Transport(
                name = "",
                speed = 2
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new transport with blank name`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Transport(
                name = "    ",
                speed = 2
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new transport with blank name and speed more than max value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Transport(
                name = "    ",
                speed = 4
            )
        }
    }

    @Test
    fun `should be equal when ids are same`() {
        val id = UUID.randomUUID()
        val transport1 = Transport(
            id = id,
            name = "Transport 1",
            speed = 2
        )
        val transport2 = Transport(
            id = id,
            name = "Transport 2",
            speed = 2
        )

        assertThat(transport1).isEqualTo(transport2)
    }

    @Test
    fun `should be not equal when ids are different`() {
        val transport1 = Transport(
            id = UUID.randomUUID(),
            name = "Transport 1",
            speed = 2
        )
        val transport2 = Transport(
            id = UUID.randomUUID(),
            name = "Transport 2",
            speed = 2
        )

        assertThat(transport1).isNotEqualTo(transport2)
    }

    @Test
    fun `should calculate new location`() {
        val from = Location(
            x = 1,
            y = 1
        )
        val to = Location(
            x = 1,
            y = 9
        )
        val expected = Location(
            x = 1,
            y = 3
        )
        val transport = Transport(
            name = "Transport 1",
            speed = 2
        )
        val actual = transport.moveCloserTo(from, to)

        assertThat(actual).isEqualTo(expected)
    }
}