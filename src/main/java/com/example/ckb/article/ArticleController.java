package com.example.ckb.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public String index(){
        return "article_list";
    }

@GetMapping("/list")
public String list(Model model){
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList",articleList);
        return "article_list";
}

    @GetMapping("/create")
    public String create(ArticleForm articleForm){
        return "article_create";
    }

    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "article_create";
        }
        this.articleService.create(articleForm.getSubject(), articleForm.getContent());
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        Article article = articleService.getarticle(id);
        model.addAttribute("article",article);
        return "article_detail";
    }




}
