package com.slavaBort.resume.youtube.model;

/**
 * Vyacheslav Alekseevich
 * 28/02/2020
 */

public class YoutubeModel {

    private String videoURL;

    private long viewCount;

    private long likeCount;

    private long dislikeCount;

    private int commentCount;



    private String name;
    private String message;

    private int counter;
    private int randNumber;



    public YoutubeModel() {
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getRandNumber() {
        return randNumber;
    }

    public void setRandNumber(int randNumber) {
        this.randNumber = randNumber;
    }
}
