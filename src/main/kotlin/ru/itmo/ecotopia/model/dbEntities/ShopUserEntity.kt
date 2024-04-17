package ru.itmo.ecotopia.model.dbEntities

import jakarta.persistence.*
import org.springframework.beans.FatalBeanException
import ru.itmo.ecotopia.model.entities.ShopUser
import ru.itmo.ecotopia.model.enums.UserRole
import java.util.*

@Entity
@Table(name = "users")
class ShopUserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    @Column(unique = true)
    private var username: String? = null,
    @Column(unique = true)
    var email: String? = null,
    @Enumerated(EnumType.STRING)
    var role: UserRole? = null,
    private var password: String? = null
) {
    fun toShopUser(): ShopUser = ShopUser (
        id = this.id ?: throw FatalBeanException("user's id is null"),
        username = this.username ?: throw FatalBeanException("user's name is null"),
        email = this.email ?: throw FatalBeanException("user's email is null"),
        role = this.role ?: throw FatalBeanException("user's role is null"),
        password = this.password ?: throw FatalBeanException("user's password is null"),
    )
}