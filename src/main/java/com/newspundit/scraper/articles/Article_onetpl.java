package com.newspundit.scraper.articles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newspundit.scraper.dto.Article;
import com.newspundit.scraper.tools.UrlScraper;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;

public class Article_onetpl {

    public static Article get_article_data(String url) throws IOException {

        Article article = new Article();

        Document document = UrlScraper.scrapeUrl(url);

        article.setAddress(url);

        article.setSite("onet.pl");

        Element element = document.selectFirst("script[type=application/ld+json]");

        String scriptContent = element.html();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(scriptContent);
            article.setTitle(jsonNode.get("headline").asText());

            JsonNode jsonNode2 = mapper.readTree(jsonNode.get("author").toString());
            String name = jsonNode2.get("name").asText();
            article.setAuthor(name);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            LocalDateTime localDateTime = LocalDateTime.parse(jsonNode.get("datePublished").asText(), formatter);
            article.setPublishedAt(localDateTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //That way of extracting tags seems to be a bit risky.
        Element element2 = document.selectFirst("script[id=InlineScript-paywallConfig]");
        String tags = extractTagsAsString(element2.toString());
        article.setTags(tags);

        return article;
    }

    private static String extractTagsAsString(String scriptContent) {
        String regex = "\"tags\":\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(scriptContent);

        StringBuilder tagsBuilder = new StringBuilder();

        if (matcher.find()) {
            String tagsContent = matcher.group(1);
            String[] tagsArray = tagsContent.split(",");
            for (String tag : tagsArray) {
                tagsBuilder.append(tag.trim().replaceAll("\"", ""));
                tagsBuilder.append(", ");
            }
            // Remove the trailing comma and space
            tagsBuilder.setLength(tagsBuilder.length() - 2);
        }

        return tagsBuilder.toString();
    }

}
