package com.direb.repository;

import com.direb.domain.Article;
import com.direb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ArticleRepository extends CrudRepository<Article, Long>, JpaRepository<Article,Long> {
    List<Article> findAllByAuthor(User user);
}
