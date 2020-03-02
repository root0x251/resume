package com.slavaBort.resume.youtube.mappingYoutube;

import com.google.gson.annotations.SerializedName;

/**
 * Vyacheslav Alekseevich
 * 25/02/2020
 */

public class Snippet2 {

    @SerializedName("authorChannelUrl")
    private String authorChannelUrl;

    @SerializedName("textOriginal")
    private String textOriginal;

    public String getAuthorChannelUrl() {
        return authorChannelUrl;
    }

    public String getTextOriginal() {
        return textOriginal;
    }

}
