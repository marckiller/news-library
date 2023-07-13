package com.newspundit.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
    public static void main(String[] args) {
        try {
            String url = "https://wiadomosci.wp.pl/"; // Replace with the URL of the news website
            Document document = Jsoup.connect(url).get();
            //String htmlContent = document.html();
            //System.out.println(htmlContent);

            System.out.println("teaserTitle--isGlonews");

            Elements headlines_Glonews = document.select("h2.teaserTitle--isGlonews"); // Replace with the appropriate CSS selector for the headlines

            for (Element headline : headlines_Glonews) {
                System.out.println(headline.text());
            }

            System.out.println("teaserTitle--isSmall");

            Elements headlines_small = document.select("h2.teaserTitle--isSmall"); // Replace with the appropriate CSS selector for the headlines

            for (Element headline : headlines_small) {
                System.out.println(headline.text());
            }

            //String htmlContent = document.html();
            //System.out.println(htmlContent);

            Elements test_elements = document.select("div.h2PrHTUx");
            System.out.println(test_elements);

            if (test_elements != null) {
                for (Element test_element : test_elements) {
                    System.out.println(test_element.text());
                }
            } else {
                System.out.println("Title and URL not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



