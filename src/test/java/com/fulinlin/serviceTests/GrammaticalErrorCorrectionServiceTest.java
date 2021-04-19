package com.fulinlin.serviceTests;

import com.fulinlin.service.GrammaticalErrorCorrectionService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrammaticalErrorCorrectionServiceTest {

    @Test
    public void should_return_corrected_sentence() {
        GrammaticalErrorCorrectionService grammaticalErrorCorrectionService = new GrammaticalErrorCorrectionService();
        assertEquals("He goes to school", grammaticalErrorCorrectionService.correctSentence("he go to the school"));
    }
}
