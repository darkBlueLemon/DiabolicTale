package com.invincible.diabolictale

import ApiInterface
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.invincible.diabolictale.data.PostContentResponse
import com.invincible.diabolictale.ui.theme.DiabolicTaleTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)


class MainActivity : ComponentActivity() {

    val BASE_URL : String = "http://192.168.34.234/"

    val apiInterface = ApiClient.create()

    private val viewModel: ViewModel by viewModels<ViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        // Creating shared prefs
        PreferencesManager.initialize(this)

        super.onCreate(savedInstanceState)




        try{
            // Create an instance of ApiInterface
            val apiInterface = ApiClient.create()

// Example usage of the getPostCount function
            val getPostCountCall = apiInterface.getLastId()
            getPostCountCall.enqueue(object : Callback<Map<String, Int>> {
                override fun onResponse(call: Call<Map<String, Int>>, response: Response<Map<String, Int>>) {
                    if (response.isSuccessful) {
                        val postCountData = response.body()
                        // Handle successful response, postCountData contains the response data
                        Log.e("myDebugTag", "onResponse: " + postCountData, )
                    } else {
                        // Handle unsuccessful response
                        Log.e("myDebugTag", "onResponse: " + "NOO", )
                    }
                }

                override fun onFailure(call: Call<Map<String, Int>>, t: Throwable) {
                    // Handle failure
                }
            })

//            val smePostId = 1 // Replace with the actual pid value for SME post
//            val getSmePostCall = apiInterface.getSmePost(smePostId)
//            getSmePostCall.enqueue(object : Callback<Map<String, String>> {
//                override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
//                    if (response.isSuccessful) {
//                        val smePostData = response.body()
//                        // Handle successful response, smePostData contains the response data
//                    } else {
//                        // Handle unsuccessful response
//                    }
//                }
//
//                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
//                    // Handle failure
//                }
//            })

// Example usage of the getFinPost function
            val finPostId = 1 // Replace with the actual pid value for financial post
            val getFinPostCall = apiInterface.getFinPost(finPostId)
            getFinPostCall.enqueue(object : Callback<Map<String, String>> {
                override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                    if (response.isSuccessful) {
                        val finPostData = response.body()
                        Log.e("myDebugTag", "onResponse: " + finPostData, )
                        // Handle successful response, finPostData contains the response data
                    } else {
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    // Handle failure
                }
            })


            //Log.e("myDebugTag", "onCreate: " + getSMEPost(1).toString(), )
            //Log.e("myDebugTag", "onCreate: " + getHello(), )
            //Log.e("myDebugTag", "onCreate: " + getLastID(), )
        }
        catch (e: Exception){
            Log.e("myDebugTag", "onCreate: " + e.toString(), )
        }
        setContent {
            DiabolicTaleTheme {

                val context = LocalContext.current
//                val viewModel = viewModel<ViewModel>()

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Box(
                    modifier = Modifier
                        .fillMaxSize()
                    ) {
//                        val signInState by PreferencesManager.firstTime
                        val state by viewModel.state.collectAsState()
                        if(PreferencesManager.firstTime && state.isFirstTime) {
                            Welcome(viewModel = viewModel)
//                            viewModel.onEvent()
                        } else if(state.isSigningIn) {
                            SignIn(viewModel = viewModel)
                        } else if(state.isSigningUp) {
                            SignUp(viewModel = viewModel)
                        }
                        else {
                            MyNavigationBar(viewModel) {
                                if (state.isAddingPost) {
                                    AddPost(viewModel = viewModel)
                                } else if (state.isDoneAnimationEnabled) {
                                    DoneLottieAnimation(viewModel)
                                } else if (state.isViewingTradeFintechDetails) {
                                    TradeFintechDetails(viewModel = viewModel)
                                } else if (state.isViewingSMEDetails) {
                                    SMEDetails(viewModel = viewModel)
                                } else if(state.isViewingFTMarket) {
                                    FTMarket(viewModel)
                                } else if(state.isViewingSMEMarket) {
                                    SMEMarket(viewModel)
                                } else if(state.isLoadingAnimationEnabled) {
                                    LoadingLottieAnimation(viewModel = viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

//    private fun getSMEPost(pid: Int) {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(ApiInterface::class.java)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val retrofitData = retrofitBuilder.getSMEPost(pid)
//
//           Log.e("myDebugTag", "getSMEPost: " + retrofitData.toString() )
//
//        }
//
////        retrofitBuilder.getLastID().raw()
//
////        Log.e("myDebugTag", "getSMEPost: " + retrofitData.toString() + " " + retrofitBuilder.getLastID().raw(), )
//    }

//    private fun getLastID(){
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.getLastID()
//
//        Log.e("myDebugTag", "getSMEPost: " + retrofitData.body().toString(), )
//
//
////        retrofitData.enqueue(object : Callback<Long?> {
////            override fun onResponse(call: Call<Long?>, response: Response<Long?>) {
////                val responseBody = response.body()
////                Log.e("myDebugTag", "onResponse: " + responseBody.toString(), )
////            }
////
////            override fun onFailure(call: Call<Long?>, t: Throwable) {
////                Log.e("myDebugTag", "onFailure: " + t.toString(), )
////            }
////        })
//    }

//    private fun getHello(){
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.getHello()
//
//        retrofitData.enqueue(object : Callback<String?> {
//            override fun onResponse(call: Call<String?>, response: Response<String?>) {
//                val responseBody = response.body()
//                Log.e("myDebugTag", "onResponse: " + responseBody.toString(), )
//            }
//
//            override fun onFailure(call: Call<String?>, t: Throwable) {
//                Log.e("myDebugTag", "onFailure: " + t.toString(), )
//            }
//        })
//    }

//    private fun putSmePost(bid:String,content:String,contact:String) {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("http://192.168.34.234/")
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.putSmePost(bid, content, contact)
//
//        retrofitData.enqueue(object : Callback<ResponsePutSmePost?>{
//            override fun onResponse(
//                call: Call<ResponsePutSmePost?>,
//                response: Response<ResponsePutSmePost?>
//            ) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ResponsePutSmePost?>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
//
//    private fun getFinPost(postid:Int) {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("http://192.168.34.234/")
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.getFinPost(postid)
//
//        retrofitData.enqueue(object : Callback<List<ResponseGetSmePost>> {
//            override fun onResponse(
//                call: Call<List<ResponseGetSmePost>>,
//                response: Response<List<ResponseGetSmePost>>
//            ) {
//                Log.e("myDebugTag", "onResponse: " + response.body(), )
//            }
//
//            override fun onFailure(call: Call<List<ResponseGetSmePost>>, t: Throwable) {
//                Log.e("myDebugTag", "onFailure: " + t.toString(), )
//            }
//
//        })
    }




