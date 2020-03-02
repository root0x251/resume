package com.slavaBort.resume.youtube.mappingYoutube;

import com.google.gson.annotations.SerializedName;

/**
 * Vyacheslav Alekseevich
 * 25/02/2020
 */

public class PageInfo {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("resultsPerPage")
    private int resultsPerPage;

    public PageInfo(int totalResults, int resultsPerPage) {
        this.totalResults = totalResults;
        this.resultsPerPage = resultsPerPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }
}
