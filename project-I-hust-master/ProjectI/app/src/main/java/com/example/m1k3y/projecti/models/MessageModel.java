package com.example.m1k3y.projecti.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MessageModel implements Parcelable, Serializable {
    public  String time;
    public String username;
    public String content;
    public String profilePhotoUrl;

    public MessageModel(String time, String username, String content, String profilePhotoUrl) {
        this.time = time;
        this.username = username;
        this.content = content;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public MessageModel() {
    }


    protected MessageModel(Parcel in) {
        time = in.readString();
        username = in.readString();
        content = in.readString();
        profilePhotoUrl = in.readString();
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel in) {
            return new MessageModel(in);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "time='" + time + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", profilePhotoUrl='" + profilePhotoUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(time);
        parcel.writeString(username);
        parcel.writeString(content);
        parcel.writeString(profilePhotoUrl);
    }
}
