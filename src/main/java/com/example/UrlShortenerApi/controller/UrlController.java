package com.example.UrlShortenerApi.controller;

import com.example.UrlShortenerApi.domain.UrlModel;
import com.example.UrlShortenerApi.domain.dto.UrlDTO;
import com.example.UrlShortenerApi.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {
    @Autowired
    UrlService urlService;

    @PostMapping()
    public ResponseEntity<UrlDTO> generateShortUrl(@RequestParam String originalUrl) {
        UrlModel urlModel = urlService.GenerateShortUrl(originalUrl);
        String domainUrl = "http://localhost:8080/";
        String shortUrl = domainUrl + urlModel.getShortUrl();

        return ResponseEntity.status(HttpStatus.CREATED).body(new UrlDTO(
                shortUrl,
                urlModel.getOriginalUrl(),
                urlModel.getCreationDate(),
                urlModel.getLastClickDate()
        ));
    }
}
