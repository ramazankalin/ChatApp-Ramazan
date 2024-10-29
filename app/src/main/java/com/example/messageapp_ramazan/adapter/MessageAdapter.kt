package com.example.messageapp_ramazan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.messageapp_ramazan.R
import com.example.messageapp_ramazan.model.Message

class MessageAdapter(private var messages: List<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.textView)

        fun bind(message: Message) {
            messageText.text = message.message

            val background = if (message.isSentByUser) R.drawable.user_message_background
            else R.drawable.system_message_background
            messageText.setBackgroundResource(background)

            val layoutParams = itemView.layoutParams as ViewGroup.MarginLayoutParams
            if (message.isSentByUser) {
                layoutParams.marginStart = 0
                layoutParams.marginEnd = 0
                messageText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            } else {
                layoutParams.marginStart = 710
                layoutParams.marginEnd = 0
                messageText.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            }
            itemView.layoutParams = layoutParams
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newMessages: List<Message>) {
        messages = newMessages
        notifyDataSetChanged()
    }
}
