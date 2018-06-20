package com.direb.repository;

import com.direb.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ArticleRepository extends CrudRepository<Article, Long>, JpaRepository<Article,Long> {
}
