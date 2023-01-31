package com.example.cutewallpapers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cutewallpapers.databinding.FragmentWallpapersCategoriesBinding

class WallpapersCategoriesFragment: Fragment() {
    private var _binding: FragmentWallpapersCategoriesBinding? = null
    private val binding get() = checkNotNull(_binding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWallpapersCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val categoriesList = listOf(
                backgroundsImageView,
                fashionImageView,
                natureImageView,
                scienceImageView,
                educationImageView,
                feelingsImageView
            )
            categoriesList.forEach {
                it.setOnClickListener {
                    val category = resources.getResourceEntryName(it.id).removeSuffix("ImageView")
                    findNavController().navigate(
                        WallpapersCategoriesFragmentDirections
                            .actionWallpapersCategoriesFragmentToWallpapersListFragment(category)
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}