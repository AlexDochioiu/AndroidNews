package com.github.alexdochioiu.news.model

import com.squareup.moshi.Json

/**
 * Created by Alex Dochioiu on 22/03/2019
 */
/**
 * [source] info for article's source
 * [author] of article
 * [title] of article
 * [description] or snippet of article
 * [url] to the article
 * [urlToImage] url for relevant image of the article
 * [publishedAt] The date and time that the article was published, in UTC (+000)
 * [content] The unformatted content of the article, where available. This is truncated to 260 chars for Developer plan users.
 */
data class ArticleModel(
    @field:Json(name = "source") val source: Source,
    @field:Json(name = "author") val author: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "urlToImage") val urlToImage: String,
    @field:Json(name = "publishedAt") val publishedAt: String,
    @field:Json(name = "content") val content: String
) {
    /**
     * [id] of article's publisher
     * [name] of article's publisher
     */
    data class Source(
        @field:Json(name = "id") val id: String,
        @field:Json(name = "name") val name: String
    )
}