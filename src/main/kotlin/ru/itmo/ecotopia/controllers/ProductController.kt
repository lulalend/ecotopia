package ru.itmo.ecotopia.controllers

import org.springframework.http.HttpStatus
import ru.itmo.ecotopia.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itmo.ecotopia.model.requests.ProductRequest
import ru.itmo.ecotopia.model.responses.ProductResponse
import java.util.UUID

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping
    fun createProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<ProductResponse> =
        ResponseEntity<ProductResponse>(productService.createProduct(productRequest), HttpStatus.CREATED)

    @GetMapping
    fun getProducts(): ResponseEntity<List<ProductResponse>> =
        ResponseEntity.ok(productService.getProducts())

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: UUID) =
        ResponseEntity.ok(productService.getProduct(id))

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: UUID, @RequestBody productRequest: ProductRequest): ResponseEntity<ProductResponse> =
        ResponseEntity.ok(productService.updateProduct(id, productRequest))

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: UUID): ResponseEntity<Unit> =
        ResponseEntity(productService.deleteProduct(id), HttpStatus.NO_CONTENT)
}