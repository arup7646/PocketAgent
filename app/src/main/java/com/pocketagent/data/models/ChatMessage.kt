package com.pocketagent.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class MessageRole { USER, AGENT }
enum class MessageStatus { SENDING, STREAMING, DONE, ERROR }

@Entity(tableName = "messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val projectId: Long,
    val role: MessageRole,
    val content: String,
    val status: MessageStatus = MessageStatus.DONE,
    val timestamp: Long = System.currentTimeMillis(),
    val hasCodeBlock: Boolean = false,
    val hasDiff: Boolean = false
)
