package com.newspundit.scraper.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class UrlScraper {
    //just to have all Articles scraped te same way

    public static Document scrapeUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

}
