package com.fulinlin.service;

import com.fulinlin.model.OtherSetting;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.intellij.openapi.components.ServiceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GrammaticalErrorCorrectionService {

    HttpURLConnection con;

    public GrammaticalErrorCorrectionService() {
//        GitCommitMessageHelperSettings settings = ServiceManager.getService(GitCommitMessageHelperSettings.class);
//        String api = settings.getDateSettings().getOtherSettings().stream()
//                .filter(otherSetting -> otherSetting.getKey().equals("grammatical-correct-api"))
//                .findFirst()
//                .map(OtherSetting::getValue)
//                .orElse("Please input your Grammatical correct api in configuration");
        try {
            URL url = new URL ("http://127.0.0.1:21046/api/texts");
            con = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String correctSentence(String inputSentence) {
        String requestBody = "{\"text\":\"" + inputSentence + "\"}";
        try {
            this.con.setRequestMethod("POST");
            this.con.setRequestProperty("Content-Type", "application/json; utf-8");
            this.con.setRequestProperty("Accept", "application/json");
            this.con.setDoOutput(true);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try(OutputStream os = this.con.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(this.con.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
