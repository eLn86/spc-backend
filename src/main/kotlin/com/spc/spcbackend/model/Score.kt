package com.spc.spcbackend.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
class Score(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    val word: String,

    val score: Int
)