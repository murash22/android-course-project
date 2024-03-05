package com.example.helloworld.features.general.password_screen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.core.ui.bottom_nav_bar.BottomNavBar

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PasswordScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                "Здравствуйте, Максим!",
                modifier = Modifier
                    .padding(top = 145.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = 31.dp, bottom = 103.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..4) {
                        Circle(Color(0xFFD9D9D9))
                        if (i < 4) Spacer(Modifier.width(22.dp))
                    }
                }
            }
            CenterRow(
                { ClickableNumberButton(text = "1") },
                { ClickableNumberButton(text = "2") },
                { ClickableNumberButton(text = "3") }
            )
            CenterRow(
                { ClickableNumberButton(text = "4") },
                { ClickableNumberButton(text = "5") },
                { ClickableNumberButton(text = "6") }
            )
            CenterRow(
                { ClickableNumberButton(text = "7") },
                { ClickableNumberButton(text = "8") },
                { ClickableNumberButton(text = "9") }
            )
            CenterRow(
                {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(75.dp)
                            .background(color = Color(0x00D9D9D9))
                    ) {}
                },
                { ClickableNumberButton(text = "0") },
                { ClickableNumberButton(text = "<") }
            )
        }
    }
}

@Composable
fun Circle(color: Color) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .background(color = color, shape = MaterialTheme.shapes.small)
    )
}

@Composable
fun ClickableNumberButton(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(75.dp)
            .clickable(onClick = {
                //TODO: добавить функцию для нажатия на кнопку (уже после создания viewmodel)
            })
            .background(color = Color(0xFFD9D9D9), shape = CircleShape)
            .clip(CircleShape)
    ) {
        Text(text = text, fontSize = 18.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun CenterRow(vararg children: @Composable () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .widthIn(300.dp)
                .padding(top = 35.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (child in children) {
                child()
            }
        }
    }
}
