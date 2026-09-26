package com.pocketagent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pocketagent.ui.components.BottomNavBar
import com.pocketagent.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    val currentProjectId = 1L
    var showProjectMenu by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Background,
        bottomBar = { BottomNavBar(navController, currentProjectId, "home") }
    ) { padding ->
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                // Top bar
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(Surface)
                        .border(width = 0.5.dp, color = Border, shape = RoundedCornerShape(0.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text("PocketAgent", fontSize = 17.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(
                                modifier = Modifier.clip(RoundedCornerShape(99.dp))
                                    .background(AccentGreenContainer).padding(horizontal = 8.dp, vertical = 2.dp)
                            ) { Text("● Claude Code", fontSize = 10.sp, color = AccentGreen) }
                        }
                    }
                    Box {
                        IconButton(onClick = { showProjectMenu = !showProjectMenu }) {
                            Icon(Icons.Outlined.MoreVert, "Projects", tint = TextSecondary)
                        }
                        DropdownMenu(
                            expanded = showProjectMenu,
                            onDismissRequest = { showProjectMenu = false },
                            modifier = Modifier.background(SurfaceVariant)
                        ) {
                            listOf("anime-trivia-bot", "reelix", "manga-color-bot").forEach { proj ->
                                DropdownMenuItem(
                                    text = { Text(proj, color = TextPrimary, fontSize = 13.sp) },
                                    onClick = { showProjectMenu = false }
                                )
                            }
                            HorizontalDivider(color = Border)
                            DropdownMenuItem(
                                text = { Text("+ New project", color = AccentPurple, fontSize = 13.sp) },
                                onClick = { showProjectMenu = false }
                            )
                        }
                    }
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Outlined.Settings, "Settings", tint = TextSecondary)
                    }
                }

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Active project card
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(AccentPurpleContainer)
                            .border(0.5.dp, AccentPurple.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(Modifier.clip(RoundedCornerShape(99.dp)).background(AccentGreenContainer).padding(horizontal = 8.dp, vertical = 2.dp)) {
                                Text("● active", fontSize = 10.sp, color = AccentGreen)
                            }
                            Text("anime-trivia-bot", fontSize = 13.sp, color = TextSecondary)
                        }
                        Text("Last: \"Fix score not updating after round 3\"", fontSize = 13.sp, color = TextPrimary)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = { navController.navigate(Screen.Chat.createRoute(currentProjectId)) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = AccentPurple, contentColor = Background),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(8.dp)
                            ) { Text("Continue chat", fontSize = 12.sp) }
                            OutlinedButton(
                                onClick = { navController.navigate(Screen.Editor.createRoute(currentProjectId)) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                border = ButtonDefaults.outlinedButtonBorder.copy(width = 0.5.dp),
                                contentPadding = PaddingValues(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = TextSecondary)
                            ) { Text("Files", fontSize = 12.sp) }
                        }
                    }

                    // Quick access
                    Text("Quick access", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = TextMuted, letterSpacing = 0.8.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        QuickCard(Icons.Outlined.Terminal, AccentPurple, "Terminal", Modifier.weight(1f)) {
                            navController.navigate(Screen.Terminal.createRoute(currentProjectId))
                        }
                        QuickCard(Icons.Outlined.Language, AccentGreen, "Preview", Modifier.weight(1f)) {
                            navController.navigate(Screen.Preview.createRoute(currentProjectId))
                        }
                    }

                    // Installed agents
                    Text("Installed agents", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = TextMuted, letterSpacing = 0.8.sp)
                    Column(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                            .background(Surface).border(0.5.dp, Border, RoundedCornerShape(12.dp))
                    ) {
                        AgentRow("Claude Code", "active", AccentGreenContainer, AccentGreen, true)
                        HorizontalDivider(color = Border, thickness = 0.5.dp)
                        AgentRow("Aider", "installed", SurfaceVariant, TextMuted, false)
                        HorizontalDivider(color = Border, thickness = 0.5.dp)
                        AgentRow("opencode", "installed", SurfaceVariant, TextMuted, false)
                    }
                }
            }
        }
    }
}

@Composable
fun QuickCard(icon: androidx.compose.ui.graphics.vector.ImageVector, color: androidx.compose.ui.graphics.Color, label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(12.dp)).background(Surface)
            .border(0.5.dp, Border, RoundedCornerShape(12.dp))
            .clickable { onClick() }.padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
    }
}

@Composable
fun AgentRow(name: String, status: String, badgeBg: androidx.compose.ui.graphics.Color, badgeText: androidx.compose.ui.graphics.Color, isActive: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(Icons.Outlined.SmartToy, null, tint = if (isActive) AccentPurple else TextMuted, modifier = Modifier.size(18.dp))
        Text(name, fontSize = 13.sp, color = TextPrimary, modifier = Modifier.weight(1f))
        Box(Modifier.clip(RoundedCornerShape(99.dp)).background(badgeBg).padding(horizontal = 8.dp, vertical = 2.dp)) {
            Text(status, fontSize = 10.sp, color = badgeText)
        }
    }
}
