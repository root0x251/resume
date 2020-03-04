package com.slavaBort.resume.youtube.mappingYoutube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Vyacheslav Alekseevich
 * 04/03/2020
 */

public class YoutubeMappingNestedComments {

    @SerializedName("items")
    private List<Items> itemsList;

    @SerializedName("nextPageToken")
    private String nextPageToken;

    public static class Items {
        @SerializedName("snippet")
        private Snippet snippet;

        public static class Snippet {

            @SerializedName("authorDisplayName")
            private String authorDisplayName;

            @SerializedName("authorProfileImageUrl")
            private String authorProfileImageUrl;

            @SerializedName("authorChannelUrl")
            private String authorChannelUrl;

            @SerializedName("textOriginal")
            private String textOriginal;

            public String getAuthorDisplayName() {
                return authorDisplayName;
            }

            public void setAuthorDisplayName(String authorDisplayName) {
                this.authorDisplayName = authorDisplayName;
            }

            public String getAuthorProfileImageUrl() {
                return authorProfileImageUrl;
            }

            public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
                this.authorProfileImageUrl = authorProfileImageUrl;
            }

            public String getAuthorChannelUrl() {
                return authorChannelUrl;
            }

            public void setAuthorChannelUrl(String authorChannelUrl) {
                this.authorChannelUrl = authorChannelUrl;
            }

            public String getTextOriginal() {
                return textOriginal;
            }

            public void setTextOriginal(String textOriginal) {
                this.textOriginal = textOriginal;
            }
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

}
