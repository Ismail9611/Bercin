package com.direb.controller;


import com.direb.domain.Article;
import com.direb.domain.User;
import com.direb.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/articles")
public class ArticleController {


    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String root(Model model){
        showAllArticles(model);
        return "articles";
    }

    @PostMapping("/add")
    public String addArticle(@AuthenticationPrincipal User user,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body){
        Article article = new Article(title, body, new Date(), user);
        articleRepository.save(article);
        return "redirect:/articles";
    }


    @GetMapping("/edit/{id}")
    public String editArticle(){
        return "article_edit";
    }

    private void showAllArticles(Model model){
        Iterable<Article> allArticles = articleRepository.findAll();
        model.addAttribute("allArticles", allArticles);
    }





}
