package com.direb.repository;

import com.direb.domain.Article;
import com.direb.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CommentRepository extends CrudRepository<Comment, Long>, JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle(Article article);
}
