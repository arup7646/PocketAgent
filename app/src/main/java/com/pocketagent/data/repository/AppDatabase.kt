package com.pocketagent.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.pocketagent.data.models.*

class Converters {
    @TypeConverter fun fromAgentType(v: AgentType) = v.name
    @TypeConverter fun toAgentType(v: String) = AgentType.valueOf(v)
    @TypeConverter fun fromProviderType(v: ProviderType) = v.name
    @TypeConverter fun toProviderType(v: String) = ProviderType.valueOf(v)
    @TypeConverter fun fromMessageRole(v: MessageRole) = v.name
    @TypeConverter fun toMessageRole(v: String) = MessageRole.valueOf(v)
    @TypeConverter fun fromMessageStatus(v: MessageStatus) = v.name
    @TypeConverter fun toMessageStatus(v: String) = MessageStatus.valueOf(v)
}

@Database(
    entities = [Project::class, ChatMessage::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun messageDao(): MessageDao
}
