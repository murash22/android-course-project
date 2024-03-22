package com.example.helloworld.core.ui.search_text_field

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.helloworld.R

@Composable
fun SearchTextField() {
    var searchText by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .border(1.dp, Color.Black)
    ) {
        Icon(
            painter = painterResource(id = +R.drawable.search),
            contentDescription = null,
            modifier = Modifier.padding(start = 9.dp, end = 9.dp)
        )
        TextField(
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            value = searchText,
            onValueChange = {input -> searchText = input},
            placeholder = { Text(text = "Поиск...") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .then(Modifier.background(Color.White))
        )
    }
}