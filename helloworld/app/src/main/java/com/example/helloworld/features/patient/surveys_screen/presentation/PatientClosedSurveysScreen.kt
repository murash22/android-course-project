package com.example.helloworld.features.patient.surveys_screen.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.helloworld.R
import com.example.helloworld.data.SurveyDTO


data class ExpandableSurvey(
    val title: String,
    val body: String,
    val surveyName: String,
    val closeDate: String,
    val closeTime: String
)

//val expandableSurveys = listOf(
//    ExpandableSurvey(
//        title = "Фидбек от доктора Заславского #4142",
//        body = "я рад, что все хорошо!",
//        surveyName = "как настроение?",
//        closeDate = "22.02.2022",
//        closeTime = "11:00"
//    ),
//    ExpandableSurvey(
//        title = "Фидбек от доктора Заславского #5923",
//        body = "я рад, что все хорошо!",
//        surveyName = "как настроение?",
//        closeDate = "22.02.2022",
//        closeTime = "11:00"
//    ),
//    ExpandableSurvey(
//        title = "Фидбек от доктора Заславского #4142",
//        body = "я рад, что все хорошо!",
//        surveyName = "как настроение?",
//        closeDate = "22.02.2022",
//        closeTime = "11:00"
//    ),
//)

@Composable
fun PatientClosedSurveysScreen(
    modifier: Modifier = Modifier,
    surveys: List<SurveyDTO>
) {
    LazyColumn {
        items(surveys) {
            ExpandableSurveyCard(expandableSurvey = it)
        }
    }

}


@Composable
private fun ExpandableSurveyCard(
    expandableSurvey: SurveyDTO
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
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
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,

                ) {
                Text(
                    text = expandableSurvey.title,
                    style = TextStyle(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.feedback, expandableSurvey.feedback),
                    style = TextStyle(fontSize = 18.sp)
                )
                if(expanded) {
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "Опрос: ${expandableSurvey.title}",
//                        style = TextStyle(fontSize = 18.sp)
//                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.close_date, expandableSurvey.closeDate ?: ""),
                        style = TextStyle(fontSize = 18.sp)
                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "Дата открытия: ${expandableSurvey.openDate}",
//                        style = TextStyle(fontSize = 18.sp)
//                    )
                }
            }
            IconButton(
                modifier = Modifier,
                onClick = { expanded = !expanded},
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