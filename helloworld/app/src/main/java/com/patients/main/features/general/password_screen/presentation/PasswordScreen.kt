package com.patients.main.features.general.password_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.patients.main.Routes
import com.patients.main.core.UserViewModel

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PasswordScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel(),
) {
    var currentInput by rememberSaveable {
        mutableStateOf("")
    }
    val onNumberClick: (String) -> Unit = { number ->
        currentInput += number
        if (currentInput.length == 4) {
            val user = userViewModel.getUserByPIN(currentInput)
            if (user != null) {
                navController.popBackStack(Routes.PasswordScreen.route, true)
                navController.navigate(user.first.role + "/${user.second}")
            } else {
                currentInput = ""
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                stringResource(R.string.welcome),
                modifier = Modifier
                    .padding(top = 145.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = 31.dp, bottom = 70.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..4) {
                        Circle(
                            if (i <= currentInput.length) Color.Black
                            else Color(0xFFD9D9D9)
                        )
                        if (i < 4) Spacer(Modifier.width(22.dp))
                    }
                }
            }
            CenterRow(
                { ClickableNumberButton(text = "1", onClick = { onNumberClick("1") }) },
                { ClickableNumberButton(text = "2", onClick = { onNumberClick("2") }) },
                { ClickableNumberButton(text = "3", onClick = { onNumberClick("3") }) }
            )
            CenterRow(
                { ClickableNumberButton(text = "4", onClick = { onNumberClick("4") }) },
                { ClickableNumberButton(text = "5", onClick = { onNumberClick("5") }) },
                { ClickableNumberButton(text = "6", onClick = { onNumberClick("6") }) }
            )
            CenterRow(
                { ClickableNumberButton(text = "7", onClick = { onNumberClick("7") }) },
                { ClickableNumberButton(text = "8", onClick = { onNumberClick("8") }) },
                { ClickableNumberButton(text = "9", onClick = { onNumberClick("9") }) }
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
                { ClickableNumberButton(text = "0", onClick = { onNumberClick("7") }) },
                { ClickableNumberButton(text = "<", onClick = {
                    if (currentInput.isNotEmpty()) {
                        currentInput = currentInput.dropLast(n = 1)
                    }
                }) }
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
fun ClickableNumberButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .size(75.dp)
            .background(color = Color(0xFFD9D9D9), shape = CircleShape)
            .clip(CircleShape)
    ) {
        Text(text = text, fontSize = 18.sp, textAlign = TextAlign.Center, color = Color.Black)
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
