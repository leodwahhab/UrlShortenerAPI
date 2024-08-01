package com.example.UrlShortenerApi.service;

import com.example.UrlShortenerApi.domain.UrlModel;
import com.example.UrlShortenerApi.domain.dto.UrlDTO;

public interface UrlService {
    UrlModel GenerateShortUrl(String originalUrl);

    void GetShortUrl();
}
