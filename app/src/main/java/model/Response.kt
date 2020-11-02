package model


data class ArticleResult(
    val article: List<Article>
)

data class Source(
    val id: Any,
    val name: String
)

data class Article(

    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val source: Source
)


//
//data class ArticleResult(val articles: List<Article>)
//
//data class Source(val id: Any, val name: String)
//
//data class Article(

//)
