package com.example.cutewallpapers.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cutewallpapers.databinding.FragmentSplashScreenBinding

class SplashScreenFragment: Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        Handler(Looper.getMainLooper()).postDelayed(
            Runnable{
                navController.navigate(
                    SplashScreenFragmentDirections
                        .actionSplashScreenFragmentToWallpapersCategoriesFragment()
                )
            }, 500)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}