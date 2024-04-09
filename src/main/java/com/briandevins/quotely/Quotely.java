package com.briandevins.quotely;

import java.util.Set;

public class Quotely {
    private static final Set<String> VALID_LANGUAGES = Set.of("english", "en", "russian", "ru");

    private final ForismaticService forismatic;

    public Quotely(ForismaticService forismatic) {
        this.forismatic = forismatic;
    }

    String getQuoteAndAttribution(String lang) {
        String language = lang.toLowerCase();
        if (!VALID_LANGUAGES.contains(language)) {
            throw new IllegalArgumentException("Invalid language argument, expecting 'English' or 'Russian'");
        }

        return forismatic.getQuote(language.substring(0, 2));
    }
}
