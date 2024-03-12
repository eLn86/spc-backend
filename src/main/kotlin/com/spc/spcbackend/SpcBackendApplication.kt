package com.spc.spcbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpcBackendApplication

fun main(args: Array<String>) {
	runApplication<SpcBackendApplication>(*args)
}
