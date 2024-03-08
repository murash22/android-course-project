package com.example.helloworld.data

data class Question(
    val name: String,
    val options: List<String>
)

data class DoctorSurvey(
    val title: String,
    val body: String,
    val questions: List<Question>
)


val doctorSurveys = listOf(
    DoctorSurvey(
        title = "Опрос от доктора Заславского",
        body = "Ежедневная проверка состояния",
        questions = listOf(
            Question("Вы принимали сегодня таблетки", listOf("Да", "Нет")),
            Question("Дышали на свежем воздухе?", listOf("Да", "Нет")),
            Question("Вы принимали сегодня таблетки", listOf("Да", "Нет")),
            Question("Дышали на свежем воздухе?", listOf("Да", "Нет")),
            Question("Вы принимали сегодня таблетки", listOf("Да", "Нет")),
            Question("Дышали на свежем воздухе?", listOf("Да", "Нет")),
        )
    ),
    DoctorSurvey(
        title = "Опрос от доктора Заславского",
        body = "Как дела?",
        questions = listOf(
            Question("Вы принимали сегодня таблетки", listOf("Да", "Нет")),
            Question("Дышали на свежем воздухе?", listOf("Да", "Нет"))
        )
    ),
    DoctorSurvey(
        title = "Опрос от доктора Заславского",
        body = "Ну что там с дипломом?",
        questions = listOf(
            Question("Вы принимали сегодня таблетки", listOf("Да", "Нет")),
            Question("Дышали на свежем воздухе?", listOf("Да", "Нет"))
        )
    ),
    DoctorSurvey(
        title = "Опрос от доктора Заславского",
        body = "Смысл жизни?",
        questions = listOf(
            Question("Вы принимали сегодня таблетки", listOf("Да", "Нет")),
            Question("Дышали на свежем воздухе?", listOf("Да", "Нет"))
        )
    ),
)