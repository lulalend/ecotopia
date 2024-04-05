package ru.itmo.ecotopia.model.requests

import ru.itmo.ecotopia.model.enums.CategoryProduct

data class CreateProductRequest (
    var name: String,
    var description: String,
    var price: Double,
    var categoryProduct: CategoryProduct,
    var rating: Double,
    var amount: Int
)