package com.example.modelfashion.Activity.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modelfashion.Common.ProgressLoadingCommon;
import com.example.modelfashion.Constants;
import com.example.modelfashion.Interface.ApiRetrofit;
import com.example.modelfashion.MainMainActivity;
import com.example.modelfashion.PreferenceManager;
import com.example.modelfashion.R;
import com.example.modelfashion.network.ApiClient;
import com.example.modelfashion.network.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    //    ImageView imgBack;
//    EditText edtAccountSu, edtPassword, edtConfirmPassword;
//    Button btnSignUp;
//    CheckBox cbRules;
//    TextView tvSignIn, tvRules;
    ApiInterface apiInterface;
    //    ProgressLoadingCommon progressLoadingCommon;
    TextInputEditText edtName;
    TextInputEditText edtEmail;
    TextInputEditText edtPw;
    TextInputEditText edtRePw;
    TextView tvSignUp;
    TextView tvForgotPw;
    TextView tvSignIn;
    ImageView imgBack;
    CheckBox cbRules;
    TextView tvRules;
    String account_type = "";
    Boolean check_register = true;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);
        preferenceManager = new PreferenceManager(getApplicationContext());
        account_type = "normal";
        viewHolder();
        setListener();
    }

    //tham chi???u
    private void viewHolder() {
//        imgBack = findViewById(R.id.imgBack);
//        edtAccountSu = findViewById(R.id.edtAccountSu);
//        edtPassword = findViewById(R.id.edtPasswordSu);
//        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
//        btnSignUp = findViewById(R.id.btnSignUp);
//        cbRules = findViewById(R.id.cbRules);
//        tvSignIn = findViewById(R.id.tvSignIn);
        apiInterface = ApiClient.provideApiInterface(SignUpActivity.this);
//        progressLoadingCommon = new ProgressLoadingCommon();
//        tvRules = findViewById(R.id.tvRules);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPw = findViewById(R.id.edtPw);
        edtRePw = findViewById(R.id.edtRePw);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvForgotPw = findViewById(R.id.tvForgotPw);
        tvSignIn = findViewById(R.id.tvSignIn);
        imgBack = findViewById(R.id.imgBack);
        cbRules = findViewById(R.id.cbRules);
        tvRules = findViewById(R.id.tvRules);
    }

    // b???t s??? ki???n
    private void setListener() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                HashMap<String, String> user = new HashMap<>();
                user.put(Constants.Key_name, edtName.getText().toString());
                user.put(Constants.Key_email, edtEmail.getText().toString());
                user.put(Constants.Key_pass, edtPw.getText().toString());
                database.collection(Constants.Key_collection_user)
                        .add(user)
                        .addOnSuccessListener(documentReference -> {

                            preferenceManager.putBoolean(Constants.Key_SignEDname, true);
                            preferenceManager.putString(Constants.Key_id_user, documentReference.getId());
                            preferenceManager.putString(Constants.Key_name, edtName.getText().toString());

                            Intent intent = new Intent(getApplicationContext(), MainMainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        })
                        .addOnFailureListener(exception -> {

                        });
//            }}
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    }
                });
            }}

//        tvRules.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDialog();
//            }
//        });
//    }
//    private void openConfirmEmailDialog(){
//        ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
//        progressDialog.setMessage("Pending");
//        progressDialog.show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
//        ApiRetrofit.apiRetrofit.SendConfirmEmail(edtEmail.getText().toString()).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                progressDialog.hide();
//                Log.e("confirm_mail",response.body());
//                if(response.body().equals("in use")){
//                    Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_SHORT).show();
//                    builder.setCancelable(true);
//                    builder.setTitle("Email ???? ???????c s??? dung, vui l??ng ????ng k?? b???ng email kh??c");
//                    builder.setNegativeButton("????ng", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            builder.create().cancel();
//                        }
//                    });
//                    builder.create().show();
//                }else {
//                    View confirm_layout = getLayoutInflater().inflate(R.layout.dialog_confirm,null,false);
//                    builder.setView(confirm_layout);
//                    EditText edt_confirm_code = confirm_layout.findViewById(R.id.edt_confirm_code);
//
//                    builder.setNegativeButton("H???y", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            builder.create().dismiss();
//                        }
//                    });
//                    builder.setPositiveButton("X??c nh???n", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            progressDialog.setMessage("Pending");
//                            progressDialog.show();
//                            ApiRetrofit.apiRetrofit.GetConfirmMail(edtEmail.getText().toString(),edt_confirm_code.getText().toString()).enqueue(new Callback<String>() {
//                                @Override
//                                public void onResponse(Call<String> call, Response<String> response) {
//                                    progressDialog.hide();
//                                    Log.e("test1",response.body());
//                                    if(response.body().trim().equalsIgnoreCase("true")){
//                                        insertUser();
//                                        alertDialog.dismiss();
//                                    }else {
////                                        builder.create().show();
//                                        Toast.makeText(SignUpActivity.this, "M?? x??c nh???n kh??ng ch??nh x??c", Toast.LENGTH_LONG).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<String> call, Throwable t) {
//                                    progressDialog.hide();
//                                }
//                            });
//                        }
//                    });
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(SignUpActivity.this, ""+t.toString(), Toast.LENGTH_LONG).show();
//                progressDialog.hide();
//            }
//        });
//    }
//    private boolean insertUser() {
//        ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
//        progressDialog.setMessage("Pending");
//        progressDialog.show();
//        ApiRetrofit.apiRetrofit.InsertUser(edtName.getText().toString(),edtPw.getText().toString(),edtEmail.getText().toString(),account_type).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                progressDialog.hide();
//                Log.e("insert_user",response.body());
//                if(response.body().trim().equalsIgnoreCase("Success")){
//                    Toast.makeText(SignUpActivity.this, "????ng k?? th??nh c??ng", Toast.LENGTH_SHORT).show();
//                    Log.e("insert_user",response.body());
//                    check_register = true;
//                }else {
//                    Toast.makeText(SignUpActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
//                    check_register = false;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//        return check_register;
//    }

                // validate
