package com.example.helloworld.data


class DoctorDTO(
    override val id: String,
    override val pin: String,
    override val password: String,
    override val email: String,
    override val name: String,
    override val role: UserRole = UserRole.Doctor,
) : UserDTO {
    fun getPatients() {

    }
}
