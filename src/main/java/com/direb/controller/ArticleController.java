package com.direb.controller;


import com.direb.domain.Article;
import com.direb.domain.User;
import com.direb.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/articles")
public class ArticleController {


    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String root(@AuthenticationPrincipal User user, Model model) {
        showAllArticlesByAuthor(user, model);
        return "articles";
    }

    @PostMapping("/add")
    public String addArticle(@AuthenticationPrincipal User user,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body) {
        Article article = new Article(title, body, new Date(), user);
        articleRepository.save(article);
        return "redirect:/articles";
    }


    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable(name = "id") Article article, Model model) {
        model.addAttribute("article", article);
        return "article_edit";
    }

    @PostMapping("/save_edit")
    public String art_edit(@RequestParam(name = "articleId") Article article,
                           @RequestParam(name = "title_edt") String title,
                           @RequestParam(name = "body_edt") String body) {
        article.setTitle(title);
        article.setBody(body);
        article.setDate(new Date());
        articleRepository.save(article);
        return "redirect:/articles";
    }

    private void showAllArticlesByAuthor(User author, Model model) {
        Iterable<Article> allArticles = articleRepository.findAllByAuthor(author);
        model.addAttribute("allArticles", allArticles);
    }

}
