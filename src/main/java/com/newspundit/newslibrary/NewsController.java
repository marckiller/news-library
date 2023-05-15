package com.newspundit.newslibrary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @GetMapping("/test")
    public int test(){
        return 1;
    }
}
