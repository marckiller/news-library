package com.newspundit.newslibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NewsDto {

    private Long id;

    private String address;

    private String site;

    private String author;

    private String title;

    private LocalDateTime published_at;
}
