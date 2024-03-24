package com.spc.spcbackend

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class SpcBackendApplicationTests {
    @Test
    fun contextLoads(context: ApplicationContext) {
        assertThat(context).isNotNull()
    }
}
