package com.transportcompany.transport_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class TransportAppApplication

fun main(args: Array<String>) {
	runApplication<TransportAppApplication>(*args)
}
