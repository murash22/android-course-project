package com.example.helloworld.data

var USERS = listOf<UserDTO>(
    PatientDTO(
        id = "1",
        name = "Александр",
        pin = "1111",
        password = "qwerty",
        email = "qwe@qwe.com",
        age = "27",
        diagnosis = "Грипп",
        sex = "Мужчина",
        status = PatientStatus.Remission
    ),
    PatientDTO(
        id = "2",
        name = "Дмитрий",
        pin = "2222",
        password = "qwerty",
        email = "qwe@qwe.com",
        age = "29",
        diagnosis = "Простуда",
        sex = "Мужчина",
        status = PatientStatus.Cured
    ),
    PatientDTO(
        id = "5",
        name = "Василий",
        pin = "1234",
        password = "qwerty",
        email = "qwe@qwe.com",
        age = "31",
        diagnosis = "Туберкулез",
        sex = "Мужчина",
        status = PatientStatus.Relapse
    ),
    PatientDTO(
        id = "6",
        name = "Серафим",
        pin = "0123",
        password = "qwerty",
        email = "qwe@qwe.com",
        age = "38",
        diagnosis = "ОРВИ",
        sex = "Мужчина",
        status = PatientStatus.Remission
    ),
    PatientDTO(
        id = "7",
        name = "Акакий",
        pin = "2233",
        password = "qwerty",
        email = "qwe@qwe.com",
        age = "38",
        diagnosis = "Температура 40",
        sex = "Мужчина",
        status = PatientStatus.Relapse
    ),
    PatientDTO(
        id = "8",
        name = "Иннокентий",
        pin = "3344",
        password = "qwerty",
        email = "qwe@qwe.com",
        age = "38",
        diagnosis = "Озноб",
        sex = "Мужчина",
        status = PatientStatus.Cured
    ),
    PatientDTO(
        id = "9",
        name = "Игорь",
        pin = "6667",
        password = "qwerty",
        email = "qwe@qwe.com",
        age = "38",
        diagnosis = "Кашель",
        sex = "Мужчина",
        status = PatientStatus.Relapse
    ),
    DoctorDTO(
        id = "3",
        name = "Стрендж",
        pin = "3333",
        password = "qwerty",
        email = "qwe@qwe.com",
    )
)

