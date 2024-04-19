package com.invincible.diabolictale

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.invincible.diabolictale.ui.theme.DiabolicTaleTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiabolicTaleTheme {

                val context = LocalContext.current

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
                                context.startActivity(Intent(context, SignInActivity::class.java))
                            }) {
                                Text(text = "Sign In")
                            }
                            Button(onClick = {
                                val intent = Intent(context, SignUpActivity::class.java)
                                intent.putExtra("SME/TradeFintech", true)
                                context.startActivity(intent)
                            }) {
                                Text(text = "Sign Up SME")
                            }
                            Button(onClick = {
                                val intent = Intent(context, SignUpActivity::class.java)
                                intent.putExtra("SME/TradeFintech", false)
                                context.startActivity(intent)
                            }) {
                                Text(text = "Sign Up Trade Fintech")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    DiabolicTaleTheme {
        Greeting2("Android")
    }
}