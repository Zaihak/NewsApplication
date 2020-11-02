package com.example.newsapplication

import adapter.ArticleListAdapter
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_article.*
import model.ArticleResult
import viewmodel.ArticleViewModel


class ArticleFragment : Fragment() {
init {
    Log.d("Start","ArticleFragment")
}
    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var articleViewModel: ArticleViewModel 

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager=LinearLayoutManager(activity)
        recyclerView.layoutManager=viewManager
        articleListAdapter=ArticleListAdapter()
        recyclerView.adapter=articleListAdapter
       observeViewModel()
    }

    fun observeViewModel(){
        articleViewModel= ViewModelProviders
            .of(this)
            .get(ArticleViewModel::class.java)


        articleViewModel.getResults().observe(this,
            Observer<ArticleResult> { result->
            recyclerView.visibility=View.VISIBLE
            articleListAdapter.updateList(result.article)
                Log.d("API",result.article.toString())
        })

        articleViewModel.getError().observe(this, Observer<Boolean> {iserror->
            if (iserror){
                txtError.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }else{
                txtError.visibility = View.GONE
            }

        })

        articleViewModel.getLoading().observe(this, Observer<Boolean>{isloading->
            loadingView.visibility = (if (isloading)
                View.VISIBLE else View.INVISIBLE)
            if (isloading) {
                txtError.visibility = View.GONE
                recyclerView.visibility = View.GONE
            }
        })

    }

    override fun onResume() {
        super.onResume()
        articleViewModel.loadResult()
    }


}

