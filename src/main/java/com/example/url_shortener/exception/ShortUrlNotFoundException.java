package com.example.url_shortener.exception;

public class ShortUrlNotFoundException extends Exception {
    public ShortUrlNotFoundException(String message) {
        super(message);
    }
}
