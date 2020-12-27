package com.vaskiv.blogapp.repository;

import com.vaskiv.blogapp.model.Color;
import com.vaskiv.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getByAgeGreaterThanEqual(Integer minAge);

    @Query(value = "select * from user join article on user.id = article.user_id where article.color = ?1", nativeQuery = true)
    List<User> getUsersWithArticleColor(Integer color);

    @Query(value = "select name from user join article on user.id = article.user_id group by user.name having COUNT(article.id) >= ?1", nativeQuery = true)
    List<String> getUserNamesWithMinArticleCount(Integer minArticleCount);

}
