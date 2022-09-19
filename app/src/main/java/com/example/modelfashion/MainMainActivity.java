package com.example.modelfashion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modelfashion.Activity.ChatActivity;
import com.example.modelfashion.Activity.MainActivity;
import com.example.modelfashion.Activity.SignIn.SignUpActivity;
import com.example.modelfashion.databinding.ActivityMainmainBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainMainActivity extends AppCompatActivity {
    private ActivityMainmainBinding binding;
    private PreferenceManager preferenceManager;
    private RecentConverAdapter converAdapter;
    private List<ChatMess> conversation;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainmainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        init();
        loadUserDETAIL();
        getToken();
        setListen();
        listenconversta();
    }

    private void init() {
        conversation = new ArrayList<>();
        converAdapter = new RecentConverAdapter(conversation);
        binding.rcvConvert.setAdapter(converAdapter);
        database = FirebaseFirestore.getInstance();
    }

    private void loadUserDETAIL() {
//        binding.tvName.setText(
//                preferenceManager.getString(Constants.Key_name));
    }



    private void setListen() {
//        binding.imgOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signOut();
//            }
//        });

        binding.floating.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), UserActivity.class)));

         binding.imgProfile.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), MainActivity.class)));


    }

//    private void signOut() {
////       signOut showTOAST("sing out");
////
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//        DocumentReference documentReference =
//                database.collection(Constants.Key_collection_user).document(
//                        preferenceManager.getString(Constants.Key_id_user)
//                );
//
//        HashMap<String, Object> update = new HashMap<>();
//        update.put(Constants.Key_FCM_TOKEN, FieldValue.delete());
//        documentReference.update(update)
//                .addOnSuccessListener(unused -> {
//                    preferenceManager.clear();
//                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
//                    startActivity(intent);
//                    finish();
////
//                })
//                .addOnFailureListener(exception -> {
//                    showTOAST(exception.getMessage());
//                });
//    }

   private void onc(User user){
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.Key_User,user);
        startActivity(intent);
   }

    private void showTOAST(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void  listenconversta(){
        database.collection(Constants.Key_collection_convertsation)
                .whereEqualTo(Constants.Key_sender_id, preferenceManager.getString(Constants.Key_id_user))
                .addSnapshotListener(eventListener);
        database.collection(Constants.Key_collection_convertsation)
                .whereEqualTo(Constants.Key_recei_id, preferenceManager.getString(Constants.Key_id_user))
                .addSnapshotListener(eventListener);
    }



    private final EventListener<QuerySnapshot> eventListener = (value, err) -> {
        if (err != null) {
            return;
        }
        if (value != null) {
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    String sederId = documentChange.getDocument().getString(Constants.Key_sender_id);
                    String receiId = documentChange.getDocument().getString(Constants.Key_recei_id);
                    ChatMess chatMess = new ChatMess();
                    chatMess.senderId = sederId;
                    chatMess.receiD = receiId;
                    if (preferenceManager.getString(Constants.Key_id_user).equals(sederId)) {
                        chatMess.conversionName = documentChange.getDocument().getString(Constants.Key_recei_name);
                        chatMess.conversionId = documentChange.getDocument().getString(Constants.Key_recei_id);

                    } else {
                        chatMess.conversionName = documentChange.getDocument().getString(Constants.Key_sender_name);
                        chatMess.conversionId = documentChange.getDocument().getString(Constants.Key_sender_id);

                    }

                    chatMess.message = documentChange.getDocument().getString(Constants.Key_last_message);
                    chatMess.dateObject = documentChange.getDocument().getDate(Constants.Key_timestamp);
                    conversation.add(chatMess);

                } else if (documentChange.getType() == DocumentChange.Type.MODIFIED) {

                    for (int i = 0; i < conversation.size(); i++) {
                        String senderid = documentChange.getDocument().getString(Constants.Key_sender_id);
                        String receiverid = documentChange.getDocument().getString(Constants.Key_recei_id);
                        if (conversation.get(i).senderId.equals(senderid) && conversation.get(i).receiD.equals(receiverid)) {
                            conversation.get(i).message = documentChange.getDocument().getString(Constants.Key_last_message);
                            conversation.get(i).dateObject = documentChange.getDocument().getDate(Constants.Key_timestamp);
                            break;
                        }
                    }
                }
            }
        }
        Collections.sort(conversation, (obj1, obj2) -> obj2.dateObject.compareTo(obj1.dateObject));
        converAdapter.notifyDataSetChanged();
        binding.rcvConvert.smoothScrollToPosition(0);
        binding.rcvConvert.setVisibility(View.VISIBLE);
    };

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken(String token) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.Key_collection_user).document(
                        preferenceManager.getString(Constants.Key_id_user)
                );

        documentReference.update((String) Constants.Key_FCM_TOKEN, token)
                .addOnSuccessListener(unused -> showTOAST("token update ssff"))
                .addOnFailureListener(e -> showTOAST("unable update"));
    }
}