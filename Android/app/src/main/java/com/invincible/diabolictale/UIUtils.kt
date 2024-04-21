package com.invincible.diabolictale

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.invincible.diabolictale.data.PostItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavigationBar(viewModel:ViewModel, content: @Composable (PaddingValues) -> Unit) {
//    val viewModel = viewModel<ViewModel>()
    val state by viewModel.state.collectAsState()
    val items = listOf(
        BottomNavigationItem(
            title = if(state.isRoleSME) "SME" else "Fintech",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Add",
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = true,
        ),
        BottomNavigationItem(
//            title = if(!state.isRoleSME) "SME" else "Fintech",
            title = "Compt.",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
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
            title = "Profile",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face,
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

                                if(index == 1) {
                                    viewModel.onEvent(UIEvent.ShowAddPost)
                                }
                                else if(index == 0) {
                                    viewModel.onEvent(UIEvent.RoleSME)
                                    viewModel.setNavBarIndex(2)
//                                    if(!state.isRoleSME) viewModel.onEvent(UIEvent.ShowFTMarket)
//                                    else viewModel.onEvent(UIEvent.ShowSMEMarket)
                                    viewModel.onEvent(UIEvent.ShowLoadingAnimation)
                                }
                                else if(index == 2) {
                                    if(state.isRoleSME) viewModel.onEvent(UIEvent.ShowFTMarket)
                                    else viewModel.onEvent(UIEvent.ShowSMEMarket)
                                }
                                else if(index == 3) {
                                    if(!state.isRoleSME) viewModel.onEvent(UIEvent.ShowFTMarket)
                                    else viewModel.onEvent(UIEvent.ShowSMEMarket)
                                }

                                if (item.title == "SME Posts") {
//                                    smeOrTradeFintech = true
                                } else if (item.title == "TF Posts") {
//                                    smeOrTradeFintech = false
                                } else if (item.title == "Add") {
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

    viewModel.getPostItemCount()
    viewModel.callFTPostItems()

    Box(
        modifier = Modifier
//                                    .height(300.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
//            .background(Color.White)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .heightIn(max = 750.dp)
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
                fontSize = 24.sp,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.size(24.dp))
            Column {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(viewModel.postItems) { item ->
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
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    imageVector = Icons.Rounded.AccountBox,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(20.dp),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .clickable {
                                            viewModel.onEvent(UIEvent.ShowTradeFintech)
                                            viewModel.postItem = item
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
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Text(
                                        text =  item.PID.toString(),
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Text(
                                        text = item.contact,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Text(
                                        text = item.content,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
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

    viewModel.getPostItemCount()
    viewModel.callSMEPostItems()

    Box(
        modifier = Modifier
//                                    .height(300.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
//            .background(Color.White)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .heightIn(max = 750.dp)
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
            Text(text = "SME Marketplace", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 24.sp, textDecoration = TextDecoration.Underline)
            Spacer(modifier = Modifier.size(24.dp))
//            Spacer(modifier = Modifier.size(12.dp))
            LazyColumn(modifier = Modifier) {
                items(viewModel.postItems) { item ->
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(imageVector = Icons.Rounded.AccountBox, contentDescription = null, modifier = Modifier
                                .size(100.dp)
                                .padding(20.dp), colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer))

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable {
                                        viewModel.onEvent(UIEvent.ShowSME)
                                        viewModel.postItem = item
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
                                Text(
                                    text =  item.PID.toString(),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = item.contact,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = item.content,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
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
            delay(100)
        }
        viewModel.onEvent(UIEvent.HideDoneAnimation)
        if(viewModel.state.value.isRoleSME) {
            viewModel.onEvent(UIEvent.HideFTMarket)
            viewModel.onEvent(UIEvent.ShowSMEMarket)
        } else {
            viewModel.onEvent(UIEvent.HideSMEMarket)
            viewModel.onEvent(UIEvent.ShowFTMarket)
        }
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
fun LoadingLottieAnimation(viewModel: ViewModel) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.done))
    var isPlaying by remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(composition = composition, isPlaying = isPlaying)
    viewModel.onEvent(UIEvent.HideDoneAnimation)
    LaunchedEffect(key1 = progress) {
        while(progress != 1f) {
            delay(100)
        }
        viewModel.onEvent(UIEvent.HideLoadingAnimation)
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
            modifier = Modifier.size(700.dp),
            composition = composition, progress = progress
        )
    }
}

@Composable
fun AddPost(viewModel: ViewModel) {
        Column {
            Text(text = "Create Post")

            var BID by rememberSaveable { mutableStateOf("") }
            TextField(
                value = BID,
                onValueChange = {
                    BID = it
                },
                label = { Text("BID") }
            )

            var content by rememberSaveable { mutableStateOf("") }
            TextField(
                value = content,
                onValueChange = {
                    content = it
                },
                label = { Text("Content") }
            )

            var contact by rememberSaveable { mutableStateOf("") }
            TextField(
                value = contact,
                onValueChange = {
                    contact = it
                },
                label = { Text("Contact") }
            )

            val postItem: PostItem = PostItem(-1, BID, content, contact)

            Button(onClick = {
                viewModel.onEvent(UIEvent.ShowDoneAnimation)
                if(viewModel.state.value.isRoleSME) viewModel.putSMEPost(postItem) else viewModel.putFTPost(postItem)
            }) {
                Text(text = "POST")
            }
        }
}

@Composable
fun TradeFintechDetails(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .padding(30.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .heightIn(max = 700.dp)
            .fillMaxWidth()
        ,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "Trade Fintech",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.size(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Trade FinTech Desc",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(text = "Service Type",
                        fontWeight = FontWeight.Bold
                        )
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(text = "Service Term",
                        fontWeight = FontWeight.Bold
                        )
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(text = "Service Desc",
                        fontWeight = FontWeight.Bold
                        )
                    Spacer(modifier = Modifier.size(24.dp))

                    if(viewModel.state.value.isRoleSME) {
                        var value by rememberSaveable { mutableStateOf("") }
                        TextField(
                            value = value,
                            onValueChange = {
                                value = it
                            },
                            label = { Text("some value") }
                        )

                        Spacer(modifier = Modifier.size(24.dp))

                        Button(onClick = {
                            viewModel.onEvent(UIEvent.ShowDoneAnimation)
                            viewModel.termAndConditions = value
                            viewModel.putTransaction()
                        }) {
                            Text(text = "REQUEST")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SMEDetails(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .padding(30.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .heightIn(max = 700.dp)
            .fillMaxWidth()
        ,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "SME Buying/sponsoring",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.size(30.dp))
            Column(
                modifier = Modifier
//                    .heightIn(min = 500.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "Company Name",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "Credit Score",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "Contact",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "Company Description",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(12.dp))
                if(!viewModel.state.value.isRoleSME) {
                    var value by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = value,
                        onValueChange = {
                            value = it
                        },
                        label = { Text("some value") }
                    )
                    Spacer(modifier = Modifier.size(12.dp))

                    Button(onClick = {
                        viewModel.onEvent(UIEvent.ShowDoneAnimation)
                        viewModel.termAndConditions = value
                        viewModel.putTransaction()
                    }) {
                        Text(text = "SPONSOR")
                    }
                }
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
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 30.dp),
//                            text = "Share files instantly\n" +
//                                    "with Jedi Share",
                text = "Login",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge)

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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row {
                    Text(text = "Don't have an account?")
                    Text(text = "Sign Up (SME)", color = Color.Blue, modifier = Modifier.clickable {
                        viewModel.onEvent(UIEvent.HideSignIn)
                        viewModel.onEvent(UIEvent.ShowSignUp)
                        viewModel.onEvent(UIEvent.RoleNotFT)
                    })
                }

                Text(text = "Sign Up (Trade/Fintech)", color = Color.Blue, modifier = Modifier.clickable {
                    viewModel.onEvent(UIEvent.HideSignIn)
                    viewModel.onEvent(UIEvent.ShowSignUp)
                    viewModel.onEvent(UIEvent.RoleFT)
                })

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
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(viewModel.state.value.isRoleSME) {
                Text(
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 30.dp),
//                            text = "Share files instantly\n" +
//                                    "with Jedi Share",
                    text = "Sign Up SME",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(text = "Please Sign Up to Continue")


//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//        if(smeOrTadeFintech) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Text(text = "Sign Up SME")

                    var email by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
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

                    var BID by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = BID,
                        onValueChange = {
                            BID = it
                        },
                        label = { Text("BID") }
                    )

                    var companyName by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = companyName,
                        onValueChange = {
                            companyName = it
                        },
                        label = { Text("Company Name") }
                    )

                    var username by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        label = { Text("Username") }
                    )

                    var password by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text("Password") }
                    )
//                    var email by rememberSaveable { mutableStateOf("") }
//                    TextField(
//                        value = email,
//                        onValueChange = {
//                            email = it
//                        },
//                        label = { Text("Email") }
//                    )
//
//                    var contact by rememberSaveable { mutableStateOf("") }
//                    TextField(
//                        value = contact,
//                        onValueChange = {
//                            contact = it
//                        },
//                        label = { Text("Contact") }
//                    )
//
//                    var username by rememberSaveable { mutableStateOf("") }
//                    TextField(
//                        value = username,
//                        onValueChange = {
//                            username = it
//                        },
//                        label = { Text("Username") }
//                    )
//
//                    var password by rememberSaveable { mutableStateOf("") }
//                    TextField(
//                        value = password,
//                        onValueChange = {
//                            password = it
//                        },
//                        label = { Text("Password") }
//                    )

                    Button(onClick = {
                        viewModel.onEvent(UIEvent.HideSignUp)
                        viewModel.onEvent(UIEvent.ShowFTMarket)
                        viewModel.putSignUp(username, email, password)
                        viewModel.putRegisterSME(BID, companyName, contact)
                    }) {
                        Text(text = "SIGN UP")
                    }
                }
            } else {
                Text(
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 30.dp),
                    text = "Sign Up Trade/Fintech",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(text = "Please Sign Up to Continue")


//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//        if(smeOrTadeFintech) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var email by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
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

                    var BID by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = BID,
                        onValueChange = {
                            BID = it
                        },
                        label = { Text("BID") }
                    )

                    var companyName by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = companyName,
                        onValueChange = {
                            companyName = it
                        },
                        label = { Text("Company Name") }
                    )

                    var username by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        label = { Text("Username") }
                    )

                    var password by rememberSaveable { mutableStateOf("") }
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text("Password") }
                    )

                    Button(onClick = {
                        viewModel.onEvent(UIEvent.HideSignUp)
                        viewModel.onEvent(UIEvent.ShowSMEMarket)
                        viewModel.putSignUp(username, email, password)
                        viewModel.putRegisterFT(BID, companyName, contact)
                    }) {
                        Text(text = "SIGN UP")
                    }
                }
            }
        }
    }
}

