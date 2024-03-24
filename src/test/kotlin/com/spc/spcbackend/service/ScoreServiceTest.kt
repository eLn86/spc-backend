package com.spc.spcbackend.service

import WordAlreadyExistsException
import com.spc.spcbackend.dto.ScoreRequest
import com.spc.spcbackend.model.Score
import com.spc.spcbackend.repository.ScoreRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
        every { scoreRepository.findByWord(any()) } returns Optional.empty()
        every { scoreRepository.save(any()) } returns savedScore

        val returnedSaveScore = scoreService.saveScore(request)

        verify { scoreRepository.save(any()) }
        assertEquals("example", returnedSaveScore.word)
        assertEquals(5, returnedSaveScore.score)
    }

    @Test
    fun `when SaveScore and word exists then throw WordAlreadyExistsException`() {
        val request = ScoreRequest(word = "example", score = 5)
        val savedScore = Score(
            id = UUID.randomUUID(),
            word = request.word,
            score = request.score
        )
        every { scoreRepository.findByWord(any()) } returns Optional.of(
            Score(
                word = "example",
                score = 5,
                id = UUID.randomUUID()
            )
        )
        every { scoreRepository.save(any()) } returns savedScore

        assertThrows<WordAlreadyExistsException> {
            scoreService.saveScore(request)
        }
    }

    @Test
    fun `getTopTenScores returns top ten scores sorted by score descending`() {
        val mockScores = (1..12).map {
            Score(UUID.randomUUID(), "word$it", it)
        }
        every { scoreRepository.findAll() } returns mockScores

        val result = scoreService.getTopTenScores()

        verify { scoreRepository.findAll() }
        assertEquals(10, result.scores.size)
        assertTrue(result.scores[0].score >= result.scores[1].score)
        assertEquals("word12", result.scores[0].word)
        assertEquals("word3", result.scores.last().word)
    }
}