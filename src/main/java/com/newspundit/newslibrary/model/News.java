package com.newspundit.newslibrary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String site;

    private String author;

    private String title;

    @Column(name="published_at", columnDefinition = "datetime")
    // p00h - publishedAt ; notacja camel case jest typowa dla javy
    private LocalDateTime  published_at;
}
