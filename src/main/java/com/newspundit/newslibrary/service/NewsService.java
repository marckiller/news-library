package com.newspundit.newslibrary.service;

import com.newspundit.newslibrary.dto.NewsDto;
import com.newspundit.newslibrary.mapper.NewsMapper;
import com.newspundit.newslibrary.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.newspundit.newslibrary.model.News;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public News getNewsById(Long id) {
        return newsRepository.getReferenceById(id);
                //.orElseThrow(() -> new NewsNotFoundException("User not found with ID: " + id));
    }

    public News addNews(NewsDto newsDto) {

        News news = NewsMapper.toEntity(newsDto);

        return newsRepository.save(news);
    }

    public News updateNews(Long id, NewsDto newsDto) {
        //it needs all fields in JSON file (not provided => null in database)

        News existingNews = newsRepository.getReferenceById(id);
        //throw exception if no id

        try {
            BeanUtils.copyProperties(newsDto, existingNews);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update news", e);
        }

        return newsRepository.save(existingNews);
    }

    public News updateNewsNotNull(Long id, NewsDto newsDto) {
        //it doesn't need all fields in JSON file (not provided => no change)

        //to be implemented

        return 0;

    }




}
