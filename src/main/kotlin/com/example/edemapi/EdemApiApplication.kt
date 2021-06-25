package com.example.edemapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class EdemApiApplication

fun main(args: Array<String>) {
    runApplication<EdemApiApplication>(*args)
}
