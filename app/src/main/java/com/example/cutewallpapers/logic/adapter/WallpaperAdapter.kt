package com.example.cutewallpapers.logic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cutewallpapers.R
import com.example.cutewallpapers.databinding.FragmentWallpapersListItemBinding
import com.example.cutewallpapers.logic.model.retrofit.pixabay.PixabayHit
import com.example.cutewallpapers.ui.WallpapersListFragmentDirections

class WallpaperAdapter(private val data: List<PixabayHit>): RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {
    class WallpaperViewHolder(val binding: FragmentWallpapersListItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentWallpapersListItemBinding.inflate(inflater, parent, false)

        return WallpaperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val webformatURL = data[position].webformatURL
        // TODO("Change placeholder and error")
//        Log.d("CAT", data.toString())
        Glide.with(holder.itemView.context)
            .load(webformatURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.mipmap.ic_launcher_foreground)
            .error(R.mipmap.ic_launcher)
            .into(holder.binding.wallpaperImageView)

        holder.itemView.setOnClickListener (
            Navigation.createNavigateOnClickListener(
                WallpapersListFragmentDirections
                    .actionWallpapersListFragmentToWallpaperFullscreenFragment(webformatURL)
            )
        )
    }

    override fun getItemCount(): Int = data.size
}