package com.example.cutewallpapers.ui

import android.app.WallpaperManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cutewallpapers.MainApplication.Companion.applicationContext
import com.example.cutewallpapers.R
import com.example.cutewallpapers.databinding.FragmentWallpaperFullscreenBinding

class WallpaperFullscreenFragment: Fragment() {
    private var _binding: FragmentWallpaperFullscreenBinding? = null
    val binding get() = checkNotNull(_binding)

    private val args by navArgs<WallpaperFullscreenFragmentArgs>()
    private val webFormatURL by lazy { args.webformatURL }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWallpaperFullscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context)
            .load(webFormatURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.mipmap.ic_launcher_foreground)
            .error(R.mipmap.ic_launcher)
            .into(binding.wallpaperImageView)

        binding.doneButton.setOnClickListener {
            val wallpaperManager = WallpaperManager.getInstance(applicationContext())
            wallpaperManager.setBitmap(binding.wallpaperImageView.drawToBitmap())
            Toast.makeText(view.context, "Wallpaper changed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}