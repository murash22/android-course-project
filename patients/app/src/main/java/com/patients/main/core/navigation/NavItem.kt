package com.patients.main.core.navigation



data class NavItem<T>(
    val route: String,
    val selectedIcon: T,
    val unselectedIcon: T
)
