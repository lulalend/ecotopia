package ru.itmo.ecotopia.service

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import ru.itmo.ecotopia.handlers.mappers.ProductMapper
import ru.itmo.ecotopia.model.entities.ProductEntity
import ru.itmo.ecotopia.model.requests.ProductRequest
import ru.itmo.ecotopia.model.responses.ProductResponse
import ru.itmo.ecotopia.repositories.ProductRepository
import java.util.UUID

@Service
class ProductServiceImpl (
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper
): ProductService {
    override fun createProduct(productRequest: ProductRequest): ProductResponse {
        val productEntity = productMapper.toEntity(productRequest)
        productRepository.save(productEntity)

        return productMapper.fromEntity(productEntity.toProduct())
    }

    override fun getProducts(): List<ProductResponse> {
        val productIterable = productRepository.findAll()
        val products = mutableListOf<ProductResponse>()
        productIterable.forEach {
            products.add(productMapper.fromEntity(it.toProduct()))
        }
        return products
    }

    override fun getProduct(id: UUID): ProductResponse {
        val optionalProduct = productRepository.findById(id)
        val productEntity = optionalProduct.orElseThrow {
            IllegalArgumentException("Product with id $id is not present")
        }
        return productMapper.fromEntity(productEntity.toProduct())
    }

    override fun updateProduct(productId: UUID, productRequest: ProductRequest): ProductResponse {
        val exists = productRepository.existsById(productId)

        if (!exists)
            throw IllegalArgumentException("Product with id $productId is not present")


        val productEntityOld = productRepository.findById(productId).orElseThrow()
        productEntityOld.name = productRequest.name;
        productEntityOld.description = productRequest.description;
        productEntityOld.price = productRequest.price;
        productEntityOld.categoryProduct = productRequest.categoryProduct;
        productEntityOld.rating = productRequest.rating;
        productEntityOld.amount = productRequest.amount;

        productRepository.save(productEntityOld)

        return productMapper.fromEntity(productEntityOld.toProduct())
    }

    override fun deleteProduct(id: UUID) {
        val exists = productRepository.existsById(id)

        if (!exists)
            throw IllegalArgumentException("Product with id $id is not present")

        productRepository.deleteById(id)
    }
}