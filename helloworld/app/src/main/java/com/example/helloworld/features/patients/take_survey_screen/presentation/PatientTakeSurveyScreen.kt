package com.example.helloworld.features.patients.take_survey_screen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloworld.data.DoctorSurvey
import com.example.helloworld.data.Question

val tmp = DoctorSurvey(
    title = "Опрос от доктора Заславского",
    body = "Ежедневная проверка состояния",
    questions = listOf(
        Question("Вы принимали сегодня таблетки", listOf("Да", "Нет")),
        Question("Дышали на свежем воздухе?", listOf("Да", "Нет"))
    )
)

//@Preview(showBackground = true)
@Composable
fun PatientTakeSurveyScreen(
    modifier: Modifier = Modifier,
    surveyCard: DoctorSurvey = tmp,
    nav: NavController,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .alpha(0.6f)
                    .clickable { nav.popBackStack() },
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = surveyCard.title
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { nav.popBackStack() }) {
                Text("Завершить")
            }
        }

        Text(
            text = surveyCard.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 9.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        LazyColumn {
            items(surveyCard.questions) {
                QuestionCard(question = it)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun QuestionCard(
    modifier: Modifier = Modifier,
    question: Question = tmp.questions[0],
) {
    var selectedAns by remember{
        mutableStateOf(true)
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        text = question.name,
        textAlign = TextAlign.Center
    )
    Column{
        for (q in question.options) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),//.border(BorderStroke(1.dp, Color.Black)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = true,
                    onClick = { /*TODO*/ }
                )
                Text(text = q)
            }
        }
    }
}
