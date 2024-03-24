package com.spc.spcbackend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.spc.spcbackend.model.Score
import com.spc.spcbackend.repository.ScoreRepository
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class ScoreControllerTest(@Autowired val mockMvc: MockMvc) {
    @Autowired
    private val scoreRepository: ScoreRepository? = null

    @Test
    fun `when Post score then Save score`() {
        scoreRepository?.deleteAll()
        val content = mapOf("word" to "scrupulous", "score" to 16)
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(content)

        mockMvc.perform(
            post("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.word").value("scrupulous"))
            .andExpect(jsonPath("$.score").value(16))
    }

    @Test
    fun `when Post empty score then throw bad request with error`() {
        val content = mapOf("word" to "", "score" to 10)
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(content)

        mockMvc.perform(
            post("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `when Post score with more than 10 words then throw bad request with error`() {
        val content = mapOf("word" to "morethantenwordsword", "score" to 10)
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(content)

        mockMvc.perform(
            post("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())
    }

    @Test
    fun `when Post score that already exists throw WordAlreadyExists error`() {
        val content = mapOf("word" to "scrupulous", "score" to 16)
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(content)

        mockMvc.perform(
            post("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.error", `is`("Word already exists, save not successful.")))
    }

    @Test
    fun `getScores endpoint returns scores`() {
        mockMvc.perform(
            get("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.scores", hasSize<Array<Score>>(1)))
            .andExpect(jsonPath("$.scores[0].word", `is`("scrupulous")))
            .andExpect(jsonPath("$.scores[0].score", `is`(16)))
    }
}
