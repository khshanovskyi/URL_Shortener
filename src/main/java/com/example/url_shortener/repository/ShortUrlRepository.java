package com.example.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.url_shortener.entity.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {
}
