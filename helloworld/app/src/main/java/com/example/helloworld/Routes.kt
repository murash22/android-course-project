package com.example.helloworld


sealed class Routes(val route: String, val argName: String? = null) {
    data object Home: Routes(route = "home")
    data object Other: Routes(route = "other")

    data object Patient: Routes(route = "patient", argName = "id")

    data object PasswordScreen: Routes(route = "pin")

    data object Doctor: Routes(route = "doctor", argName = "id")
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

sealed class DoctorRoutes(
    val route: String,
    val argName: String? = null
) {
    data object CheckSurvey: DoctorRoutes(route = "check_survey", argName = "id")
    fun checkSurveyWithArg(id: String) : String {
        return route + "/${id}"
    }

    data object UncheckedSurveys: DoctorRoutes(route = "unchecked_surveys")

    data object ExpectingSurveys: DoctorRoutes(route = "expecting_surveys")

    data object ClosedSurveys: DoctorRoutes(route = "closed_surveys")

    data object PatientsList: DoctorRoutes(route = "patients")

    data object Statistics: DoctorRoutes(route = "statistics")

    data object PatientSurveys: DoctorRoutes(route = "patients/{id}/surveys", argName = "id")

    data object PatientInfo: DoctorRoutes(route = "patients/{id}/info", argName = "id")

    data object PatientCreateSurvey: DoctorRoutes(route = "patients/{id}/create_survey", argName = "id")
}