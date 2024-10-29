package com.example.messageapp_ramazan.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "message")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("strMessage")
    @SerializedName("strMessage")
    val message: String,

    @ColumnInfo("strMessageSent")
    @SerializedName("strMessageSent")
    val isSentByUser: Boolean
) : Parcelable
