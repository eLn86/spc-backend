package com.spc.spcbackend.repository

import com.spc.spcbackend.model.Score
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ScoreRepository : JpaRepository<Score, UUID> {
    fun findByWord(word: String): Optional<Score>
}