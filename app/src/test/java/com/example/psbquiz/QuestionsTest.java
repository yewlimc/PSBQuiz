package com.example.psbquiz;

import com.example.psbquiz.Models.Questions;

import org.junit.*;
import static org.junit.Assert.*;

public class QuestionsTest {

    private Questions questions;
    private String question = "question";
    private String correctAnswer = "correctAnswer";
    private String correctAnswerDesc = "correctAnswerDesc";
    private String answerA = "answer";
    private String answerB = "answer";
    private String answerC = "answer";
    private String answerD = "answer";

    @Before
    public void setUp() throws Exception {
        questions = new Questions();
    }

    @After
    public void tearDown() throws Exception {
        questions = null;
    }

    @Test
    public void testQuestionConstructor() {
        assertNotNull("Could not create basic questions object. ", questions);
        Questions questions = new Questions (question, correctAnswer, correctAnswerDesc, answerA, answerB, answerC, answerD);
        assertNotNull("Could not create complex questions object. ", questions);

        assertEquals("Question is not set as expected. ", question, questions.getQuestion());
    }

    @Test
    public void getSetQuestion() {
        questions.setQuestion(question);
        assertEquals("Question is not set as expected. ", question, questions.getQuestion());
    }

    @Test
    public void getSetCorrectAnswer() {
        questions.setCorrectAnswer(correctAnswer);
        assertEquals("correctAnswer is not set as expected. ", correctAnswer, questions.getCorrectAnswer());
    }

    @Test
    public void getSetCorrectAnswerDesc() {
        questions.setCorrectAnswerDesc(correctAnswerDesc);
        assertEquals("correctAnswerDesc is not set as expected. ", correctAnswerDesc, questions.getCorrectAnswerDesc());
    }

    @Test
    public void getSetAnswerA() {
        questions.setAnswerA(answerA);
        assertEquals("AnswerA is not set as expected. ", answerA, questions.getAnswerA());
    }

    @Test
    public void getSetAnswerB() {
        questions.setAnswerB(answerB);
        assertEquals("AnswerB is not set as expected. ", answerB, questions.getAnswerB());
    }

    @Test
    public void getSetAnswerC() {
        questions.setAnswerC(answerC);
        assertEquals("AnswerC is not set as expected. ", answerC, questions.getAnswerC());
    }

    @Test
    public void getSetAnswerD() {
        questions.setAnswerD(answerD);
        assertEquals("AnswerD is not set as expected. ", answerD, questions.getAnswerD());
    }
}

