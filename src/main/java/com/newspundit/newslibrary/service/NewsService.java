package com.newspundit.newslibrary.service;

import com.newspundit.newslibrary.dto.NewsDto;
import com.newspundit.newslibrary.mapper.NewsMapper;
import com.newspundit.newslibrary.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
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

        News existingNews = newsRepository.getReferenceById(id);

        existingNews.setPublished_at(newsDto.getPublished_at());
        existingNews.setSite(newsDto.getSite());
        existingNews.setTitle(newsDto.getTitle());
        existingNews.setAuthor(newsDto.getAuthor());
        existingNews.setAddress(newsDto.getAddress());
        existingNews.setId(newsDto.getId());

        return newsRepository.save(existingNews);
    }


}
