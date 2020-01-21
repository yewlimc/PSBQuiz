package com.example.psbquiz;

import com.example.psbquiz.Models.AnsweredQuestions;

import org.junit.*;
import static org.junit.Assert.*;

public class AnsweredQuestionsTest {

    private AnsweredQuestions answeredQuestions;
    private String question = "question";
    private String answered = "answered";
    private String correctAnswer = "correctAnswer";
    private String correctAnswerDesc = "correctAnswerDesc";

    @Before
    public void setUp() throws Exception {
        answeredQuestions = new AnsweredQuestions();
    }

    @After
    public void tearDown() throws Exception {
        answeredQuestions = null;
    }

    @Test
    public void testAnsweredQuestionConstructor() {
        assertNotNull("Could not create basic AnsweredQuestions object. ", answeredQuestions);
        answeredQuestions = new AnsweredQuestions (question, answered, correctAnswer, correctAnswerDesc);
        assertNotNull("Could not create complex AnsweredQuestions object. ", answeredQuestions);

        assertEquals("Question is not set as expected. ", question, answeredQuestions.getQuestion());
    }

    @Test
    public void getSetQuestion() {
        answeredQuestions.setQuestion(question);
        assertEquals("Question is not set as expected. ", question, answeredQuestions.getQuestion());
    }

    @Test
    public void getSetCorrectAnswer() {
        answeredQuestions.setCorrectAnswer(correctAnswer);
        assertEquals("correctAnswer is not set as expected. ", correctAnswer, answeredQuestions.getCorrectAnswer());
    }

    @Test
    public void getSetAnswered() {
        answeredQuestions.setAnswered(answered);
        assertEquals("answered is not set as expected. ", answered, answeredQuestions.getAnswered());
    }

    @Test
    public void getSetCorrectAnswerDesc() {
        answeredQuestions.setCorrectAnswerDesc(correctAnswerDesc);
        assertEquals("correctAnswerDesc is not set as expected. ", correctAnswerDesc, answeredQuestions.getCorrectAnswerDesc());
    }

    @Test
    public void answeredQuestionsInstantiates()
    {
        answeredQuestions = new AnsweredQuestions (question, answered, correctAnswer, correctAnswerDesc);
        assertEquals(true, answeredQuestions instanceof AnsweredQuestions);
    }
}