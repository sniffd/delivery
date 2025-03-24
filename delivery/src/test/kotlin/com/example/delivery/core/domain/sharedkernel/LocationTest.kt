package com.example.delivery.core.domain.sharedkernel

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class LocationTest {

    @Test
    fun `should create a new location with min value`() {
        Location(
            x = 1,
            y = 1
        )
    }

    @Test
    fun `should throw an exception when creating a new location with x less than min value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 0,
                y = 1
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new location with y less than min value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 1,
                y = 0
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new location with x and y less than min value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 0,
                y = 0
            )
        }
    }

    @Test
    fun `should create a new location with max value`() {
        Location(
            x = 10,
            y = 10
        )
    }

    @Test
    fun `should throw an exception when creating a new location with x more than max value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 11,
                y = 10
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new location with y more than max value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 10,
                y = 11
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new location with x and y more than max value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 11,
                y = 11
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new location with x less then min value and y more than max value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 0,
                y = 11
            )
        }
    }

    @Test
    fun `should throw an exception when creating a new location with x more than max value and y less then min value`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Location(
                x = 11,
                y = 0
            )
        }
    }

    @Test
    fun `should return true when comparing two locations with equals x and y`() {
        val location1 = Location(
            x = 5,
            y = 6
        )

        val location2 = Location(
            x = 5,
            y = 6
        )

        assertThat(location1 == location2).isTrue()
    }

    @Test
    fun `should return false when comparing two locations with different x and y`() {
        val location1 = Location(
            x = 5,
            y = 6
        )

        val location2 = Location(
            x = 7,
            y = 8
        )

        assertThat(location1 == location2).isFalse()
    }

    @Test
    fun `should count steps correctly`() {
        val location1 = Location(
            x = 4,
            y = 9
        )

        val location2 = Location(
            x = 2,
            y = 6
        )

        val expected = 5

        assertThat(location1.countStepsTo(location2)).isEqualTo(expected)
    }

    @Test
    fun `should create random location`() {
        Location.createRandom()
    }
}
