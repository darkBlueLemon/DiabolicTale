package com.invincible.diabolictale

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.invincible.diabolictale.ui.theme.DiabolicTaleTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiabolicTaleTheme {

                val context = LocalContext.current

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
//                        val context = LocalContext.current
//                        context.startActivity(Intent(context, WelcomeActivity::class.java))
                        Column {
                            Text(text = "Login")
                            Text(text = "Please Sign In to Continue")

                            var text by rememberSaveable { mutableStateOf("") }
                            TextField(
                                value = text,
                                onValueChange = {
                                    text = it
                                },
                                label = { Text("EMAIL") }
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
                                context.startActivity(Intent(context, MainActivity::class.java))
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
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    DiabolicTaleTheme {
        Greeting3("Android")
    }
}