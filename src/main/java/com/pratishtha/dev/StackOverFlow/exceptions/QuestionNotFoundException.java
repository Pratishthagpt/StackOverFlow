package com.pratishtha.dev.StackOverFlow.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
