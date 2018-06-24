package com.direb.controller;


import com.direb.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @GetMapping({"/", "/index"})
    public String main(Model model){
        showAllArticles(model);
        return "index";
    }

    private void showAllArticles(Model model){
        model.addAttribute("allArticles", articleRepository.findAll());
    }


}
