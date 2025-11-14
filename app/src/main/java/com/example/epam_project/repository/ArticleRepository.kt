package com.example.epam_project.repository

import android.util.Log
import com.example.epam_project.data.Article
import com.example.epam_project.data.ArticleDao
import com.example.epam_project.network.ArticleApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val api: ArticleApiService,
    private val dao: ArticleDao
) {
    fun getArticles(): Flow<List<Article>> = flow {
        try {
            Log.d("ArticleRepository", "Fetching from API...")
            val response = withContext(Dispatchers.IO) {
                api.getArticles(50, 0).execute()
            }

            Log.d("ArticleRepository", "Executed, success=${response.isSuccessful}")

            if (response.isSuccessful) {
                val remoteArticles = response.body()?.results?.map {
                    Article(id = it.id, title = it.title, body = it.summary)
                } ?: emptyList()

                Log.d("ArticleRepository", "Fetched ${remoteArticles.size} articles from API")

                dao.insertAll(remoteArticles)
            } else {
                Log.e("ArticleRepository", "API error: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("ArticleRepository", "Network call failed: ${e.localizedMessage}")
        }

        val cached = dao.getAll()
        Log.d("ArticleRepository", "Emitting ${cached.size} articles from DB")
        emit(cached)
    }

    fun getArticleById(id: Int): Flow<Article?> = flow {
        val article = dao.getAll().find { it.id == id }
        emit(article)
    }
}
