package com.pocketagent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pocketagent.ui.theme.*

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        containerColor = Background,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().background(Surface)
                    .windowInsetsPadding(WindowInsets.statusBars).padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Outlined.ArrowBack, "Back", tint = TextSecondary)
                }
                Text("Settings", fontSize = 15.sp, color = TextPrimary)
            }
        }
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            Text("Settings — coming soon", color = TextMuted)
        }
    }
}
