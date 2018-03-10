package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.awt.*;

public class Home {
    @FXML
    private VBox container;
    @FXML
    private Label title;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;

    public Home(){
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
    }

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    public void setTimerText(String timerText) {
        mTimerText.set(timerText);
    }

    public void setTimerText(int reaminingSeconds){
        int minutes = reaminingSeconds / 60;
        int seconds = reaminingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes,seconds));
    }

    private void prepareAttempt(AttemptKind kind){
        clearAttemptStyles();
        mCurrentAttempt = new Attempt("", kind);
        addAttemtpStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getmRemaningSeconds());
    }

    private void addAttemtpStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles(){
        for (AttemptKind kind : AttemptKind.values()){
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }

    }

    public void DEBUG(ActionEvent actionEvent) {
        System.out.println("Hey Zach!");
    }
}
