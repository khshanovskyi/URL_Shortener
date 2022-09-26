package com.example.url_shortener.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.url_shortener.entity.ShortUrl;
import com.example.url_shortener.exception.ShortUrlNotFoundException;
import com.example.url_shortener.service.ShortUrlService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("short/urls")
@RequiredArgsConstructor
public class UrlController {

    private static final String BASE_URL = "http://localhost:8081/short/urls";

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ResponseEntity<ShortUrl> generateShortUrl(@RequestBody ShortUrl shortUrl) {
        ShortUrl url = shortUrlService.generate(shortUrl);
        return ResponseEntity
          .created(generateUri(url))
          .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String id) {
        URI url = null;
        try {
            url = shortUrlService.getOriginalUrl(id);
        } catch (ShortUrlNotFoundException e) {
            url = URI.create(BASE_URL);
        }
        return ResponseEntity
          .status(HttpStatus.PERMANENT_REDIRECT)
          .location(url)
          .build();
    }

    private URI generateUri(ShortUrl shortUrl) {
        return URI.create(String.format("%s/%s", BASE_URL, shortUrl.getId()));
    }
}
