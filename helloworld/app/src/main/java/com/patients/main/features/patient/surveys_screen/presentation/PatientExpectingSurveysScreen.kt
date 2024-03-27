package com.patients.main.features.patient.surveys_screen.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.patients.main.R
import com.patients.main.core.ui.survey_card.SurveyCard
import com.patients.main.data.SurveyDTO
import com.patients.main.data.USERS
import com.patients.main.data.UserRole


@Composable
fun PatientExpectingSurveysScreen(
    modifier: Modifier = Modifier,
    surveys: List<SurveyDTO>
) {
    LazyColumn {
        items(surveys) {survey ->
            SurveyCard(
                title = stringResource(
                    R.string.survey_from_doctor_name,
                    USERS.filter { it.id == survey.doctorID && it.role == UserRole.Doctor }[0].name
                ),
                body = survey.title,
            )
        }
    }
}


