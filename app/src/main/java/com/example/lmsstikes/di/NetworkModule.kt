package com.example.lmsstikes.di

import android.content.Context
import com.example.lmsstikes.util.CacheInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.Cache
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


val NetworkModule = module {
    single { createOkHttpClient(get()) }
}

fun createOkHttpClient(applicationContext: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val cacheInterceptor = CacheInterceptor()

    val httpCacheDirectory = File(applicationContext.cacheDir, "http-cache")
    val cacheSize = 10 * 1024 * 1024 // 10 MiB
    val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(cacheInterceptor)
        .cache(cache)
        .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS))
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .serializeNulls()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setPrettyPrinting()
        .setVersion(1.0)
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("")
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()

    return retrofit.create(T::class.java)
}