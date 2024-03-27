package com.patients.main.core.ui.survey_card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SurveyCard(
    title: String,
    body: String,
    borderColor: Color = Color.Unspecified,
    onClick: (() -> Unit)? = null
) {
    var modifier = Modifier
        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        .fillMaxWidth()
    if (onClick != null) {
        modifier = modifier
           .clickable(onClick = onClick)
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.Black),
        color = borderColor
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = body,
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}
