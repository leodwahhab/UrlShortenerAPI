package com.example.UrlShortenerApi.service.impl;

import com.example.UrlShortenerApi.repository.UrlRepository;
import com.example.UrlShortenerApi.service.UrlService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    UrlRepository urlRepository;

    //TODO generate a new short Url
    //the new URL must length 5 to 10 char
    //validate if the generated URL doesn't exist
    @Override
    public String generateShortUrl(String originalUrl) {
        return "";
    }

    @Override
    public void GetShortUrl() {

    }
}
