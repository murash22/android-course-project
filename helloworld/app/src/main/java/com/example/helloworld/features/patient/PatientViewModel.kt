package com.example.helloworld.features.patient

import androidx.lifecycle.ViewModel
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.SURVEYS
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS
import com.example.helloworld.data.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PatientViewModel : ViewModel() {
    private val _users = MutableStateFlow(USERS)
    val users = _users.asStateFlow()
    private val _surveys = MutableStateFlow(SURVEYS)
    val surveys = _surveys.asStateFlow()

    fun onSubmitSurvey(id: String) {
        _surveys.update { currentState ->
            currentState.map {
                if (it.id == id) {
                    it.completed =true
                    it
                } else {
                    it
                }
            }
        }
        SURVEYS = _surveys.value
    }

    fun onSubmitQuestionAnswer(surveyId: String, questionId: String, answer: String) {
        for (survey in surveys.value) {
            if (survey.id == surveyId) {
                for (question in survey.questions) {
                    if (question.id == questionId) {
                        question.answer = answer
                    }
                }
            }
        }
    }

    fun getPatient(id: String) : PatientDTO {
        return users.value.filter { it.id == id && it.role == UserRole.Patient }[0] as PatientDTO
    }

    fun getSurveys(id: String) : List<SurveyDTO> {
        return surveys.value.filter { it.patientID == id }
    }
}