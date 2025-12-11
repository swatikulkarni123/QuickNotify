package com.swa.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swa.quicknotify.core.QuickNotify
import com.swa.quicknotify.toast.QuickToast
import com.swa.sampleapp.ui.theme.ToastImageLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToastImageLibraryTheme {
                // QuickNotifyInit()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(20.dp)) {
                        Greeting("")

                        Button(onClick = {
                            Log.d("QuickToast", "Button click")
                            QuickNotify.showSnackbar(
                                "Hello from QuickNotify - snackbar!",
                                duration = 1000
                            )
                        }) {
                            Text("Show snackbar")
                        }

                        showCustomDialog()

                        customAlert()
                    }
                }
            }
        }
    }

    @Composable
    fun showCustomDialog() {
        val p = painterResource(R.drawable.ic_profile)
        Button(onClick = {
            Log.d("QuickToast", "Button click")
            QuickNotify.showDialog(
                title = "Delete Account?",
                body = "This action cannot be undone.",
                image = p,
                btn1Text = "Cancel",
                onBtn1Click = { /* handle */ },
                btn2Text = "Delete",
                btn2Color = Color.Red,
                onBtn2Click = { /* handle delete */ },
                btn3Text = "Continue",
                btn3Color = Color.Green,
                onBtn3Click = { /* handle Continue */ }
            )
        }) {
            Text("Show dialog")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val bitmap: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_profile)

    Button(onClick = {
        Log.d("QuickToast", "Button click")
        QuickNotify.showToast("Hello from QuickNotify!", duration = 5000, icon = bitmap)
    }) {
        Text("Show Toast")
    }
}


@Composable
fun customAlert() {

    val bitmap: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_profile)

    Button(onClick = {
        Log.d("QuickToast", "Show custom view as alert")
        QuickNotify.showOverlay(
            overlayAlignment = Alignment.Center,
            autoCancel = false,
            content = { dismiss ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF222222))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(Color(0xFF4CAF50), RoundedCornerShape(15.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = bitmap,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Success!",
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "Your data has been saved successfully.",
                                    color = Color(0xFFBDBDBD),
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Icon(
                                imageVector = bitmap,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.size(20.dp).clickable(
                                    onClick = {
                                        dismiss()
                                        QuickNotify.showToast("Clicked")
                                    }
                                )
                            )
                        }
                    }
                }
            }
        )

    }) {
        Text("Show custom view as alert")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToastImageLibraryTheme {
        Greeting("Android")
    }
}