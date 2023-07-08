package com.newspundit.newslibrary.service;

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

}
