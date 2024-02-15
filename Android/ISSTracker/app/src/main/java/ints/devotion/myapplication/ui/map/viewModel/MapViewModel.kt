package ints.devotion.myapplication.ui.map.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ints.devotion.myapplication.data.repository.IssRepository
import ints.devotion.myapplication.model.IssNow
import ints.devotion.myapplication.model.ViewState
import ints.devotion.myapplication.model.error.ResultWrapper
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val issRepository: IssRepository,
): ViewModel() {
    private val _mapState = MutableStateFlow<ViewState>(ViewState.Loading)
    val mapState = _mapState.asStateFlow()

    private var fetchInterval: Long = 5000
    private var fetchJob: Job? = null

    init {
        startPeriodicFetch(fetchInterval)
    }

    private fun startPeriodicFetch(interval: Long) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            flow {
                while (currentCoroutineContext().isActive) {
                    emit(Unit)
                    delay(interval)
                }
            }.collect {
                fetchCoordinates()
            }
        }
    }

    private fun fetchCoordinates() = viewModelScope.launch{
        issRepository.fetchApi().distinctUntilChanged().collect{
            when(it) {
                is ResultWrapper.GenericError -> {
                    _mapState.value = ViewState.Error(exception = Exception("Error on map fetch"))
                }
                is ResultWrapper.Success<*> -> {
                    val resultWrapper = it.value as IssNow
                    _mapState.value = ViewState.Success(success = resultWrapper)
                }
                else -> {
                    _mapState.value = ViewState.Error(exception = Exception("Error on map fetch"))
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchJob?.cancel()
    }
}