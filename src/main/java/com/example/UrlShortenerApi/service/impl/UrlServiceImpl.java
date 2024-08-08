package com.example.UrlShortenerApi.service.impl;

import com.example.UrlShortenerApi.domain.UrlModel;
import com.example.UrlShortenerApi.repository.UrlRepository;
import com.example.UrlShortenerApi.service.UrlService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    UrlRepository urlRepository;

    public UrlModel generateShortUrl(String originalUrl) {
        if(!validateUrl(originalUrl) || originalUrl == null)
            throw new IllegalArgumentException();

        String newShortUrl;

        do {
            newShortUrl = generateRandomUrl();
        } while (urlRepository.findByShortUrl(newShortUrl).isPresent());

        UrlModel url = new UrlModel();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(newShortUrl);
        url.setCreationDate(LocalDateTime.now());

        return urlRepository.save(url);
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(NoSuchElementException::new)
                .getOriginalUrl();
    }

    private boolean validateUrl(String url) {
        String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        return matcher.find();
    }

    private String generateRandomUrl() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }


}
