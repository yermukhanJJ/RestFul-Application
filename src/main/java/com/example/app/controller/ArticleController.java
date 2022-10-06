package com.example.app.controller;

import com.example.app.entity.Article;
import com.example.app.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> getArticlesLikeTitle(@RequestParam(required = false) String word) {
        return articleService.getArticles(word);
    }

    @GetMapping("/articles/{id}")
    public Article getArticles(@PathVariable("id") Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PutMapping("articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable(value = "id") Long id,
                                                 @RequestBody Article article) {

        return ResponseEntity.ok(articleService.upArticle(id, article));
    }

    @DeleteMapping("articles/{id}")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable(value = "id") Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
