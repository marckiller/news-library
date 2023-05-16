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
}

