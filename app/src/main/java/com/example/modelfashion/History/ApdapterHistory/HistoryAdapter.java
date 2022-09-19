package com.example.modelfashion.History.ApdapterHistory;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.modelfashion.History.ViewHistory.DetailHistoryActivity;
import com.example.modelfashion.Model.MHistory.ProductHistory;
import com.example.modelfashion.Model.response.bill.Bill;

import com.example.modelfashion.Model.response.my_product.MyProduct;
import com.example.modelfashion.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends BaseAdapter {

//    List<ModelHistory> listModel;
    ArrayList<Bill> arr_bill;
    Context context;
    ArrayList<MyProduct> arr_my_product;
    String user_id;
    public HistoryAdapter(Context context, ArrayList<Bill> arr_bill, ArrayList<MyProduct> arr_my_product, String user_id){

        this.context = context;
        this.arr_bill = arr_bill;
        this.arr_my_product = arr_my_product;
        this.user_id = user_id;
    }
//    public HistoryAdapter(Context context,List<ModelHistory> listModel) {
//        this.listModel = listModel;
//        this.context = context;
//    }

    @Override
    public int getCount() {
        return arr_bill.size();
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
        view = LayoutInflater.from(context).inflate(R.layout.item_history,viewGroup,false);

//        Locale locale = new Locale("vi","VN");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
//        String price0 = numberFormat.format(Double.parseDouble(listModel.get(i).getProductHistoryList().get(0).getmPriceProduct()));
        //Tham chiếu
        ImageView img_subproduct0 = view.findViewById(R.id.img_subproduct0);
        TextView tv_status = view.findViewById(R.id.tv_status);
        TextView tv_name_subproduct0 = view.findViewById(R.id.tv_name_subproduct0);
        TextView tv_sumproduct0 = view.findViewById(R.id.tv_sumproduct0);
        TextView tv_size_subproduct0 = view.findViewById(R.id.tv_size_subproduct0);
        TextView tv_price0 = view.findViewById(R.id.tv_price0);
        TextView tv_sumSP = view.findViewById(R.id.tv_sumSP);
        TextView tv_sumPrice = view.findViewById(R.id.tv_sumPrice);
        TextView tv_detail = view.findViewById(R.id.tv_detail);
        TextView item_history_ma = view.findViewById(R.id.item_history_ma);
        TextView item_history_time = view.findViewById(R.id.item_history_time);
        TextView tv_feedback = view.findViewById(R.id.tv_feedback);
        LinearLayout ll_item_history = view.findViewById(R.id.ll_item_history);

        //Set data
//        item_history_ma.setText("Mã đơn: "+listModel.get(i).getmIDHistory());
//        item_history_time.setText("Ngày nhận: "+listModel.get(i).getmTimeOrder());
//        Glide.with(context).load(listModel.get(i).getProductHistoryList().get(0).getmImgeProduct()).into(img_subproduct0);
//        tv_name_subproduct0.setText(listModel.get(i).getProductHistoryList().get(0).getmNameProduct());
//        tv_sumproduct0.setText("x"+listModel.get(i).getProductHistoryList().get(0).getmSumProduct());
//        tv_size_subproduct0.setText(listModel.get(i).getProductHistoryList().get(0).getmSizeProduct());
//        tv_price0.setText(price0);
//        tv_sumSP.setText(String.valueOf(listModel.get(i).getProductHistoryList().size())+" Sản phẩm");
//        tv_sumPrice.setText("Tổng: "+sumPrice(listModel.get(i).getProductHistoryList()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        item_history_ma.setText("Mã đơn: DH"+ arr_bill.get(i).getBillId());

        Glide.with(context).load(arr_my_product.get(i).getPhotos().get(0)).into(img_subproduct0);
        tv_status.setText(arr_bill.get(i).getStatus());
        tv_name_subproduct0.setText(arr_my_product.get(i).getProduct_name());
        tv_sumproduct0.setText("x"+arr_bill.get(i).getBillDetail().get(0).getQuantity());
        tv_size_subproduct0.setText(arr_bill.get(i).getBillDetail().get(0).getSize());
        tv_price0.setText(decimalFormat.format(Integer.parseInt(arr_my_product.get(i).getPrice()))+" VNĐ");
        tv_sumSP.setText(arr_bill.get(i).getBillDetail().size()+" Sản phẩm");
        tv_sumPrice.setText("Tổng: "+decimalFormat.format(Integer.parseInt(arr_bill.get(i).getAmount()))+" VNĐ");

        tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHistoryActivity.class);
                intent.putExtra("bill_id", arr_bill.get(i).getBillId());
                intent.putExtra("user_id", user_id);
                intent.putExtra("date", arr_bill.get(i).getDateShipped());
                intent.putExtra("amount", arr_bill.get(i).getAmount());
                intent.putExtra("status", arr_bill.get(i).getStatus());
                context.startActivity(intent);
            }
        });
        if(arr_bill.get(i).getStatus().matches("Đã giao")){
            item_history_time.setText("Ngày nhận: "+ arr_bill.get(i).getDateShipped());
            tv_feedback.setVisibility(View.VISIBLE);
            tv_feedback.setText("Phản hồi");
        }else {
            item_history_time.setText("Ngày đặt: "+ arr_bill.get(i).getDateCreated());
            tv_feedback.setVisibility(View.VISIBLE);
            tv_feedback.setText("Hủy đơn");

        }
        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arr_bill.get(i).getStatus().matches("Đã giao")) {
                    loadDialogFeedback(context, "DH " + arr_bill.get(i).getBillId());
                }else {
                    showDialogCancelOrder();
                }

            }
        });
        return view;
    }

