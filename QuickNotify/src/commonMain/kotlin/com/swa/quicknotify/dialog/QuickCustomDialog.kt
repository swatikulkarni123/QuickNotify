package com.swa.quicknotify.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun QuickCustomDialog(
    onDismiss: () -> Unit,

    topImage: Painter? = null,

    title: String,
    body: String,

    shape: Shape = RoundedCornerShape(16.dp),

    btn1Text: String? = null,
    btn1Color: Color = Color(0xFF1976D2),
    btn1Icon: ImageVector? = null,
    onBtn1Click: (() -> Unit)? = null,

    btn2Text: String? = null,
    btn2Color: Color = Color(0xFF388E3C),
    btn2Icon: ImageVector? = null,
    onBtn2Click: (() -> Unit)? = null,

    btn3Text: String? = null,
    btn3Color: Color = Color(0xFFD32F2F),
    btn3Icon: ImageVector? = null,
    onBtn3Click: (() -> Unit)? = null,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Card(
                shape = shape,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (topImage != null) {
                        Image(
                            painter = topImage,
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(bottom = 12.dp),
                        )
                    }

                    Text(
                        text = title,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )

                    Text(
                        text = body,
                        fontSize = 15.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(bottom = 20.dp),
                    )

                    if (btn1Text != null || btn2Text != null || btn3Text != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            if (btn1Text != null) {
                                QuickDialogButton(
                                    text = btn1Text,
                                    containerColor = btn1Color,
                                    icon = btn1Icon,
                                    onClick = { onBtn1Click?.invoke() },
                                )
                            }
                            if (btn2Text != null) {
                                QuickDialogButton(
                                    text = btn2Text,
                                    containerColor = btn2Color,
                                    icon = btn2Icon,
                                    onClick = { onBtn2Click?.invoke() },
                                )
                            }
                            if (btn3Text != null) {
                                QuickDialogButton(
                                    text = btn3Text,
                                    containerColor = btn3Color,
                                    icon = btn3Icon,
                                    onClick = { onBtn3Click?.invoke() },
                                )
                            }
                        }
                    }
                }
            }

            // Show close icon when no action buttons are provided
            if (btn1Text == null && btn2Text == null && btn3Text == null) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(26.dp)
                        .clickable { onDismiss() },
                )
            }
        }
    }
}
