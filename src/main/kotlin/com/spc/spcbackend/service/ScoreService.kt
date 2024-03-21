package com.spc.spcbackend.service

import ScoreRequest
import ScoreResponse
import com.spc.spcbackend.model.Score
import com.spc.spcbackend.repository.ScoreRepository
import org.springframework.stereotype.Service

@Service
class ScoreService(private val scoreRepository: ScoreRepository) {
    fun saveScore(scoreRequest: ScoreRequest): ScoreResponse {
        val score = Score(word = scoreRequest.word, score = scoreRequest.score)
        val savedScore = scoreRepository.save(score)
        return toScoreResponse(savedScore)
    }

    private fun toScoreResponse(score: Score): ScoreResponse =
        ScoreResponse(
            id = score.id!!,
            word = score.word,
            score = score.score
        )
}