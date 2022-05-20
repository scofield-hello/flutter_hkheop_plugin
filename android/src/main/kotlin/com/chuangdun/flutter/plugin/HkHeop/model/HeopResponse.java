package com.chuangdun.flutter.plugin.HkHeop.model;

import java.util.List;

public class HeopResponse
{
    private String requestURL;
    private int statusCode;
    private String statusString;
    private String subStatusCode;
    private int errorCode;
    private String errorMsg;
    private float similarity;
    private List<Targets> targets;

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getSubStatusCode() {
        return subStatusCode;
    }

    public void setSubStatusCode(String subStatusCode) {
        this.subStatusCode = subStatusCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }

    public List<Targets> getTargets() {
        return targets;
    }

    public void setTargets(List<Targets> targets) {
        this.targets = targets;
    }

    @Override
    public String toString() {
        return "HeopResponse{" +
                "requestURL='" + requestURL + '\'' +
                ", statusCode=" + statusCode +
                ", statusString='" + statusString + '\'' +
                ", subStatusCode='" + subStatusCode + '\'' +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", similarity=" + similarity +
                ", targets=" + targets +
                '}';
    }
}