@Composable
fun Welcome(viewModel: ViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = Modifier
                        .padding(top = 60.dp),
                    text = "Welcome to",
                    style = MaterialTheme.typography.headlineMedium
                    ,
                    color = Color.Black,
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 5.dp),
                    text = "Commerce Simplified",
                    textDecoration = TextDecoration.Underline,
//                    color = Color(0xFFec1c22),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
//                                textDecoration = TextDecoration.Underline
                )
            }

            Column (
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(
                    modifier = Modifier
//                                    .shadow(elevation = 8.dp, shape = CircleShape)
//                                    .size(280.dp)
//                                    .clip(CircleShape)
//                                    .background(MyRedSecondary)
//                                    .background(Color.White)
                ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.welcome_image),
//                                    contentDescription = "Image with Shadow",
//                                    modifier = Modifier.fillMaxSize(),
//                                )
//                    AnimatedPreloader(modifier = Modifier.size(400.dp), R.raw.welcome_activity_animation)
                }

                Text(
                    modifier = Modifier
                        .padding(bottom = 30.dp),
//                            text = "Share files instantly\n" +
//                                    "with Jedi Share",
                    text = "Quickly transfer photos, videos, documents, audio files",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium)

            }


//                        Text(
//                            text = "Jedi Share is the ultimate file sharing app, designed to make sharing files a breeze.",
//                            textAlign = TextAlign.Center,
//                            style = MaterialTheme.tyopography.h6)


            Button(
                onClick = {
//                    val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
//                    startActivity(intent)
                viewModel.onEvent(UIEvent.ShowSignIn)
                viewModel.onEvent(UIEvent.HideFirstTime)
                },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(50),
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFec1c22)),
            ) {
                Text(
                    text = "Sign In",
                    style=MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }






//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Column {
//            Row(modifier = Modifier.fillMaxWidth()) {
//                Text(text = "WELCOME")
//            }
//            Button(onClick = { /*TODO*/ }) {
//                Text(text = "Tutorial")
//            }
//
//            Button(
//                onClick = {
////                    val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
////                    startActivity(intent)
//                },
//                modifier = Modifier
//                    .padding(20.dp)
//                    .fillMaxWidth(),
//                shape = RoundedCornerShape(50),
////                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFec1c22)),
//            ) {
//                Text(
//                    text = "Sign In",
//                    style=MaterialTheme.typography.headlineMedium,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//            Button(onClick = {
////                                context.startActivity(Intent(context, SignInActivity::class.java))
//                viewModel.onEvent(UIEvent.ShowSignIn)
//                viewModel.onEvent(UIEvent.HideFirstTime)
////                PreferencesManager.firstTime = false
//            },
////                colors = ButtonDefaults.buttonColors(
////                    contentColor = MaterialTheme.colorScheme.primaryContainer
////                )
//                ) {
//                Text(text = "Sign In")
//            }
////            Button(onClick = {
////                viewModel.onEvent(UIEvent.ShowSignUp)
////                viewModel.onEvent(UIEvent.HideFirstTime)
//////                PreferencesManager.firstTime = false
//////                val intent = Intent(context, SignUpActivity::class.java)
//////                intent.putExtra("SME/TradeFintech", true)
//////                context.startActivity(intent)
////            }) {
////                Text(text = "Sign Up SME")
////            }
////            Button(onClick = {
////                viewModel.onEvent(UIEvent.ShowSignUp)
////                viewModel.onEvent(UIEvent.HideFirstTime)
//////                PreferencesManager.firstTime = false
//////                val intent = Intent(context, SignUpActivity::class.java)
//////                intent.putExtra("SME/TradeFintech", false)
//////                context.startActivity(intent)
////            }) {
////                Text(text = "Sign Up Trade Fintech")
////            }
//        }
//    }
}
