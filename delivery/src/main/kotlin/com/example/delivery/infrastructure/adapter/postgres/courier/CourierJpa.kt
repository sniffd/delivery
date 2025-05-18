package com.example.delivery.infrastructure.adapter.postgres.courier

import com.example.delivery.core.domain.model.aggregate.courier.CourierStatus
import com.example.delivery.core.domain.sharedkernel.Location
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.util.*

@Entity
class CourierJpa(
    @Id
    val id: UUID,
    val name: String,
    @OneToOne
    val transport: TransportJpa,
    @Embedded
    var location: Location,
    var status: CourierStatus
)