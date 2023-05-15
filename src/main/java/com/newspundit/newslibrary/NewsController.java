package com.newspundit.newslibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
