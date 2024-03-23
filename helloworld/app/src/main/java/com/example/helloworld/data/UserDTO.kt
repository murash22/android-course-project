package com.example.helloworld.data

enum class UserRole(val role: String) {
    Patient("patient"),
    Doctor("doctor")
}


interface UserDTO {
    val id: String
    val email: String
    val password: String
    val pin: String
    val role: UserRole
    val name: String
}
