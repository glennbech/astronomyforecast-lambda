package com.glennbech.astronomyforecast;

import com.google.gson.Gson;

import java.util.Map;

public class LambdaResponse<T> {

    private String statusCode;
    private Map<String, String> headers;
    private String body;

    public LambdaResponse(String statusCode, Map<String, String> headers, T content) {
        this.statusCode = statusCode;
        this.body = new Gson().toJson(content);
        this.headers = headers;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
