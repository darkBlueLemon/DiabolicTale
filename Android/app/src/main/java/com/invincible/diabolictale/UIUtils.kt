package com.invincible.diabolictale

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

var smeOrTradeFintech by mutableStateOf(true)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationBar(viewModel:ViewModel, content: @Composable (PaddingValues) -> Unit) {
//    val viewModel = viewModel<ViewModel>()
    val items = listOf(
        BottomNavigationItem(
            title = "Add",
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "SME Posts",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = true,
        ),
        BottomNavigationItem(
            title = "TF Posts",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false,
        ),
//        BottomNavigationItem(
//            title = "Home",
//            selectedIcon = Icons.Filled.Home,
//            unselectedIcon = Icons.Outlined.Home,
//            hasNews = false,
////            badgeCount = 45
//        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = false,
        ),
    )
//    var selectedItemIndex by rememberSaveable {
//        mutableStateOf(1)
//    }

    val context = LocalContext.current

    Surface(
//        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = viewModel.navBarIndex == index,
//                            modifier = Modifier.clickable (
//                                indication = null,
//                                interactionSource = remember { MutableInteractionSource() }
//                            ) {
//                                selectedItemIndex = index
//                            },
                            onClick = {
                                viewModel.setNavBarIndex(index)
                                viewModel.onEvent(UIEvent.HideAddPost)

                                if (item.title == "SME Posts") {
//                                    smeOrTradeFintech = true
                                    viewModel.onEvent(UIEvent.ShowSMEMarket)
                                } else if (item.title == "TF Posts") {
                                    viewModel.onEvent(UIEvent.ShowFTMarket)
//                                    smeOrTradeFintech = false
                                } else if (item.title == "Add") {
                                    viewModel.onEvent(UIEvent.ShowAddPost)
//                                    val intent = Intent(context, AddPostActivity::class.java)
//                                    intent.putExtra("viewModel", viewModel)
//                                    context.startActivity(intent)
//                                    context.startActivity(
//                                        Intent(
//                                            context,
//                                            AddPostActivity::class.java
//                                        )
//                                    )
//                                    viewModel.setNavBarIndex(1)
                                }
                            },
//                            interactionSource = remember { MutableInteractionSource() } ,
                            label = {
                                Text(text = item.title)
                            },
                            alwaysShowLabel = true,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == viewModel.navBarIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            },
            content = content
        )
    }
}

@Composable
fun FTMarket(viewModel: ViewModel) {
    Box(
        modifier = Modifier
//                                    .height(300.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
//            .background(Color.White)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
//            .heightIn(max = 660.dp),
        ,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "Trade and Fintech Marketplace",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Column {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(20) { item ->
                        Box(
                            modifier = Modifier
//                                    .height(300.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(20.dp))
//                            .background(Color(0xFF08808E))
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .fillMaxWidth(),
//                            .heightIn(max = 660.dp),
                            contentAlignment = Alignment.Center,
                        ) {
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
@Composable
fun SMEMarket(viewModel: ViewModel) {

    Box(
        modifier = Modifier
//                                    .height(300.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
//            .background(Color.White)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
//            .heightIn(max = 660.dp),
                ,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "SME Marketplace", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 24.sp)
//            Spacer(modifier = Modifier.size(12.dp))
            LazyColumn(modifier = Modifier) {
                items(20) { item ->
                    Box(
                        modifier = Modifier
//                                    .height(300.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(20.dp))
//                            .background(Color(0xFF08808E))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .fillMaxWidth(),
//                            .heightIn(max = 660.dp),
                        contentAlignment = Alignment.Center,
                    ) {
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
                                text = "SME",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(text = "Contracts",
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(text = "Terms",
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(text = "Online Applications",
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun DoneLottieAnimation(viewModel: ViewModel) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.done))
    var isPlaying by remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(composition = composition, isPlaying = isPlaying)
//    viewModel.onEvent(UIEvent.HideDoneAnimation)
    LaunchedEffect(key1 = progress) {
        while(progress != 1f) {
            delay(1000)
        }
        viewModel.onEvent(UIEvent.HideDoneAnimation)
        viewModel.setNavBarIndex(2)
//        if(progress == 0f) {
//            isPlaying = true
//        }
//        if(progress == 1f) {
//            isPlaying = false
//        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = composition, progress = progress
        )
    }
}

@Composable
fun AddPost(viewModel: ViewModel) {
        Column {
            Text(text = "Create Post")

            var serviceName by rememberSaveable { mutableStateOf("") }
            TextField(
                value = serviceName,
                onValueChange = {
                    serviceName = it
                },
                label = { Text("Service Name") }
            )

            var serviceType by rememberSaveable { mutableStateOf("") }
            TextField(
                value = serviceType,
                onValueChange = {
                    serviceType = it
                },
                label = { Text("Service Type") }
            )

            var value by rememberSaveable { mutableStateOf("") }
            TextField(
                value = value,
                onValueChange = {
                    value = it
                },
                label = { Text("Value") }
            )

            Button(onClick = {
                viewModel.onEvent(UIEvent.ShowDoneAnimation)
            }) {
                Text(text = "POST")
            }
        }
}

@Composable
fun TradeFintechDetails(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Trade FinTech Desc",
                fontWeight = FontWeight.Bold
            )
            Text(text = "Service Type")
            Text(text = "Service Term")
            Text(text = "Service Desc")

            var value by rememberSaveable { mutableStateOf("") }
            TextField(
                value = value,
                onValueChange = {
                    value = it
                },
                label = { Text("some value") }
            )

            Button(onClick = {
                viewModel.onEvent(UIEvent.ShowDoneAnimation)
            }) {
                Text(text = "REQUEST")
            }
        }
    }
}

@Composable
fun SMEDetails(viewModel: ViewModel) {
    Box(
        modifier = Modifier
//                                    .height(300.dp)
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
//            .background(Color.White)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
//            .heightIn(max = 660.dp),
        ,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "SME Details and Buying/sponsoring",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Company Name",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Credit Score",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Contact",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Company Description",
                fontWeight = FontWeight.Bold
            )


            var value by rememberSaveable { mutableStateOf("") }
            TextField(
                value = value,
                onValueChange = {
                    value = it
                },
                label = { Text("some value") }
            )

            Button(onClick = {
                 viewModel.onEvent(UIEvent.ShowDoneAnimation)
            }) {
                Text(text = "SPONSOR")
            }
        }
    }
}

@Composable
fun SignIn(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(text = "Login")
            Text(text = "Please Sign In to Continue")

            var bid by rememberSaveable { mutableStateOf("") }
            TextField(
                value = bid,
                onValueChange = {
                    bid = it
                },
                label = { Text("BID") }
            )
            var password by rememberSaveable { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("PASSWORD") }
            )

            Button(onClick = {
                viewModel.onEvent(UIEvent.HideSignIn)
//                context.startActivity(Intent(context, MainActivity::class.java))
            }) {
                Text(text = "LOGIN")
            }

            Text(text = "Forgot Password?", color = Color.Blue, modifier = Modifier.clickable {  })

            Row {
                Text(text = "Don't have an account?")
                Text(text = "Sign Up", color = Color.Blue, modifier = Modifier.clickable {  })
            }
        }
    }
}

