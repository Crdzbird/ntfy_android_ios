package ints.devotion.myapplication.data.network

import ints.devotion.myapplication.model.IssNow
import retrofit2.http.GET

interface IssService {
    @GET(HttpInterceptor.ISS_NOW)
    suspend fun fetchIssPosition(): IssNow
}
