package ints.devotion.myapplication.model

sealed class ViewState {
    data object Loading : ViewState()
    data class Success(val success: Any) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}