//    private String sumPrice(List<ProductHistory> list){
//        int sum = 0;
//        for (int i = 0;i<list.size();i++){
//            sum+=Integer.parseInt(list.get(i).getmPriceProduct())*Integer.parseInt(list.get(i).getmSumProduct());
//        }
//        Locale locale = new Locale("vi","VN");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
//        String sumP = numberFormat.format(Double.parseDouble(String.valueOf(sum)));
//        return sumP;
//    }

    private void loadDialogFeedback(Context context,String maDH){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_feedback);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        EditText edt_title = dialog.findViewById(R.id.edt_title);
        EditText edt_content = dialog.findViewById(R.id.edt_content);
        TextView btn_send = dialog.findViewById(R.id.btn_send);
        edt_title.setText("Phản hồi đơn hàng "+maDH);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content;
                content = edt_content.getText().toString().trim();
                if (content.isEmpty() ){
                    Toast.makeText(context,"Bạn chưa nhập nội dung",Toast.LENGTH_SHORT).show();
                }else {
                    String uriText = "mailto:" + context.getString(R.string.email) +
                            "?subject=" +"Feedback đơn hàng " +maDH +
                            "&body=" + content;
                    Uri uri = Uri.parse(uriText);
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                    sendIntent.setData(uri);
                    context.startActivity(Intent.createChooser(sendIntent, "Send Email"));
                    dialog.dismiss();

                }
            }
        });
        dialog.show();
    }
    private void showDialogCancelOrder(){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_cancel_order);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView tv_cancel = dialog.findViewById(R.id.tv_cancel);
        TextView tv_hotline,tv_email,tv_zalo;
        ImageView img_coppy_hotline,img_email,img_coppy_zalo,img_page;
        tv_hotline = dialog.findViewById(R.id.tv_hotline);
        tv_email = dialog.findViewById(R.id.tv_email);
        tv_zalo = dialog.findViewById(R.id.tv_zalo);
        img_coppy_hotline = dialog.findViewById(R.id.img_coppy_hotline);
        img_email = dialog.findViewById(R.id.img_email);
        img_coppy_zalo = dialog.findViewById(R.id.img_coppy_zalo);
        img_page = dialog.findViewById(R.id.img_page);
        tv_hotline.setText("Hotline   : "+context.getString(R.string.hotline));
        tv_email.setText("Email      : "+context.getString(R.string.email));
        tv_zalo.setText("Zalo        : "+context.getString(R.string.zalo));
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        img_coppy_hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClipboard(context,context.getString(R.string.hotline));
                Toast.makeText(context,"copy hotline successfully",Toast.LENGTH_SHORT).show();
            }
        });
        img_coppy_zalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClipboard(context,context.getString(R.string.zalo));
                Toast.makeText(context,"copy zalo successfully",Toast.LENGTH_SHORT).show();
            }
        });
        img_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText = "mailto:" + context.getString(R.string.email) +
                        "?subject=" +"" +
                        "&body=" + "";
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                context.startActivity(Intent.createChooser(sendIntent, "Send Email"));
                dialog.dismiss();
            }
        });
        img_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = context.getString(R.string.linkpage);
                Intent intentPrivacy = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                context.startActivity(intentPrivacy);
            }
        });

        dialog.show();

    }

    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }

}
