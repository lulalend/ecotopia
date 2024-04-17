package ru.itmo.ecotopia.service

import org.springframework.stereotype.Service
import ru.itmo.ecotopia.handlers.mappers.ProductInCartMapper
import ru.itmo.ecotopia.model.ids.ProductCartId
import ru.itmo.ecotopia.model.requests.ProductInCartRequest
import ru.itmo.ecotopia.model.requests.ProductRequest
import ru.itmo.ecotopia.model.responses.ProductInCartResponse
import ru.itmo.ecotopia.repositories.CartRepository
import ru.itmo.ecotopia.repositories.ProductInCartRepository
import ru.itmo.ecotopia.repositories.ProductRepository

@Service
class ProductInCartServiceImpl (
    private val productInCartRepository: ProductInCartRepository,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val productInCartMapper: ProductInCartMapper,
    private val productService: ProductService
): ProductInCartService {
    override fun addProductToCart(productInCartRequest: ProductInCartRequest): ProductInCartResponse {
        val productCartId = ProductCartId (
            productId = productInCartRequest.productId,
            cartId = productInCartRequest.cartId
        )
        val exists = productInCartRepository.existsById(productCartId)
        if (exists)
            throw IllegalArgumentException("this entity already exists");

        val productInCartEntity = productInCartMapper.toEntity(productInCartRequest)
        val product = productService.getProduct(productInCartRequest.productId)

        if (product.amount >= 1) {
            productInCartEntity.amount = 1
            val productDTO = ProductRequest(
                name = product.name,
                description = product.description,
                price = product.price,
                categoryProduct = product.categoryProduct,
                rating = product.rating,
                amount = product.amount - 1
            )
            productInCartRepository.save(productInCartEntity)
            productService.updateProduct(
                productId = product.id,
                productRequest = productDTO
            )
        } else
            throw IllegalArgumentException("this product is out of stock")

        return productInCartMapper.fromEntity(productInCartEntity.toProductInCart())
    }

    override fun getProductsInCart(): List<ProductInCartResponse> {
        val productInCartIterable = productInCartRepository.findAll()
        val productsInCart = mutableListOf<ProductInCartResponse>()
        productInCartIterable.forEach {
            productsInCart.add(productInCartMapper.fromEntity(it.toProductInCart()))
        }
        return productsInCart
    }

    override fun updateProductInCart(productInCartRequest: ProductInCartRequest): ProductInCartResponse {
        cartRepository.findById(productInCartRequest.cartId).orElseThrow {
            IllegalArgumentException("Cart with id ${productInCartRequest.cartId} is not present")
        }

        productRepository.findById(productInCartRequest.productId).orElseThrow {
            IllegalArgumentException("Product with id ${productInCartRequest.productId} is not present")
        }

        val productCartId = ProductCartId (
            productId = productInCartRequest.productId,
            cartId = productInCartRequest.cartId
        )

        val currentProductInCart = productInCartRepository.findById(productCartId).orElseThrow()
        val product = productService.getProduct(productInCartRequest.productId)

        if (product.amount >= 1) {
            currentProductInCart.amount = currentProductInCart.amount?.plus(1)
            val productDTO = ProductRequest(
                name = product.name,
                description = product.description,
                price = product.price,
                categoryProduct = product.categoryProduct,
                rating = product.rating,
                amount = product.amount - 1
            )
            productInCartRepository.save(currentProductInCart)
            productService.updateProduct(
                productId = product.id,
                productRequest = productDTO
            )
        }

        return productInCartMapper.fromEntity(currentProductInCart.toProductInCart())
    }

    override fun deleteProductInCart(productInCartRequest: ProductInCartRequest) {
        val productCartId = ProductCartId (
            productId = productInCartRequest.productId,
            cartId = productInCartRequest.cartId
        )
        val exists = productInCartRepository.existsById(productCartId)

        if (!exists)
            throw IllegalArgumentException("check ids")

        productInCartRepository.deleteById(productCartId)
    }
}