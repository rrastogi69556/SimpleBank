package com.rabo.simpleBank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBankApplication

fun main(args: Array<String>) {
	runApplication<SimpleBankApplication>(*args)
}
