package com.example.helloworld

import com.example.helloworld.features.patients.surveys_screen.presentation.Screen

sealed class Routes(val route: String) {
    object Home: Routes(route = "home")
    object Other: Routes(route = "other")
}

sealed class PatientRoutes(
    route: String,
    val argName: String = ""
) : Routes(route) {
    object TakeSurvey: PatientRoutes(route = "take_survey", argName = "id")
    fun takeSurveyWithArg(id: Int): String {
         return route + "/${id}"
    }
}

sealed class DoctorRoutes(route: String): Routes(route) {

}