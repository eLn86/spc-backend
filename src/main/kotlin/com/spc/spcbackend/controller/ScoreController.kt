package com.spc.spcbackend.controller

import TopTenScoresResponse
import WordAlreadyExistsException
import com.spc.spcbackend.dto.ScoreRequest
import com.spc.spcbackend.service.ScoreService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/api/scores")
class ScoreController(private val scoreService: ScoreService) {
    @PostMapping
    fun saveScore(@RequestBody @Valid request: ScoreRequest): ResponseEntity<Any> {
        return try {
            val savedScoreResponse = scoreService.saveScore(request)
            ResponseEntity.ok(savedScoreResponse)
        } catch (ex: WordAlreadyExistsException) {
            ResponseEntity.status(HttpStatus.CONFLICT)
                .body(mapOf("error" to "Word already exists, save not successful."))
        }
    }

    @GetMapping
    fun getTopTenScores(): ResponseEntity<TopTenScoresResponse> =
        ResponseEntity.ok(scoreService.getTopTenScores())

    @DeleteMapping("/{id}")
    fun deleteScoreById(@PathVariable id: UUID): ResponseEntity<Void> {
        scoreService.deleteScoreById(id)
        return ResponseEntity.noContent().build<Void>()
    }

    @DeleteMapping("/delete")
    fun deleteScoresById(@RequestBody ids: List<UUID>): ResponseEntity<Void> {
        scoreService.deleteScoresById(ids)
        return ResponseEntity.noContent().build<Void>()
    }
}