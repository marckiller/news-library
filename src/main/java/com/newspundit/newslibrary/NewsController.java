package com.newspundit.newslibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("/sites")
    public List<String> getSites() {
        return newsRepository.getSites();
    }
    @GetMapping("/news/{id}")
    public News getById(@PathVariable("id") int id){
        return newsRepository.getById(id);
    }

    @PostMapping("/news")
    public int ass(@RequestBody List<News> news){
        return newsRepository.save(news);
    }

    @PatchMapping("/news/{id}")
    public int update(@PathVariable("id") int id, @RequestBody News updatedNews){
        News news = newsRepository.getById((id));
        if (news != null){
            if (updatedNews.getAddress() != null) news.setAddress(updatedNews.getAddress());
            if (updatedNews.getSite() != null) news.setSite(updatedNews.getSite());
            if (updatedNews.getAuthor() != null) news.setAuthor(updatedNews.getAuthor());
            if (updatedNews.getTitle() != null) news.setTitle(updatedNews.getTitle());

            newsRepository.update(news);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/news/{id}")
    public int delete(@PathVariable("id") int id){
        return newsRepository.delete(id);
    }
}
