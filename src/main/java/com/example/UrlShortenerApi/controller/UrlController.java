package com.example.UrlShortenerApi.controller;

import com.example.UrlShortenerApi.domain.UrlModel;
import com.example.UrlShortenerApi.domain.dto.UrlDTO;
import com.example.UrlShortenerApi.service.QrCodeService;
import com.example.UrlShortenerApi.service.UrlService;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/url")
@CrossOrigin(origins = "*")
public class UrlController {
    @Autowired
    UrlService urlService;

    @Autowired
    QrCodeService qrCodeService;

    @PostMapping()
    public ResponseEntity<UrlDTO> generateShortUrl(@RequestParam("url") String url) throws IOException, WriterException {
        UrlModel urlModel = urlService.generateShortUrl(url);
        String domainUrl = "http://localhost:8080/url/";
        String shortUrl = domainUrl + urlModel.getShortUrl();
        String qrCode = qrCodeService.generateQrCode(shortUrl);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UrlDTO(
                shortUrl,
                urlModel.getOriginalUrl(),
                qrCode
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
