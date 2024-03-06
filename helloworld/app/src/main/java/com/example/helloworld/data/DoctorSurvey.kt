package com.example.helloworld.data

data class DoctorSurvey(
    val title: String,
    val body: String
)


val doctorSurveys = listOf(
    DoctorSurvey("Опрос от доктора Заславского", "Как настроение?"),
    DoctorSurvey("Опрос от доктора Заславского", "Как дела?"),
    DoctorSurvey("Опрос от доктора Заславского", "Ну что там с дипломом?"),
    DoctorSurvey("Опрос от доктора Заславского", "Смысл жизни?"),
)