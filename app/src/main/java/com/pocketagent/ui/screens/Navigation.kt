package com.pocketagent.ui.screens

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Chat : Screen("chat/{projectId}") {
        fun createRoute(projectId: Long) = "chat/$projectId"
    }
    object Editor : Screen("editor/{projectId}") {
        fun createRoute(projectId: Long) = "editor/$projectId"
    }
    object Terminal : Screen("terminal/{projectId}") {
        fun createRoute(projectId: Long) = "terminal/$projectId"
    }
    object Preview : Screen("preview/{projectId}") {
        fun createRoute(projectId: Long) = "preview/$projectId"
    }
    object Settings : Screen("settings")
}
