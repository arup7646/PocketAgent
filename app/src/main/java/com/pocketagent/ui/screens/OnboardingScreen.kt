package com.pocketagent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pocketagent.data.models.AgentType
import com.pocketagent.ui.theme.*

@Composable
fun OnboardingScreen(navController: NavController) {
    var step by remember { mutableIntStateOf(0) }
    var selectedAgent by remember { mutableStateOf(AgentType.CLAUDE_CODE) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        when (step) {
            0 -> WelcomeStep(onNext = { step = 1 })
            1 -> RuntimeStep(onNext = { step = 2 })
            2 -> AgentPickStep(
                selected = selectedAgent,
                onSelect = { selectedAgent = it },
                onDone = { navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }}
            )
        }
    }
}

@Composable
fun WelcomeStep(onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(AccentPurpleContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Terminal, null, tint = AccentPurple, modifier = Modifier.size(32.dp))
        }
        Text("PocketAgent", fontSize = 26.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
        Text(
            "AI coding on your phone.\nMultiple agents, one app.",
            fontSize = 14.sp, color = TextSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 22.sp
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            FeatureCard(Icons.Outlined.Memory, AccentPurpleContainer, AccentPurple, "Runs on-device", "PRoot Linux, no root needed")
            FeatureCard(Icons.Outlined.SmartToy, AccentGreenContainer, AccentGreen, "Multiple agents", "Claude, Aider, opencode & more")
            FeatureCard(Icons.Outlined.Key, AccentAmberContainer, AccentAmber, "Your API key", "OpenRouter, Anthropic, Gemini")
        }
        StepDots(current = 0, total = 3)
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = TextPrimary, contentColor = Background),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Get started", modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}

@Composable
fun FeatureCard(icon: androidx.compose.ui.graphics.vector.ImageVector, containerColor: Color, iconColor: Color, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Surface)
            .border(0.5.dp, Border, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(containerColor),
            contentAlignment = Alignment.Center
        ) { Icon(icon, null, tint = iconColor, modifier = Modifier.size(20.dp)) }
        Column {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
            Text(subtitle, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Composable
fun RuntimeStep(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(Modifier.height(40.dp))
        Box(
            modifier = Modifier.size(64.dp).clip(RoundedCornerShape(18.dp)).background(AccentGreenContainer),
            contentAlignment = Alignment.Center
        ) { Icon(Icons.Outlined.Memory, null, tint = AccentGreen, modifier = Modifier.size(32.dp)) }
        Text("Setting up runtime", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
        Text("Installing Linux on your device.\nOne-time setup — takes ~2 min.", fontSize = 13.sp, color = TextSecondary,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center, lineHeight = 20.sp)
        Column(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
                .background(Surface).border(0.5.dp, Border, RoundedCornerShape(12.dp)).padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            TerminalLine("$ installing proot-ubuntu ARM64...", AccentPurple)
            TerminalLine("✓ Core runtime ready", AccentGreen)
            TerminalLine("$ installing Node.js 20...", AccentPurple)
            TerminalLine("✓ Node.js ready", AccentGreen)
            TerminalLine("$ installing Python 3.11...", AccentPurple)
            TerminalLine("✓ Python ready", AccentGreen)
            TerminalLine("$ finalizing...", AccentPurple)
            TerminalLine("▋", TextMuted)
        }
        LinearProgressIndicator(
            progress = { 0.72f },
            modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(99.dp)),
            color = AccentPurple, trackColor = SurfaceVariant
        )
        StepDots(current = 1, total = 3)
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = TextPrimary, contentColor = Background),
            shape = RoundedCornerShape(10.dp)
        ) { Text("Continue", modifier = Modifier.padding(vertical = 4.dp)) }
    }
}

@Composable
fun TerminalLine(text: String, color: Color) {
    Text(text, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace, fontSize = 11.sp, color = color)
}

@Composable
fun AgentPickStep(selected: AgentType, onSelect: (AgentType) -> Unit, onDone: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Choose your coding agent", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
        Text("You can change this later in Settings.", fontSize = 13.sp, color = TextSecondary)
        AgentType.entries.forEach { agent ->
            AgentOption(agent = agent, isSelected = selected == agent, onSelect = { onSelect(agent) })
        }
        StepDots(current = 2, total = 3)
        Button(
            onClick = onDone,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = TextPrimary, contentColor = Background),
            shape = RoundedCornerShape(10.dp)
        ) { Text("Continue", modifier = Modifier.padding(vertical = 4.dp)) }
    }
}

@Composable
fun AgentOption(agent: AgentType, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) AccentPurpleContainer else Surface)
            .border(if (isSelected) 1.5.dp else 0.5.dp, if (isSelected) AccentPurple else Border, RoundedCornerShape(12.dp))
            .clickable { onSelect() }.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.size(38.dp).clip(RoundedCornerShape(10.dp)).background(AccentPurpleContainer),
            contentAlignment = Alignment.Center
        ) { Icon(Icons.Outlined.SmartToy, null, tint = AccentPurple, modifier = Modifier.size(20.dp)) }
        Column(Modifier.weight(1f)) {
            Text(agent.displayName, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
            Text(if (agent.requiresNode) "Node.js agent" else "Python agent", fontSize = 12.sp, color = TextSecondary)
        }
        Box(
            modifier = Modifier.size(18.dp).clip(CircleShape)
                .background(if (isSelected) AccentPurple else Color.Transparent)
                .border(1.5.dp, if (isSelected) AccentPurple else BorderStrong, CircleShape),
            contentAlignment = Alignment.Center
        ) { if (isSelected) Box(Modifier.size(6.dp).clip(CircleShape).background(Color.White)) }
    }
}

@Composable
fun StepDots(current: Int, total: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(total) { i ->
            Box(modifier = Modifier
                .height(6.dp)
                .width(if (i == current) 18.dp else 6.dp)
                .clip(RoundedCornerShape(99.dp))
                .background(if (i == current) AccentPurple else BorderStrong)
            )
        }
    }
}
