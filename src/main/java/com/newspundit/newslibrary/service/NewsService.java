package com.newspundit.newslibrary.service;

import com.newspundit.newslibrary.dto.NewsDto;
import com.newspundit.newslibrary.mapper.NewsMapper;
import com.newspundit.newslibrary.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
//import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.newspundit.newslibrary.model.News;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public News getNewsById(Long id) {
        return newsRepository.getReferenceById(id);
                //.orElseThrow(() -> new NewsNotFoundException("News not found with ID: " + id));
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
            BeanUtils.copyProperties(existingNews, newsDto);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update news", e);
        }

        return newsRepository.save(existingNews);
    }

    public News updateNewsNotNull(Long id, NewsDto newsDto) {
        //it doesn't need all fields in JSON file (not provided => no change)

        News existingNews = newsRepository.getReferenceById(id);

        try {
            java.util.Map<String, String> propertyMap = BeanUtils.describe(newsDto);
            for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                String propertyName = entry.getKey();
                String propertyValue = entry.getValue();
                if (propertyValue != null) {
                    BeanUtils.setProperty(existingNews, propertyName, propertyValue);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace(); // Handle or throw an exception as needed
        }
        return newsRepository.save(existingNews);
    }
}


