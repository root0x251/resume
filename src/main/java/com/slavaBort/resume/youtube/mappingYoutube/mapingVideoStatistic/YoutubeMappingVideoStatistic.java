package com.slavaBort.resume.youtube.mappingYoutube.mapingVideoStatistic;

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
