package com.tando.flashchatnewfirebase;

/**
 * Created by tan089 on 8/17/2017.
 */

public class InstantMessage {
    private String message;
    private String author;
    //Construct for strings
    public InstantMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public InstantMessage() {
    }
    //Get the strings above
    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}

