package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.awt.*;

public class Home {
    @FXML
    private VBox container;
    @FXML
    private Label title;

    private Attempt mCurrentAttempt;

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

}
