package com.invincible.diabolictale

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.invincible.diabolictale.ui.theme.DiabolicTaleTheme

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)


class MainActivity : ComponentActivity() {

    private val viewModel: ViewModel by viewModels<ViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        // Creating shared prefs
        PreferencesManager.initialize(this)

        super.onCreate(savedInstanceState)
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
}
