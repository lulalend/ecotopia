package ru.itmo.ecotopia.model.entities

import ru.itmo.ecotopia.model.enums.UserRole
import java.util.UUID

data class ShopUser (
    val id: UUID,
    val username: String,
    val email: String,
    val role: UserRole,
    val password: String
)