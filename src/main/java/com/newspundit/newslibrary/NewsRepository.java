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
        // MP - wydaje się, że distinct nic tu nie wnosi
        return jdbcTemplate.queryForList("SELECT DISTINCT site FROM news", String.class);
    }

    public News getById(int id) {
        /* MP - ten BeanPropertyRowMapper pewnie możesz stworzyć raz w klasie i może nawet jako static. Nie będziesz wtedy
        * zużywał zasobów na tworzeniu nowych obiektów za każdym razem */
        return jdbcTemplate.queryForObject("SELECT * FROM news WHERE id = ?", BeanPropertyRowMapper.newInstance(News.class), id);
    }

    public int save(List<News> newsList) {
        newsList.forEach(news -> jdbcTemplate.update("INSERT INTO news(address,site,author,title) VALUES (?,?,?,?)",
                news.getAddress(), news.getSite(), news.getAuthor(), news.getTitle()));
        return 1;
    }

    public int update(News news){
        return jdbcTemplate.update("UPDATE news SET address=?,site=?,author=?,title=? WHERE id = ?",
                news.getAddress(), news.getSite(), news.getAuthor(), news.getTitle(), news.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM news WHERE id = ?", id);
    }
}
