package com.example.modelfashion.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modelfashion.Model.ItemSaleMain;
import com.example.modelfashion.R;

import java.util.ArrayList;

public class VpSaleMainFmAdapter extends RecyclerView.Adapter<VpSaleMainFmAdapter.ViewHolder> {
    private ArrayList<ItemSaleMain> arrItem;

    public VpSaleMainFmAdapter(ArrayList<ItemSaleMain> arrItem) {
        this.arrItem = arrItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sale_item_main,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ItemSaleMain item = arrItem.get(i);
        viewHolder.imgView.setImageResource(item.getImgResource());
    }

    @Override
    public int getItemCount() {
        if(arrItem!=null){
            return arrItem.size();
        }else
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imgView = itemView.findViewById(R.id.img_sale_main);
        }
    }
}
