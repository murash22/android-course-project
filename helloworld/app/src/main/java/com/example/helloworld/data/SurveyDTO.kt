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
    val openDate: String,
    var closeDate: String?,
    val title: String,
    val feedback: String = "",
    var questions: List<SurveyQuestion>,
    var completed: Boolean
)
