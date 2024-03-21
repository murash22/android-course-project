package com.example.helloworld.features.patient.home_screen.presentation


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.helloworld.PatientRoutes
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.doctorSurveys


//@Preview(
//    showBackground = true,
//    apiLevel = 33
//)
@Composable
fun PatientHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
//    surveys: List<Any>
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(doctorSurveys) { index, item ->
            SurveyCard(
                title = item.title,
                body = item.body,
            ) {
                navController.navigate(
                    route = PatientRoutes.TakeSurvey.takeSurveyWithArg(
                        index
                    )
                )
            }
        }
    }

}

