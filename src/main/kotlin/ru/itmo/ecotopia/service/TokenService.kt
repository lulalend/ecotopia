package ru.itmo.ecotopia.service

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSObject
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import ru.itmo.ecotopia.security.BearerTokenUserAuthentication
import ru.itmo.ecotopia.security.configuration.RsaKeyProperties
import java.time.Duration
import java.time.Instant
import java.util.*


@Service
class TokenService(
    private val rsaKeyProperties: RsaKeyProperties
) {
    private val rsaSsaSigner: RSASSASigner = RSASSASigner(rsaKeyProperties.privateKey)
    private val rsaSsaVerifier: RSASSAVerifier = RSASSAVerifier(rsaKeyProperties.publicKey)
    fun generateToken(authentication: Authentication): String {
        val currentTime = Instant.now()
        val expirationTime = Date.from(currentTime.plus(Duration.ofHours(12)))
        return SignedJWT(
            jwsHeader,
            JWTClaimsSet.Builder()
                .claim(PERMISSIONS_CLAIM_KEY, authentication.authorities.map { it.authority })
                .subject(authentication.name)
                .issueTime(Date.from(currentTime))
                .issuer("ecotopia")
                .expirationTime(expirationTime)
                .build()
        )
            .apply { sign(rsaSsaSigner) }.serialize()
    }

    fun verifyTokenAndGetAuthentication(token: String): Authentication {
        val jwsObject = verifyAndGetJws(token)
        val claimsSet = JWTClaimsSet.parse(jwsObject.payload.toJSONObject())
        val expirationTime = claimsSet.expirationTime.toInstant()
        if (Instant.now().isAfter(expirationTime)) {
            throw Exception("Token has been expired")
        }
        return BearerTokenUserAuthentication(
            username = claimsSet.subject,
            permissions = claimsSet.getStringListClaim(PERMISSIONS_CLAIM_KEY)
                .map {
                    SimpleGrantedAuthority(it)
                     },
            token = token,
        )
    }

    private fun verifyAndGetJws(token: String): JWSObject {
        val jwsObject: JWSObject
        val isValid: Boolean
        try {
            jwsObject = JWSObject.parse(token)
            isValid = jwsObject.verify(rsaSsaVerifier)
        } catch (exception: Exception) {
            throw Exception("An exception occurred while verifying token", exception)
        }
        if (!isValid) {
            throw Exception("Token is not verified")
        }
        return jwsObject
    }

    companion object {
        private val jwsHeader: JWSHeader = JWSHeader.Builder(JWSAlgorithm.RS256).build()
        private const val PERMISSIONS_CLAIM_KEY = "permissions"
    }

}