var SURVEYS = listOf<SurveyDTO>(
    SurveyDTO( // Закрытый опрос
        id = "2",
        title = "Проверка самочувствия (закрытый)",
        patientID = "1",
        doctorID = "3",
        openDate = "01.01.2024",
        closeDate = "02.01.2024",
        questions = listOf(
            SurveyQuestion(
                title = "Как вы себя чувствуете сегодня?",
                options = listOf("Отлично", "Хорошо", "Плохо"),
                answer = "Отлично"
            ),
            SurveyQuestion(
                title = "Есть ли у вас с аппетит?",
                options = listOf("Да", "Нет"),
                answer = "Да"
            ),
            SurveyQuestion(
                title = "Как вы оцениваете свой уровень энергии?",
                options = listOf("Высокий", "Средний", "Низкий"),
                answer = "Высокий"
            ),
            SurveyQuestion(
                title = "Сколько часов вы спали прошлой ночью?",
                options = listOf("Более 8 часов", "От 6 до 8 часов", "Менее 6 часов"),
                answer = "Более 8 часов"
            ),
        ),
        feedback = "Здоров как бык.",
        completed = true
    ),
    SurveyDTO(  // Еще не проверенный, но пройденный опрос
        id = "3",
        title = "Проверка самочувствия (не проверенный)",
        patientID = "1",
        doctorID = "3",
        openDate = "02.01.2024",
        closeDate = null,
        questions = listOf(
            SurveyQuestion(
                title = "Как вы себя чувствуете сегодня?",
                options = listOf("Отлично", "Хорошо", "Плохо"),
                answer = "Отлично"
            ),
            SurveyQuestion(
                title = "Как вы оцениваете свой уровень энергии?",
                options = listOf("Высокий", "Средний", "Низкий"),
                answer = "Высокий"
            ),
            SurveyQuestion(
                title = "Сколько часов вы спали прошлой ночью?",
                options = listOf("Более 8 часов", "От 6 до 8 часов", "Менее 6 часов"),
                answer = "Менее 6 часов"
            ),
        ),
        feedback = "",
        completed = true
    ),
    SurveyDTO(  // Еще не пройденный опрос
        id = "1",
        title = "Проверка самочувствия (не пройденный)",
        patientID = "1",
        doctorID = "3",
        openDate = "03.01.2024",
        closeDate = null,
        questions = listOf(
            SurveyQuestion(
                title = "Как вы себя чувствуете сегодня?",
                options = listOf("Отлично", "Хорошо", "Плохо"),
            ),
            SurveyQuestion(
                title = "Как вы оцениваете свой уровень энергии?",
                options = listOf("Высокий", "Средний", "Низкий"),
            ),
            SurveyQuestion(
                title = "Сколько часов вы спали прошлой ночью?",
                options = listOf("Более 8 часов", "От 6 до 8 часов", "Менее 6 часов"),
            ),
        ),
        feedback = "",
        completed = false
    ),
    SurveyDTO( // Закрытый опрос
        id = "4",
        title = "Проверка самочувствия (закрытый)",
        patientID = "2",
        doctorID = "3",
        openDate = "05.03.2024",
        closeDate = "02.01.2024",
        questions = listOf(
            SurveyQuestion(
                title = "Как вы себя чувствуете сегодня?",
                options = listOf("Отлично", "Хорошо", "Плохо"),
                answer = "Плохо"
            ),
            SurveyQuestion(
                title = "Как вы оцениваете свой уровень энергии?",
                options = listOf("Высокий", "Средний", "Низкий"),
                answer = "Низкий"
            ),
            SurveyQuestion(
                title = "Сколько часов вы спали прошлой ночью?",
                options = listOf("Более 8 часов", "От 6 до 8 часов", "Менее 6 часов"),
                answer = "От 6 до 8 часов"
            ),
        ),
        feedback = "Почаще гуляйте на свежем воздухе и принимайте витамин Д3",
        completed = true
    ),
    SurveyDTO(  // Еще не проверенный, но пройденный опрос
        id = "5",
        title = "Проверка самочувствия (не проверенный)",
        patientID = "2",
        doctorID = "3",
        openDate = "06.03.2024",
        closeDate = null,
        questions = listOf(
            SurveyQuestion(
                title = "Как вы себя чувствуете сегодня?",
                options = listOf("Отлично", "Хорошо", "Плохо"),
                answer = "Отлично"
            ),
            SurveyQuestion(
                title = "Как вы оцениваете свой уровень энергии?",
                options = listOf("Высокий", "Средний", "Низкий"),
                answer = "Низкий"
            ),
            SurveyQuestion(
                title = "Сколько часов вы спали прошлой ночью?",
                options = listOf("Более 8 часов", "От 6 до 8 часов", "Менее 6 часов"),
                answer = "От 6 до 8 часов"
            ),
        ),
        feedback = "",
        completed = true
    ),
    SurveyDTO(  // Еще не пройденный опрос
        id = "6",
        title = "Проверка самочувствия (не пройденный)",
        patientID = "2",
        doctorID = "3",
        openDate = "07.03.2024",
        closeDate = null,
        questions = listOf(
            SurveyQuestion(
                title = "Как вы себя чувствуете сегодня?",
                options = listOf("Отлично", "Хорошо", "Плохо"),
            ),
            SurveyQuestion(
                title = "Как вы оцениваете свой уровень энергии?",
                options = listOf("Высокий", "Средний", "Низкий"),
            ),
            SurveyQuestion(
                title = "Сколько часов вы спали прошлой ночью?",
                options = listOf("Более 8 часов", "От 6 до 8 часов", "Менее 6 часов"),
            ),
        ),
        feedback = "",
        completed = false
    ),
)