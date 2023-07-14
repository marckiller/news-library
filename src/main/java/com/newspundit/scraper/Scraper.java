package com.newspundit.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
    //date, author and article can be scrapped via given url
    public static void main(String[] args) {
        try {
            String url = "https://wiadomosci.wp.pl/"; // Replace with the URL of the news website

            Document document = Jsoup.connect(url).get();

            Elements elements = document.select("a.h2PrHTUx");

            for (Element element : elements) {
                String title = element.attr("title");
                System.out.println("Title: " + title);
                String href = "https://wiadomosci.wp.pl"+element.attr("href");
                System.out.println("href: " + href);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



