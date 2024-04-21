import com.invincible.diabolictale.data.PostContentResponse
import com.invincible.diabolictale.data.PostCountResponse
import com.invincible.diabolictale.data.PostItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//interface ApiInterface {
//
//      @GET("smeGetPost")
//      fun getSMEPost(@Query("pid") pid: Int): Response<PostContentResponse>
//
//      @GET("lastid")
//      fun getLastID(): Response<PostCountResponse>
//
//      @GET("/")
//      fun getHello(): Call<String>

//    @GET("smeGetPost")
//    fun getSmePost(@Query("postid") postId: Int): Call<ResponseGetSmePost>
//
//    @POST("smePutPost")
//    fun putSmePost(@Query("bid") bid: String, @Query("content") content: String, @Query("contact") contact: String): Call<ResponsePutSmePost>
//
//    @GET("finGetPost")
//    fun getFinPost(@Query("pid") pid: Int): Call<List<ResponseGetSmePost>>
//
//    @POST("finPutPost")
//    fun putFinPost(@Query("bid") bid: String, @Body body: RequestBody): Call<ResponsePutSmePost>
//
//    @GET("putSignUp")
//    fun putSignUp(@Query("bid") bid: String): Call<ResponsePutSignUp>
//
//    @GET("getProfile")
//    fun getProfile(@Query("bid") bid: String): Call<ResponseGetProfile>
//
//    @POST("putSignInDetails")
//    fun putSignInDetails(@Query("bid") bid: String): Call<PutSignInDetails>
//}



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
      @POST("/freg")
      fun registerFT(
            @Query("businessID") businessID: String,
            @Query("companyName") companyName: String,
            @Query("contactDetails") contactDetails: String
      ): Call<Void>
      @POST("/smereg")
      fun registerSME(
            @Query("businessID") businessID: String,
            @Query("companyName") companyName: String,
            @Query("contactDetails") contactDetails: String
      ): Call<Void>

      @POST("/createTransaction")
      fun createTransaction(
            @Query("buyerbid") buyerBid: String,
            @Query("sellerbid") sellerBid: String,
            @Query("termsandconditions") termsAndConditions: String
      ): Call<Void>


      @POST("/signup")
      fun signUp(
            @Query("username") username: String,
            @Query("email") email: String,
            @Query("password") password: String
      ): Call<Void>

      @GET("/lastid")
      fun getLastId(): Call<Map<String, Int>>

      @POST("/signin")
      fun signIn(
            @Query("email") email: String,
            @Query("password") password: String
      ): Call<Void>

      @POST("/smePutPost")
      fun putSmePost(
            @Query("bid") bid: String,
            @Query("content") content: String,
            @Query("contact") contact: String
      ): Call<Void>

      @GET("/smeGetPost")
      fun getSmePost(@Query("pid") pid: Int): Call<PostItem>

      @POST("/finPutPost")
      fun putFinPost(
            @Query("bid") bid: String,
            @Query("content") content: String,
            @Query("contact") contact: String
      ): Call<Void>

      @GET("/finGetPost")
      fun getFinPost(@Query("pid") pid: Int): Call<PostItem>

}

class ApiClient {
      companion object {
            private const val BASE_URL = "http://192.168.34.234/"

            fun create(): ApiInterface {
                  val client = OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                        .build()
                  val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                  return retrofit.create(ApiInterface::class.java)
            }
      }
}
