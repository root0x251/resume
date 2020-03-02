package com.slavaBort.resume.youtube.controller;

import com.google.gson.Gson;
import com.slavaBort.resume.customRequest.CustomRequest;
import com.slavaBort.resume.youtube.mappingYoutube.mapingVideoStatistic.YoutubeMappingComments;
import com.slavaBort.resume.youtube.mappingYoutube.mapingVideoStatistic.YoutubeMappingVideoStatistic;
import com.slavaBort.resume.youtube.model.YoutubeModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Vyacheslav Alekseevich
 * 21/02/2020Î
 */

@Controller
@RequestMapping("/")
public class Youtube extends CustomRequest {

    @Value("${api.key.youtube}")
    private String youtubeApiKey;

    @Value("${get.video.statistic.url}")
    private String urlVideoStatistic;

    @Value("${get.thread.comments.url}")
    private String urlThreadComments;

    @Value("${get.nested.comments.url}")
    private String urlNestedComments;

    private Map<String, String> requestMap = new HashMap<>();

    private String videoID;
    private int randNumber;


    @GetMapping("/")
    public String youtubeGetRandComment(YoutubeModel youtubeModel, Model model) {


        model.addAttribute("youtubeInputForm", youtubeModel);
        return "youtube/index";
    }

    @PostMapping("/")
    public String informationAboutVideo(@ModelAttribute YoutubeModel youtubeModel, Model model) {
        String url = youtubeModel.getVideoURL();
        if (!url.equals("")) {
            videoID = url.substring(url.indexOf("=") + 1);
            if (!videoID.contains("&")) {
                // ok
                // get video statistic
                requestMap.put("key", youtubeApiKey);
                requestMap.put("part", "statistics");
                requestMap.put("id", videoID);
                String jsonString = customGetRequest(urlVideoStatistic, requestMap).toString();
                YoutubeMappingVideoStatistic videoStatistic = new Gson().fromJson(jsonString, YoutubeMappingVideoStatistic.class);

                youtubeModel.setViewCount(videoStatistic.getItemsList().get(0).getStatistics().getViewCount());
                youtubeModel.setCommentCount(videoStatistic.getItemsList().get(0).getStatistics().getCommentCount());
                youtubeModel.setLikeCount(videoStatistic.getItemsList().get(0).getStatistics().getLikeCount());
                youtubeModel.setDislikeCount(videoStatistic.getItemsList().get(0).getStatistics().getDislikeCount());

                randNumber = youtubeModel.getCommentCount();

                requestMap.clear();

                searchForAComment();

            } else {
                //error
                System.out.println("ID error");
            }
        } else {
            // error
            System.out.println("URL is null");
        }

        model.addAttribute("youtubeInputForm", youtubeModel);

        return "youtube/index";
    }

    private void searchForAComment() {
        long n = new Random().nextInt(randNumber) + 1;
        int result = 0;

        System.out.println(n);

        requestMap.put("key", youtubeApiKey);
        requestMap.put("part", "snippet");
        requestMap.put("order", "relevance");
        requestMap.put("videoId", videoID);
        requestMap.put("nextPageToken", "");

        String jsonString = customGetRequest(urlThreadComments, requestMap).toString();

        YoutubeMappingComments youtubeMappingComments = new Gson().fromJson(jsonString, YoutubeMappingComments.class);

        for (YoutubeMappingComments.Items items : youtubeMappingComments.getItemsList()) {
            System.out.println("id  " + items.getId());

            System.out.println("top lvl comment " + items.getSnippetOne().getTopLevelComment());

            System.out.println("name " + items.getSnippetOne().getTopLevelComment().getSnippetTwo().getAuthorDisplayName());
            System.out.println("text " + items.getSnippetOne().getTopLevelComment().getSnippetTwo().getTextOriginal());

            System.out.println("============");
            System.out.println("============");
            System.out.println("============");
            System.out.println("============");
        }


    }

//    @GetMapping("/")
//    public String getCountComments() {
//
//        YoutubeMappingVideoInfo youtubeMappingVideoInfo;
//        Gson gson = new Gson();
//
//        String url = "https://www.googleapis.com/youtube/v3/videos";
//        Map<String, String> requestMap = new HashMap<>();
//        requestMap.put("key", "AIzaSyBs-WHv4h3uIuUDwkY5dW59hrk54QBjCrM");
//        requestMap.put("id", "9bZkp7q19f0");
//        requestMap.put("part", "statistics");
//
//        String jsonString = customGetRequest(url, requestMap).toString();
//        youtubeMappingVideoInfo = gson.fromJson(jsonString, YoutubeMappingVideoInfo.class);
//
//
//        System.out.println(youtubeMappingVideoInfo.getItemsList().get(0).getStatistics().getCommentCount());
//
//
//        return "youtube/index";
//    }
//
//    @GetMapping("/w")
//    public String getRandComment() {
//
//
//        YoutubeMapper youtubeMapper = new YoutubeMapper();
//        Gson gson = new Gson();
//
//        String url = "https://www.googleapis.com/youtube/v3/commentThreads";
//        Map<String, String> requestMap = new HashMap<>();
//        requestMap.put("key", "AIzaSyBs-WHv4h3uIuUDwkY5dW59hrk54QBjCrM");
//        requestMap.put("videoId", "dJP7lu9gXjc");
//        requestMap.put("part", "snippet");
//        requestMap.put("maxResults", "100");
//        requestMap.put("pageToken", "");
//
//
//        do {
//            String jsonString = customGetRequest(url, requestMap).toString();
//            youtubeMapper = gson.fromJson(jsonString, YoutubeMapper.class);
//            requestMap.replace("pageToken", youtubeMapper.getNextPageToken());
//            countComment += youtubeMapper.getPageInfo().getTotalResults();
//            System.out.println(countComment);
//
//
//        } while (youtubeMapper.getNextPageToken() != null);


//        String jsonString = customGetRequest(url, requestMap).toString();
//        YoutubeMapper youtubeMapper = new Gson().fromJson(jsonString, YoutubeMapper.class);
//        String oldPageToken = youtubeMapper.getNextPageToken();
//        do {
//            countComment += youtubeMapper.getPageInfo().getTotalResults();
//            requestMap.replace("pageToken", oldPageToken, youtubeMapper.getNextPageToken());
//            oldPageToken = youtubeMapper.getNextPageToken();
//            jsonString = customGetRequest(url, requestMap).toString();
//            youtubeMapper = new Gson().fromJson(jsonString, YoutubeMapper.class);
//            System.out.println(youtubeMapper.getItemsList().size());
//        } while (!oldPageToken.equals(""));
//        return "youtube";
//    }

}