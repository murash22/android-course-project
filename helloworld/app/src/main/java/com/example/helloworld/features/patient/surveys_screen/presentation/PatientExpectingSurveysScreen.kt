package com.example.helloworld.features.patient.surveys_screen.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS
import com.example.helloworld.data.UserRole


@Composable
fun PatientExpectingSurveysScreen(
    modifier: Modifier = Modifier,
    surveys: List<SurveyDTO>
) {
    LazyColumn {
        items(surveys) {survey ->
            SurveyCard(
                title = "Опрос от доктора ${USERS.filter { it.id == survey.doctorID && it.role == UserRole.Doctor }[0].name}",
                body = survey.title,
            )
        }
    }
}


