package com.example.UrlShortenerApi.controller;

import com.example.UrlShortenerApi.domain.UrlModel;
import com.example.UrlShortenerApi.domain.dto.UrlDTO;
import com.example.UrlShortenerApi.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/url")
public class UrlController {
    @Autowired
    UrlService urlService;

    @PostMapping()
    public ResponseEntity<UrlDTO> generateShortUrl(@PathParam("url") String originalUrl) {
        UrlModel urlModel = urlService.GenerateShortUrl(originalUrl);
        String domainUrl = "http://localhost:8080/url/";
        String shortUrl = domainUrl + urlModel.getShortUrl();

        return ResponseEntity.status(HttpStatus.CREATED).body(new UrlDTO(
                shortUrl,
                urlModel.getOriginalUrl()
        ));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> redirectUrl(@PathVariable("shortUrl") String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        if(originalUrl == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL not found");
        }
        response.sendRedirect(originalUrl);
        return ResponseEntity.status(HttpStatus.OK).body("URL found.");
    }
}
