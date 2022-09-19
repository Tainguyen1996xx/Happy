package com.example.modelfashion.Adapter.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modelfashion.Model.response.category.Category;
import com.example.modelfashion.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public CategoryAdapter() {
    }

    private List<String> listCategory = new ArrayList<>();;
    private ItemClickListener mClickListener;
    private int index;

    public void setListCategory(List<String> list) {
        this.listCategory.clear();
        this.listCategory = list;
        notifyDataSetChanged();
    }

    public List<String> getListCategory() {
        return this.listCategory;
    }

    public String getCategory(int position) {
        return listCategory.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_left_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = listCategory.get(position);
        holder.myTextView.setText(category);
        if (position == index) {
            holder.myTextView.setBackgroundColor(holder.myTextView.getContext().getResources().getColor(R.color.grey_active));
        }else {
            holder.myTextView.setBackgroundColor(holder.myTextView.getContext().getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tv_category_name);
            myTextView.setOnClickListener(view -> {
                mClickListener.onItemClick(view, getAdapterPosition());
            });
        }
    }

    public void highLightSelectedItem(int position) {
        index = position;
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
