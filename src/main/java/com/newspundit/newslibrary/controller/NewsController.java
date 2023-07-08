package com.newspundit.newslibrary.controller;


import com.newspundit.newslibrary.dto.NewsDto;
import com.newspundit.newslibrary.mapper.NewsMapper;
import com.newspundit.newslibrary.model.News;
import com.newspundit.newslibrary.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public NewsDto getUserById(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        return NewsMapper.toDto(news);
    }

}
