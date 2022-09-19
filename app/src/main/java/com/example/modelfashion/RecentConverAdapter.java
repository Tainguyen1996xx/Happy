package com.example.modelfashion;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modelfashion.databinding.ItemContainerRecentConversionBinding;

import java.util.List;

public class RecentConverAdapter extends RecyclerView.Adapter<RecentConverAdapter.ConversionViewHoder> {

    private final List<ChatMess> chatMesses;

    public RecentConverAdapter(List<ChatMess> chatMesses) {
        this.chatMesses = chatMesses;
    }

    @NonNull
    @Override
    public ConversionViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHoder(
                ItemContainerRecentConversionBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecentConverAdapter.ConversionViewHoder holder, int position) {
        holder.setData(chatMesses.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMesses.size();
    }

        class ConversionViewHoder extends RecyclerView.ViewHolder {
        ItemContainerRecentConversionBinding binding;

        ConversionViewHoder(ItemContainerRecentConversionBinding itemContainerRecentConversionBinding) {
            super(itemContainerRecentConversionBinding.getRoot());
            binding = itemContainerRecentConversionBinding;
        }

        void setData(ChatMess chatMess) {
            binding.tvText.setText(chatMess.conversionName);
            binding.tvRecentMess.setText(chatMess.message);
        }
    }
}