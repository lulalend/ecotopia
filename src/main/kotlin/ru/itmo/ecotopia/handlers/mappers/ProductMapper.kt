package ru.itmo.ecotopia.handlers.mappers

import org.springframework.stereotype.Service
import ru.itmo.ecotopia.model.entities.Product
import ru.itmo.ecotopia.model.entities.ProductEntity
import ru.itmo.ecotopia.model.enums.CategoryProduct
import ru.itmo.ecotopia.model.requests.ProductRequest
import ru.itmo.ecotopia.model.responses.ProductResponse
import java.util.*

@Service
class ProductMapper: Mapper<ProductEntity, Product, ProductRequest, ProductResponse> {
    override fun toEntity(domain: ProductRequest): ProductEntity = ProductEntity (
        null,
        domain.name,
        domain.description,
        domain.price,
        domain.categoryProduct,
        domain.rating,
        domain.amount
    )
    override fun fromEntity(entity: Product): ProductResponse = ProductResponse (
        entity.id,
        entity.name,
        entity.description,
        entity.price,
        entity.categoryProduct,
        entity.rating,
        entity.amount
    )
}