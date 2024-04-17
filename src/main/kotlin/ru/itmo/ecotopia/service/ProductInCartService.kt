package ru.itmo.ecotopia.service

import ru.itmo.ecotopia.model.requests.ProductInCartRequest
import ru.itmo.ecotopia.model.responses.ProductInCartResponse

interface ProductInCartService {
    fun addProductToCart(productInCartRequest: ProductInCartRequest): ProductInCartResponse
    fun updateProductInCart(productInCartRequest: ProductInCartRequest): ProductInCartResponse
    fun getProductsInCart(): List<ProductInCartResponse>
    fun deleteProductInCart(productInCartRequest: ProductInCartRequest)
}