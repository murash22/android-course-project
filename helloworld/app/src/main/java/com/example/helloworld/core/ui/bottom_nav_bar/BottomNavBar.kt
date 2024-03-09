package com.example.helloworld.core.ui.bottom_nav_bar

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.helloworld.R
import com.example.helloworld.Routes

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    nav: NavController
) {
    val colors = listOf(
        Color(0xFF3C9AFB),
        Color.Black
    )
    var homeColor by remember {
        mutableStateOf(colors[0])
    }
    var otherColor by remember {
        mutableStateOf(colors[1])
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(59.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Divider(
            color = LocalContentColor.current.copy(alpha = 0.12f),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = +R.drawable.home_filled),
                tint = homeColor,
                contentDescription = null,
                modifier = Modifier.clickable(onClick = {
                    homeColor = colors[0]
                    otherColor = colors[1]
                    nav.navigate(Routes.Home.route)
                })
            )
            Icon(
                painter = painterResource(id = +R.drawable.patients),
                tint = otherColor,
                contentDescription = null,
                modifier = Modifier.clickable(onClick = {
                    homeColor = colors[1]
                    otherColor = colors[0]
                    nav.navigate(Routes.Other.route)
                })
            )
        }
        Divider(
            color = LocalContentColor.current.copy(alpha = 0.12f),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}