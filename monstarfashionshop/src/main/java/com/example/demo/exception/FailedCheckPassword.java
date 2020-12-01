package com.example.demo.exception;

public class FailedCheckPassword extends Exception {
    public FailedCheckPassword (String s) {
        super(s);
    }
}
