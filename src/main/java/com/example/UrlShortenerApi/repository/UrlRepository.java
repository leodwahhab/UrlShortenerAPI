package com.example.UrlShortenerApi.repository;

import com.example.UrlShortenerApi.domain.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlModel, Long> {
}
