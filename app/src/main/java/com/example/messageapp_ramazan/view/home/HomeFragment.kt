package com.example.messageapp_ramazan.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.messageapp_ramazan.adapter.MessageAdapter
import com.example.messageapp_ramazan.databinding.FragmentHomeBinding
import com.example.messageapp_ramazan.model.Message
import com.example.messageapp_ramazan.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var messageAdapter: MessageAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        messageAdapter = MessageAdapter(messages)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = messageAdapter
        }

        binding.button.setOnClickListener {
            val userMessageContent = binding.textInputEditText.text.toString()
            if (userMessageContent.isNotBlank()) {
                viewModel.addMessage(userMessageContent, true)
                binding.textInputEditText.text?.clear()
            }
        }

        viewModel.messageData.observe(viewLifecycleOwner) { newMessages ->
            messages.clear()
            messages.addAll(newMessages)
            messageAdapter.updateList(messages)
            binding.recyclerview.scrollToPosition(messages.size - 1)
        }
        return binding.root
    }
}
