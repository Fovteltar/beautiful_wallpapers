package com.example.cutewallpapers.logic.model.retrofit.pixabay

//import com.example.cutewallpapers.MainApplication.Companion.isInternetAvailable
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.cutewallpapers.MainApplication

object RetrofitClient {
    // 50 mb
    private const val CACHE_SIZE = 50L  * 1024 * 1024
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .cache(Cache(MainApplication.getCacheDirectory(), CACHE_SIZE))
                        /*.addNetworkInterceptor { chain ->
                            val response: Response = chain.proceed(chain.request())
                            val cacheControl = CacheControl.Builder()
                                .maxAge(10, TimeUnit.DAYS)
                                .build()
                            response.newBuilder()
                                .header("Cache-Control", cacheControl.toString())
                                .build()
                        }
                        .addInterceptor{ chain ->
                            val builder: Request.Builder = chain.request().newBuilder()
                            if (!isInternetAvailable()) {
                                builder.cacheControl(CacheControl.FORCE_CACHE);
                            }
                            chain.proceed(builder.build());
                        }*/
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}