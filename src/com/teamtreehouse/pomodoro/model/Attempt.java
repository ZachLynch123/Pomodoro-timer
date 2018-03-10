package com.teamtreehouse.pomodoro.model;

public class Attempt {
    private String mMessage;
    private int mRemaningSeconds;
    private AttemptKind mKind;


    public Attempt(String message, AttemptKind kind) {
        mMessage = message;
        mKind = kind;
        mRemaningSeconds = kind.getTotalSeconds();
    }

    public String getmMessage() {
        return mMessage;
    }

    public int getmRemaningSeconds() {
        return mRemaningSeconds;
    }

    public AttemptKind getmKind() {
        return mKind;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public void tick() {
        mRemaningSeconds--;
    }
}
