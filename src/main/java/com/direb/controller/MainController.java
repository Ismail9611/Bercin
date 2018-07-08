package com.direb.controller;


import com.direb.domain.Article;
import com.direb.domain.Comment;
import com.direb.domain.User;
import com.direb.repository.ArticleRepository;
import com.direb.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;


@Controller
public class MainController {

    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @GetMapping({"/", "/index"})
    public String main(Model model){
        showAllArticles(model);
        return "index";
    }

    @GetMapping("/article_more/{id}")
    public String showMoreArticle(@PathVariable(name = "id") Article article, Model model){
        model.addAttribute("article", article);
        showAllComments(article, model);
        return "article_more";
    }

    @PostMapping("/add_comment")
    public String addComment(@AuthenticationPrincipal User user,
                             @RequestParam(name = "comment_add_text") String text,
                             @RequestParam(name = "articleId") Article article){
        Comment comment = new Comment(user, text, new Date(System.currentTimeMillis()), article);
        commentRepository.save(comment);
        return "redirect:/article_more/" + article.getId();
    }


    private void showAllArticles(Model model){
        model.addAttribute("allArticles", articleRepository.findAll());
    }

    private void showAllComments(Article article, Model model){
        model.addAttribute("allComments", commentRepository.findAllByArticle(article));
    }


}
