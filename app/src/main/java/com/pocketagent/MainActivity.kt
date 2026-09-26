package com.pocketagent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pocketagent.ui.screens.*
import com.pocketagent.ui.theme.Background
import com.pocketagent.ui.theme.PocketAgentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketAgentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Onboarding.route
                    ) {
                        composable(Screen.Onboarding.route) {
                            OnboardingScreen(navController)
                        }
                        composable(Screen.Home.route) {
                            HomeScreen(navController)
                        }
                        composable(Screen.Chat.route) { back ->
                            val projectId = back.arguments?.getString("projectId")?.toLong() ?: 0L
                            ChatScreen(navController, projectId)
                        }
                        composable(Screen.Editor.route) { back ->
                            val projectId = back.arguments?.getString("projectId")?.toLong() ?: 0L
                            EditorScreen(navController, projectId)
                        }
                        composable(Screen.Terminal.route) { back ->
                            val projectId = back.arguments?.getString("projectId")?.toLong() ?: 0L
                            TerminalScreen(navController, projectId)
                        }
                        composable(Screen.Preview.route) { back ->
                            val projectId = back.arguments?.getString("projectId")?.toLong() ?: 0L
                            PreviewScreen(navController, projectId)
                        }
                        composable(Screen.Settings.route) {
                            SettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
