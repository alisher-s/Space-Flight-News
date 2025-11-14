package com.example.epam_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epam_project.data.Article
import com.example.epam_project.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _article = MutableLiveData<Article?>()
    val article: LiveData<Article?> = _article

    fun loadArticle(id: Int) {
        viewModelScope.launch {
            repository.getArticleById(id).collectLatest {
                _article.value = it
            }
        }
    }
}
