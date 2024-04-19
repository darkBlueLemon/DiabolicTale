package com.invincible.diabolictale

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.invincible.diabolictale.ui.theme.DiabolicTaleTheme

class MainActivity : ComponentActivity() {
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
                            Text(text = "HOME PAGE")
                            LazyColumn (modifier = Modifier.fillMaxWidth()){
                                items(20) { item ->
                                    Column (
                                        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable {  }
                                    ) {
                                        Text(text = "SWE", fontWeight = FontWeight.Bold)
                                        Text(text = "Contracts")
                                        Text(text = "Terms")
                                        Text(text = "Online Applications")
                                        Text(text = "")
                                    }
                                }
                            }
                            Button(onClick = {
                                context.startActivity(Intent(context, WelcomeActivity::class.java))
                            }) {
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiabolicTaleTheme {
        Greeting("Android")
    }
}