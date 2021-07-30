package com.jpmorgan.album.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jpmorgan.album.data.Album
import com.jpmorgan.album.R
import com.jpmorgan.album.databinding.ViewAlbumItemBinding

class AlbumAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {


    inner class AlbumViewHolder(val albumItemBinding: ViewAlbumItemBinding) :
        RecyclerView.ViewHolder(albumItemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(
            DataBindingUtil.inflate<ViewAlbumItemBinding>(
                LayoutInflater.from(parent.context), R.layout.view_album_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.albumItemBinding.album = albums[position]
    }

    override fun getItemCount(): Int = albums.size
}