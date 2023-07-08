package com.newspundit.newslibrary.mapper;

import com.newspundit.newslibrary.dto.NewsDto;
import com.newspundit.newslibrary.model.News;

public class NewsMapper {

    public static NewsDto toDto(News news) {

        NewsDto newsDto = new NewsDto();

        newsDto.setId(news.getId());

        newsDto.setAddress(news.getAddress());

        newsDto.setAuthor(news.getAuthor());

        newsDto.setSite(news.getSite());

        newsDto.setTitle(news.getTitle());

        return newsDto;
    }

    public static News toEntity(NewsDto newsDto) {

        News news = new News();

        news.setId(newsDto.getId());

        news.setTitle(newsDto.getTitle());

        news.setAddress(newsDto.getAddress());

        news.setAuthor(newsDto.getAuthor());

        news.setSite(newsDto.getSite());

        news.setTitle(newsDto.getTitle());
    }
}
