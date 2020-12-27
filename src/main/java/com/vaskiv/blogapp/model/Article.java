package com.vaskiv.blogapp.model;

import javax.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Color color;

    public Article() {
    }

    public Article(Article toCopy) {
        this.text = toCopy.text;
        this.color = toCopy.color;
    }

    public Article(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
