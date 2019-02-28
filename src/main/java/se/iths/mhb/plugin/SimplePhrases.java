// Help by
// File path code lines, from "Calculator" plugin by Maksym Gorbunov
// Looping through arraylist, suggested at
//      https://www.baeldung.com/find-list-element-java
// helped with this file's writing.

package se.iths.mhb.plugin;

import se.iths.mhb.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimplePhrases implements HttpService {
    private final String baseHtmlPage;

    public SimplePhrases() {
        this.baseHtmlPage = getResourceFileAsString("default_index.html");
    }

    @Address("/simplephrases")
    @RequestMethod(Http.Method.GET)
    public HttpResponse getMethod(HttpRequest httpRequest) {
        int id = 1;
        try {
            id = httpRequest.getParameters().stream().
                    filter(p -> p.getKey().toLowerCase().equals("showid"))
                    .findFirst()
                    .map(p -> Math.max(1, Integer.parseInt(p.getValue())))
                    .orElse(1);

        } catch (NumberFormatException e) {
            System.err.println("Not a number");
        }

        String htmlPage = createHtmlFile(id);
        return HttpResponse.newBuilder()
                .statusCode(200)
                .setHeader("Content-type", "text/html")
                .body(htmlPage.getBytes())
                .build();
    }


    private String createHtmlFile(int phraseId) {
        List<PhraseObject> listOfPhrases = new DatabaseHandler().retrievePhrases();
        int size = listOfPhrases.size();
        PhraseObject phraseObject1 = listOfPhrases.stream()
                .filter(p -> p.getId() == phraseId)
                .findFirst().orElse(listOfPhrases.get(0));

        String updateTwoToIndexString = StaticToDynamic.replaceLine(baseHtmlPage,
                "<!--insert phrase-->",
                phraseObject1.getPhrase());
        String updateThreeToIndexString = StaticToDynamic.replaceLine(updateTwoToIndexString,
                "<!--insert phraseReference-->",
                phraseObject1.getReference());
        String updateFourToIndexString = StaticToDynamic.replaceLine(updateThreeToIndexString,
                "<!-- insertLinkToNextPhrase -->  ",
                "/simplephrases" +
                        "?showid=" + Math.min(phraseId + 1, size));
        String updateFiveToIndexString = StaticToDynamic.replaceLine(updateFourToIndexString,
                "<!-- insertLinkToPreviousPhrase -->",
                "/simplephrases" +
                        "?showid=" + Math.min(size, Math.max(1, phraseId - 1)));
        return updateFiveToIndexString;
    }

    @IncludeFiles
    public Map<String, byte[]> getFiles() {
        List<String> fileNames = List.of("styles.css", "background1.jpg");
        Map<String, byte[]> files = new HashMap<>();

        for (String fileName : fileNames) {
            try {
                byte[] bytes = getClass().getResourceAsStream(fileName).readAllBytes();
                files.put("/simplephrases/" + fileName, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    private String getResourceFileAsString(String fileName) {
        try (InputStream is = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String toString() {
        return "SimplePhrases";
    }
}
