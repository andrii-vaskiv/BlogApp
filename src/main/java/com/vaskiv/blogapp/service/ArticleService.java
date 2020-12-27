package com.vaskiv.blogapp.service;

import com.vaskiv.blogapp.dto.ArticleRequestDto;
import com.vaskiv.blogapp.dto.ArticleResponseDto;
import com.vaskiv.blogapp.exception.ArticleNotFoundException;
import com.vaskiv.blogapp.exception.UserNotFoundException;
import com.vaskiv.blogapp.model.Article;
import com.vaskiv.blogapp.model.User;
import com.vaskiv.blogapp.repository.ArticleRepository;
import com.vaskiv.blogapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRrepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRrepository, UserRepository userRepository) {
        this.articleRrepository = articleRrepository;
        this.userRepository = userRepository;
    }

    public Iterable<ArticleResponseDto> getAllArticles() {
        return articleRrepository.findAll().stream()
                .map(article -> new ArticleResponseDto(article))
                .collect(Collectors.toList());
    }

    public Iterable<ArticleResponseDto> getUserArticles(Long userId) {
        User user = userRepository.getOne(userId);
        if(user == null) {
            throw new UserNotFoundException("no user with id " + userId);
        }
        return user.getArticles().stream()
                .map(article -> new ArticleResponseDto(article))
                .collect(Collectors.toList());
    }

    public ArticleResponseDto createArticle(Long userId, ArticleRequestDto articleDto) {
        User user = userRepository.getOne(userId);
        if(user == null) {
            throw new UserNotFoundException("no user with id " + userId);
        }
        Article newArticle = articleDto.toArticle();
        Article saveArticle = articleRrepository.save(newArticle);
        user.getArticles().add(saveArticle);
        userRepository.save(user);
        return new ArticleResponseDto(saveArticle);
    }

    public ArticleResponseDto updateArticle(Long articleId, ArticleRequestDto updatedArticle) {
        Article toUpdateArticle = articleRrepository.getOne(articleId);
        if(toUpdateArticle == null) {
            throw new ArticleNotFoundException("no article with id " + articleId);
        }
        //TODO: change update logic
        toUpdateArticle.setText(updatedArticle.getText());
        toUpdateArticle.setColor(updatedArticle.getColor());
        Article savedArticle = articleRrepository.save(toUpdateArticle);
        return new ArticleResponseDto(savedArticle);
    }

    public void deleteArticleById(Long userId, Long articleId) {
        articleRrepository.deleteById(articleId);
    }

}
