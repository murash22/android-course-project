package com.patients.main.features.patient.home_screen.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patients.main.PatientRoutes
import com.patients.main.R
import com.patients.main.core.ui.about_button.AboutButton
import com.patients.main.core.ui.survey_card.SurveyCard
import com.patients.main.data.SurveyDTO
import com.patients.main.data.USERS
import com.patients.main.data.UserRole


//@Preview(
//    showBackground = true,
//)
@Composable
fun PatientHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    patientName: String,
    surveys: List<SurveyDTO>,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.hello_name, patientName),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            AboutButton(modifier = Modifier)
        }
        if (surveys.isNotEmpty()) {
            LazyColumn {
                items(surveys) { item ->
                    SurveyCard(
                        title = stringResource(
                            R.string.survey_from_doctor_name,
                            USERS.find { it.id == item.doctorID && it.role == UserRole.Doctor }!!.name
                        ),
                        body = item.title,
                    ) {
                        navController.navigate(
                            route = PatientRoutes.TakeSurvey.takeSurveyWithArg(
                                item.id
                            )
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

