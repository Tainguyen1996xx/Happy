package com.example.modelfashion.Common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.CountDownTimer;

import com.example.modelfashion.R;
import com.example.modelfashion.Utility.Constants;

public class ProgressLoadingCommon {
    public void showProgressLoading(Context context) {
        ProgressDialog progressdialog = new ProgressDialog(context);
        new CountDownTimer(2000, 1000) {
            public void onFinish() {
                progressdialog.hide();
            }

            public void onTick(long millisUntilFinished) {
                progressdialog.setMessage("Vui lòng đợi");
                progressdialog.setCancelable(true);
                progressdialog.show();
            }
        }.start();
    }
}
