package com.spc.spcbackend.service

import com.spc.spcbackend.model.Score
import com.spc.spcbackend.repository.ScoreRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class ScoreServiceTest {
    @Mock
    lateinit var scoreRepository: ScoreRepository

    @InjectMocks
    lateinit var scoreService: ScoreService

    @Test
    fun `when SaveScore then Repository save is called and Score object is returned`() {
        val score = Score(word = "example", score = 5)
        whenever(scoreRepository.save(score)).thenReturn(score)

        val savedScore = scoreService.saveScore(score)

        verify(scoreRepository).save(score)
        assertEquals("example", savedScore.word)
        assertEquals(5, savedScore.score)
    }
}