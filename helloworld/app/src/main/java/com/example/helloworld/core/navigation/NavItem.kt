package com.example.helloworld.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
