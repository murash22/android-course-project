package com.example.helloworld.features.patients.home_screen.presentation


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.helloworld.PatientRoutes
import com.example.helloworld.Routes
import com.example.helloworld.core.ui.bottom_nav_bar.BottomNavBar
import com.example.helloworld.core.ui.survey_card.SurveyCard
import com.example.helloworld.data.doctorSurveys
import com.example.helloworld.features.patients.surveys_screen.presentation.PatientSurveysScreen
import com.example.helloworld.features.patients.take_survey_screen.presentation.PatientTakeSurveyScreen


@Preview(
    showBackground = true,
    apiLevel = 33
)
@Composable
fun PatientHomeScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomNavBar(nav = navController) },
    ) {paddingValues ->
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            composable(
                route = "home"
            ) {
                LazyColumn(modifier = Modifier.padding(paddingValues)) {
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

            composable(
                route = Routes.Other.route
            ) {
                PatientSurveysScreen(modifier = Modifier.padding(paddingValues))
            }

            composable(
                route = PatientRoutes.TakeSurvey.route + "/{${PatientRoutes.TakeSurvey.argName}}",
                arguments = listOf(
                    navArgument(PatientRoutes.TakeSurvey.argName) {
                        type = NavType.IntType
                        nullable = false
                    }
                )
            ) { entry ->
                PatientTakeSurveyScreen(
                    modifier = Modifier.padding(paddingValues),
                    nav = navController,
                    surveyCard = doctorSurveys[entry.arguments?.getInt(PatientRoutes.TakeSurvey.argName) ?: 0]
                )
            }
        }
    }
}

