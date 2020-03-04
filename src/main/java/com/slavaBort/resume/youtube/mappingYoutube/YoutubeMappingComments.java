package com.slavaBort.resume.youtube.mappingYoutube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Vyacheslav Alekseevich
 * 28/02/2020
 */

public class YoutubeMappingComments {

    @SerializedName("nextPageToken")
    private String nextPageToken;

    @SerializedName("pageInfo")
    private PageInfo pageInfo;

    public static class PageInfo {

        @SerializedName("totalResults")
        private int totalResults;

        @SerializedName("resultsPerPage")
        private int resultsPerPage;

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(int resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }
    }

    @SerializedName("items")
    private List<Items> itemsList;

    public static class Items {
        @SerializedName("id")
        private String id;

        @SerializedName("snippet")
        private SnippetOne snippetOne;

        public static class SnippetOne {

            @SerializedName("topLevelComment")
            private TopLevelComment topLevelComment;

            @SerializedName("totalReplyCount")
            private int totalReplyCount;

            public static class TopLevelComment {

                @SerializedName("snippet")
                private SnippetTwo snippetTwo;

                public static class SnippetTwo {

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

                public SnippetTwo getSnippetTwo() {
                    return snippetTwo;
                }

                public void setSnippetTwo(SnippetTwo snippetTwo) {
                    this.snippetTwo = snippetTwo;
                }

            }

            public TopLevelComment getTopLevelComment() {
                return topLevelComment;
            }

            public void setTopLevelComment(TopLevelComment topLevelComment) {
                this.topLevelComment = topLevelComment;
            }

            public int getTotalReplyCount() {
                return totalReplyCount;
            }

            public void setTotalReplyCount(int totalReplyCount) {
                this.totalReplyCount = totalReplyCount;
            }
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public SnippetOne getSnippetOne() {
            return snippetOne;
        }

        public void setSnippetOne(SnippetOne snippetOne) {
            this.snippetOne = snippetOne;
        }
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }
}
