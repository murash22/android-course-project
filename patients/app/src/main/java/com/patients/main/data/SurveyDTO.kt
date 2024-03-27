package com.patients.main.data

data class SurveyQuestion(
    var id: String,
    var title: String,
    var options: List<String>,
    var answer: String = ""
)

data class SurveyDTO(
    val id: String,
    val doctorID: String,
    val patientID: String,
    var openDate: String,
    var closeDate: String?,
    val title: String,
    var feedback: String = "",
    var questions: List<SurveyQuestion>,
    var completed: Boolean
)
