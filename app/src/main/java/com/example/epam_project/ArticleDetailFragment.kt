package com.example.epam_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.epam_project.databinding.FragmentArticleDetailBinding
import com.example.epam_project.viewmodel.ArticleDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment : Fragment() {
    private var _b: FragmentArticleDetailBinding? = null
    private val binding get() = _b!!
    private val viewModel: ArticleDetailViewModel by viewModels()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentArticleDetailBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("articleId") ?: return
        viewModel.loadArticle(id)

        viewModel.article.observe(viewLifecycleOwner) { article ->
            article?.let {
                binding.textTitle.text = it.title
                binding.textBody.text = it.body
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
