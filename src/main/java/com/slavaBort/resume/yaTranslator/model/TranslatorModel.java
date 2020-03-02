package com.slavaBort.resume.yaTranslator.model;

/**
 * Vyacheslav Alekseevich
 * 21/02/2020
 */

public class TranslatorModel {

    private String inputText;
    private String translateText;
    private String selectedLanguageTranslateFrom;
    private String selectedLanguageTranslateTo;

    public TranslatorModel() {
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getTranslateText() {
        return translateText;
    }

    public void setTranslateText(String translateText) {
        this.translateText = translateText;
    }

    public String getSelectedLanguageTranslateFrom() {
        return selectedLanguageTranslateFrom;
    }

    public void setSelectedLanguageTranslateFrom(String selectedLanguageTranslateFrom) {
        this.selectedLanguageTranslateFrom = selectedLanguageTranslateFrom;
    }

    public String getSelectedLanguageTranslateTo() {
        return selectedLanguageTranslateTo;
    }

    public void setSelectedLanguageTranslateTo(String selectedLanguageTranslateTo) {
        this.selectedLanguageTranslateTo = selectedLanguageTranslateTo;
    }

}

