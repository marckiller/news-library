package com.newspundit.scraper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Article {
    /*
    This class is sort of twinned with the one from packeage newslibrary.model.News
I just wanted the two package to work independently of each other. Perhaps for less code I should use that package.
Anyway, here I include all the fields from that class, but additionally extend it with what I can gather
     */

    private String address;

    private String site;

    private String author = null;

    private String title = null;

    private LocalDateTime publishedAt = null;

    private String tags = null;

    public void show(){
        System.out.println("Url:         " + address);
        System.out.println("Author:      " + author);
        System.out.println("Site:        " + site);
        System.out.println("Title:       " + title);
        System.out.println("Date:        " + publishedAt);
        System.out.println("Tags:        " + tags);
    };

}
