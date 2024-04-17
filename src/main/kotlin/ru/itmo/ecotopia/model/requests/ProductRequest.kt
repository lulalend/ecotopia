package ru.itmo.ecotopia.model.requests

import ru.itmo.ecotopia.model.enums.CategoryProduct

data class ProductRequest (
    val name: String,
    val description: String?,
    val price: Double,
    val categoryProduct: CategoryProduct,
    val rating: Double,
    val amount: Int
)