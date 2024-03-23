package com.example.helloworld.features.patient.surveys_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.helloworld.data.SurveyDTO


private sealed class Screen (val route: String) {
    data object ClosedSurveys: Screen("closed")
    data object ExpectingSurveys: Screen("expecting")
}

@Composable
fun PatientSurveysScreen(
    modifier: Modifier = Modifier,
    surveys: List<SurveyDTO>
) {
    val navController = rememberNavController()
    Column(modifier = modifier) {
        TopNavBar(navController = navController)

        NavHost(navController = navController, startDestination = Screen.ClosedSurveys.route) {
            composable(route = Screen.ClosedSurveys.route) {
                PatientClosedSurveysScreen(surveys = surveys.filter { it.completed && it.feedback.isNotEmpty() })
            }

            composable(route = Screen.ExpectingSurveys.route) {
                PatientExpectingSurveysScreen(surveys = surveys.filter { it.completed && it.feedback.isEmpty() })
            }
        }

    }


}

@Composable
private fun TopNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val activeColor = Color(0xFFD9494C)
    val inActiveColor = Color.Black
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextButton(
            onClick = {
                navController.navigate(Screen.ClosedSurveys.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            Text(
                text = "Закрытые",
                color = if (currentDestination?.hierarchy?.any {it.route == Screen.ClosedSurveys.route} == true) activeColor else inActiveColor
            )
        }
        TextButton(
            onClick = {
                navController.navigate(Screen.ExpectingSurveys.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            Text(
                text = "Непроверенные",
                color = if (currentDestination?.hierarchy?.any {it.route == Screen.ExpectingSurveys.route} == true) activeColor else inActiveColor
            )
        }
    }
}