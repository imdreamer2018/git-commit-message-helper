package com.fulinlin.service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translation;

public class GoogleTranslateService {

    String apiKey = "your google translate app key";
    Translate translate;

    public GoogleTranslateService() {
        this.translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();
    }

    public String detectLanguage(String mysteriousText) {
        Detection detection = this.translate.detect(mysteriousText);
        return detection.getLanguage();
    }

    public String translate(String mysteriousText) {
        Translation translation = this.translate.translate(
                mysteriousText,
                Translate.TranslateOption.sourceLanguage(detectLanguage(mysteriousText)),
                Translate.TranslateOption.targetLanguage("en"));
        return translation.getTranslatedText();
    }
}
