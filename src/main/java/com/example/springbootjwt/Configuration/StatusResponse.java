package com.example.springbootjwt.Configuration;

public class StatusResponse {
    private int statusCode;
    private String statusMsg;
    private Object data;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

}
