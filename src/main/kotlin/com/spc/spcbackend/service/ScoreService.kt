package com.spc.spcbackend.service

import com.spc.spcbackend.model.Score
import com.spc.spcbackend.repository.ScoreRepository
import org.springframework.stereotype.Service

@Service
class ScoreService(private val scoreRepository: ScoreRepository) {
    fun saveScore(score: Score) = scoreRepository.save(score)
}