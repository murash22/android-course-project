package com.example.helloworld.features.doctor

import androidx.lifecycle.ViewModel
import com.example.helloworld.data.PatientDTO
import com.example.helloworld.data.SURVEYS
import com.example.helloworld.data.SurveyDTO
import com.example.helloworld.data.USERS
import com.example.helloworld.data.UserDTO
import com.example.helloworld.data.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DoctorViewModel(
    val doctorId: String
) : ViewModel() {
    private var _user = MutableStateFlow(USERS.find { it.id == doctorId && it.role == UserRole.Doctor }!!)
    val doctor: StateFlow<UserDTO> = _user.asStateFlow()

    private var _surveys = MutableStateFlow(SURVEYS.filter { it.doctorID == doctorId })
//    val surveys: StateFlow<List<SurveyDTO>> = _surveys.asStateFlow()

    private var _patients = MutableStateFlow(USERS.filter {
        user -> _surveys.value.any { user.id == it.patientID && user.role == UserRole.Patient}
    })
    val patients: StateFlow<List<UserDTO>> = _patients.asStateFlow()

    fun onSearchSurveys(str: String) : List<SurveyDTO> {
        if (str.isNotEmpty()) {
            val tmp = _patients.value.filter { it.name.contains(str, true) }
            return _surveys.value.filter {survey ->
                tmp.any { survey.patientID == it.id }
            }

        }
        return _surveys.value

    }

    fun onSearchPatients(str: String) : List<PatientDTO> {
        if (str.isNotEmpty()) {
            return _patients.value.filter { it.name.contains(str, true) } as List<PatientDTO>
        }
        return _patients.value as List<PatientDTO>
    }

    fun getPatient(id: String) : PatientDTO {
        return _patients.value.find { it.id == id && it.role == UserRole.Patient } as PatientDTO
    }

    fun getPatientSurveys(id: String) : List<SurveyDTO> {
        return _surveys.value.filter { it.patientID == id }
    }

    fun onCheckSurvey(id: String, feedback: String) {
        val foundSurvey = _surveys.value.find { it.id == id }
        foundSurvey?.feedback = feedback
        foundSurvey?.closeDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }


}