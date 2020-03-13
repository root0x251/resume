package com.slavaBort.resume.youtube.mappingYoutube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Vyacheslav Alekseevich
 * 28/02/2020
 */

public class YoutubeMappingVideoStatistic {

    @SerializedName("items")
    private List<Items> itemsList;

    public class Items {

        @SerializedName("snippet")
        private Snippet snippet;

        public class Snippet {

            @SerializedName("thumbnails")
            private Thumbnails thumbnails;

            public class Thumbnails {

                @SerializedName("maxres")
                private Maxres maxres;

                public class Maxres {

                    @SerializedName("url")
                    private String url;

                    public String getUrl() {
                        return url;
                    }

                }

                public Maxres getStandard() {
                    return maxres;
                }
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }
        }

        public Snippet getSnippet() {
            return snippet;
        }

        @SerializedName("statistics")
        private Statistics statistics;

        public class Statistics {

            @SerializedName("viewCount")
            private long viewCount;

            @SerializedName("likeCount")
            private long likeCount;

            @SerializedName("dislikeCount")
            private long dislikeCount;

            @SerializedName("commentCount")
            private int commentCount;

            public long getViewCount() {
                return viewCount;
            }

            public long getLikeCount() {
                return likeCount;
            }

            public long getDislikeCount() {
                return dislikeCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

        }

        public Statistics getStatistics() {
            return statistics;
        }
    }

    public List<Items> getItemsList() {
        return itemsList;
    }
}
