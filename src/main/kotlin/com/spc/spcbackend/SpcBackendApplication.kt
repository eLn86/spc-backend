package com.spc.spcbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SpcBackendApplication

fun main(args: Array<String>) {
	runApplication<SpcBackendApplication>(*args)
}
