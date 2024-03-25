package com.example.helloworld.features.doctor.home_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.helloworld.DoctorRoutes
import com.example.helloworld.R


//fun NavGraphBuilder.doctorHomeNavGraph(
//    navController: NavController,
//    surveys: List<SurveyDTO>,
//    modifier: Modifier = Modifier
//) {
//    navigation(
//        route = Routes.Home.route,
//        startDestination = "unchecked_surveys"
//    ) {
//        composable(route = "unchecked_surveys") {
//            Column(
//                modifier = modifier
//            ) {
//                DoctorHomeTopNavBar(navController = navController)
//                DoctorUncheckedSurveys(
//                    navController = navController,
//                    surveys = surveys.filter { it.completed && it.feedback.isEmpty()},
//                    onCheckSurvey = {}
//                )
//            }
//        }
//
//        composable(route = "expecting_surveys") {
//            Column(
//                modifier = modifier
//            ) {
//                DoctorHomeTopNavBar(navController = navController)
//                DoctorExpectingSurveys(
//                    surveys = surveys.filter { !it.completed }
//                )
//            }
//        }
//
//        composable(route = "closed_surveys") {
//            Column(
//                modifier = modifier
//            ) {
//                DoctorHomeTopNavBar(navController = navController)
//                DoctorClosedSurveys(surveys = surveys.filter { it.completed && it.feedback.isNotEmpty() })
//            }
//        }
//    }
//}

private data class TopNavItem (
    val route: String,
    val text: String
)

@Composable
fun DoctorHomeTopNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val activeColor = Color(0xFFD9494C)
    val inActiveColor = Color.Black
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navItems = listOf(
        TopNavItem(
            route = DoctorRoutes.UncheckedSurveys.route,
            text = stringResource(id = R.string.unchecked)
        ),
        TopNavItem(
            route = DoctorRoutes.ExpectingSurveys.route,
            text = stringResource(R.string.unfinished)
        ),
        TopNavItem(
            route = DoctorRoutes.ClosedSurveys.route,
            text = stringResource(id = R.string.closed)
        )
    )

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (item in navItems) {
            TextButton(
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                Text(
                    text = item.text,
                    color = if (currentDestination?.hierarchy?.any {it.route == item.route} == true) activeColor else inActiveColor
                )
            }
        }
    }
}


