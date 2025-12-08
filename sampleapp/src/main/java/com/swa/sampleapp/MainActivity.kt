package com.swa.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.swa.quicknotify.core.QuickNotify
import com.swa.sampleapp.ui.theme.ToastImageLibraryTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToastImageLibraryTheme {
               // QuickNotifyInit()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Button(onClick = {
        Log.d("QuickToast", "Button click")
        QuickNotify.showToast( "Hello from QuickNotify!", duration = 5000)
    }) {
        Text("Show Toast")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToastImageLibraryTheme {
        Greeting("Android")
    }
}