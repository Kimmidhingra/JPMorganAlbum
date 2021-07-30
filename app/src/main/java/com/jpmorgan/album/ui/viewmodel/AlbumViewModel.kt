package com.jpmorgan.album.ui.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jpmorgan.album.data.Album
import com.jpmorgan.album.repository.AlbumRepository
import com.jpmorgan.album.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val showLoader = MutableLiveData(true)
    lateinit var albums: LiveData<Resource<List<Album>>>

    init {
        getAlbums()
    }

    private fun getAlbums() {
        albums = albumRepository.getAlbums().asLiveData()
    }

}