//    private Boolean validate() {
//        Pattern special = Pattern.compile("[!#$%&*^()_+=|<>?{}\\[\\]~-]");
//        if(edtName.getText().toString().contains(" ") || edtPw.getText().toString().contains(" ")){
//            Toast.makeText(SignUpActivity.this, "T??n ????ng nh???p, m???t kh???u kh??ng c?? d???u c??ch", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (edtName.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty() || edtPw.getText().toString().isEmpty() || edtRePw.getText().toString().isEmpty()) {
//            Toast.makeText(SignUpActivity.this, "Kh??ng ????? tr???ng", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if(edtName.getText().toString().length() < 8 || edtName.getText().toString().length()>16){
//            Toast.makeText(SignUpActivity.this, "T??n ????ng nh???p ch??? g???m 8-16 k?? t???", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if (special.matcher(edtName.getText().toString()).find() || special.matcher(edtEmail.getText().toString()).find()) {
//            Toast.makeText(SignUpActivity.this, "Kh??ng ???????c vi???t k?? t??? ?????c bi???t", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if (edtPw.getText().toString().length() < 8 || edtPw.getText().toString().length()>16) {
//            Toast.makeText(SignUpActivity.this, "M???t kh???u ch??? g???m 8-16 k?? t???", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if (!edtPw.getText().toString().equalsIgnoreCase(edtRePw.getText().toString())) {
//            Toast.makeText(SignUpActivity.this, "M???t kh???u kh??ng gi???ng nhau", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if (!cbRules.isChecked()) {
//            Toast.makeText(SignUpActivity.this, "Vui l??ng ?????c v?? ?????ng ?? v???i ch??nh s??ch b???o m???t", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

//    private void openDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
//
//        // Set Title and Message:
//        builder.setTitle("Ch??nh s??ch b???o m???t.").setMessage("Ch??ng t??i thu th???p, l??u tr??? v?? x??? l?? th??ng tin c???a b???n cho qu?? tr??nh mua h??ng, cho nh???ng th??ng b??o sau n??y v?? ????? cung c???p d???ch v???. Ch??ng t??i kh??ng gi???i h???n th??ng tin c?? nh??n: danh hi???u, t??n, gi???i t??nh, ng??y sinh, email, ?????a ch???, ?????a ch??? giao h??ng, s??? ??i???n tho???i, fax, chi ti???t thanh to??n, chi ti???t thanh to??n b???ng th??? ho???c chi ti???t t??i kho???n ng??n h??ng.\n" +
//                "\n" +
//                "Ch??ng t??i s??? d??ng th??ng tin b???n ???? cung c???p ????? x??? l?? ????n ?????t h??ng, cung c???p c??c d???ch v??? v?? th??ng tin y??u c???u th??ng qua trang web v?? theo y??u c???u c???a b???n. Ch??ng t??i c?? th??? chuy???n t??n v?? ?????a ch??? cho b??n th??? ba ????? h??? giao h??ng cho b???n (v?? d??? cho b??n chuy???n ph??t nhanh ho???c nh?? cung c???p).");
//
//        //
//        builder.setCancelable(true);
//
//        // Create "Positive" button with OnClickListener.
//        builder.setPositiveButton("????ng", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                builder.create().dismiss();
//            }
//        });
//        // Create AlertDialog:
//        AlertDialog alert = builder.create();
//        alert.show();
//    }

                //ch???n back
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();  // optional depending on your needs


