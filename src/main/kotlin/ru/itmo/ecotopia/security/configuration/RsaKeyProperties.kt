package ru.itmo.ecotopia.security.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ConfigurationProperties(prefix = "rsa")
data class RsaKeyProperties (
    val publicKey: RSAPublicKey,
    val privateKey: RSAPrivateKey,

)