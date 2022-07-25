package tw.eita.mrvn.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import tw.eita.mrvn.BuildConfig

interface ApexApiService {

    @GET("news")
    suspend fun fetchNews(): List<News>

    @GET("maprotation")
    suspend fun fetchMapRotation(): MapObj

    @GET("crafting")
    suspend fun fetchCraft(): List<CraftBundle>

}