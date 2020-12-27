package com.vaskiv.blogapp.dto;

import com.vaskiv.blogapp.model.Article;
import com.vaskiv.blogapp.model.Color;

public class ArticleRequestDto {
    private String text;
    private Color color;

    public ArticleRequestDto(String text, Color color, Long userId) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Article toArticle() {
        return new Article(this.getText(), this.getColor());
    }
}
