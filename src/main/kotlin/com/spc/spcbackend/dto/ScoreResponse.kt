import java.util.*

data class ScoreResponse(
    val id: UUID,
    val word: String,
    val score: Int
)