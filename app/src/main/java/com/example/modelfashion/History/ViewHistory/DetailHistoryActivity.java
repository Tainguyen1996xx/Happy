package com.example.modelfashion.History.ViewHistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modelfashion.History.ApdapterHistory.DetailHistoryAdapter;
import com.example.modelfashion.Interface.ApiRetrofit;
import com.example.modelfashion.Model.MHistory.ModelHistory;
import com.example.modelfashion.Model.MHistory.ProductHistory;
import com.example.modelfashion.Model.User;
import com.example.modelfashion.Model.response.bill.BillDetail;
import com.example.modelfashion.Model.response.my_product.MyProduct;
import com.example.modelfashion.OrderStatus.ViewOrderStatus.OrderStatusActivity;
import com.example.modelfashion.R;
import com.example.modelfashion.Utility.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryActivity extends AppCompatActivity {
    TextView tv_dh_detail_history,phoneNumber_detail_history,address_detail_history,
            date_detail_history,summoney_detail_history,title_date_detail_history;
//    List<ProductHistory> list;
    ListView lv_detail_history;
    ImageView back_detail_history;
    String bill_id, user_id, date_shipped, amount,status;
    ArrayList<BillDetail> arr_bill_detail = new ArrayList<>();
    ArrayList<MyProduct> arr_my_product = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        tv_dh_detail_history = findViewById(R.id.tv_dh_detail_history);
        phoneNumber_detail_history = findViewById(R.id.phoneNumber_detail_history);
        address_detail_history = findViewById(R.id.address_detail_history);
        date_detail_history = findViewById(R.id.date_detail_history);
        summoney_detail_history = findViewById(R.id.summoney_detail_history);
        title_date_detail_history = findViewById(R.id.title_date_detail_history);
        lv_detail_history = findViewById(R.id.lv_detail_history);
        back_detail_history = findViewById(R.id.back_detail_history);
        back_detail_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        Locale locale = new Locale("vi","VN");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormat format = new DecimalFormat("###,###,###");
        Intent intent = getIntent();
        bill_id = intent.getStringExtra("bill_id");
        user_id = intent.getStringExtra("user_id");
        date_shipped = intent.getStringExtra("date");
        amount = intent.getStringExtra("amount");
        status = intent.getStringExtra("status");
        if(status.matches("Đã giao")){
            title_date_detail_history.setText("Ngày nhận hàng:");
        }else {
            title_date_detail_history.setText("Ngày mua hàng:");
        }
        //Set data bill detail
        GetBillDetail(bill_id);
        tv_dh_detail_history.setText("HD "+bill_id);
        date_detail_history.setText(date_shipped);
        summoney_detail_history.setText(format.format(Integer.parseInt(amount))+" VNĐ");
        SetUserData();
//        int index = intent.getIntExtra("index",-1);
//        String view = intent.getStringExtra("view");
//        if(view.matches("History")) {
//            ModelHistory modelHistory = HistoryActivity.listModelHistory.get(index);
//            list = new ArrayList<>();
//            list = modelHistory.getProductHistoryList();
//            tv_dh_detail_history.setText("Mã đơn : "+modelHistory.getmIDHistory());
//            phoneNumber_detail_history.setText(modelHistory.getmPhoneNumber());
//            address_detail_history.setText(" - "+modelHistory.getmAddress());
//            title_date_detail_history.setText("Ngày nhận hàng: ");
//            date_detail_history.setText(modelHistory.getmTimeRecieve());
//            date_detail_history.setTextColor(Color.parseColor("#000000"));
//            String summoney = sumPrice(list);
//            summoney_detail_history.setText(summoney);
//            DetailHistoryAdapter historyAdapter = new DetailHistoryAdapter(DetailHistoryActivity.this,list);
//            lv_detail_history.setAdapter(historyAdapter);
//        }else if(view.matches("OrderStatus")){
//            ModelHistory modelHistory = OrderStatusActivity.listModelOrderStatus.get(index);
//            list = new ArrayList<>();
//            list = modelHistory.getProductHistoryList();
//            tv_dh_detail_history.setText("Mã đơn : "+modelHistory.getmIDHistory());
//            phoneNumber_detail_history.setText(modelHistory.getmPhoneNumber());
//            address_detail_history.setText(" - "+modelHistory.getmAddress());
//            title_date_detail_history.setText("Tình trạng đơn: ");
//            date_detail_history.setText(modelHistory.getmStatus());
//            if(modelHistory.getmStatus().matches("Đang Chờ")){
//                date_detail_history.setTextColor(Color.parseColor("#FF0000"));
//            }else if(modelHistory.getmStatus().matches("Đang Giao")){
//                date_detail_history.setTextColor(Color.parseColor("#008E06"));
//            }
//            String summoney = sumPrice(list);
//            summoney_detail_history.setText(summoney);
//            DetailHistoryAdapter historyAdapter = new DetailHistoryAdapter(DetailHistoryActivity.this,list);
//            lv_detail_history.setAdapter(historyAdapter);
//        }
    }

    private void GetBillDetail(String bill_id){
        ApiRetrofit.apiRetrofit.GetBillDetailInBill(bill_id).enqueue(new Callback<ArrayList<BillDetail>>() {
            @Override
            public void onResponse(Call<ArrayList<BillDetail>> call, Response<ArrayList<BillDetail>> response) {
                arr_bill_detail = response.body();
                ArrayList<String> arr_detail_id = new ArrayList<>();
                for (int i = 0; i< arr_bill_detail.size(); i++){
                    arr_detail_id.add(arr_bill_detail.get(i).getDetailId());
                }
                JSONArray jsonArray = new JSONArray(arr_detail_id);
                SetData(jsonArray);
            }

            @Override
            public void onFailure(Call<ArrayList<BillDetail>> call, Throwable t) {

            }
        });
    }

    private void SetData(JSONArray arr_detail_id){
        ApiRetrofit.apiRetrofit.GetProductByDetailId(arr_detail_id).enqueue(new Callback<ArrayList<MyProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<MyProduct>> call, Response<ArrayList<MyProduct>> response) {
                arr_my_product = response.body();
                DetailHistoryAdapter detailHistoryAdapter = new DetailHistoryAdapter(DetailHistoryActivity.this,arr_bill_detail,arr_my_product);
                lv_detail_history.setAdapter(detailHistoryAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<MyProduct>> call, Throwable t) {

            }
        });
    }

    private void SetUserData(){
        ApiRetrofit.apiRetrofit.GetUserById(user_id).enqueue(new Callback<com.example.modelfashion.Model.response.User.User>() {
            @Override
            public void onResponse(Call<com.example.modelfashion.Model.response.User.User> call, Response<com.example.modelfashion.Model.response.User.User> response) {
                com.example.modelfashion.Model.response.User.User user1 = response.body();
                phoneNumber_detail_history.setText(user1.getPhone());
                address_detail_history.setText(user1.getAddress());
            }

            @Override
            public void onFailure(Call<com.example.modelfashion.Model.response.User.User> call, Throwable t) {

            }
        });
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
}