package org.example;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TelegramNotificationSender {

    private static String CHAT_ID = null;
    private static String TOKEN = null;

    public TelegramNotificationSender() {
        CHAT_ID = System.getProperty("chatId");
        TOKEN = System.getProperty("token");

        if (CHAT_ID == null || TOKEN == null) {
            throw new RuntimeException("chatId and/or token not set in system properties");
        }
    }

    public void sendMessage (String message) {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        UriBuilder builder = UriBuilder
                .fromUri("https://api.telegram.org")
                .path("/{token}/sendMessage")
                .queryParam("chat_id", CHAT_ID)
                .queryParam("text", message);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(builder.build("bot" + TOKEN))
                .timeout(Duration.ofSeconds(5))
                .build();


        HttpResponse<String> response;


            try {
                client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

    }




}
