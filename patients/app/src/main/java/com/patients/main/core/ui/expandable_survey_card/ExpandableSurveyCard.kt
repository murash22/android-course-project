package com.patients.main.core.ui.expandable_survey_card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patients.main.R
import com.patients.main.data.SurveyDTO

private fun getStringSlice(str: String, len: Int) : String {
    if (str.length > len) {
        return str.slice(0..len)
    }
    return str
}

@Composable
fun ExpandableSurveyCard(
    expandableSurvey: SurveyDTO
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val maxLen = 17
    var feedback by rememberSaveable { mutableStateOf(getStringSlice(expandableSurvey.feedback, maxLen)) }
    Surface(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalAlignment = Alignment.Start,

                ) {
                Text(
                    text = expandableSurvey.title,
                    style = TextStyle(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.feedback, feedback),
                    style = TextStyle(fontSize = 18.sp)
                )
                if(expanded) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.close_date, expandableSurvey.closeDate ?: ""),
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            }
            IconButton(
                modifier = Modifier.fillMaxWidth(0.3f),
                onClick = {
                    expanded = !expanded
                    if (expanded) {
                        feedback = expandableSurvey.feedback
                    } else {
                        feedback = getStringSlice(expandableSurvey.feedback, maxLen)
                    }
                },
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

    }
}