package com.vaskiv.blogapp.repository;

import com.vaskiv.blogapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
