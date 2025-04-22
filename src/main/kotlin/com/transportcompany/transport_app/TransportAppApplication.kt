package com.transportcompany.transport_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

//@EnableWebSecurity
@SpringBootApplication
class TransportAppApplication

fun main(args: Array<String>) {
	runApplication<TransportAppApplication>(*args)
}
