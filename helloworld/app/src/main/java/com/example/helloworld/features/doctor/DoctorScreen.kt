package com.example.helloworld.features.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.helloworld.DoctorRoutes
import com.example.helloworld.R
import com.example.helloworld.Routes
import com.example.helloworld.core.navigation.NavItem
import com.example.helloworld.core.ui.bottom_nav_bar.BottomNavBar
import com.example.helloworld.core.ui.top_bar.TopBar
import com.example.helloworld.features.doctor.check_survey.presentation.CheckSurveyScreen
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorClosedSurveys
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorExpectingSurveys
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorHomeTopNavBar
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorUncheckedSurveys


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
) {
    val doctorViewModel: DoctorViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DoctorViewModel(doctorId) as T
            }
        }
    )
    val doctor by doctorViewModel.doctor.collectAsState()
//    val surveys by doctorViewModel.surveys.collectAsState()

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination
    val topBarTitle = if (currentDestination?.route == Routes.Other.route) {
        stringResource(R.string.list_of_patients)
    } else {
        stringResource(R.string.hello_name, doctor.name)
    }

    var filteredSurveys by rememberSaveable {
        mutableStateOf(doctorViewModel.onSearchPatients(""))
    }
    Scaffold (
        modifier = modifier,
        topBar = {
            TopBar(
                title = topBarTitle,
                navController = navController,
                visibleScreens = listOf(
                    DoctorRoutes.UncheckedSurveys.route,
                    DoctorRoutes.ExpectingSurveys.route,
                    DoctorRoutes.ClosedSurveys.route
                ),
                onSearch = {
                    filteredSurveys = doctorViewModel.onSearchPatients(it)
                }
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                navItems = navItems,
                visibleScreens = listOf(
                    Routes.Home.route,
                    Routes.Other.route,
                    DoctorRoutes.UncheckedSurveys.route,
                    DoctorRoutes.ExpectingSurveys.route,
                    DoctorRoutes.ClosedSurveys.route
                )
            )
        },
    ) {paddingValues ->
        NavHost(navController = navController, startDestination = Routes.Home.route) {

            navigation(
                route = Routes.Home.route,
                startDestination = DoctorRoutes.UncheckedSurveys.route
            ) {
                composable(route = DoctorRoutes.UncheckedSurveys.route) {
                    Column(
                        modifier = modifier.padding(paddingValues)
                    ) {
                        DoctorHomeTopNavBar(navController = navController)
                        DoctorUncheckedSurveys(
                            navController = navController,
                            surveys = filteredSurveys.filter { it.completed && it.feedback.isEmpty()},
                            onCheckSurvey = {}
                        )
                    }
                }

                composable(route = DoctorRoutes.ExpectingSurveys.route) {
                    Column(
                        modifier = modifier.padding(paddingValues)
                    ) {
                        DoctorHomeTopNavBar(navController = navController)
                        DoctorExpectingSurveys(
                            surveys = filteredSurveys.filter { !it.completed }
                        )
                    }
                }

                composable(route = DoctorRoutes.ClosedSurveys.route) {
                    Column(
                        modifier = modifier.padding(paddingValues)
                    ) {
                        DoctorHomeTopNavBar(navController = navController)
                        DoctorClosedSurveys(surveys = filteredSurveys.filter { it.completed && it.feedback.isNotEmpty() })
                    }
                }
            }

            composable(
                route = DoctorRoutes.CheckSurvey.route + "/{${DoctorRoutes.CheckSurvey.argName}}",
                arguments = listOf(
                    navArgument(DoctorRoutes.CheckSurvey.argName ?: "id") {
                        type = NavType.StringType
                        nullable = false
                    }
                )
            ) { entry ->
                val surveyCard = filteredSurveys.find { it.id == (entry.arguments?.getString(DoctorRoutes.CheckSurvey.argName) ?: "0") }!!
                CheckSurveyScreen(
                    navController = navController,
                    surveyCard = surveyCard,
                    onCheck = doctorViewModel::onCheckSurvey,
                    patient = doctorViewModel.getPatient(surveyCard.patientID),
                    modifier = Modifier.padding(paddingValues)
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
//
        }
    }
}