package org.meogo

import org.meogo.global.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(JwtProperties::class)
@SpringBootApplication(scanBasePackages = [ "org.meogo"])
class MeogoBackendApplication

fun main(args: Array<String>) {
    runApplication<MeogoBackendApplication>(*args)
}
