package com.victorsouza19.buzznow.api

import com.victorsouza19.buzznow.api.models.ArticleData
import com.victorsouza19.buzznow.constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

interface RetrofitService {

    // ?country=us&apiKey=API_KEY
    @GET("top-headlines")
    fun getTopHeadlines(@QueryMap options: HashMap<String, String>): Call<ArticleData>

    // ?q=tesla&from=2024-08-03&sortBy=publishedAt&apiKey=API_KEY
    @GET("everything")
    fun getEverything(@QueryMap options: HashMap<String, String>): Call<ArticleData>

    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(3,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)

        }.build()

        private val retrofitService : RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService {
            return retrofitService
        }
    }

}