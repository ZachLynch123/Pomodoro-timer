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

    private Home(){
        mTimerText = new SimpleStringProperty();
    }

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty mTimerTextProperty() {
        return mTimerText;
    }

    public void setmTimerText(String timerText) {
        mTimerText.set(timerText);
    }

    private void prepareAttempt(AttemptKind kind){
        clearAttemptStyles();
        mCurrentAttempt = new Attempt("", kind);
        addAttemtpStyle(kind);
        title.setText(kind.getDisplayName());
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
