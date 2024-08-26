package org.meogo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MeogoBackendApplication

fun main(args: Array<String>) {
    runApplication<MeogoBackendApplication>(*args)
}
