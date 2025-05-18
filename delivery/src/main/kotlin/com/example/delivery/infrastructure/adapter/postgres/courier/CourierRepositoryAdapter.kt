package com.example.delivery.infrastructure.adapter.postgres.courier

import com.example.delivery.core.domain.model.aggregate.courier.Courier
import com.example.delivery.core.domain.model.aggregate.courier.CourierStatus
import com.example.delivery.core.domain.model.aggregate.courier.Transport
import com.example.delivery.core.port.CourierRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CourierRepositoryAdapter(private val courierJpaRepository: CourierJpaRepository) : CourierRepository {

    override fun add(courier: Courier) {
        val courierJpa = CourierJpa(
            id = courier.id,
            name = courier.name,
            transport = TransportJpa(
                id = courier.transport.id,
                name = courier.transport.name,
                speed = courier.transport.speed
            ),
            location = courier.location,
            status = courier.status
        )

        courierJpaRepository.save(courierJpa)
    }

    override fun update(courier: Courier) {
        val courierJpa = CourierJpa(
            id = courier.id,
            name = courier.name,
            transport = TransportJpa(
                id = courier.transport.id,
                name = courier.transport.name,
                speed = courier.transport.speed
            ),
            location = courier.location,
            status = courier.status
        )

        courierJpaRepository.save(courierJpa)
    }

    override fun get(id: UUID): Courier {
        val courierJpa = courierJpaRepository.findById(id).get()

        val courier = Courier.restore(
            id = courierJpa.id,
            name = courierJpa.name,
            transport = Transport(
                id = courierJpa.transport.id,
                name = courierJpa.transport.name,
                speed = courierJpa.transport.speed
            ),
            location = courierJpa.location,
            status = courierJpa.status
        )

        return courier
    }

    override fun getAllInFreeStatus(): List<Courier> {
        val couriersJpa = courierJpaRepository.findAllByStatus(CourierStatus.FREE)

        return couriersJpa.map {
            Courier.restore(
                id = it.id,
                name = it.name,
                transport = Transport(
                    id = it.transport.id,
                    name = it.transport.name,
                    speed = it.transport.speed
                ),
                location = it.location,
                status = it.status
            )
        }
    }
}