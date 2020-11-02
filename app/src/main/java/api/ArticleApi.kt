package api

import android.util.Log
import model.ArticleResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ArticleApi {
    private val articleInterface: ArticleInterface

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        articleInterface = retrofit.create(
            ArticleInterface::class.java
        )
    }

    fun getResult(): Call<ArticleResult> {
        return articleInterface.getArticles()
    }


}