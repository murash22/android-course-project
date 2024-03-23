package com.example.helloworld.data

enum class PatientStatus {
    Cured,
    Remission,
    Relapse,
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
    val diagnosis: String,
    val status: PatientStatus
) : UserDTO {

}