package com.dbernic.testtask.di

import com.dbernic.testtask.data.Constants
import com.dbernic.testtask.data.datasource.HttpInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@InternalCoroutinesApi
object RestModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().apply {
            setLenient()
            setPrettyPrinting()
        }.create()
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
    ): Retrofit.Builder {
        return Retrofit.Builder().apply {
            client(okHttpClient)
            addConverterFactory(gsonFactory)
        }
    }

    @Singleton
    @Provides
    fun provideHttpInterface(retrofitBuilder: Retrofit.Builder) : HttpInterface {
        return retrofitBuilder
            .baseUrl(Constants.URL)
            .build()
            .create(HttpInterface::class.java)
    }


}