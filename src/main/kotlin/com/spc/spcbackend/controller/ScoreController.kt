package com.spc.spcbackend.controller

import ScoreResponse
import TopTenScoresResponse
import com.spc.spcbackend.dto.ScoreRequest
import com.spc.spcbackend.service.ScoreService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/scores")
class ScoreController(private val scoreService: ScoreService) {
    @PostMapping
    fun saveScore(@RequestBody @Valid request: ScoreRequest): ResponseEntity<ScoreResponse> =
        ResponseEntity.ok(scoreService.saveScore(request))

    @GetMapping
    fun getTopTenScores(): ResponseEntity<TopTenScoresResponse> =
        ResponseEntity.ok(scoreService.getTopTenScores())
}