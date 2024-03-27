package com.patients.main.data

sealed class PatientStatus(val name: String) {
    data object Cured : PatientStatus("Вылечился")
    data object Remission : PatientStatus("Ремиссия")
    data object Relapse : PatientStatus("Рецидив")
}


class PatientDTO(
    override val id: String,
    override val pin: String,
    override val password: String,
    override val email: String,
    override val name: String,
    override val role: UserRole = UserRole.Patient,
    val sex: String,
    val age: String,
    var diagnosis: String,
    var status: PatientStatus
) : UserDTO {

}