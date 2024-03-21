package com.spc.spcbackend.dto

import jakarta.validation.constraints.*

data class ScoreRequest(
    @field:NotBlank(message = "Word cannot be blank.")
    @field:Size(max = 10, message = "Word must be 10 characters or less.")
    @field:Pattern(regexp = "^[A-Za-z]+$", message = "Word must only contain letters.")
    val word: String,

    @field:NotNull(message = "Score must be provided.")
    @field:Positive(message = "Score must be a positive number greater than zero.")
    val score: Int
)