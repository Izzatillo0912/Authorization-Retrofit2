package arfo.developer.apitoken

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("token")
    fun checkUser(@Field("username") username: String, @Field("password") password: String, ): Call<LoginModel>

    @GET("giving_orders")
    fun getOta(
        @Query("page") page : Int,
        @Query("driver") driver : Int,
        @Query("type") type : String
    ) : Call<List<OtaModel>>
}