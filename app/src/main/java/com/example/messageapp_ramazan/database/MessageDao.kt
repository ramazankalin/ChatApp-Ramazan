package com.example.messageapp_ramazan.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.messageapp_ramazan.model.Message

@Dao
interface MessageDao {
    @Insert
    suspend fun insertAll(messages: List<Message>)

    @Insert
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM message WHERE strMessageSent = :isSentByUser")
    suspend fun findByUser(isSentByUser: Boolean): List<Message>

    @Query("DELETE FROM message")
    suspend fun deleteAllMessages()
}

