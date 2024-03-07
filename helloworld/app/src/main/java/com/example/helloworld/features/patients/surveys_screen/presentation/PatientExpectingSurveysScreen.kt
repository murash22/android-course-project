package com.example.helloworld.features.patients.surveys_screen.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.helloworld.core.ui.survey_card.SurveyCard


@Composable
fun PatientExpectingSurveysScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(3) {
            SurveyCard(title = "Доктор ${it+1}", body ="Ваше самочувствие в ${it+2}-й день недели?")
        }
    }
}


