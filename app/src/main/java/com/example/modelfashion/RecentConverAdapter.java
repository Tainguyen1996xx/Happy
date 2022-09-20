package com.example.modelfashion;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modelfashion.databinding.ItemContainerRecentConversionBinding;

import java.util.List;

public class RecentConverAdapter extends RecyclerView.Adapter<RecentConverAdapter.ConversionViewHoder> {

    private final List<ChatMess> chatMesses;
    private final converSation converSation;

    public RecentConverAdapter(List<ChatMess> chatMesses, com.example.modelfashion.converSation converSation) {
        this.chatMesses = chatMesses;
        this.converSation = converSation;
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
            binding.getRoot().setOnClickListener(v -> {
                User user = new User();
                user.id =  chatMess.conversionId;
                user.name =  chatMess.conversionName;
                converSation.converClicked(user);
            });
        }
    }

}