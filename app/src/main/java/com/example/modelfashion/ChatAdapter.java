package com.example.modelfashion;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modelfashion.databinding.ItemContainerReceiMessBinding;
import com.example.modelfashion.databinding.ItemContainerSentmessBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ChatAdapter(List<ChatMess> chatMesses, String senderID) {
        this.chatMesses = chatMesses;
        this.senderID = senderID;
    }

    private final List<ChatMess> chatMesses;
    private final String senderID;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            return new SenMessViewHolder(
                    ItemContainerSentmessBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false)
            );
        } else {
            return new ReceivedViewHolder(
                    ItemContainerReceiMessBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent, false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SenMessViewHolder) holder).setData(chatMesses.get(position));
        } else {
            ((ReceivedViewHolder) holder).setData(chatMesses.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return chatMesses.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMesses.get(position).senderId.equals(senderID)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }

    }

    static class SenMessViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerSentmessBinding binding;

        SenMessViewHolder(ItemContainerSentmessBinding itemContainerSentmessBinding) {
            super(itemContainerSentmessBinding.getRoot());
            binding = itemContainerSentmessBinding;

        }

        void setData(ChatMess chatMessage) {
            binding.textMess.setText(chatMessage.message);
            binding.tvDatetime.setText(chatMessage.datetime);
        }
    }

    static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerReceiMessBinding binding;

        ReceivedViewHolder(ItemContainerReceiMessBinding itemContainerReceiMessBinding) {
            super(itemContainerReceiMessBinding.getRoot());
            binding = itemContainerReceiMessBinding;
        }

        void setData(ChatMess chatMessage) {
            binding.textMess.setText(chatMessage.message);
            binding.tvDatetime.setText(chatMessage.datetime);
        }
    }
}