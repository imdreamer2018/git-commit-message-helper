package com.fulinlin.service;

import com.fulinlin.model.OtherSetting;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class GoogleTranslateService {

    Translate translate;

    public GoogleTranslateService() {
        GitCommitMessageHelperSettings settings = new GitCommitMessageHelperSettings();
        settings.getDateSettings();
        String apiKey = settings.getDateSettings().getOtherSettings().stream()
                                .filter(otherSetting -> otherSetting.getKey().equals("google-translate-api-key"))
                                .findFirst()
                                .map(OtherSetting::getValue)
                                .orElse("Please input your Google Translate Api Key in configuration");
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
