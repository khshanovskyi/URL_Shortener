package com.example.url_shortener.service;

import java.lang.annotation.Repeatable;
import java.net.URI;
import java.util.Objects;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.example.url_shortener.entity.ShortUrl;
import com.example.url_shortener.exception.ShortUrlNotFoundException;
import com.example.url_shortener.repository.ShortUrlRepository;
import com.example.url_shortener.util.RandomUrlIdUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;

    @Retryable
    public ShortUrl generate(ShortUrl shortUrl) {
        Objects.requireNonNull(shortUrl, "Provided [shortUrl] object cannot be null");
        shortUrl.setId(RandomUrlIdUtil.generateId());
        return shortUrlRepository.save(shortUrl);
    }

    @Cacheable("urls")
    public URI getOriginalUrl(String id) throws ShortUrlNotFoundException {
        ShortUrl shortUrl = shortUrlRepository.findById(id)
          .orElseThrow(() -> new ShortUrlNotFoundException(String.format("Url by id [%s] not found", id)));
        return URI.create(shortUrl.getOriginalUrl());
    }
}
