package com.example.modelfashion.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modelfashion.ChatAdapter;
import com.example.modelfashion.ChatMess;
import com.example.modelfashion.Constants;
import com.example.modelfashion.PreferenceManager;
import com.example.modelfashion.User;
import com.example.modelfashion.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private User receiUser;
    private List<ChatMess> chatMesses;
    private PreferenceManager preferenceManager;
    private ChatAdapter chatAdapter;
    private FirebaseFirestore database;
    private String conversationId = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();
        loadReceiDetail();
        init();
        listenerMess();

    }

    private void init() {
        preferenceManager = new PreferenceManager(getApplicationContext());
        chatMesses = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMesses, preferenceManager.getString(Constants.Key_id_user));
        binding.rcvCHat.setAdapter(chatAdapter);
        database = FirebaseFirestore.getInstance();
    }

    private void sendMessage() {
        HashMap<String, Object> message = new HashMap<>();
        message.put(Constants.Key_sender_id, preferenceManager.getString(Constants.Key_id_user));
        message.put(Constants.Key_recei_id, receiUser.id);
        message.put(Constants.Key_message, binding.inputMess.getText().toString());
        message.put(Constants.Key_timestamp, new Date());
        database.collection(Constants.Key_collection_chat).add(message);
        if (conversationId != null){
            updateConvert(binding.inputMess.getText().toString());
        }else {
            HashMap<String, Object> conversion = new HashMap<>();
            conversion.put(Constants.Key_sender_id,preferenceManager.getString(Constants.Key_id_user));
            conversion.put(Constants.Key_sender_name,preferenceManager.getString(Constants.Key_name));
            conversion.put(Constants.Key_recei_id,receiUser.id);
            conversion.put(Constants.Key_recei_name,receiUser.name);
            conversion.put(Constants.Key_last_message,binding.inputMess.getText().toString());
            conversion.put(Constants.Key_timestamp,new Date());
            addConvert(conversion);
        }
        binding.inputMess.setText(null);
    }

    private void listenerMess() {
        database.collection(Constants.Key_collection_chat)
                .whereEqualTo(Constants.Key_sender_id, preferenceManager.getString(Constants.Key_id_user))
                .whereEqualTo(Constants.Key_recei_id, receiUser.id)
                .addSnapshotListener(evenListterner);

        database.collection(Constants.Key_collection_chat)
                .whereEqualTo(Constants.Key_sender_id, receiUser.id)
                .whereEqualTo(Constants.Key_recei_id, preferenceManager.getString(Constants.Key_id_user))
                .addSnapshotListener(evenListterner);
    }


    @SuppressLint("NotifyDataSetChanged")
    private final EventListener<QuerySnapshot> evenListterner = (value, err) -> {
        if (err != null) {
            return;
        }
        if (value != null) {
            int count = chatMesses.size();
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    ChatMess chatMess = new ChatMess();
                    chatMess.senderId = documentChange.getDocument().getString(Constants.Key_sender_id);
                    chatMess.receiD = documentChange.getDocument().getString(Constants.Key_recei_id);
                    chatMess.message = documentChange.getDocument().getString(Constants.Key_message);
                    chatMess.datetime = getReadDateTime(documentChange.getDocument().getDate(Constants.Key_timestamp));
                    chatMess.dateObject = documentChange.getDocument().getDate(Constants.Key_timestamp);
                    chatMesses.add(chatMess);
                }
            }
            Collections.sort(chatMesses, (obj1, obj2) -> obj1.dateObject.compareTo(obj2.dateObject));
            if (count == 0) {
                chatAdapter.notifyDataSetChanged();
            } else {
                chatAdapter.notifyItemRangeInserted(chatMesses.size(), chatMesses.size());
                binding.rcvCHat.smoothScrollToPosition(chatMesses.size() - 1);
            }
            binding.rcvCHat.setVisibility(View.VISIBLE);
        }
        binding.progress.setVisibility(View.GONE);
        if (conversationId == null){
            checkForconvers();
        }
    };

    private void loadReceiDetail() {
        receiUser = (User) getIntent().getSerializableExtra(Constants.Key_User);
        binding.tvName.setText(receiUser.name);
    }

    private void setListeners() {
        binding.imageback.setOnClickListener(v -> onBackPressed());
        binding.layoutSend.setOnClickListener(v -> sendMessage());
    }

    private String getReadDateTime(Date date) {
        return new SimpleDateFormat("MMMM,yyyy - hh:mm a", Locale.getDefault()).format(date);
    }


    public void addConvert(HashMap<String , Object> conversion) {
        database.collection(Constants.Key_collection_convertsation)
                .add(conversion)
                .addOnSuccessListener(documentReference ->conversationId = documentReference.getId());

    }

    public void updateConvert(String messs){
        DocumentReference documentReference =
                database.collection(Constants.Key_collection_convertsation).document(conversationId);
        documentReference.update(Constants.Key_last_message, messs,Constants.Key_timestamp,new Date());
    }

    private void checkForconvers(){
        if(chatMesses.size() != 0){
            checkForconversRemote(
                    preferenceManager.getString(Constants.Key_id_user),
                    receiUser.id
            );

            checkForconversRemote(
                    receiUser.id,
                    preferenceManager.getString(Constants.Key_id_user)

            );
        }
    }

    private void checkForconversRemote(String senderid, String receverId){
        database.collection(Constants.Key_collection_convertsation)
                .whereEqualTo(Constants.Key_sender_id,senderid)
                .whereEqualTo(Constants.Key_recei_id,receverId)
                .get().addOnCompleteListener(conversionOnCompleteListener);
    }
    private final OnCompleteListener<QuerySnapshot> conversionOnCompleteListener = task -> {
        if (task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0) {
            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
            conversationId = documentSnapshot.getId();
        }
    };
}