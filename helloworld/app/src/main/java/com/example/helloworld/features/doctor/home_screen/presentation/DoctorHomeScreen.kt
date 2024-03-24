package com.example.helloworld.features.doctor.home_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloworld.DoctorRoutes
import com.example.helloworld.R
import com.example.helloworld.core.ui.about_button.AboutButton
import com.example.helloworld.core.ui.search_text_field.SearchTextField
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DoctorHomeScreen(
    navController: NavController,
    doctorName: String,
    surveys: List<SurveyDTO>,
    modifier: Modifier = Modifier
) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.hello_name, doctorName),
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.weight(1f))

                AboutButton(modifier = Modifier)
            }
            SearchTextField(
                onSearch = {}
            )
            if (surveys.isNotEmpty()) {
                LazyColumn {
                    items(surveys) { item ->
                        SurveyCard(
                            title = item.title,
                            body = stringResource(
                                R.string.patient_name,
                                USERS.find { it.id == item.patientID }!!.name
                            ),
                        ) {
                            navController.navigate(
                                DoctorRoutes.CheckSurvey.checkSurveyWithArg(item.id)
                            )
                        }
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

    }
}

