package com.example.modelfashion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.modelfashion.Activity.ChatActivity;
import com.example.modelfashion.Activity.MainActivity;
import com.example.modelfashion.databinding.ActivityMainmainBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaesActivity extends AppCompatActivity {

private DocumentReference documentReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager preferenceManager= new PreferenceManager(getApplicationContext());
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        documentReference = database.collection(Constants.Key_collection_user).document(preferenceManager.getString(Constants.Key_id_user));
    }

    @Override
    protected void onPause() {
        super.onPause();
        documentReference.update(Constants.Key_last_Avalibity, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        documentReference.update(Constants.Key_last_Avalibity, 1);
    }
}