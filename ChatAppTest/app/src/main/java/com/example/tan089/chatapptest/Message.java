package com.example.tan089.chatapptest;

import java.util.Date;

/**
 * Created by tan089 on 8/25/2017.
 */

public class Message {
    private String message,user_name;
    private long messageTime;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
    public Message() {
    }
    public Message(String message, String user_name) {
        this.message = message;
        this.user_name = user_name;
        messageTime = new Date().getTime();
    }
}
