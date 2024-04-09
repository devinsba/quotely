package com.briandevins.quotely;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class ForismaticService {
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .followRedirects(Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();

    private final String baseUrl;

    ForismaticService() {
        baseUrl = System.getenv().getOrDefault("API_BASE_URL", "http://api.forismatic.com/api/1.0/");
    }

    /**
     * Communicate with the Forismatic API to retrieve a quote and it's author
     *
     * @param lang Two letter lowercase language code, either "en" or "ru"
     * @return The quote with the author name following it in parentheses
     * @throws RuntimeException Unchecked RuntimeException instance that wraps the cause
     */
    String getQuote(String lang) {
        HttpRequest request = null;
        try {
            // Using the text response type since the "user" for this data is a text based console
            request = HttpRequest.newBuilder(new URI(baseUrl + "?method=getQuote&format=text&lang=" + lang))
                    .POST(BodyPublishers.noBody())
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (response.statusCode() != 200) {
            throw new RuntimeException(new IOException("Request to forismatic was not successful"));
        }
        return response.body();
    }
}
