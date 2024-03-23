package com.example.helloworld


sealed class Routes(val route: String) {
    data object Home: Routes(route = "home")
    data object Other: Routes(route = "other")
}

sealed class PatientRoutes(
    route: String,
    val argName: String = ""
) : Routes(route) {
    data object TakeSurvey: PatientRoutes(route = "take_survey", argName = "id")
    fun takeSurveyWithArg(id: Int): String {
         return route + "/${id}"
    }
    fun takeSurveyWithArg(id: String): String {
        return route + "/${id}"
    }
}

sealed class DoctorRoutes(route: String): Routes(route) {

}