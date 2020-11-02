package viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import api.ArticleApi
import model.ArticleResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel : ViewModel() {
    init {
        Log.d("Start", "ArticleViewModel")

    }

    val results: MutableLiveData<ArticleResult> = MutableLiveData()
    var articleLoadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getResults(): LiveData<ArticleResult> = results

    fun getError(): LiveData<Boolean> = articleLoadError

    fun getLoading(): LiveData<Boolean> = loading


    private val articleApi = ArticleApi()
    fun loadResult() {
        loading.value = true
        val call = articleApi.getResult()
        call.enqueue(object : Callback<ArticleResult> {
            override fun onResponse(call: Call<ArticleResult>, response: Response<ArticleResult>) {
                response?.isSuccessful.let {
                    loading.value = false
                    val resultList = ArticleResult(
                        response?.body()?.article ?: emptyList()
                    )
                    results.value = resultList
                    Log.d("APICall", response.body().toString())

                }

            }

            override fun onFailure(call: Call<ArticleResult>, t: Throwable) {
                articleLoadError.value = true
                loading.value = false

            }

        })


    }


}