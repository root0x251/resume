package com.slavaBort.resume.youtube.mappingYoutube;

import com.google.gson.annotations.SerializedName;

/**
 * Vyacheslav Alekseevich
 * 25/02/2020
 */

public class Snippet {

    @SerializedName("topLevelComment")
    private TopLevelComment topLevelCommentList;

    public TopLevelComment getTopLevelCommentList() {
        return topLevelCommentList;
    }

}
