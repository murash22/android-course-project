package com.example.helloworld.features.doctor

import androidx.lifecycle.ViewModel
import com.example.helloworld.data.SURVEYS
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS
import com.example.helloworld.data.UserDTO
import com.example.helloworld.data.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter


class DoctorViewModel(
    val doctorId: String
) : ViewModel() {
    private var _user = MutableStateFlow(USERS.find { it.id == doctorId && it.role == UserRole.Doctor }!!)
    val doctor: StateFlow<UserDTO> = _user.asStateFlow()

    var _surveys = MutableStateFlow(SURVEYS.filter { it.doctorID == doctorId })
    val surveys: StateFlow<List<SurveyDTO>> = _surveys.asStateFlow()

    var _patients = MutableStateFlow(USERS.filter {
        user -> surveys.value.any { user.id == it.patientID }
    })
    val patients: StateFlow<List<UserDTO>> = _patients.asStateFlow()

    fun onSearchPatients(str: String) : List<SurveyDTO> {
        if (str.isNotEmpty()) {
            val tmp = _patients.value.filter { it.name.contains(str, true) }
            return _surveys.value.filter {survey ->
                tmp.any { survey.patientID == it.id }
            }

        }
        return SURVEYS.filter { it.doctorID == doctorId }

    }

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

}