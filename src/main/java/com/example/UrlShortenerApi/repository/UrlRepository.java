package com.example.UrlShortenerApi.repository;

import com.example.UrlShortenerApi.domain.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlModel, Long> {
    public Optional<UrlModel> findByShortUrl(String shortUrl);
}
