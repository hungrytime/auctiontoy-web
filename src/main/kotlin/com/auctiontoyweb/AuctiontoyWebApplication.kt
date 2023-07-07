package com.auctiontoyweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AuctiontoyWebApplication

fun main(args: Array<String>) {
	runApplication<AuctiontoyWebApplication>(*args)
}
