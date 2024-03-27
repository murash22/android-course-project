package com.patients.main.core

import androidx.lifecycle.ViewModel
import com.patients.main.data.USERS
import com.patients.main.data.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {
    private val _users = MutableStateFlow(USERS)
    val users = _users.asStateFlow()

    fun getUserByPIN(
        pin: String
    ) : Pair<UserRole, String>? {
        val user = users.value.find { it.pin == pin }
        if (user != null) {
            return Pair(user.role, user.id)
        }
        return null
    }
}