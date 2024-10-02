package com.victorsouza19.buzznow.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victorsouza19.buzznow.api.models.Article
import com.victorsouza19.buzznow.api.models.ArticleData
import com.victorsouza19.buzznow.constants.API_KEY
import com.victorsouza19.buzznow.constants.DEFAULT_COUNTRY
import com.victorsouza19.buzznow.constants.DEFAULT_LANGUAGE
import com.victorsouza19.buzznow.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MainViewModel constructor(private val repository: MainRepository): ViewModel() {
    val liveList = MutableLiveData<List<Article>>()
    val errorMessage = MutableLiveData<String>()

    fun getEverything(){
        val map = HashMap<String, String>()
        map["apiKey"] = API_KEY
        map["q"] = DEFAULT_COUNTRY
        map["language"] = DEFAULT_LANGUAGE


        val request = repository.getEverything(map)
        request.enqueue(object: Callback<ArticleData>{
            override fun onResponse(call: Call<ArticleData>, response: Response<ArticleData>) {
                if(response.isSuccessful && response.body() != null){
                    val articleList = response.body()?.articles
                    liveList.postValue(articleList)
                } else {
                    errorMessage.postValue(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ArticleData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}