package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Getmoviename {
    public Getmoviename() {
        String url = "https://www.forumcinemas.lv/filmas/sobrid-kinoteatri";
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String html = response.toString();

            Document doc = Jsoup.parse(html);
            Elements movies = doc.select(".event-list-item-inner");
            for (Element movie : movies) {
                System.out.print(movie.select(".event-name").not(".hidden").text() + " - ");
                System.out.println(movie.select(".event-original-name").text());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
