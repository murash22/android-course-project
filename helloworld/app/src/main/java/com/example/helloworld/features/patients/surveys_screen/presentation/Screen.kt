package com.example.helloworld.features.patients.surveys_screen.presentation

sealed class Screen (val route: String) {
    object ClosedSurveys: Screen("closed")
    object ExpectingSurveys: Screen("expecting")
}