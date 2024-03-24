package com.example.helloworld


sealed class Routes(val route: String, val argName: String? = null) {
    data object Home: Routes(route = "home")
    data object Other: Routes(route = "other")

    data object Patient: Routes(route = "patient", argName = "id")

    data object PasswordScreen: Routes(route = "pin")
}

sealed class PatientRoutes(
    val route: String,
    val argName: String = ""
) {
    data object TakeSurvey: PatientRoutes(route = "take_survey", argName = "id")
    fun takeSurveyWithArg(id: Int): String {
         return route + "/${id}"
    }
    fun takeSurveyWithArg(id: String): String {
        return route + "/${id}"
    }
}

sealed class DoctorRoutes(val route: String) {

}