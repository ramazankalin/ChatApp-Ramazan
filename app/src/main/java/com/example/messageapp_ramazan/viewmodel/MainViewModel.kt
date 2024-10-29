package com.example.messageapp_ramazan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.messageapp_ramazan.database.MessageDao
import com.example.messageapp_ramazan.database.MessageDatabase
import com.example.messageapp_ramazan.model.Message
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    val messageData = MutableLiveData<List<Message>>()
    private var messageDatabase: MessageDatabase? = null
    private var messageDao: MessageDao? = null

    init {
        messageDatabase = MessageDatabase.getInstance(application)
        messageDao = messageDatabase?.messageDao()
        messageData.value = emptyList()
        //clearMessages()
    }

    private fun clearMessages() = viewModelScope.launch {
        messageDao?.deleteAllMessages()
        messageData.postValue(emptyList())
    }

    fun addMessage(content: String, isSentByUser: Boolean) = viewModelScope.launch {
        val newMessage = Message(message = content, isSentByUser = isSentByUser)
        messageDao?.insertMessage(newMessage)

        val currentMessages = messageData.value?.toMutableList() ?: mutableListOf()
        currentMessages.add(newMessage)

        if (isSentByUser) {
            val systemMessage = Message(message = "Mesaj alındı", isSentByUser = false)
            messageDao?.insertMessage(systemMessage)
            currentMessages.add(systemMessage)
        }

        messageData.postValue(currentMessages)
    }

    fun findByUser(isSentByUser: Boolean) = viewModelScope.launch {
        val messages = messageDao?.findByUser(isSentByUser) ?: emptyList()
        messageData.postValue(messages)
    }
}
