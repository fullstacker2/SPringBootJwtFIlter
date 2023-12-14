package com.example.springbootjwt.ExceptionHandler;

public class MovieException extends Exception{
    private static final long serialVersionUID = 1L;
    private  String errorMsg;

    public MovieException() {
    }
    public MovieException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
