package com.spc.spcbackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ScoreControllerTest(@Autowired val mockMvc: MockMvc) {
    @Test
    fun `when Post score then Save score`() {
        val content = mapOf("word" to "test", "score" to 10)
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(content)

        mockMvc.perform(
            post("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.word").value("test"))
            .andExpect(jsonPath("$.score").value(10))
    }
}
