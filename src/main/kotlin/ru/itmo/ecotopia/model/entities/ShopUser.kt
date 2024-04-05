package ru.itmo.ecotopia.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table("users")
data class User (
    @Id
    val id: UUID,
    val name: String,
    val surname: String,
    @Column(unique = true)
    val email: String,
)