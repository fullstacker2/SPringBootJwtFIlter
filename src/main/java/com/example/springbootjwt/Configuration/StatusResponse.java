package com.example.springbootjwt.Configuration;

public class StatusResponse {
    private int statusCode;
    private String statusMsg;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

}
