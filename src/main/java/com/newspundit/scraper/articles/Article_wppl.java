package com.newspundit.scraper.articles;

import com.newspundit.scraper.dto.Article;
import com.newspundit.scraper.tools.UrlScraper;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article_wppl {

    public static Article get_article_data(String url) throws IOException {

        Article article = new Article();

        Document document = UrlScraper.scrapeUrl(url);

        article.setAddress(url);

        article.setSite("wp.pl");

        article.setAuthor(document.selectFirst("meta[name=author]").attr("content"));

        // Convert the string to LocalDateTime using the formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        LocalDateTime localDateTime = LocalDateTime.parse(document.selectFirst("meta[property=article:published_time]").attr("content"), formatter);
        article.setPublishedAt(localDateTime);

        article.setTitle(document.selectFirst("meta[property=og:title]").attr("content"));

        article.setTags(document.selectFirst("meta[property=artice:tag]").attr("content"));

        return article;
    }

}
