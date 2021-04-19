package com.fulinlin.serviceTests;

import com.fulinlin.service.GoogleTranslateService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GoogleTranslateServiceTest {

    GoogleTranslateService googleTranslateService = new GoogleTranslateService();

    @Test
    public void should_return_detect_language_response() {
        String detectedLanguage = googleTranslateService.detectLanguage("Hola Mundo");
        assertEquals("es", detectedLanguage);
    }

    @Test
    public void should_return_translated_text_response() {
        String translatedText = googleTranslateService.translate("你好");
        assertFalse(translatedText.isEmpty());
    }
}
