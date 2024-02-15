package ints.devotion.myapplication.data.repository

import ints.devotion.myapplication.data.network.IssService
import ints.devotion.myapplication.model.error.ResultWrapper
import ints.devotion.myapplication.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IssRepository @Inject constructor(
    private val issService: IssService
) {
    fun fetchApi() =
        flow {
            val api = safeApiCall(Dispatchers.IO) {
                issService.fetchIssPosition()
            }
            when (api) {
                is ResultWrapper.GenericError -> emit(api)
                is ResultWrapper.Loading -> emit(api)
                is ResultWrapper.NetworkError -> emit(api)
                is ResultWrapper.Success<*> -> emit(api)
            }
        }.flowOn(Dispatchers.IO)
}