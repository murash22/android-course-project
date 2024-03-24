package com.example.helloworld.core.ui.about_button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AboutButton(
    modifier: Modifier = Modifier,
) {
    var isDialogShown by rememberSaveable {
        mutableStateOf(false)
    }
    val developers = listOf(
        "Мыратгелдиев Ашыр", "Торопыгин Антон", "Тишкин Максим"
    )
    IconButton(onClick = { isDialogShown = true }) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Outlined.Info,
            contentDescription = "About"
        )
    }
    if (isDialogShown) {
        Dialog(
            onDismissRequest = { isDialogShown = false }
        ) {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = "Разработчики"
                    )
                    for (dev in developers) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                fontSize = 20.sp,
                                text = dev
                            )
                        }
                    }
                }
            }
        }
    }
}