package ru.itmo.ecotopia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.itmo.ecotopia.security.configuration.RsaKeyProperties

@ConfigurationPropertiesScan("ru.itmo.ecotopia.security.configuration")
@SpringBootApplication
class EcotopiaApplication

fun main(args: Array<String>) {
    runApplication<EcotopiaApplication>(*args)
}
