package com.example.modelfashion.OrderStatus.AdapterOrderStatus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.modelfashion.History.ViewHistory.DetailHistoryActivity;
import com.example.modelfashion.Model.MHistory.ModelHistory;
import com.example.modelfashion.Model.MHistory.ProductHistory;
import com.example.modelfashion.R;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderStatusAdapter extends BaseAdapter {
    Context context;
    List<ModelHistory> listModel;


    public OrderStatusAdapter(Context context, List<ModelHistory> listModel) {
        this.context = context;
        this.listModel = listModel;
    }

    @Override
    public int getCount() {
        return listModel.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_orderstatus,viewGroup,false);
        Locale locale = new Locale("vi","VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        String price0 = numberFormat.format(Double.parseDouble(listModel.get(i).getProductHistoryList().get(0).getmPriceProduct()));
        ImageView img_subproduct0 = view.findViewById(R.id.img_subproduct0);
        TextView tv_name_subproduct0 = view.findViewById(R.id.tv_name_subproduct0);
        TextView tv_sumproduct0 = view.findViewById(R.id.tv_sumproduct0);
        TextView tv_size_subproduct0 = view.findViewById(R.id.tv_size_subproduct0);
        TextView tv_price0 = view.findViewById(R.id.tv_price0);
        TextView tv_sumSP = view.findViewById(R.id.tv_sumSP);
        TextView tv_sumPrice = view.findViewById(R.id.tv_sumPrice);
        TextView tv_detail = view.findViewById(R.id.tv_detail);
        TextView item_orderstatus_ma = view.findViewById(R.id.item_orderstatus_ma);
        TextView item_orderstatus_time = view.findViewById(R.id.item_orderstatus_time);
        TextView item_orderstatus_status = view.findViewById(R.id.item_orderstatus_status);
        item_orderstatus_ma.setText("Mã đơn: "+listModel.get(i).getmIDHistory());
        item_orderstatus_time.setText("Ngày đặt hàng: "+listModel.get(i).getmTimeOrder());
        item_orderstatus_status.setText(listModel.get(i).getmStatus());
        if(listModel.get(i).getmStatus().contains("Chờ xác nhận")){
            item_orderstatus_status.setTextColor(Color.parseColor("#FF0000"));
        }else if(listModel.get(i).getmStatus().contains("Đang Giao")) {
            item_orderstatus_status.setTextColor(Color.parseColor("#008E06"));
        }else if(listModel.get(i).getmStatus().contains("Chờ lấy hàng")) {
            item_orderstatus_status.setTextColor(Color.parseColor("#ff9800"));
        }
        Glide.with(context).load(listModel.get(i).getProductHistoryList().get(0).getmImgeProduct()).into(img_subproduct0);
        tv_name_subproduct0.setText(listModel.get(i).getProductHistoryList().get(0).getmNameProduct());
        tv_sumproduct0.setText("x"+listModel.get(i).getProductHistoryList().get(0).getmSumProduct());
        tv_size_subproduct0.setText(listModel.get(i).getProductHistoryList().get(0).getmSizeProduct());
        tv_price0.setText(price0);
        tv_sumSP.setText(String.valueOf(listModel.get(i).getProductHistoryList().size())+" Sản phẩm");
        tv_sumPrice.setText("Tổng: "+sumPrice(listModel.get(i).getProductHistoryList()));
        tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHistoryActivity.class);
                intent.putExtra("index",i);
                intent.putExtra("view","OrderStatus");
                context.startActivity(intent);

            }
        });
        return view;

    }
    private String sumPrice(List<ProductHistory> list){
        int sum = 0;
        for (int i = 0;i<list.size();i++){
            sum+=Integer.parseInt(list.get(i).getmPriceProduct())*Integer.parseInt(list.get(i).getmSumProduct());
        }
        Locale locale = new Locale("vi","VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        String sumP = numberFormat.format(Double.parseDouble(String.valueOf(sum)));
        return sumP;
    }
}
