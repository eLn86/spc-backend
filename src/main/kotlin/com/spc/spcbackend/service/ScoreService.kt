package com.spc.spcbackend.service

import ScoreResponse
import TopTenScoresResponse
import com.spc.spcbackend.dto.ScoreRequest
import com.spc.spcbackend.model.Score
import com.spc.spcbackend.repository.ScoreRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ScoreService(private val scoreRepository: ScoreRepository) {
    fun saveScore(scoreRequest: ScoreRequest): ScoreResponse {
        val score = Score(word = scoreRequest.word, score = scoreRequest.score)
        val savedScore = scoreRepository.save(score)
        return toScoreResponse(savedScore)
    }

    fun getTopTenScores(): TopTenScoresResponse {
        val allScores: List<Score> = scoreRepository.findAll()
        val topTenScores = allScores
            .sortedByDescending { it.score }
            .take(10)
        return toTopTenScoresResponse(topTenScores)
    }

    fun deleteScoreById(id: UUID) {
        return scoreRepository.deleteById(id)
    }

    fun deleteScoresById(ids: List<UUID>) {
        return scoreRepository.deleteAllById(ids)
    }

    private fun toScoreResponse(score: Score): ScoreResponse =
        ScoreResponse(
            id = score.id!!,
            word = score.word,
            score = score.score
        )

    private fun toTopTenScoresResponse(scores: List<Score>): TopTenScoresResponse =
        TopTenScoresResponse(
            scores = scores
        )
}