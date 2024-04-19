package com.invincible.diabolictale

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.invincible.diabolictale.ui.theme.DiabolicTaleTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiabolicTaleTheme {

                val context = LocalContext.current
                val smeOrTadeFintech = intent.getBooleanExtra("SME/TradeFintech", false)

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if(smeOrTadeFintech) {
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
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                }) {
                                    Text(text = "SIGN UP")
                                }
                            }
                        } else {
                            Column {
                                Text(text = "Sign Up Trade Fintech")

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

                                var bid by rememberSaveable { mutableStateOf("") }
                                TextField(
                                    value = bid,
                                    onValueChange = {
                                        bid = it
                                    },
                                    label = { Text("BID") }
                                )

                                Button(onClick = {
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                }) {
                                    Text(text = "SIGN UP")
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
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    DiabolicTaleTheme {
        Greeting4("Android")
    }
}