package ru.itmo.ecotopia.service

import ru.itmo.ecotopia.model.requests.ProductRequest
import ru.itmo.ecotopia.model.responses.ProductResponse
import java.util.UUID

interface ProductService {
    fun createProduct(productRequest: ProductRequest): ProductResponse
    fun getProducts(): List<ProductResponse>
    fun getProduct(id: UUID): ProductResponse
    fun updateProduct(productId: UUID, productRequest: ProductRequest): ProductResponse
    fun deleteProduct(id: UUID)
}