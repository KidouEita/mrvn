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

    companion object {
        val instance: ApexApiService by lazy {
            val logging = HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    var request = chain.request()
                    val url =
                        request.url.newBuilder().addQueryParameter("auth", BuildConfig.API_KEY)
                            .build()
                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                })
                .addInterceptor(logging)
                .build()
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()
            retrofit.create(ApexApiService::class.java)
        }
    }

}