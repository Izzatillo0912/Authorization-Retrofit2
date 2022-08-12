package arfo.developer.apitoken

import android.content.ContentValues
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitClient {

    private val client = OkHttpClient.Builder().addInterceptor { chain: Interceptor.Chain ->
        val newRequest: Request = chain.request().newBuilder()
            .header(
                "Authorization", " Bearer $token"
            )
            .build()
        chain.proceed(newRequest)
    }.build()


    private const val BASE_URL = "https://back.yuvish.uz/"
    fun getRetrofit(): ApiService {
        Log.e(ContentValues.TAG, "getRetrofit: $token")
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

}