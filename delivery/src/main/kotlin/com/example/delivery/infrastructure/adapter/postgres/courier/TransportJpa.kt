package com.example.delivery.infrastructure.adapter.postgres.courier

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
class TransportJpa(
    @Id
    val id: UUID,
    val name: String,
    val speed: Int
)