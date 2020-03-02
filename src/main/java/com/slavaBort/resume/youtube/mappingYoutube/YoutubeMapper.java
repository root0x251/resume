package com.slavaBort.resume.youtube.mappingYoutube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author: vaceslavalekseevic
 * @date :   21/02/2020
 */


public class YoutubeMapper {

    @SerializedName("nextPageToken")
    private String nextPageToken;

    @SerializedName("pageInfo")
    private PageInfo pageInfo;

    @SerializedName("items")
    private List<Items> itemsList;

//    public YoutubeMapper(String nextPageToken, PageInfo pageInfo, List<Items> itemsList) {
//        this.nextPageToken = nextPageToken;
//        this.pageInfo = pageInfo;
//        this.itemsList = itemsList;
//    }

    // return new page token
    public String getNextPageToken() {
        return nextPageToken;
    }

    //
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

}


