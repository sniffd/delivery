package com.example.delivery.infrastructure.adapter.postgres.courier

import com.example.delivery.core.domain.model.aggregate.courier.CourierStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CourierJpaRepository: JpaRepository<CourierJpa, UUID> {

    fun findAllByStatus(status: CourierStatus): List<CourierJpa>
}