package com.newspundit.newslibrary.controller;


import com.newspundit.newslibrary.dto.NewsDto;
import com.newspundit.newslibrary.mapper.NewsMapper;
import com.newspundit.newslibrary.model.News;
import com.newspundit.newslibrary.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public NewsDto getNewsById(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        return NewsMapper.toDto(news);

    }

    @PostMapping("")
    public ResponseEntity<News> addNews(@RequestBody NewsDto newsDto) {
        News newNews = newsService.addNews(newsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newNews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        News updatedNews = newsService.updateNews(id, newsDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedNews);
    }



}
