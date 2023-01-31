package com.example.cutewallpapers.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cutewallpapers.databinding.FragmentWallpapersListBinding
import com.example.cutewallpapers.logic.adapter.WallpaperAdapter
import com.example.cutewallpapers.logic.model.retrofit.pixabay.Common
import com.example.cutewallpapers.logic.model.retrofit.pixabay.PixabayFullResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WallpapersListFragment: Fragment() {
    private var _binding: FragmentWallpapersListBinding? = null
    val binding get() = checkNotNull(_binding)

    private val args by navArgs<WallpapersListFragmentArgs>()
    private val category by lazy { args.category }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWallpapersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wallpapersRecyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = WallpaperAdapter(emptyList())
        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
//                Log.d("AGAIN?", "YUP")
                Common.retrofitService.getImageList(Common.KEY, category)
                    .enqueue( object: Callback<PixabayFullResponse> {
                        override fun onResponse(
                            call: Call<PixabayFullResponse>,
                            response: Response<PixabayFullResponse>
                        ) {

                            if (response.isSuccessful) {
                                binding.wallpapersRecyclerView.adapter = WallpaperAdapter(
                                    response.body()?.hits ?: emptyList()
                                )
                            }
                        }

                        override fun onFailure(call: Call<PixabayFullResponse>, t: Throwable) {
                            // Network Error
                        }
                    }
                    )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}