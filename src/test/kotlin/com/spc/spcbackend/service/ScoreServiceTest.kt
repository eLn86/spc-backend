package com.spc.spcbackend.service

import ScoreRequest
import com.spc.spcbackend.model.Score
import com.spc.spcbackend.repository.ScoreRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class ScoreServiceTest {
    @MockK
    lateinit var scoreRepository: ScoreRepository

    @InjectMockKs
    lateinit var scoreService: ScoreService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when SaveScore then Repository save is called and Score object is returned`() {
        val request = ScoreRequest(word = "example", score = 5)
        val savedScore = Score(
            id = UUID.randomUUID(),
            word = request.word,
            score = request.score
        )
        every { scoreRepository.save(any()) } returns savedScore

        val returnedSaveScore = scoreService.saveScore(request)

        verify { scoreRepository.save(any()) }
        assertEquals("example", returnedSaveScore.word)
        assertEquals(5, returnedSaveScore.score)
    }
}