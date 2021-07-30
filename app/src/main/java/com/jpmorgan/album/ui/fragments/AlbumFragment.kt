package com.jpmorgan.album.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jpmorgan.album.R
import com.jpmorgan.album.data.Album
import com.jpmorgan.album.databinding.FragmentAlbumBinding
import com.jpmorgan.album.ui.adapter.AlbumAdapter
import com.jpmorgan.album.ui.viewmodel.AlbumViewModel
import com.jpmorgan.album.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding

    // lazy inject viewmodel
    val albumViewModel: AlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_album,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = albumViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumViewModel.albums.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Error -> {
                    showProgressBar(false)
                    showError(resource.throwable?.localizedMessage)
                    showAlbums(resource.data)
                }

                is Resource.Loading -> {
                    showProgressBar(true)
                }
                is Resource.Success -> {
                    showProgressBar(false)
                    showAlbums(resource.data)
                }
            }

        })

    }

    private fun showError(errorMessage: String?) {
        errorMessage?.let {
            albumViewModel.errorMessage.value = it
        } ?: kotlin.run {
            albumViewModel.errorMessage.value = resources.getString(R.string.something_went_wrong)
        }

    }

    private fun showAlbums(data: List<Album>?) {
        data?.sortedBy {
            it.title
        }?.let { albums ->
            binding.apply {
                rvAlbum.apply {
                    adapter = AlbumAdapter(albums)
                }
            }
        }
    }

    private fun showProgressBar(isShow: Boolean) {
        albumViewModel.showLoader.value = isShow
    }
}