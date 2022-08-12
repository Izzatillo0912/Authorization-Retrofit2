package arfo.developer.apitoken

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("token")
    fun checkUser(@Field("username") username: String, @Field("password") password: String, ): Call<LoginModel>
}