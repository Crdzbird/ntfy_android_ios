package ints.devotion.myapplication.model.error

data class ErrorResponse(
    val code: Int,
    val message: String,
    val data: Any
)