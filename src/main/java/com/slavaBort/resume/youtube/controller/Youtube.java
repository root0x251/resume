package com.slavaBort.resume.youtube.controller;

import com.google.gson.Gson;
import com.slavaBort.resume.customRequest.CustomRequest;
import com.slavaBort.resume.youtube.mappingYoutube.YoutubeMappingComments;
import com.slavaBort.resume.youtube.mappingYoutube.YoutubeMappingNestedComments;
import com.slavaBort.resume.youtube.mappingYoutube.YoutubeMappingVideoStatistic;
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
@RequestMapping({"/youtube-rand", "/youtube-rand/"})
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
    private int counter;

    private String name;
    private String message;
    private String avatar;

    @GetMapping
    public String youtubeGetRandComment(YoutubeModel youtubeModel, Model model) {
        model.addAttribute("youtubeInputForm", youtubeModel);
        return "youtube/index";
    }

    @PostMapping
    public String informationAboutVideo(@ModelAttribute YoutubeModel youtubeModel, Model model) {
        String url = youtubeModel.getVideoURL();
        if (!url.equals("")) {
            try {
                videoID = url.substring(url.indexOf("=") + 1);
                if (!videoID.contains("&")) {
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

                    randNumber = new Random().nextInt(videoStatistic.getItemsList().get(0).getStatistics().getCommentCount());

                    requestMap.clear();

                    searchForAComment();

                    youtubeModel.setName(name);
                    youtubeModel.setMessage(message);
                    youtubeModel.setAvatar(avatar);


                } else {
                    //error
                    System.out.println("ID error");
                }
            } catch (IndexOutOfBoundsException | NullPointerException ignore) {
                System.out.println("incorrect link");
            }
        } else {
            System.out.println("URL is null");
        }

        model.addAttribute("youtubeInputForm", youtubeModel);

        System.out.println("///// endd //////");

        return "youtube/index";
    }


    private void searchForAComment() {
        YoutubeModel youtubeModel = new YoutubeModel();
        counter = 0;

        // comment
        YoutubeMappingComments youtubeMappingComments = new YoutubeMappingComments();

        // nested comments
        YoutubeMappingNestedComments youtubeMappingNestedComments = new YoutubeMappingNestedComments();

        Gson gson = new Gson();

        String jsonString;

        // temp counter
        int tempCounter;

        boolean isFind = false;

        try {
            do {
                requestMap.put("key", youtubeApiKey);
                requestMap.put("part", "snippet");
                requestMap.put("videoId", videoID);
                requestMap.put("maxResults", "100");
                requestMap.put("pageToken", youtubeMappingComments.getNextPageToken());

                jsonString = customGetRequest(urlThreadComments, requestMap).toString();

                requestMap.clear();

                youtubeMappingComments = gson.fromJson(jsonString, YoutubeMappingComments.class);

                for (YoutubeMappingComments.Items comment : youtubeMappingComments.getItemsList()) {
                    if (randNumber > counter) {
                        counter += 1;
                        tempCounter = counter;
                        tempCounter += comment.getSnippetOne().getTotalReplyCount();
                        if (randNumber > tempCounter) {
                            counter = tempCounter;
                        } else {
                            try {
                                do {
                                    if (!isFind) {
                                        requestMap.put("key", youtubeApiKey);
                                        requestMap.put("part", "snippet");
                                        requestMap.put("parentId", comment.getId());
                                        requestMap.put("maxResults", "100");

                                        if (youtubeMappingNestedComments.getNextPageToken() != null) {
                                            requestMap.put("pageToken", youtubeMappingNestedComments.getNextPageToken());
                                        }

                                        jsonString = customGetRequest(urlNestedComments, requestMap).toString();

                                        requestMap.clear();

                                        youtubeMappingNestedComments = new Gson().fromJson(jsonString, YoutubeMappingNestedComments.class);

                                        for (YoutubeMappingNestedComments.Items nestedCommens : youtubeMappingNestedComments.getItemsList()) {
                                            if (randNumber > counter) {
                                                counter += 1;
                                            } else if (randNumber == counter) {
                                                // our comment
                                                name = nestedCommens.getSnippet().getAuthorDisplayName();
                                                message = nestedCommens.getSnippet().getTextOriginal();
                                                avatar = nestedCommens.getSnippet().getAuthorProfileImageUrl();
                                                isFind = true;
                                                break;
                                            }
                                        }
                                    } else {
                                        break;
                                    }

                                } while (youtubeMappingNestedComments.getNextPageToken() != null);
                            } catch (NullPointerException e) {
                                System.out.println("error Null pointer nested comments");
                                e.printStackTrace();
                            }
                        }

                    } else if (randNumber == counter) {
                        // our comment
                        if (!isFind) {
                            name = comment.getSnippetOne().getTopLevelComment().getSnippetTwo().getAuthorDisplayName();
                            message = comment.getSnippetOne().getTopLevelComment().getSnippetTwo().getTextOriginal();
                            avatar = comment.getSnippetOne().getTopLevelComment().getSnippetTwo().getAuthorProfileImageUrl();
                            isFind = true;
                        }
                        break;
                    }
                }
            } while (youtubeMappingComments.getNextPageToken() != null);

        } catch (NullPointerException e) {
            System.out.println("error Null pointer comment");
            e.printStackTrace();
        }

        System.out.println(counter);
        System.out.println(name);
        System.out.println(message);
        System.out.println(avatar);

//        youtubeModel.setName(name);
//        youtubeModel.setMessage(message);
//        youtubeModel.setAvatar(avatar);


        System.out.println(randNumber);


    }
}
