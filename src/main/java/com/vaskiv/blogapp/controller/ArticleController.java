package com.vaskiv.blogapp.controller;

import com.vaskiv.blogapp.dto.ArticleRequestDto;
import com.vaskiv.blogapp.dto.ArticleResponseDto;
import com.vaskiv.blogapp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService service) {
        this.articleService = service;
    }

    @GetMapping("/all/articles")
    public Iterable<ArticleResponseDto> getArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{userId}/articles")
    public Iterable<ArticleResponseDto> getUserArticles(@PathVariable Long userId) {
        return articleService.getUserArticles(userId);
    }

    @PostMapping("/{userId}/articles")
    public ArticleResponseDto createUserArticle(@PathVariable Long userId, @RequestBody ArticleRequestDto articleDto) {
        return articleService.createArticle(userId, articleDto);
    }

    @PutMapping("/{userId}/articles/{articleId}")
    public ArticleResponseDto updateUserArticle(@PathVariable Long articleId, @RequestBody ArticleRequestDto articleDto) {
        return articleService.updateArticle(articleId, articleDto);
    }

    @DeleteMapping("/{userId}/articles/{articleId}")
    public void deleteUserArticle(@PathVariable Long userId, @PathVariable Long articleId) {
        articleService.deleteArticleById(userId, articleId);
    }
}
