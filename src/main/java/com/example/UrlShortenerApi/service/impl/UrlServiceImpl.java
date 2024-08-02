package com.example.UrlShortenerApi.service.impl;

import com.example.UrlShortenerApi.domain.UrlModel;
import com.example.UrlShortenerApi.repository.UrlRepository;
import com.example.UrlShortenerApi.service.UrlService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    UrlRepository urlRepository;

    //TODO generate a new short Url
    //the new URL must length 5 to 10 char
    //validate if the generated URL doesn't exist
    @Override
    public UrlModel GenerateShortUrl(String originalUrl) {
        String newShortUrl;

        do {
            newShortUrl = generateShortUrl();
        } while (urlRepository.findByShortUrl(newShortUrl) != null);

        UrlModel url = new UrlModel();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(newShortUrl);
        url.setCreationDate(LocalDateTime.now());

        return urlRepository.save(url);
    }

    private String generateShortUrl() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        String originalUrl = urlRepository.findByShortUrl(shortUrl).getOriginalUrl();
        System.out.println(originalUrl);
        return originalUrl;
    }
}
