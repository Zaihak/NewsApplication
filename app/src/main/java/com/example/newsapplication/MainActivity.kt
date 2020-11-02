package com.example.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    init {
        Log.d("Start","Main Activity")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val articleFragment = ArticleFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.frameContainer, articleFragment)
                .commit()
        }
    }
}