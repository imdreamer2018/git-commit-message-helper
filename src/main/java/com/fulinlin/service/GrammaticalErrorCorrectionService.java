package com.fulinlin.service;

import com.fulinlin.model.OtherSetting;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.intellij.openapi.components.ServiceManager;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class GrammaticalErrorCorrectionService {

    WebClient webClient;

    public GrammaticalErrorCorrectionService() {
        GitCommitMessageHelperSettings settings = ServiceManager.getService(GitCommitMessageHelperSettings.class);
        String url = settings.getDateSettings().getOtherSettings().stream()
                .filter(otherSetting -> otherSetting.getKey().equals("grammatical-correct-api"))
                .findFirst()
                .map(OtherSetting::getValue)
                .orElse("Please input your Grammatical correct api in configuration");
        webClient = WebClient.builder()
                             .baseUrl(url)
                             .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                             .build();
    }

    public Mono<String> correctSentence(String inputSentence) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("text", inputSentence);
        return this.webClient.post()
                .body(Mono.just(requestBody), HashMap.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
