package com.example.helloworld.features.doctor

import androidx.lifecycle.ViewModel
import com.example.helloworld.data.DoctorDTO
import com.example.helloworld.data.SURVEYS
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS
import com.example.helloworld.data.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class DoctorViewModel : ViewModel() {
    private val _users = MutableStateFlow(USERS)
    val users = _users.asStateFlow()
    private val _surveys = MutableStateFlow(SURVEYS)
    val surveys = _surveys.asStateFlow()

//    fun onSubmitSurvey(id: String) {
//        _surveys.update { currentState ->
//            currentState.map {
//                if (it.id == id) {
//                    it.completed =true
//                    it
//                } else {
//                    it
//                }
//            }
//        }
//        SURVEYS = _surveys.value
//    }

//    fun onSubmitQuestionAnswer(surveyId: String, questionTitle: String, answer: String) {
//        for (survey in surveys.value) {
//            if (survey.id == surveyId) {
//                for (question in survey.questions) {
//                    if (question.title == questionTitle) {
//                        question.answer = answer
//                    }
//                }
//            }
//        }
//    }

    fun getDoctor(id: String) : DoctorDTO {
        return users.value.find { it.id == id && it.role == UserRole.Doctor } as DoctorDTO
    }

    fun getSurveys(id: String) : List<SurveyDTO> {
        return surveys.value.filter { it.doctorID == id }
    }
}