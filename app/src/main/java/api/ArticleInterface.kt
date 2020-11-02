package api

import model.ArticleResult as ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface ArticleInterface {

    @Headers("X-Api-Key: e5a7abe04cb44e41843fc49f810f6591")
    @GET("v2/top-headlines?country=us")
    fun getArticles(): Call<ArticleResult>


}