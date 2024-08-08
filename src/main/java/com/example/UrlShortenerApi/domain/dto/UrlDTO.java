package com.example.UrlShortenerApi.domain.dto;

public record UrlDTO(String shortUrl, String originalUrl, String qrCode) {
}
