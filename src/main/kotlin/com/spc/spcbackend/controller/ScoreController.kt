package com.spc.spcbackend.controller

import com.spc.spcbackend.model.Score
import com.spc.spcbackend.service.ScoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/scores")
class ScoreController(private val scoreService: ScoreService) {
    @PostMapping
    fun saveScore(@RequestBody score: Score): ResponseEntity<Score> =
        ResponseEntity.ok(scoreService.saveScore(score))
}