@Composable
fun SignUp(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        if(smeOrTadeFintech) {
            Column {
                Text(text = "Sign Up SME")

                var companyName by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = companyName,
                    onValueChange = {
                        companyName = it
                    },
                    label = { Text("Email") }
                )

                var contact by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = contact,
                    onValueChange = {
                        contact = it
                    },
                    label = { Text("Contact") }
                )

                var creditScore by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = creditScore,
                    onValueChange = {
                        creditScore = it
                    },
                    label = { Text("Credit Score") }
                )

                var bid by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = bid,
                    onValueChange = {
                        bid = it
                    },
                    label = { Text("BID") }
                )

                Button(onClick = {
                    viewModel.onEvent(UIEvent.HideSignUp)
                }) {
                    Text(text = "SIGN UP")
                }
            }
//        } else {
//            Column {
//                Text(text = "Sign Up Trade Fintech")
//
//                var companyName by rememberSaveable { mutableStateOf("") }
//                TextField(
//                    value = companyName,
//                    onValueChange = {
//                        companyName = it
//                    },
//                    label = { Text("Email") }
//                )
//
//                var contact by rememberSaveable { mutableStateOf("") }
//                TextField(
//                    value = contact,
//                    onValueChange = {
//                        contact = it
//                    },
//                    label = { Text("Contact") }
//                )
//
//                var bid by rememberSaveable { mutableStateOf("") }
//                TextField(
//                    value = bid,
//                    onValueChange = {
//                        bid = it
//                    },
//                    label = { Text("BID") }
//                )
//
//                Button(onClick = {
//                    context.startActivity(Intent(context, MainActivity::class.java))
//                }) {
//                    Text(text = "SIGN UP")
//                }
//            }
//        }
    }
}

@Composable
fun Welcome(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "WELCOME")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Tutorial")
            }
            Button(onClick = {
//                                context.startActivity(Intent(context, SignInActivity::class.java))
                viewModel.onEvent(UIEvent.ShowSignIn)
                viewModel.onEvent(UIEvent.HideFirstTime)
//                PreferencesManager.firstTime = false
            },
//                colors = ButtonDefaults.buttonColors(
//                    contentColor = MaterialTheme.colorScheme.primaryContainer
//                )
                ) {
                Text(text = "Sign In")
            }
            Button(onClick = {
                viewModel.onEvent(UIEvent.ShowSignUp)
                viewModel.onEvent(UIEvent.HideFirstTime)
//                PreferencesManager.firstTime = false
//                val intent = Intent(context, SignUpActivity::class.java)
//                intent.putExtra("SME/TradeFintech", true)
//                context.startActivity(intent)
            }) {
                Text(text = "Sign Up SME")
            }
            Button(onClick = {
                viewModel.onEvent(UIEvent.ShowSignUp)
                viewModel.onEvent(UIEvent.HideFirstTime)
//                PreferencesManager.firstTime = false
//                val intent = Intent(context, SignUpActivity::class.java)
//                intent.putExtra("SME/TradeFintech", false)
//                context.startActivity(intent)
            }) {
                Text(text = "Sign Up Trade Fintech")
            }
        }
    }
}