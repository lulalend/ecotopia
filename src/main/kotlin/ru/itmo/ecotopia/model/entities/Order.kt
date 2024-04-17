package ru.itmo.ecotopia.model.entities

import ru.itmo.ecotopia.model.enums.StatusOrder
import java.time.Instant
import java.util.*

data class Order (
    val id: UUID,
    val date: Instant,
    val status: StatusOrder,
    val totalCost: Double,
    val user: ShopUser
)