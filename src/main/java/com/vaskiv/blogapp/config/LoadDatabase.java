package com.vaskiv.blogapp.config;

import com.vaskiv.blogapp.model.Article;
import com.vaskiv.blogapp.model.Color;
import com.vaskiv.blogapp.model.User;
import com.vaskiv.blogapp.repository.ArticleRepository;
import com.vaskiv.blogapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ArticleRepository articleRepository) {
        return args -> {
            User user1 = new User("User1", 22);
            Article article1 = new Article("Article on DNA", Color.BLACK);
            Article article2 = new Article("Article on microbiology", Color.BLUE);
            user1.setArticles(Arrays.asList(article1, article2));

            User user2 = new User("User2", 19);
            Article article3 = new Article("Space science", Color.RED);
            Article article4 = new Article("How to build rocket in  home", Color.GREEN);
            user2.setArticles(Arrays.asList(article3, article4));

            log.info("Preloading " + userRepository.save(user1));
            log.info("Preloading " + userRepository.save(user2));
        };
    }
}
