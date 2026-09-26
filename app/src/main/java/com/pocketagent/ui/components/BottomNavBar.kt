package com.pocketagent.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pocketagent.ui.screens.Screen
import com.pocketagent.ui.theme.*

data class NavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String)

@Composable
fun BottomNavBar(navController: NavController, projectId: Long, current: String) {
    val items = listOf(
        NavItem("Home", Icons.Outlined.Home, "home"),
        NavItem("Chat", Icons.Outlined.Chat, "chat"),
        NavItem("Files", Icons.Outlined.FolderOpen, "editor"),
        NavItem("Terminal", Icons.Outlined.Terminal, "terminal"),
        NavItem("Preview", Icons.Outlined.Language, "preview"),
    )
    Row(
        modifier = Modifier.fillMaxWidth().background(SurfaceElevated)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { item ->
            val isActive = current == item.route
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier.weight(1f).padding(vertical = 4.dp)
            ) {
                IconButton(onClick = {
                    val route = when (item.route) {
                        "home" -> Screen.Home.route
                        "chat" -> Screen.Chat.createRoute(projectId)
                        "editor" -> Screen.Editor.createRoute(projectId)
                        "terminal" -> Screen.Terminal.createRoute(projectId)
                        "preview" -> Screen.Preview.createRoute(projectId)
                        else -> Screen.Home.route
                    }
                    navController.navigate(route) { launchSingleTop = true }
                }, modifier = Modifier.size(28.dp)) {
                    Icon(item.icon, item.label, tint = if (isActive) AccentPurple else TextMuted, modifier = Modifier.size(22.dp))
                }
                Text(item.label, fontSize = 10.sp, color = if (isActive) AccentPurple else TextMuted)
            }
        }
    }
}
