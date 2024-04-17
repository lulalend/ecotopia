package ru.itmo.ecotopia.model.entities

import jakarta.persistence.*
import org.springframework.beans.FatalBeanException
import ru.itmo.ecotopia.model.enums.CategoryProduct
import java.util.UUID

@Entity
@Table(name="products")
data class ProductEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(unique = true)
    var name: String? = null,

    var description: String? = null,

    var price: Double? = null,

    @Enumerated(EnumType.STRING)
    var categoryProduct: CategoryProduct? = null,

    var rating: Double? = null,

    var amount: Int? = null
) {
    fun toProduct(): Product = Product (
        id = this.id ?: throw FatalBeanException("product's id is null"),
        name = this.name ?: throw FatalBeanException("product's name is null"),
        description = this.description,
        price = this.price ?: throw FatalBeanException("product's price is null"),
        categoryProduct = this.categoryProduct ?: throw FatalBeanException("product's category is null"),
        rating = this.rating ?: throw FatalBeanException("product's rating is null"),
        amount = this.amount ?: throw FatalBeanException("product's amount is null"),
    )
}