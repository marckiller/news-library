package com.newspundit.newslibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getSites() {
        return jdbcTemplate.queryForList("SELECT DISTINCT site FROM news", String.class);
    }

    public News getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM news WHERE id = ?", BeanPropertyRowMapper.newInstance(News.class), id);
    }

    public int save(List<News> newsList) {
        newsList.forEach(news -> jdbcTemplate.update("INSERT INTO news(address,site,author,title) VALUES (?,?,?,?)",
                news.getAddress(), news.getSite(), news.getAuthor(), news.getTitle()));
        return 1;
    }
}
