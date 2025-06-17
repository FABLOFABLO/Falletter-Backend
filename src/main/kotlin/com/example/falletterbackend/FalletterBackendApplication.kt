package com.example.falletterbackend

import com.example.falletterbackend.common.security.TokenProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(TokenProperties::class)
@SpringBootApplication
class FalletterBackendApplication

fun main(args: Array<String>) {
	runApplication<FalletterBackendApplication>(*args)
}
