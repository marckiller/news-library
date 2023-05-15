package com.newspundit.newslibrary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class News {

    private int id;
    private String address;
    private String site;
    private String publishedAt;
    private String author;
    private String title;

}
