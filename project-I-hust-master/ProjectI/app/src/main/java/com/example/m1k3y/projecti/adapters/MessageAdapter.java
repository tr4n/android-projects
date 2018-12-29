package com.example.m1k3y.projecti.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m1k3y.projecti.R;
import com.example.m1k3y.projecti.models.MessageModel;
import com.example.m1k3y.projecti.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<MessageModel> messageModelList = new ArrayList<>();
    private Context context;

    public MessageAdapter(List<MessageModel> messageModelList, Context context) {
        this.messageModelList = messageModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_layout, null);

        return new MessageViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.setData(messageModelList, position);
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private Context context;

        private View itemView;
        private ImageView ivProfileLeft;

        private TextView tvLeftUsername;

        private TextView tvLeftContent;
        private TextView tvLeftTime;


        public MessageViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            this.ivProfileLeft = itemView.findViewById(R.id.iv_profile_left);
            this.tvLeftUsername = itemView.findViewById(R.id.tv_left_username);
            this.tvLeftContent = itemView.findViewById(R.id.tv_left_content);
            this.tvLeftTime = itemView.findViewById(R.id.tv_left_time);
        }

        public void setData(List<MessageModel> messageModelList, int position) {
            final MessageModel messageModel = messageModelList.get(position);
            Picasso.get().load(messageModel.getProfilePhotoUrl()).into(ivProfileLeft);
            tvLeftTime.setText(Utils.getDisplayTime(messageModel.getTime()));
            tvLeftContent.setText(messageModel.getContent());
            tvLeftUsername.setText(messageModel.getUsername());

            if(ivProfileLeft.getDrawable() == null) {
                ivProfileLeft.setImageResource(R.drawable.avatar);

            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.getStringClipboard(context,messageModel.getTime(),  messageModel.getContent());
                    Toast.makeText(context, "Message: \"" + messageModel.getContent() + "\" \n is copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }

}
