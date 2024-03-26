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
import com.example.helloworld.core.ui.top_bar.TopSearchBar
import com.example.helloworld.features.doctor.check_survey.presentation.CheckSurveyScreen
import com.example.helloworld.features.doctor.edit_patient_info.presentation.EditPatientInfoScreen
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorClosedSurveys
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorExpectingSurveys
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorHomeTopNavBar
import com.example.helloworld.features.doctor.home_screen.presentation.DoctorUncheckedSurveys
import com.example.helloworld.features.doctor.patient_surveys.presentation.PatientSurveysScreen
import com.example.helloworld.features.doctor.patients_list_screen.presentation.PatientsListScreen
import com.example.helloworld.features.doctor.statistics.presentation.StatisticScreen


val navItems: List<NavItem<ImageVector>> = listOf(
    NavItem(
        route = Routes.Home.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    NavItem(
        route = Routes.Other.route,
        selectedIcon = Icons.Filled.AccountBox,
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
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination
    val topBarTitle = if (currentDestination?.route == DoctorRoutes.PatientsList.route) {
        stringResource(R.string.list_of_patients)
    } else {
        stringResource(R.string.surveys_list)
    }

    var filteredSurveys by rememberSaveable {
        mutableStateOf(doctorViewModel.onSearchSurveys(""))
    }

    var filteredPatients by rememberSaveable {
        mutableStateOf(doctorViewModel.onSearchPatients(""))
    }

    var isResetSearchFilter by rememberSaveable {
        mutableStateOf(false)
    }

    val barVisibleScreens = listOf(
        DoctorRoutes.UncheckedSurveys.route,
        DoctorRoutes.ExpectingSurveys.route,
        DoctorRoutes.ClosedSurveys.route,
        DoctorRoutes.PatientsList.route,
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            TopSearchBar(
                title = topBarTitle,
                navController = navController,
                visibleScreens = barVisibleScreens,
                isResetFilter = isResetSearchFilter,
                onSearch = {
                    isResetSearchFilter = false
                    val parentRoute = navBackStackEntry?.destination?.parent?.route
                    when (parentRoute) {
                        Routes.Home.route -> filteredSurveys = doctorViewModel.onSearchSurveys(it)
                        Routes.Other.route -> filteredPatients =
                            doctorViewModel.onSearchPatients(it)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                navItems = navItems,
                onResetSearchFilter = {
                    isResetSearchFilter = true
                    filteredSurveys = doctorViewModel.onSearchSurveys("")
                    filteredPatients = doctorViewModel.onSearchPatients("")
                },
                visibleScreens = barVisibleScreens
            )
        },
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Routes.Home.route) {

            navigation( //home
                route = Routes.Home.route,
                startDestination = DoctorRoutes.UncheckedSurveys.route
            ) {
                // home/unchecked_survveys
                composable(route = DoctorRoutes.UncheckedSurveys.route) {
                    Column(
                        modifier = modifier.padding(paddingValues)
                    ) {
                        DoctorHomeTopNavBar(navController = navController)
                        DoctorUncheckedSurveys(
                            navController = navController,
                            surveys = filteredSurveys.filter { it.completed && it.feedback.isEmpty() },
                        )
                    }
                }
                // home/expecting_survveys
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
                // home/closed_survveys
                composable(route = DoctorRoutes.ClosedSurveys.route) {
                    Column(
                        modifier = modifier.padding(paddingValues)
                    ) {
                        DoctorHomeTopNavBar(navController = navController)
                        DoctorClosedSurveys(surveys = filteredSurveys.filter { it.completed && it.feedback.isNotEmpty() })
                    }
                }
            }

            composable( // check_survey/{id}
                route = DoctorRoutes.CheckSurvey.route + "/{${DoctorRoutes.CheckSurvey.argName}}",
                arguments = listOf(
                    navArgument(DoctorRoutes.CheckSurvey.argName ?: "id") {
                        type = NavType.StringType
                        nullable = false
                    }
                )
            ) { entry ->
                val surveyCard = filteredSurveys.find {
                    it.id == (entry.arguments?.getString(DoctorRoutes.CheckSurvey.argName) ?: "0")
                }!!
                CheckSurveyScreen(
                    navController = navController,
                    surveyCard = surveyCard,
                    onCheck = doctorViewModel::onCheckSurvey,
                    patient = doctorViewModel.getPatient(surveyCard.patientID),
                    modifier = Modifier.padding(paddingValues)
                )
            }

            navigation( // other
                route = Routes.Other.route,
                startDestination = DoctorRoutes.PatientsList.route
            ) {

                // other/patients
                composable(route = DoctorRoutes.PatientsList.route) {
                    PatientsListScreen(
                        patients = filteredPatients,
                        onClickPatient = { id ->
                            navController.navigate(DoctorRoutes.PatientsList.route + "/${id}/surveys")
                        },
                        modifier = modifier.padding(paddingValues)
                    )
                }

                // other/statistics
                composable(route = DoctorRoutes.Statistics.route) {
                    StatisticScreen(navController = navController, patients = filteredPatients)
                }

                // other/patients/{id}/surveys
                composable(
                    route = DoctorRoutes.PatientSurveys.route,
                    arguments = listOf(
                        navArgument(DoctorRoutes.PatientSurveys.argName!!) {
                            type = NavType.StringType
                            nullable = false
                        }
                    ),
                ) { entry ->
                    val patientId =
                        entry.arguments?.getString(DoctorRoutes.PatientSurveys.argName!!)!!
                    PatientSurveysScreen(
                        surveys = doctorViewModel.getPatientSurveys(patientId),
                        onBack = { navController.popBackStack() },
                        onPatientInfo = {
                            navController.navigate(
                                DoctorRoutes.PatientInfo.route.replace(
                                    "{id}",
                                    patientId
                                )
                            )
                        },
                        onAddSurvey = {
                            navController.navigate(
                                DoctorRoutes.PatientCreateSurvey.route.replace(
                                    "{id}",
                                    patientId
                                )
                            )
                        },
                        patient = doctorViewModel.getPatient(patientId)
                    )
                }

                // other/patients/{id}/info
                composable(
                    route = DoctorRoutes.PatientInfo.route,
                    arguments = listOf(
                        navArgument(DoctorRoutes.PatientInfo.argName!!) {
                            type = NavType.StringType
                            nullable = false
                        }
                    )
                ) { entry ->
                    val patientId = entry.arguments?.getString(DoctorRoutes.PatientInfo.argName!!)!!
                    EditPatientInfoScreen(
                        navController = navController,
                        patient = doctorViewModel.getPatient(patientId)
                    )

                }

                // other/patients/{id}/create_survey
                composable(
                    route = DoctorRoutes.PatientCreateSurvey.route,
                    arguments = listOf(
                        navArgument(DoctorRoutes.PatientCreateSurvey.argName!!) {
                            type = NavType.StringType
                            nullable = false
                        }
                    )
                ) { entry ->
                    val patientId =
                        entry.arguments?.getString(DoctorRoutes.PatientCreateSurvey.argName!!)!!
                }
            }
        }
    }
}