package com.example.modelfashion.Adapter.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.modelfashion.Model.response.my_product.MyProduct;
import com.example.modelfashion.Model.response.product.ProductPreview;
import com.example.modelfashion.R;

import java.util.ArrayList;
import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {

    private List<MyProduct> listProduct = new ArrayList<>();
    private ItemClickListener mClickListener;

    public void setListProduct(List<MyProduct> list) {
        this.listProduct.clear();
        this.listProduct = list;
        notifyDataSetChanged();
    }

    public List<MyProduct> getListProduct() {
        return this.listProduct;
    }

    public MyProduct getProduct(int position) {
        return listProduct.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clothes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyProduct product = listProduct.get(position);
        holder.tvName.setText(product.getProduct_name());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));
        holder.itemView.setOnClickListener(view -> {
            mClickListener.onItemClick(position, product);
        });
        if (product.getPhotos().get(0).contains("http:")){
            Glide.with(holder.imgAva.getContext())
                    .load(product.getPhotos().get(0).replace("http:","https:"))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .placeholder(R.drawable.test_img)
                    .into(holder.imgAva);
        }else {
            Glide.with(holder.imgAva.getContext())
                    .load(product.getPhotos().get(0))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .placeholder(R.drawable.test_img)
                    .into(holder.imgAva);
        }

    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView imgAva;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvPrice = itemView.findViewById(R.id.tv_product_price);
            imgAva = itemView.findViewById(R.id.img_clothes_avatar);
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, MyProduct productPreview);
    }
}
