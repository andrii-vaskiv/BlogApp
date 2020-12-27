package com.vaskiv.blogapp.exception;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(String msg) {
        super(msg);
    }
}
