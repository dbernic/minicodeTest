package com.dbernic.testtask.data.repositories

import com.dbernic.testtask.data.datasource.HttpInterface
import com.dbernic.testtask.data.entities.HelloResponse
import retrofit2.Response
import javax.inject.Inject
import javax.sql.DataSource

class HelloRepository @Inject constructor(
    private val dataSource: HttpInterface
) {
    suspend fun getHello(): Response<HelloResponse> = dataSource.getData()
}