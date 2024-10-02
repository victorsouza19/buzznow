package com.victorsouza19.buzznow.repositories

import com.victorsouza19.buzznow.api.RetrofitService
import java.util.HashMap

class MainRepository(private val retrofitService: RetrofitService) {

    fun getEverything(options: HashMap<String, String>) = retrofitService.getEverything(options)
    fun getTopHeadlines(options: HashMap<String, String>) = retrofitService.getTopHeadlines(options)

}