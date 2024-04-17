package ru.itmo.ecotopia.model.entities

import java.util.UUID

data class Cart(
    val id: UUID,
    val user: ShopUser,
)
