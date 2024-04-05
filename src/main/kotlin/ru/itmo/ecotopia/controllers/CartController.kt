package ru.itmo.ecotopia.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itmo.ecotopia.model.requests.ProductRequest
import ru.itmo.ecotopia.model.responses.ProductResponse
import java.util.*

class CartRepository {
    @PostMapping
    fun createProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<ProductResponse> =
        ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED)


}