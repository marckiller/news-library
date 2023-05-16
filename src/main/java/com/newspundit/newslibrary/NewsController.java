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
}
