package com.rachellimaa.config.network

import com.rachellimaa.config.di.ModuleProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rachellimaa.config.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val RETROFIT_QUALIFIER = named("RetrofitQualifier")

class NetworkModule: ModuleProvider {
    override val module: Module by lazy {
        module {
            single(RETROFIT_QUALIFIER) {
                val json = Json { ignoreUnknownKeys = true }
                val factory = json.asConverterFactory("application/json".toMediaType())
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(factory)
                    .build()
            }
        }
    }
}