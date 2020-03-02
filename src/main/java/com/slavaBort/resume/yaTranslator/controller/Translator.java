package com.slavaBort.resume.yaTranslator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slavaBort.resume.customRequest.CustomRequest;
import com.slavaBort.resume.yaTranslator.model.TranslatorModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Vyacheslav Alekseevich
 * 21/02/2020
 */

@Controller
@RequestMapping({"/", "/translator-ya", "/translator-ya/"})
public class Translator extends CustomRequest {

    @Value("${api.key.yandex}")
    private String yaApiKey;

    @Value("${get.lang.dict.url}")
    private String urlLangDict;

    @Value("${get.output.text.url}")
    private String urlTranslator;

    // lang array
    private Map<String, String> dictionaryWithLanguages = new HashMap<>();

    // map with request param
    private Map<String, String> paramMap = new HashMap<>();

    public Translator() {
    }

//    {
//        dictionaryWithLanguages.put("ru", "Русский");
//        dictionaryWithLanguages.put("ru1", "Русский1");
//        dictionaryWithLanguages.put("ru2", "Русский2");
//        dictionaryWithLanguages.put("ru3", "Русский3");
//    }

//    @GetMapping("/")
    @GetMapping
    public String langDictionary(TranslatorModel translatorModel, Model model) {

        // checking the language dictionary
        if (dictionaryWithLanguages.isEmpty()) {
            paramMap.put("key", yaApiKey);
            paramMap.put("ui", "ru");

            JSONObject jsonObject = customGetRequest(urlLangDict, paramMap).getJSONObject("langs");
            try {
                ObjectMapper mapper = new ObjectMapper();
                dictionaryWithLanguages = mapper.readValue(jsonObject.toString(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // clear dict with request param
            paramMap.clear();
        }

        model.addAttribute("translateForm", translatorModel);
        model.addAttribute("dictionaryWithLanguages", dictionaryWithLanguages);

        return "ya/index";
    }

//    @PostMapping("/")
    @PostMapping
    public String translate(@ModelAttribute TranslatorModel translatorModel, Model model) {

        String translateText = translatorModel.getInputText();
        // check input text
        if (!translateText.isEmpty()) {
            String langFrom = translatorModel.getSelectedLanguageTranslateFrom();
            String langTo = translatorModel.getSelectedLanguageTranslateTo();
            paramMap.put("key", yaApiKey);
            paramMap.put("text", translateText);
            if (!langFrom.equals("") && !langFrom.isEmpty()) {
                paramMap.put("lang", langFrom + "-" + langTo);
            } else {
                paramMap.put("lang", langTo);
            }
            paramMap.put("lang", langTo);
            JSONObject outputJson = customGetRequest(urlTranslator, paramMap);
            try {
                JSONArray result = outputJson.getJSONArray("text");
                translatorModel.setTranslateText(result.get(0).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            paramMap.clear();
        } else {
            System.out.println("error");
        }
        model.addAttribute("translateForm", translatorModel);
        model.addAttribute("dictionaryWithLanguages", dictionaryWithLanguages);
        return "ya/index";
    }

}
