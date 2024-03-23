package com.example.helloworld.features.patient.home_screen.presentation


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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloworld.PatientRoutes
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS
import com.example.helloworld.data.UserRole


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
                text = "Привет, $patientName!",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.weight(1f))

//            Icon(
//                modifier = Modifier
//                    .size(28.dp)
//                    .clickable {  },
//                imageVector = Icons.Outlined.Info,
//                contentDescription = null
//            )

        }
        if (surveys.isNotEmpty()) {
            LazyColumn {
                items(surveys) { item ->
                    SurveyCard(
                        title = "Опрос от доктора ${USERS.filter { it.id == item.doctorID && it.role == UserRole.Doctor }[0].name}",
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
                text = "Нет доступных опросов.",
                fontSize = 20.sp,
            )
        }
    }
}

