package com.newspundit.scraper;

import com.newspundit.scraper.articles.Article_onetpl;
import com.newspundit.scraper.articles.Article_wppl;
import com.newspundit.scraper.dto.Article;

public class Scraper {

    public static void main(String[] args) {
        try {
            System.out.println("\n Sample wp.pl article scrapped: ");

            Article article = Article_wppl.get_article_data("https://wiadomosci.wp.pl/prosili-zelenskiego-zeby-ochlonal-awantura-na-szczycie-nato-w-wilnie-6919185345338208a");
            article.show();

            System.out.println("\n Sample onet.pl article scrapped: ");

            Article article2 = Article_onetpl.get_article_data("https://www.onet.pl/informacje/onetwiadomosci/rosyjski-general-wagnerowcy-sa-na-bialorusi-by-zajac-przesmyk-suwalski/j3bvv0g,79cfc278");
            article2.show();

            System.out.println("\n Sample wp.pl article scrapped: ");

            Article article3 = Article_wppl.get_article_data("https://wiadomosci.wp.pl/wagnerowcy-w-drodze-do-moskwy-ogromny-konwoj-na-autostradzie-relacja-na-zywo-6919978762103744a");
            article3.show();

            System.out.println("\n Sample onet.pl article scrapped: ");

            Article article4 = Article_onetpl.get_article_data("https://www.onet.pl/informacje/onetwiadomosci/co-zmieni-tzw-ustawa-kamilka-ekspertka-wskazuje-najwazniejsze-elementy/n9g9ffr,79cfc278");
            article4.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
