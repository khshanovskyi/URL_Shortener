package com.example.url_shortener.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "shortened_urls")
public class ShortUrl {
    @Id
    private String id;
    @Column(name = "original_url", nullable = false)
    @JsonProperty("url")
    private String originalUrl;
    private String title;
    @Column(insertable = false)
    private LocalDateTime createdAt;
}

