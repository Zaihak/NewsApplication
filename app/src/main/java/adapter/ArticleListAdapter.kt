package adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items_article.view.*
import model.Article

class ArticleListAdapter(var articleList: List<Article> = ArrayList())
    : RecyclerView.Adapter<ArticleListAdapter.ArtilceViewHolder>() {
    init {
        Log.d("Start","ArticleListAdapter")

    }
    class ArtilceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var view:View= itemView
        private lateinit var article: Article

        fun bindArticle(article : Article){
            Log.d("API","DataBinding")

            this.article =article
            Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.loading)
                .into(view.articleImage)
            view.ArticleTitle.text=article.title
            view.ArticleDescription.text=article.description
            view.ArticleDate.text=article.publishedAt
            Log.d("API","DataBinding")

        }
    }

    fun updateList(article: List<Article>) {
        this.articleList = article
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtilceViewHolder {
            var view= LayoutInflater.from(parent.context)
                      .inflate(R.layout.items_article,parent,false)
        Log.d("ARTICLEList","Hello")
        return ArtilceViewHolder(view)
    }


    override fun onBindViewHolder(holder: ArtilceViewHolder, position: Int) {
        holder.bindArticle(articleList[position])
       Log.d("ARTICLEList","Hello")


    }

    override fun getItemCount(): Int {
        Log.d("API",articleList.size.toString())
        return articleList.size
    }
//    fun bindArticle(article: Article) {
//        this.article = article
//        Picasso.get()
//            .load(article.urlToImage)
//            .placeholder(R.drawable.loading)
//            .into(view.articleImage)
//        view.articleTitle.text = article.title
//        view.articleDescription.text = article.description
//        view.articleDate.text = toSimpleString(article.publishedAt)
//    }


}