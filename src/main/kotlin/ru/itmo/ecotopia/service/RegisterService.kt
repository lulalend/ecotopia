package ru.itmo.ecotopia.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.itmo.ecotopia.model.dbEntities.ShopUserEntity
import ru.itmo.ecotopia.model.entities.ShopUser
import ru.itmo.ecotopia.model.enums.UserRole
import ru.itmo.ecotopia.model.requests.SignUpRequest

@Service
class RegisterService(
    private val shopUserService: ShopUserService,
    private val passwordEncoder: PasswordEncoder,
) {

//    @Transactional
    fun registerNewUser(request: SignUpRequest): ShopUser {
        val encodedPassword = passwordEncoder.encode(request.password)
        val newUser = ShopUserEntity(
            username = request.username,
            password = encodedPassword,
            email = request.email,
            role = UserRole.CUSTOMER
            )
        return shopUserService.createUser(newUser)
    }
}