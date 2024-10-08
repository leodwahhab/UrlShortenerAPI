package com.example.UrlShortenerApi.service;

import com.example.UrlShortenerApi.domain.UrlModel;

public interface UrlService {
    UrlModel generateShortUrl(String originalUrl);

    String getOriginalUrl(String shortUrl);
}
