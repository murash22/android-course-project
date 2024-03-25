package com.example.helloworld.data

data class SurveyQuestion(
    val title: String,
    val options: List<String>,
    var answer: String = options[0]

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
