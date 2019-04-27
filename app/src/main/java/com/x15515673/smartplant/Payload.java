package com.x15515673.smartplant;

import java.util.Date;

/**
 * Class that sends information to Firebase from our sensors
 */
public class Payload
{
    private String state;
    private int rateLimit;
    private String message;
    private long date;


    public Payload(String state, int rateLimit, String message, long date)
    {
        this.state = state;
        this.rateLimit = rateLimit;
        this.message = message;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(int rateLimit) {
        this.rateLimit = rateLimit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}