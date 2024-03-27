package com.patients.main.features.patient.surveys_screen.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import com.patients.main.core.ui.expandable_survey_card.ExpandableSurveyCard
import com.patients.main.data.SurveyDTO


data class ExpandableSurvey(
    val title: String,
    val body: String,
    val surveyName: String,
    val closeDate: String,
    val closeTime: String
)


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


