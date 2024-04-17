package ru.itmo.ecotopia.model.entities

import ru.itmo.ecotopia.model.enums.CategoryProduct
import java.util.UUID

data class Product (
    val id: UUID = UUID.randomUUID(),

    val name: String,

    val description: String?,

    val price: Double,

    val categoryProduct: CategoryProduct,

    val rating: Double,

    val amount: Int
)