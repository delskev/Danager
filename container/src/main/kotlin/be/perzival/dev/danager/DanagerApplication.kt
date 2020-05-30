package be.perzival.dev.danager

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class DanagerApplication

fun main(args: Array<String?>) {
    SpringApplication.run(DanagerApplication::class.java, *args)
}

