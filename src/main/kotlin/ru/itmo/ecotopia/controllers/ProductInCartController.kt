package ru.itmo.ecotopia.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itmo.ecotopia.model.requests.ProductInCartRequest
import ru.itmo.ecotopia.model.requests.ProductRequest
import ru.itmo.ecotopia.model.responses.ProductInCartResponse
import ru.itmo.ecotopia.model.responses.ProductResponse
import ru.itmo.ecotopia.service.ProductInCartService
import java.util.*

@RestController
@RequestMapping("/api/product_in_cart")
class ProductInCartController (
    private val productInCartService: ProductInCartService
) {
    @PostMapping
    fun createProduct(@RequestBody productInCartRequest: ProductInCartRequest): ResponseEntity<ProductInCartResponse> =
        ResponseEntity<ProductInCartResponse>(productInCartService.addProductToCart(productInCartRequest), HttpStatus.CREATED)

    @GetMapping
    fun getProductsInCart(): ResponseEntity<List<ProductInCartResponse>> =
        ResponseEntity.ok(productInCartService.getProductsInCart())

    @PutMapping
    fun updateProduct(@RequestBody productInCartRequest: ProductInCartRequest):
            ResponseEntity<ProductInCartResponse> =
        ResponseEntity.ok(productInCartService.updateProductInCart(productInCartRequest))

    @DeleteMapping
    fun deleteProduct(@RequestBody productInCartRequest: ProductInCartRequest): ResponseEntity<Unit> =
        ResponseEntity(productInCartService.deleteProductInCart(productInCartRequest), HttpStatus.NO_CONTENT)
}