package com.example.helloworld.core.ui.bottom_nav_bar

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.helloworld.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier
) {
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
                tint = Color(0xFF3C9AFB),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = {
                    //TODO: навигация
                })
            )
            Icon(
                painter = painterResource(id = +R.drawable.patients),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = {
                    //TODO: навигация
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