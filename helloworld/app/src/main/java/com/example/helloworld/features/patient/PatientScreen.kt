package com.example.helloworld.features.patient

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.helloworld.PatientRoutes
import com.example.helloworld.Routes
import com.example.helloworld.core.navigation.NavItem
import com.example.helloworld.core.ui.bottom_nav_bar.BottomNavBar
import com.example.helloworld.data.doctorSurveys
import com.example.helloworld.features.patient.home_screen.presentation.PatientHomeScreen
import com.example.helloworld.features.patient.surveys_screen.presentation.PatientSurveysScreen
import com.example.helloworld.features.patient.take_survey_screen.presentation.PatientTakeSurveyScreen

val navItems: List<NavItem> = listOf(
    NavItem(
        route = Routes.Home.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    NavItem(
        route = Routes.Other.route,
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle
    )
)

@Composable
fun PatientScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold (
        modifier = modifier,
        bottomBar = { BottomNavBar(navController = navController, navItems = navItems) },
    ) {paddingValues ->
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            composable(
                route = Routes.Home.route
            ) {
                PatientHomeScreen(
                    modifier = Modifier.padding(paddingValues),
                    navController = navController,
//                    surveys = surveys
                )
            }

            composable(
                route = Routes.Other.route
            ) {
                PatientSurveysScreen(
                    modifier = Modifier.padding(paddingValues)
                )
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