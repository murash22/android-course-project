package com.example.helloworld.features.doctor

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworld.DoctorRoutes
import com.example.helloworld.Routes
import com.example.helloworld.core.navigation.NavItem
import com.example.helloworld.core.ui.bottom_nav_bar.BottomNavBar
import com.example.helloworld.features.doctor.check_survey.presentation.CheckSurveyScreen
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorHomeScreen


val navItems: List<NavItem<ImageVector>> = listOf(
    NavItem(
        route = Routes.Home.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    NavItem(
        route = Routes.Other.route,
        selectedIcon =  Icons.Filled.AccountBox,
        unselectedIcon = Icons.Outlined.AccountBox
    )
)

@Composable
fun DoctorScreen(
    modifier: Modifier = Modifier,
    doctorId: String,
    doctorViewModel: DoctorViewModel = viewModel(),
) {
    val doctor = doctorViewModel.getDoctor(doctorId)
    val surveys = doctorViewModel.getSurveys(doctorId)
    val navController = rememberNavController()

    Scaffold (
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                navItems = navItems,
                visibleScreens = listOf(Routes.Home, Routes.Other)
            )
        },
    ) {paddingValues ->
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            composable(
                route = Routes.Home.route,
            ) {
                DoctorHomeScreen(
                    modifier = Modifier.padding(paddingValues),
                    navController = navController,
                    doctorName =  doctor.name,
                    surveys = surveys.filter { it.feedback.isEmpty() && it.completed }
                )
            }

//            composable(
//                route = Routes.Other.route
//            ) {
//                PatientSurveysScreen(
//                    modifier = Modifier.padding(paddingValues),
//                    surveys = surveys
//                )
//            }
//
//            composable(
//                route = DoctorRoutes.CheckSurvey.route + "/{${DoctorRoutes.CheckSurvey.argName}}",
//                arguments = listOf(
//                    navArgument(DoctorRoutes.CheckSurvey.argName) {
//                        type = NavType.StringType
//                        nullable = false
//                    }
//                )
//            ) { entry ->
//                CheckSurveyScreen(
//                    navController = navController,
//                    surveyCard = surveys.find { it.id == (entry.arguments?.getString(DoctorRoutes.CheckSurvey.argName) ?: "") }!!,
//                    modifier = Modifier.padding(paddingValues)
//                )
//            }
        }
    }
}