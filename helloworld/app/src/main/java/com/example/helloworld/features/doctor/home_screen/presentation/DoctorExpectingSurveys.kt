package com.example.helloworld.features.doctor.home_screen.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.R
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS

@Composable
fun DoctorExpectingSurveys(
    surveys: List<SurveyDTO>,
    modifier: Modifier = Modifier
) {
    if (surveys.isNotEmpty()) {
        LazyColumn {
            items(surveys) { item ->
                SurveyCard(
                    title = item.title,
                    body = stringResource(
                        R.string.patient_name,
                        USERS.find { it.id == item.patientID }!!.name
                    ),
                )
            }
        }
    } else {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.no_available_survey),
            fontSize = 20.sp,
        )
    }
}