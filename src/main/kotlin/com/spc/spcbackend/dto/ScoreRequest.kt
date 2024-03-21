import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ScoreRequest(
    @field:NotBlank(message = "Word cannot be blank.")
    @field:Size(max = 10, message = "Word must be 10 characters or less.")
    @field:Pattern(regexp = "^[A-Za-z]+$", message = "Word must only contain letters.")
    val word: String,

    @field:NotBlank(message = "Score cannot be blank.")
    @field:Positive(message = "Score must be a positive number greater than zero.")
    val score: Int
)