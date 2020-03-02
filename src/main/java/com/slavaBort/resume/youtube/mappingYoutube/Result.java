package com.slavaBort.resume.youtube.mappingYoutube;

import java.util.Map;

/**
 * Vyacheslav Alekseevich
 * 25/02/2020
 */

public class Result {

    private int countComments;
    private Map<String, String> result;

    public int getCountComments() {
        return countComments;
    }

    public void setCountComments(int countComments) {
        this.countComments = countComments;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

}
