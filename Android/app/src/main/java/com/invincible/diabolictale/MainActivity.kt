package com.invincible.diabolictale

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.PredictiveBackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
                                } else if (state.isViewingTradeFintech) {
                                    TradeFintechDetails(viewModel = viewModel)
                                } else if (state.isViewingSME) {
                                    SMEDetails(viewModel = viewModel)
                                }
                                else {
                                    if (smeOrTradeFintech) {
                                        Column {
                                            LazyColumn(modifier = Modifier) {
                                                items(20) { item ->
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(16.dp)
                                                            .clickable {
                                                                viewModel.onEvent(UIEvent.ShowSME)
//                                                            context.startActivity(
//                                                                Intent(
//                                                                    context,
//                                                                    SmeDetailsActivity::class.java
//                                                                )
//                                                            )
                                                            }
                                                    ) {
                                                        Text(
                                                            text = "SWE",
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Text(text = "Contracts")
                                                        Text(text = "Terms")
                                                        Text(text = "Online Applications")
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        Column {
                                            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                                items(20) { item ->
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(16.dp)
                                                            .clickable {
                                                                viewModel.onEvent(UIEvent.ShowTradeFintech)
//                                                            context.startActivity(
//                                                                Intent(
//                                                                    context,
//                                                                    FinTechActivity::class.java
//                                                                )
//                                                            )
                                                            }
                                                    ) {
                                                        Text(
                                                            text = "FinTech",
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                        Text(text = "whatever else")
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
