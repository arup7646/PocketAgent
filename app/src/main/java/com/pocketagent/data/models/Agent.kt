package com.pocketagent.data.models

enum class AgentType(
    val displayName: String,
    val installCmd: String,
    val runCmd: String,
    val requiresNode: Boolean,
    val requiresPython: Boolean
) {
    CLAUDE_CODE(
        displayName = "Claude Code",
        installCmd = "npm install -g @anthropic-ai/claude-code",
        runCmd = "claude",
        requiresNode = true,
        requiresPython = false
    ),
    AIDER(
        displayName = "Aider",
        installCmd = "pip install aider-chat",
        runCmd = "aider",
        requiresNode = false,
        requiresPython = true
    ),
    OPENCODE(
        displayName = "opencode",
        installCmd = "npm install -g opencode-ai",
        runCmd = "opencode",
        requiresNode = true,
        requiresPython = false
    ),
    CODEX(
        displayName = "Codex CLI",
        installCmd = "npm install -g @openai/codex",
        runCmd = "codex",
        requiresNode = true,
        requiresPython = false
    ),
    GEMINI_CLI(
        displayName = "Gemini CLI",
        installCmd = "npm install -g @google/gemini-cli",
        runCmd = "gemini",
        requiresNode = true,
        requiresPython = false
    )
}

enum class ProviderType(val displayName: String) {
    ANTHROPIC("Anthropic"),
    OPENROUTER("OpenRouter"),
    OPENAI("OpenAI"),
    GEMINI("Google Gemini"),
    DEEPSEEK("DeepSeek")
}
