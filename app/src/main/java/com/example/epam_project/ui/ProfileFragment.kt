package com.example.epam_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.epam_project.databinding.FragmentProfileBinding
import com.example.epam_project.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _b: FragmentProfileBinding? = null
    private val b get() = _b!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentProfileBinding.inflate(i, c, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            b.textName.text = profile.name
            b.textEmail.text = profile.email
            Glide.with(this).load(profile.avatarUrl).into(b.imageAvatar)
        }

        viewModel.loadProfile()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
