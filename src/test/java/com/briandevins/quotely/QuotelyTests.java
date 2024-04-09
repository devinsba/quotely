package com.briandevins.quotely;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuotelyTests {

    @ParameterizedTest
    @ValueSource(strings = {"eng", "rus", "englishhh", "zz"})
    void testInvalidLanguageThrowsError(String input) {
        Quotely quotely = new Quotely(new ForismaticService());

        assertThrows(IllegalArgumentException.class, () -> quotely.getQuoteAndAttribution(input));
    }

    @ParameterizedTest
    @CsvSource({
            "English,en",
            "english,en",
            "en,en",
            "Russian,ru",
            "russian,ru",
            "ru,ru"
    })
    void testLanguageIsPassedAs2LettersLowercase(String input, String expected) {
        final String[] capturedLanguage = new String[1];
        Quotely quotely = new Quotely(new ForismaticService() {
            @Override
            String getQuote(String lang) {
                capturedLanguage[0] = lang;
                return "output";
            }
        });
        String output = quotely.getQuoteAndAttribution(input);

        assertEquals(expected, capturedLanguage[0]);
        assertEquals("output", output);
    }
}
