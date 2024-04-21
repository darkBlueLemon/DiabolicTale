package com.invincible.diabolictale

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.invincible.diabolictale.data.PostItem
import com.invincible.diabolictale.ui.theme.DiabolicTaleTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

//        val postItems: MutableState<PostItem> = mutableListOf()
//        val postItems = mutableStateListOf<PostItem>()

        super.onCreate(savedInstanceState)




//        try{
//            // Create an instance of ApiInterface
//            val apiInterface = ApiClient.create()
//
//// Example usage of the getPostCount function
////            val postCountData: Int
//            var postCountData: Map<String, Int>? = null
//
//            val getPostCountCall = apiInterface.getLastId()
//            getPostCountCall.enqueue(object : Callback<Map<String, Int>> {
//                override fun onResponse(call: Call<Map<String, Int>>, response: Response<Map<String, Int>>) {
//                    if (response.isSuccessful) {
//                        postCountData = response.body()
//                        Log.e("MYTAG", postCountData.toString())
//                        // Handle successful response, postCountData contains the response data
//                        Log.e("myDebugTag", "onResponse: " + postCountData, )
//                    } else {
//                        // Handle unsuccessful response
//                        Log.e("myDebugTag", "onResponse: " + "NOO", )
//                    }
//                }
//
//                override fun onFailure(call: Call<Map<String, Int>>, t: Throwable) {
//                    // Handle failure
//                }
//            })
////            Log.e("MYTAG", postCountData.toString())
//
//// Example usage of the getSm{ePost function
////            viewModel.state.
//            for(i in 1..20) {
//                val smePostId = i // Replace with the actual pid value for SME post
//                val getSmePostCall = apiInterface.getSmePost(smePostId)
//                getSmePostCall.enqueue(object : Callback<PostItem> {
//                    override fun onResponse(call: Call<PostItem>, response: Response<PostItem>) {
//                        if (response.isSuccessful) {
//                            val smePostItem = response.body()
//                            if (smePostItem != null) {
//                                viewModel.postItems.add(smePostItem)
//                            }
//                            Log.e("MYTAG", smePostItem.toString())
//                            Log.e("MYTAG", viewModel.postItems.size.toString())
//                            Log.e("MYTAG", viewModel.postItems.toString())
//                            // Handle successful response, smePostItem contains the parsed PostItem object
//                        } else {
//                            // Handle unsuccessful response
//                            Log.e("MYTAG", "ON FAIL FIN POST")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<PostItem>, t: Throwable) {
//                        // Handle failure
//                        Log.e("MYTAG", "ON FAIL FIN POST")
//                    }
//                })
//            }
//            Log.e("MYTAG", viewModel.postItems.toString())
////)
//
//// Example usage of the getFinPost function
//// Example usage of the getFinPost function
//
////            for(i in 1..(postCountData?.get?: 0)) {
//            for(i in 1..10) {
//                val finPostId = i // Replace with the actual pid value for financial post
//                val getFinPostCall = apiInterface.getFinPost(finPostId)
//                getFinPostCall.enqueue(object : Callback<PostItem> {
//                    override fun onResponse(call: Call<PostItem>, response: Response<PostItem>) {
//                        if (response.isSuccessful) {
//                            val finPostItem = response.body()
//                            Log.e("MYTAG", finPostItem.toString())
//                            Log.e("MYTAG", viewModel.postItems.size.toString())
//                            Log.e("MYTAG", viewModel.postItems.toString())
//                            if (finPostItem != null) {
//                                viewModel.postItems.add(finPostItem)
//                                Log.e("MYTAG", finPostItem.toString())
//                                Log.e("MYTAG", viewModel.postItems.size.toString())
//                                Log.e("MYTAG", viewModel.postItems.toString())
//                            }
//                            // Handle successful response, finPostItem contains the parsed PostItem object
//                        } else {
//                            // Handle unsuccessful response
//                            Log.e("MYTAG", "ON FAIL FIN POST")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<PostItem>, t: Throwable) {
//                        // Handle failure
//                        Log.e("MYTAG", "ON FAIL FIN POST")
//                    }
//                })            // Handle successful response, finPostData contains the response data
//            }
//            Log.e("MYTAG", "fjdslfkajsdfjlasdjfjsdajfldasjlfjlajsdfja")
//
//
//            //Log.e("myDebugTag", "onCreate: " + getSMEPost(1).toString(), )
//            //Log.e("myDebugTag", "onCreate: " + getHello(), )
//            //Log.e("myDebugTag", "onCreate: " + getLastID(), )
//        }
//        catch (e: Exception){
//            Log.e("myDebugTag", "onCreate: " + e.toString(), )
//        }
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
                                }
                                else if(state.isLoadingAnimationEnabled) {
                                    LoadingLottieAnimation(viewModel = viewModel)
                                }
                                else if (state.isDoneAnimationEnabled) {
                                    DoneLottieAnimation(viewModel)
                                }
                                else if (state.isViewingTradeFintechDetails) {
                                    TradeFintechDetails(viewModel = viewModel)
                                }
                                else if (state.isViewingSMEDetails) {
                                    SMEDetails(viewModel = viewModel)
                                }
                                else if(state.isViewingFTMarket) {
                                    FTMarket(viewModel)
                                }
                                else if(state.isViewingSMEMarket) {
                                    SMEMarket(viewModel)
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




