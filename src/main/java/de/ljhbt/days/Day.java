package de.ljhbt.days;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Day {

    default List<String> getInput(int yearNumber, int dayNumber, String userCookie) throws URISyntaxException, IOException, InterruptedException {
        CookieHandler.setDefault(new CookieManager());
        HttpCookie sessionCookie = new HttpCookie("session", userCookie);
        sessionCookie.setPath("/");
        sessionCookie.setVersion(0);
        ((CookieManager) CookieHandler.getDefault()).getCookieStore().add(new URI("https://adventofcode.com"),
                sessionCookie);
        HttpClient client = HttpClient.newBuilder()
                .cookieHandler(CookieHandler.getDefault())
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://adventofcode.com/%d/day/%d/input".formatted(yearNumber, dayNumber)))
                .GET().build();
        String response = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
        return new ArrayList<>(Arrays.asList(response.split("\n")));
    }

    String solvePart1(List<String> input);

    String solvePart2(List<String> input);
}
