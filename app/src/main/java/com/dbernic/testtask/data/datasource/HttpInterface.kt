package com.dbernic.testtask.data.datasource

import com.dbernic.testtask.data.entities.HelloResponse
import retrofit2.Response
import retrofit2.http.GET

interface HttpInterface {

    @GET("mockdata")
    suspend fun getData(): Response<HelloResponse>
}