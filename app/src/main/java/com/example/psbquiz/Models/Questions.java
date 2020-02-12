package com.example.psbquiz.Models;

public class Questions {
    String question, correctAnswer, correctAnswerDesc, answerA, answerB, answerC, answerD;

    public Questions(String question, String correctAnswer, String correctAnswerDesc, String answerA, String answerB, String answerC, String answerD) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.correctAnswerDesc = correctAnswerDesc;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public Questions() {

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswerDesc() {
        return correctAnswerDesc;
    }

    public void setCorrectAnswerDesc(String correctAnswerDesc) {
        this.correctAnswerDesc = correctAnswerDesc;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }
}
