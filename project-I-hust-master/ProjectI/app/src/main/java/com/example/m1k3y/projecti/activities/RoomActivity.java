package com.example.m1k3y.projecti.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m1k3y.projecti.R;
import com.example.m1k3y.projecti.adapters.CustomLayoutManager;
import com.example.m1k3y.projecti.adapters.MessageAdapter;
import com.example.m1k3y.projecti.models.MessageModel;
import com.example.m1k3y.projecti.models.PassingDataModel;
import com.example.m1k3y.projecti.services.WelcomeService;
import com.example.m1k3y.projecti.utils.Utils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_auto_new)
    ImageView ivAutoNew;
    @BindView(R.id.iv_send)
    ImageView ivSend;
    @BindView(R.id.et_typing_message)
    EditText etTypingMessage;

    @BindView(R.id.rv_messages)
    RecyclerView rvMessages;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;


    private List<MessageModel> messageModelList = new ArrayList<>();
    private PassingDataModel passingDataModel;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private MessageAdapter messageAdapter;
    private CustomLayoutManager customLayoutManager;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private static final String TAG = "RoomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("message_list");

        Initialization();
        setupUI();
    }

    private void Initialization() {


        passingDataModel = (PassingDataModel) getIntent().getSerializableExtra("passing_data_model");
        Picasso.get().load(passingDataModel.getProfilePhotoUrl()).into(ivAvatar);
        messageModelList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageModelList, this);
        customLayoutManager = new CustomLayoutManager(this, 1);

        rvMessages.setLayoutManager(customLayoutManager);
        rvMessages.setAdapter(messageAdapter);
        welcomeUser(passingDataModel.getUsername());


    }

    private void setupUI() {
        //welcomeUser();
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        etTypingMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    //   sendMessage();

                    return true;
                }
                return false;
            }


        });

        displayMessagesFirst();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                messageModelList.add(messageModel);
                messageAdapter.notifyItemInserted(messageModelList.size() - 1);
                customLayoutManager.setTargetStartPos(messageModelList.size() - 1, 0);

                String name = messageModel.username;
                String content = messageModel.content;
                String time = messageModel.time;


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                displayMessagesFirst();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void welcomeUser(String displayName) {

        Intent intent = new Intent(this, WelcomeService.class);
        intent.putExtra("display_name", displayName);

        PendingIntent pendingIntent = PendingIntent.getService(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        startService(intent);
    }



    private void displayMessagesFirst() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageModelList = new ArrayList<>();
                //iterate through each user, ignoring their UID
                for (DataSnapshot messageSnapShot : dataSnapshot.getChildren()) {
                    MessageModel messageModel = messageSnapShot.getValue(MessageModel.class);
                    //  String content = dataSnapshot.getValue("content");

                    Log.d(TAG, "collectDatas: " + messageModel);
                    messageModelList.add(messageModel);

                }


                messageAdapter = new MessageAdapter(messageModelList, RoomActivity.this);
                rvMessages.setAdapter(messageAdapter);
                customLayoutManager.setTargetStartPos(messageModelList.size() - 1, 0);
                rvMessages.setLayoutManager(customLayoutManager);
                // rvMessages.smoothScrollToPosition(messageModelList.size());
                Log.d(TAG, "onDataChange: " + dataSnapshot.getValue());


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void sendMessage() {
        if (etTypingMessage.getText().toString().trim().length() < 1) {
            return;
        }
        updateFirebaseDatabase(passingDataModel.getUsername(), etTypingMessage.getText().toString().trim(), passingDataModel.getProfilePhotoUrl());


        etTypingMessage.setText("");
        etTypingMessage.clearFocus();

        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    private void updateFirebaseDatabase(String name, String content, String photoUrl) {
        databaseReference.child(Utils.getCurrentTimeMilis()).setValue(
                new MessageModel(
                        Utils.getTime(),
                        name,
                        content,
                        photoUrl

                )
        );

    }


    @OnClick({R.id.iv_back, R.id.iv_auto_new, R.id.iv_send, R.id.et_typing_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(RoomActivity.this, SignInActivity.class));
                break;
            case R.id.iv_auto_new:
                displayMessagesFirst();
                break;
            case R.id.iv_send:
                sendMessage();
                ;
                Log.d(TAG, "onViewClicked: " + messageModelList.size());

                break;

            case R.id.et_typing_message:
                etTypingMessage.setFocusableInTouchMode(true);
                etTypingMessage.setHint("Type a message...");


                break;

        }
    }
}
