package com.example.ckb.article;

import com.example.ckb.user.SiteUser;
import com.example.ckb.user.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "article_create";
        }
        SiteUser siteUser = this.userService.getUser((principal.getName()));
        this.articleService.create(articleForm.getSubject(), articleForm.getContent(),siteUser);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        Article article = articleService.getarticle(id);
        model.addAttribute("article",article);
        return "article_detail";
    }
@PreAuthorize("isAuthenticated()")//로그인이 외었을때
    @GetMapping("/modify/{id}")
    public String articleModify(ArticleForm articleForm, @PathVariable("id") Integer id, Principal principal){
    Article article = this.articleService.getarticle(id);
    if(!article.getAuthor().getUsername().equals(principal.getName())){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
    }
     articleForm.setSubject(article.getSubject());
    articleForm.setContent(article.getContent());
    return "article_create";

}
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String articleModify(@Valid ArticleForm articleForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "article_create";
        }
        Article article = this.articleService.getarticle(id);
        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.articleService.modify(article, articleForm.getSubject(), articleForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }


}
