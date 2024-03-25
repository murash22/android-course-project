package com.example.helloworld.core.ui.search_text_field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.helloworld.R

@Composable
fun SearchTextField(
    onSearch: (String) -> Unit,
    label: String = "Поиск",
    modifier: Modifier = Modifier,
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = +R.drawable.search),
                    contentDescription = null
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            value = searchText,
            onValueChange = {
                input -> searchText = input
                onSearch(searchText)
            },
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
//            keyboardActions = KeyboardActions(
//                onDone = {  onSearch(searchText) }
//            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)

        )
    }
}