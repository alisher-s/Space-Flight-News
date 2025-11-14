package com.example.epam_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epam_project.databinding.FragmentArticleListBinding
import com.example.epam_project.viewmodel.ArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment : Fragment() {
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticleListViewModel by viewModels()
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentArticleListBinding.inflate(i, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ArticleAdapter { id ->
            findNavController().navigate(
                R.id.action_list_to_detail,
                bundleOf("articleId" to id)
            )
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadArticles()
        }

        viewModel.articles.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            adapter.submitList(it)
        }

        binding.swipeRefresh.isRefreshing = true
        viewModel.loadArticles()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
