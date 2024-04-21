package com.invincible.diabolictale

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.invincible.diabolictale.data.PostItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel: ViewModel() {

    val postItems = mutableStateListOf<PostItem>()
    var postItem: PostItem? = null
    var termAndConditions: String = ""
    val apiInterface = ApiClient.create()
    var postCountData: Map<String, Int>? = null

    fun getPostItemCount() {
        val getPostCountCall = apiInterface.getLastId()
        getPostCountCall.enqueue(object : Callback<Map<String, Int>> {
            override fun onResponse(call: Call<Map<String, Int>>, response: Response<Map<String, Int>>) {
                if (response.isSuccessful) {
                    postCountData = response.body()
                    Log.d("MYTAG", postCountData.toString())
                    // Handle successful response, postCountData contains the response data
                    Log.d("myDebugTag", "onResponse: " + postCountData, )
                } else {
                    // Handle unsuccessful response
                    Log.d("myDebugTag", "onResponse: " + "NOO", )
                }
            }

            override fun onFailure(call: Call<Map<String, Int>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun callSMEPostItems() {
        postItems.clear()
        for(i in 1..(postCountData?.get("sme_post_count") ?: 0)) {
            val smePostId = i // Replace with the actual pid value for SME post
            val getSmePostCall = apiInterface.getSmePost(smePostId)
            getSmePostCall.enqueue(object : Callback<PostItem> {
                override fun onResponse(call: Call<PostItem>, response: Response<PostItem>) {
                    if (response.isSuccessful) {
                        val smePostItem = response.body()
                        if (smePostItem != null) {
                            postItems.add(smePostItem)
                        }
                        Log.d("MYTAG", smePostItem.toString())
                        Log.d("MYTAG", postItems.size.toString())
                        Log.d("MYTAG", postItems.toString())
                        // Handle successful response, smePostItem contains the parsed PostItem object
                    } else {
                        // Handle unsuccessful response
                        Log.d("MYTAG", "ON FAIL FIN POST")
                    }
                }

                override fun onFailure(call: Call<PostItem>, t: Throwable) {
                    // Handle failure
                    Log.d("MYTAG", "ON FAIL FIN POST")
                }
            })
        }
    }

    fun callFTPostItems() {
        postItems.clear()
//        for(i in 1..10) {
        for(i in 1..(postCountData?.get("financier_post_count") ?: 0)) {
            val finPostId = i // Replace with the actual pid value for financial post
            val getFinPostCall = apiInterface.getFinPost(finPostId)
            getFinPostCall.enqueue(object : Callback<PostItem> {
                override fun onResponse(call: Call<PostItem>, response: Response<PostItem>) {
                    if (response.isSuccessful) {
                        val finPostItem = response.body()
                        Log.d("MYTAG", finPostItem.toString())
                        Log.d("MYTAG", postItems.size.toString())
                        Log.d("MYTAG", postItems.toString())
                        if (finPostItem != null) {
                            postItems.add(finPostItem)
                            Log.d("MYTAG", finPostItem.toString())
                            Log.d("MYTAG", postItems.size.toString())
                            Log.d("MYTAG", postItems.toString())
                        }
                        // Handle successful response, finPostItem contains the parsed PostItem object
                    } else {
                        // Handle unsuccessful response
                        Log.d("MYTAG", "ON FAIL FIN POST")
                    }
                }

                override fun onFailure(call: Call<PostItem>, t: Throwable) {
                    // Handle failure
                    Log.d("MYTAG", "ON FAIL FIN POST")
                }
            })            // Handle successful response, finPostData contains the response data
        }
    }

    fun putSMEPost(postItem: PostItem) {
        // Example usage of the putSmePost function
        val putSmePostCall = apiInterface.putSmePost(postItem.BID, postItem.content,postItem.contact)
        putSmePostCall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle successful response
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun putFTPost(postItem: PostItem) {
        // Example usage of the putFinPost function
        val putFinPostCall = apiInterface.putFinPost(postItem.BID, postItem.content,postItem.contact)
        putFinPostCall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle successful response
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun putTransaction() {
        // Example usage of the createTransaction function
        val buyerBid = postItem?.BID
        val sellerBid = "123"
        val termsAndConditions = termAndConditions
        val createTransactionCall =
            buyerBid?.let { apiInterface.createTransaction(it, sellerBid, termsAndConditions) }
        if (createTransactionCall != null) {
            createTransactionCall.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Handle successful response
                    } else {
                        // Handle unsuccessful response
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }

    fun putSignUp(username: String, email: String, password: String) {
//        val username = username
//        val email = email
//        val password = password
        val signUpCall = apiInterface.signUp(username, email, password)
        signUpCall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle successful response
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun putRegisterFT(businessID:String, companyName:String, contactDetails:String) {
        // Example usage of the registerSME function
        val registerSMECall = apiInterface.registerFT(businessID, companyName, contactDetails)
        registerSMECall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle successful response
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun putRegisterSME(businessID:String, companyName:String, contactDetails:String) {
        val registerSMECall = apiInterface.registerSME(businessID, companyName, contactDetails)
        registerSMECall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle successful response
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private var _navBarIndex by mutableStateOf(2)
    val navBarIndex: Int get() = _navBarIndex
    fun setNavBarIndex(newIndex: Int) {
        _navBarIndex = newIndex
    }

    private val _state = MutableStateFlow(UIState())
    val state: MutableStateFlow<UIState> get() = _state

    fun onEvent(event: UIEvent) {
        when(event) {
            UIEvent.ShowAddPost -> {
                _state.update { it.copy(
                    isAddingPost = true
                )
                }
            }
            UIEvent.HideAddPost -> {
                _state.update { it.copy(
                    isAddingPost = false
                )
                }
            }
            UIEvent.ShowDoneAnimation -> {
                _state.update { it.copy(
                    isDoneAnimationEnabled = true,
                    isViewingFTMarket = false,
                    isViewingSMEMarket = false,
                    isAddingPost = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false
                )
                }
            }
            UIEvent.HideDoneAnimation -> {
                _state.update { it.copy(
                    isDoneAnimationEnabled = false,
                )
                }
            }
            UIEvent.ShowLoadingAnimation -> {
                _state.update { it.copy(
                    isLoadingAnimationEnabled = true,
                    isAddingPost = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false,
                    isViewingFTMarket = false,
                    isViewingSMEMarket = false,
                )
                }
            }
            UIEvent.HideLoadingAnimation -> {
                if(_state.value.isRoleSME) {
                    _state.update {
                        it.copy(
                            isLoadingAnimationEnabled = false,
                            isViewingSMEMarket = true
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoadingAnimationEnabled = false,
                            isViewingFTMarket = true
                        )
                    }
                }
            }
            UIEvent.ShowTradeFintech -> {
                _state.update { it.copy(
                    isViewingTradeFintechDetails = true,
                )
                }
            }
            UIEvent.HideTradeFintech -> {
                _state.update { it.copy(
                    isViewingTradeFintechDetails = false,
                )
                }
            }
            UIEvent.ShowSME -> {
                _state.update { it.copy(
                    isViewingSMEDetails = true,
                )
                }
            }
            UIEvent.HideSME -> {
                _state.update { it.copy(
                    isViewingSMEDetails = false,
                )
                }
            }
            UIEvent.ShowSignIn -> {
                _state.update { it.copy(
                    isSigningIn = true,
                )
                }
            }
            UIEvent.HideSignIn -> {
                _state.update { it.copy(
                    isSigningIn = false,
                )
                }
            }
            UIEvent.ShowSignUp -> {
                _state.update { it.copy(
                    isSigningUp = true,
                )
                }
            }
            UIEvent.HideSignUp -> {
                _state.update { it.copy(
                    isSigningUp = false,
                )
                }
            }
            UIEvent.ShowFirstTime -> {
                _state.update { it.copy(
                    isFirstTime = true,
                )
                }
            }
            UIEvent.HideFirstTime -> {
                _state.update { it.copy(
                    isFirstTime = false,
                )
                }
            }
            UIEvent.ShowFTMarket -> {
                _state.update { it.copy(
                    isViewingFTMarket = true,
                    isViewingSMEMarket = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false
                )
                }
            }
            UIEvent.HideFTMarket -> {
                _state.update { it.copy(
                    isViewingFTMarket = false,
                )
                }
            }
            UIEvent.ShowSMEMarket -> {
                _state.update { it.copy(
                    isViewingSMEMarket = true,
                    isViewingFTMarket = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false
                )
                }
            }
            UIEvent.HideSMEMarket -> {
                _state.update { it.copy(
                    isViewingSMEMarket = false,
                )
                }
            }
            UIEvent.RoleSME -> {
                _state.update { it.copy(
                    isRoleSME = !_state.value.isRoleSME
                )
                }
            }
            UIEvent.RoleFT -> {
                _state.update { it.copy(
                    isRoleSME = false
                )
                }
            }
            UIEvent.RoleNotFT -> {
                _state.update { it.copy(
                    isRoleSME = true
                )
                }
            }
        }
    }
}