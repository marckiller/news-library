package com.newspundit.newslibrary.repository;

import com.newspundit.newslibrary.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    //Add custom querry methods if necesary

}
