package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;


public class Home {
    private final AudioClip mApplause;
    @FXML
    private VBox container;
    @FXML
    private Label title;
    @FXML
    private TextArea message;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeLine;

    public Home(){
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
        mApplause = new AudioClip(getClass().getResource("/sounds/FF7.mp3").toExternalForm());
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
        reset();
        mCurrentAttempt = new Attempt("", kind);
        addAttemtpStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getmRemaningSeconds());
        mTimeLine = new Timeline();
        mTimeLine.setCycleCount(kind.getTotalSeconds());
        mTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getmRemaningSeconds());
        }));
        mTimeLine.setOnFinished(e -> {
            saveCurrentAttempt();
            mApplause.play();
            prepareAttempt(mCurrentAttempt.getmKind() == AttemptKind.FOCUS ?
                            AttemptKind.BREAK : AttemptKind.FOCUS);
        pauseTimer();});
    }

    private void saveCurrentAttempt() {
        mCurrentAttempt.setmMessage(message.getText());
        mCurrentAttempt.save();
    }

    private void reset() {
        clearAttemptStyles();
        if (mTimeLine !=null && mTimeLine.getStatus() == Animation.Status.RUNNING){
            mTimeLine.stop();

        }
    }


    public void playTimer(){
        if (mTimeLine != null) {
            container.getStyleClass().add("playing");
            mTimeLine.play();
        }
    }
    public void pauseTimer(){
        container.getStyleClass().remove("playing");
        mTimeLine.pause();
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

    public void handleRestart(ActionEvent actionEvent) {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }

    public void handlePlay(ActionEvent actionEvent) {
        playTimer();
    }

    public void handlePause(ActionEvent actionEvent) {
        pauseTimer();
    }
}
